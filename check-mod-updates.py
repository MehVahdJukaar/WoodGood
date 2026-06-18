#!/usr/bin/env python3
"""
check-mod-updates.py
Checks mod-registry.json for newer versions using cursemaven metadata and Modrinth API.
No API keys required.

CurseForge: fetches maven-metadata.xml from www.cursemaven.com — no auth needed.
  Auto-updates single-loader entries and entries where all loaders share the same file ID.
  For mods with different per-loader file IDs, prints a warning (can't tell which loader
  a new file targets without the CF API).
Modrinth: queries api.modrinth.com — no auth needed, loader-specific.
Maven-only entries (quark, zeta, registrate): skipped — update those manually.

Usage:
    python check-mod-updates.py              # check and update registry
    python check-mod-updates.py --dry-run    # print changes without writing
    python check-mod-updates.py --slug twigs # check a single mod by slug
"""

import json
import os
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
import xml.etree.ElementTree as ET
from datetime import date
from typing import Optional

REGISTRY_PATH = os.path.join(os.path.dirname(os.path.abspath(__file__)), "mod-registry.json")
CURSEMAVEN = "https://www.cursemaven.com/curse/maven"
MR_API = "https://api.modrinth.com/v2"
REQUEST_DELAY = 0.25  # seconds between requests


def http_get(url: str) -> Optional[str]:
    req = urllib.request.Request(url, headers={"User-Agent": "WoodGood-mod-registry-checker/1.0"})
    try:
        with urllib.request.urlopen(req, timeout=10) as resp:
            return resp.read().decode("utf-8")
    except urllib.error.HTTPError as e:
        if e.code in (404, 410, 422):
            return None
        raise
    except urllib.error.URLError as e:
        raise RuntimeError(str(e.reason)) from e


def cursemaven_latest(cf_slug: str, project_id: int) -> Optional[str]:
    """Return the latest file ID (string) from cursemaven maven-metadata.xml, or None."""
    url = f"{CURSEMAVEN}/{cf_slug}-{project_id}/maven-metadata.xml"
    text = http_get(url)
    if not text:
        return None
    try:
        root = ET.fromstring(text)
        latest = (
            root.findtext("versioning/release")
            or root.findtext("versioning/latest")
        )
        if not latest:
            versions = root.findall("versioning/versions/version")
            latest = versions[-1].text if versions else None
        return latest.strip() if latest else None
    except ET.ParseError:
        return None


def modrinth_latest(modrinth_id: str, loader: str, mc_version: str) -> Optional[str]:
    """Return the latest Modrinth version string for the given loader+MC, or None."""
    mr_loader = {"common": "neoforge", "neoforge": "neoforge", "fabric": "fabric"}.get(loader, loader)
    params = urllib.parse.urlencode({
        "loaders": json.dumps([mr_loader]),
        "game_versions": json.dumps([mc_version]),
    })
    text = http_get(f"{MR_API}/project/{modrinth_id}/version?{params}")
    if not text:
        return None
    try:
        data = json.loads(text)
        return data[0]["version_number"] if isinstance(data, list) and data else None
    except (json.JSONDecodeError, KeyError, IndexError):
        return None


def check_mod(slug: str, mod: dict, mc_version: str, dry_run: bool) -> tuple[list[str], list[str]]:
    """Returns (changes, warnings)."""
    changes, warnings = [], []
    mod_mc = mod.get("mc_version") or mc_version
    curse_id = mod.get("curse_project_id")
    modrinth_id = mod.get("modrinth_id")
    # curse_maven_slug overrides the registry slug for cursemaven URL construction
    cf_slug = mod.get("curse_maven_slug", slug)

    # --- CurseForge via cursemaven metadata ---
    if curse_id:
        time.sleep(REQUEST_DELAY)
        latest = cursemaven_latest(cf_slug, curse_id)
        if latest:
            active = {k: v for k, v in mod.get("curse_files", {}).items()
                      if not k.endswith("_backup")}
            unique_current = set(str(v) for v in active.values())
            all_same = len(unique_current) <= 1
            current_max = max((int(v) for v in unique_current), default=0)

            if int(latest) > current_max:
                if all_same or len(active) == 1:
                    # Safe to update: only one file ID in use across all loaders
                    for loader in list(active):
                        changes.append(f"  [curse/{loader}] {active[loader]} -> {latest}")
                        if not dry_run:
                            mod["curse_files"][loader] = latest
                else:
                    # Different per-loader file IDs — can't assign without CF API
                    warnings.append(
                        f"  [curse] new file {latest} found (current: {dict(active)}) "
                        f"— loader unclear, update manually"
                    )

    # --- Modrinth ---
    if modrinth_id:
        for loader, current_ver in list(mod.get("modrinth_versions", {}).items()):
            time.sleep(REQUEST_DELAY)
            latest_ver = modrinth_latest(modrinth_id, loader, mod_mc)
            if latest_ver and latest_ver != current_ver:
                changes.append(f"  [modrinth/{loader}] {current_ver} -> {latest_ver}")
                if not dry_run:
                    mod["modrinth_versions"][loader] = latest_ver

    return changes, warnings


def main():
    dry_run = "--dry-run" in sys.argv
    filter_slug = None
    if "--slug" in sys.argv:
        idx = sys.argv.index("--slug")
        if idx + 1 < len(sys.argv):
            filter_slug = sys.argv[idx + 1]

    with open(REGISTRY_PATH) as f:
        registry = json.load(f)

    mc_version = registry.get("_mc_version", "1.21.1")

    if dry_run:
        print("[DRY RUN] No changes will be written.\n")

    mods = registry["mods"]
    if filter_slug and filter_slug not in mods:
        print(f"ERROR: slug '{filter_slug}' not found in registry.")
        sys.exit(1)
    targets = {filter_slug: mods[filter_slug]} if filter_slug else mods

    all_changes: dict[str, list[str]] = {}
    all_warnings: dict[str, list[str]] = {}
    all_errors: list[str] = []

    for slug, mod in targets.items():
        if mod.get("source") == "local":
            continue
        if not mod.get("curse_project_id") and not mod.get("modrinth_id"):
            continue  # maven-only — skip

        print(f"  {slug}", end="", flush=True)
        try:
            changes, warnings = check_mod(slug, mod, mc_version, dry_run)
        except Exception as e:
            print(f" ERROR: {e}")
            all_errors.append(f"{slug}: {e}")
            continue

        if not dry_run:
            mod["last_checked"] = date.today().isoformat()

        if changes or warnings:
            label = f"[{len(changes)}u/{len(warnings)}w]" if warnings else f"[{len(changes)} update(s)]"
            print(f" {label}")
            for c in changes:
                print(c)
            for w in warnings:
                print(f"  WARN {w.strip()}")
            if changes:
                all_changes[slug] = changes
            if warnings:
                all_warnings[slug] = warnings
        else:
            print(" ok")

    if not dry_run:
        with open(REGISTRY_PATH, "w") as f:
            json.dump(registry, f, indent=2)
        print(f"\nRegistry saved.")

    print("\n--- Summary ---")
    total_c = sum(len(v) for v in all_changes.values())
    total_w = sum(len(v) for v in all_warnings.values())

    if all_changes:
        print(f"{total_c} auto-update(s) applied:")
        for slug, lines in all_changes.items():
            for line in lines:
                print(f"  {slug}:{line.strip()}")
    if all_warnings:
        print(f"\n{total_w} manual check(s) needed:")
        for slug, lines in all_warnings.items():
            for line in lines:
                print(f"  {slug}:{line.strip()}")
    if not all_changes and not all_warnings:
        print("All checked mods are up to date.")
    if all_errors:
        print(f"\n{len(all_errors)} error(s):")
        for e in all_errors:
            print(f"  ! {e}")


if __name__ == "__main__":
    main()
