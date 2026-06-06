#!/usr/bin/env python3
"""
check-mod-updates.py
Checks mod-registry.json for newer CurseForge/Modrinth versions and updates it in place.
Maven-based deps (quark, zeta, registrate) are skipped — update those manually.

Usage:
    python check-mod-updates.py              # check and update registry
    python check-mod-updates.py --dry-run    # print changes without writing
    python check-mod-updates.py --slug twigs # check a single mod by slug

Requires:
    CF_API_KEY env var for CurseForge lookups (get one at https://console.curseforge.com)
"""

import json
import os
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from datetime import date
from typing import Optional

REGISTRY_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "mod-registry.json")
CF_API = "https://api.curseforge.com/v1"
MR_API = "https://api.modrinth.com/v2"

# CurseForge modLoaderType enum
CF_LOADER_IDS = {
    "common": 6,   # common uses NeoForge-compatible (universal) files
    "fabric": 2,
    "neoforge": 6,
}

# Polite delay between API calls (seconds)
REQUEST_DELAY = 0.3


def get_cf_api_key() -> str:
    key = os.environ.get("CF_API_KEY") or os.environ.get("CURSEFORGE_API_KEY")
    if not key:
        print("ERROR: Set CF_API_KEY to your CurseForge API key.")
        print("       Get one at https://console.curseforge.com")
        sys.exit(1)
    return key


def http_get(url: str, headers: dict) -> Optional[dict]:
    req = urllib.request.Request(url, headers={**headers, "Accept": "application/json"})
    try:
        with urllib.request.urlopen(req, timeout=10) as resp:
            return json.loads(resp.read())
    except urllib.error.HTTPError as e:
        if e.code in (404, 422):
            return None
        raise
    except urllib.error.URLError as e:
        raise RuntimeError(f"Network error: {e.reason}") from e


def cf_latest_file(project_id: int, loader: str, mc_version: str, api_key: str) -> Optional[str]:
    """Return the latest CurseForge file ID (as string) for the given loader+MC version, or None."""
    loader_type = CF_LOADER_IDS.get(loader, 6)
    params = urllib.parse.urlencode({
        "gameVersion": mc_version,
        "modLoaderType": loader_type,
        "pageSize": 20,
    })
    url = f"{CF_API}/mods/{project_id}/files?{params}"
    data = http_get(url, {"x-api-key": api_key})
    if not data:
        return None
    files = data.get("data", [])
    if not files:
        return None
    # Files are sorted newest-first by default
    return str(files[0]["id"])


def mr_latest_version(modrinth_id: str, loader: str, mc_version: str) -> Optional[str]:
    """Return the latest Modrinth version string for the given loader+MC version, or None."""
    # Modrinth loader names
    mr_loader = {"common": "neoforge", "fabric": "fabric", "neoforge": "neoforge"}.get(loader, loader)
    params = urllib.parse.urlencode({
        "loaders": json.dumps([mr_loader]),
        "game_versions": json.dumps([mc_version]),
    })
    url = f"{MR_API}/project/{modrinth_id}/version?{params}"
    data = http_get(url, {})
    if not data or not isinstance(data, list) or not data:
        return None
    return data[0]["version_number"]


def check_mod(slug: str, mod: dict, mc_version: str, api_key: str, dry_run: bool) -> list[str]:
    """Check one mod for updates. Returns list of human-readable change strings."""
    mod_mc = mod.get("mc_version", mc_version)
    curse_id = mod.get("curse_project_id")
    modrinth_id = mod.get("modrinth_id")
    changes = []

    # CurseForge file checks
    if curse_id:
        for loader, current_id in list(mod.get("curse_files", {}).items()):
            if loader.endswith("_backup"):
                continue  # skip backup entries
            time.sleep(REQUEST_DELAY)
            latest_id = cf_latest_file(curse_id, loader, mod_mc, api_key)
            if latest_id and latest_id != str(current_id):
                changes.append(f"  [curse/{loader}] {current_id} -> {latest_id}")
                if not dry_run:
                    mod["curse_files"][loader] = latest_id

    # Modrinth version checks
    if modrinth_id:
        for loader, current_ver in list(mod.get("modrinth_versions", {}).items()):
            time.sleep(REQUEST_DELAY)
            latest_ver = mr_latest_version(modrinth_id, loader, mod_mc)
            if latest_ver and latest_ver != current_ver:
                changes.append(f"  [modrinth/{loader}] {current_ver} -> {latest_ver}")
                if not dry_run:
                    mod["modrinth_versions"][loader] = latest_ver

    return changes


def main():
    dry_run = "--dry-run" in sys.argv
    filter_slug = None
    for i, arg in enumerate(sys.argv[1:]):
        if arg == "--slug" and i + 2 < len(sys.argv):
            filter_slug = sys.argv[i + 2]

    with open(REGISTRY_PATH) as f:
        registry = json.load(f)

    mc_version = registry.get("_mc_version", "1.21.1")
    api_key = get_cf_api_key()

    if dry_run:
        print("[DRY RUN] No changes will be written.\n")

    all_updates: dict[str, list[str]] = {}
    all_errors: list[str] = []

    mods = registry["mods"]
    targets = {filter_slug: mods[filter_slug]} if filter_slug else mods

    for slug, mod in targets.items():
        if mod.get("source") == "local":
            continue  # local flatDir jars have no remote to check
        if not mod.get("curse_project_id") and not mod.get("modrinth_id"):
            continue  # maven-only, skip

        print(f"  {slug}", end="", flush=True)
        try:
            changes = check_mod(slug, mod, mc_version, api_key, dry_run)
        except Exception as e:
            print(f" ERROR: {e}")
            all_errors.append(f"{slug}: {e}")
            continue

        if changes:
            print(f" [{len(changes)} update(s)]")
            for c in changes:
                print(c)
            all_updates[slug] = changes
        else:
            print(" ok")

    if not dry_run:
        registry["_last_checked"] = date.today().isoformat()
        with open(REGISTRY_PATH, "w") as f:
            json.dump(registry, f, indent=2)
        print(f"\nRegistry written. _last_checked = {registry['_last_checked']}")

    print("\n--- Summary ---")
    total = sum(len(v) for v in all_updates.values())
    if all_updates:
        print(f"{total} update(s) across {len(all_updates)} mod(s):")
        for slug, changes in all_updates.items():
            for c in changes:
                print(f"  {slug}{c.strip()}")
    else:
        print("All checked mods are up to date.")
    if all_errors:
        print(f"\n{len(all_errors)} error(s):")
        for e in all_errors:
            print(f"  ! {e}")


if __name__ == "__main__":
    main()
