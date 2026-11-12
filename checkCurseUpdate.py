#!/usr/bin/env python3
import argparse
import asyncio
import json
import os
import re
from concurrent.futures import ThreadPoolExecutor
from urllib.error import HTTPError
from urllib.request import Request, urlopen

""" USAGE
• Check all NeoForge mods (do one loader at a time):
python check_updates.py --neoforge

• update specific mods across all of build.gradles (fabric, common, neoforge):
python check_updates.py --fabric --common --neoforge --slug jei rei --apply

[option for loaders]: --neoforge, --fabric, --common
[NOTE]: --apply will actually update the import in build.gradle.kts but not using "--apply" is no different from dry-run
[NOTE]: --slug can have one slug or more

[LAST NOTE]: 
If you are updating to newer minecraft version, then search --mc_version via main_async() at the bottom to modify
"""

# ANSI Text Formatting Constants
ANSI_RESET = "\033[0m"
BG_RED = "\033[41m"
BG_GREEN = "\033[42m"
BG_YELLOW = "\033[43m"
BG_BLUE = "\033[44m"
BG_CYAN = "\033[46m"
TEXT_BLACK = "\033[30m"
TEXT_WHITE = "\033[37m"

CURSE_MAVEN_PATTERN = re.compile(r"curse\.maven:([a-zA-Z0-9_-]+)-(\d+):(\d+)")

def fetch_mod_data(project_id: str):
    """Synchronous network worker executing inside the ThreadPoolExecutor."""
    url = f"https://api.cfwidget.com/{project_id}"
    req = Request(url, headers={"User-Agent": "Python-AsyncCurseUpdateChecker/3.0"})
    with urlopen(req, timeout=10) as response:
        return json.loads(response.read().decode("utf-8"))
    return None


async def process_single_mod(mod: dict, loader: str, mc_version: str, executor: ThreadPoolExecutor):
    try:
        # Offload blocking network I/O to thread pool
        loop = asyncio.get_running_loop()
        data = await loop.run_in_executor(executor, fetch_mod_data, mod["projectId"])
        files = data.get("files", [])

        latest_file = None
        for current_file in files:
            versions = [str(v) for v in current_file.get("versions", [])]
            has_mc_version = mc_version in versions

            # Dynamic loader check logic
            has_loader = any(v.lower() == loader.lower() for v in versions) or (
                    loader == "Common" and any(v.lower() in ["fabric", "neoforge"] for v in versions)
            )
            if has_mc_version and has_loader:
                latest_file = current_file
                break

        if not latest_file:
            print(f"{BG_RED}{TEXT_BLACK} [UNAVAILABLE] {ANSI_RESET}  {mod['slug']} ({loader}): No files for {mc_version} listed in API.\n")
            return None

        latest_id = int(latest_file["id"])
        latest_name = latest_file.get("name", "unknown").replace(".jar", "").strip()

        previous_file = next((f for f in files if int(f["id"]) == mod["oldFileId"]), None)
        previous_name = (previous_file.get("name", "unknown").replace(".jar", "").strip() if previous_file else "unknown")

        if latest_id > mod["oldFileId"]:
            print(f"{BG_YELLOW}{TEXT_WHITE} [UPDATE] {ANSI_RESET}       {previous_name} ({mod['oldFileId']}) -> {latest_name} ({latest_id})\n")
            return mod, latest_id
        else:
            print(f"{BG_GREEN}{TEXT_WHITE} [LATEST] {ANSI_RESET}       {previous_name} ({mod['oldFileId']}) -> up to date\n")
            return None

    except HTTPError as e:
        print(f"{BG_RED}{TEXT_BLACK} [ERROR] {ANSI_RESET}        {mod['slug']} <{mod['projectId']}>: HTTP {e.code}")
    except Exception as e:
        print(f"{BG_RED}{TEXT_BLACK} [ERROR] {ANSI_RESET}        {mod['slug']} <{mod['projectId']}>: {str(e)}")

    print() # newline
    return None

async def check_imports_in_loader(loader: str, file_path: str, mc_version: str, slugs_filter: set, apply_updates: bool, executor: ThreadPoolExecutor):
    # Strip down absolute path to project relative path
    relative_path = os.path.relpath(file_path, os.getcwd())

    if not os.path.exists(file_path):
        print(f"{BG_YELLOW}{TEXT_BLACK} [WARN] {ANSI_RESET} Target build file not found for {loader}: {relative_path}\n")
        return

    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    matches = CURSE_MAVEN_PATTERN.findall(content)
    if not matches:
        return

    dependencies = []
    seen = set()
    for slug, project_id, old_file_id in matches:
        if slugs_filter and slug.lower() not in slugs_filter:
            continue

        unique_key = f"{project_id}:{old_file_id}"
        if unique_key not in seen:
            seen.add(unique_key)
            dependencies.append({
                "slug": slug,
                "projectId": project_id,
                "oldFileId": int(old_file_id)
            })

    if slugs_filter:
        found_slugs = {d["slug"].lower() for d in dependencies}
        missing = slugs_filter - found_slugs
        if missing:
            print(f"{BG_YELLOW}{TEXT_BLACK} [{loader} WARN] {ANSI_RESET} Slugs not found in {os.path.basename(file_path)}: {', '.join(missing)}")

    if not dependencies:
        print(f"[{loader}] No matching curse.maven dependencies found to check.\n")
        return

    print(f"=== Checking {len(dependencies)} {loader} dependencies ({relative_path}) for Minecraft {mc_version} ===")

    # Create concurrent task pool for all mod lookups
    tasks = [process_single_mod(mod, loader, mc_version, executor) for mod in dependencies]
    results = await asyncio.gather(*tasks)

    pending_updates = [res for res in results if res is not None]

    if pending_updates and apply_updates:
        print() # newline
        for mod, latest_id in pending_updates:
            old_import = f"curse.maven:{mod['slug']}-{mod['projectId']}:{mod['oldFileId']}"
            new_import = f"curse.maven:{mod['slug']}-{mod['projectId']}:{latest_id}"

            content = content.replace(old_import, new_import)
            print(f"{BG_CYAN}{TEXT_WHITE} [APPLIED] {ANSI_RESET} Replaced '{old_import}' -> '{new_import}' in {relative_path}")

        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"\n[SAVED]        Updated {relative_path} ({len(pending_updates)} mod(s) updated).")
    print()

async def main_async():
    parser = argparse.ArgumentParser(description="Checks and updates CurseMaven dependencies.")
    parser.add_argument("--fabric", action="store_true", help="Check Fabric dependencies.")
    parser.add_argument("--common", action="store_true", help="Check Common dependencies.")
    parser.add_argument("--neoforge", action="store_true", help="Check NeoForge dependencies.")
    parser.add_argument("--slug", nargs="*", default=[], help="Specific mod slug(s) to check.")
    parser.add_argument("--apply", action="store_true", help="Write updated file IDs directly to files.")
    parser.add_argument("--max-workers", type=int, default=16, help="Maximum concurrent connection pools.")

    # MODIFY the MINECRAFT VERSION if needed
    parser.add_argument("--mc-version", default="1.21.1", help="Minecraft target version (default: 1.21.1).")

    args = parser.parse_args()

    targets = []
    if args.fabric:
        targets.append(("Fabric", "fabric/build.gradle.kts"))
    if args.common:
        targets.append(("Neoforge", "common/build.gradle.kts"))
    if args.neoforge:
        targets.append(("NeoForge", "neoforge/build.gradle.kts"))

    if not targets:
        print("Please choose at least one loader flag: --fabric, --common, or --neoforge")
        return

    slugs_filter = {s.lower() for s in args.slug} if args.slug else set()

    with ThreadPoolExecutor(max_workers=args.max_workers) as executor:
        for loader, file_path in targets:
            await check_imports_in_loader(
                loader=loader,
                file_path=file_path,
                mc_version=args.mc_version,
                slugs_filter=slugs_filter,
                apply_updates=args.apply,
                executor=executor
            )

def main():
    asyncio.run(main_async())

if __name__ == "__main__":
    main()