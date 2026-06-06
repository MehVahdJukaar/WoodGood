#!/usr/bin/env python3
"""
apply-mod-updates.py
Updates CurseForge file IDs and Modrinth version strings in build.gradle.kts files
based on the current state of mod-registry.json.

Rules:
  - Only modifies ACTIVE (uncommented) lines — commented lines are left untouched.
  - Preserves existing -neoforge / -fabric version suffixes on Modrinth entries.
    e.g. if build file has 27-neoforge and registry says 29, result is 29-neoforge.
  - CurseForge: matches curse.maven:{any-slug}-{project_id}:{file_id}.
  - Modrinth: matches maven.modrinth:{project_id_or_slug}:{version}.

Usage:
    python apply-mod-updates.py              # apply updates to build files
    python apply-mod-updates.py --dry-run    # print changes without writing
"""

import json
import os
import re
import sys
from pathlib import Path

REGISTRY_PATH = Path(__file__).parent / "mod-registry.json"
BUILD_FILES = {
    "common":   Path(__file__).parent / "common"   / "build.gradle.kts",
    "fabric":   Path(__file__).parent / "fabric"   / "build.gradle.kts",
    "neoforge": Path(__file__).parent / "neoforge" / "build.gradle.kts",
}

LOADER_SUFFIXES = {
    "fabric":   "-fabric",
    "neoforge": "-neoforge",
    "common":   None,
}


def strip_loader_suffix(version: str, loader: str) -> str:
    suffix = LOADER_SUFFIXES.get(loader)
    if suffix and version.endswith(suffix):
        return version[: -len(suffix)]
    return version


def apply_loader_suffix(version: str, loader: str, had_suffix: bool) -> str:
    if not had_suffix:
        return version
    suffix = LOADER_SUFFIXES.get(loader)
    return f"{version}{suffix}" if suffix else version


def process_file(path: Path, loader: str, registry: dict, dry_run: bool) -> list[str]:
    """Read, patch, and optionally write a build.gradle.kts file. Returns change descriptions."""
    lines = path.read_text().splitlines(keepends=True)
    changes = []

    # --- Build lookup tables from registry ---

    # curse: project_id (int) -> new_file_id (str)
    curse_map: dict[int, str] = {}
    for mod in registry["mods"].values():
        pid = mod.get("curse_project_id")
        if pid:
            fid = mod.get("curse_files", {}).get(loader)
            if fid:
                curse_map[pid] = str(fid)

    # modrinth: modrinth_id (str) -> new_version (str)
    modrinth_map: dict[str, str] = {}
    for mod in registry["mods"].values():
        mid = mod.get("modrinth_id")
        if mid:
            ver = mod.get("modrinth_versions", {}).get(loader)
            if ver:
                modrinth_map[mid] = str(ver)

    # --- Scan and patch ---
    for i, line in enumerate(lines):
        stripped = line.lstrip()
        if stripped.startswith("//"):
            continue  # skip commented lines

        # ── CurseForge ──────────────────────────────────────────────────────
        cf_match = re.search(r'curse\.maven:[^"]*?-(\d+):(\d+)', line)
        if cf_match:
            project_id = int(cf_match.group(1))
            old_fid = cf_match.group(2)
            new_fid = curse_map.get(project_id)
            if new_fid and new_fid != old_fid:
                new_line = re.sub(
                    rf'(curse\.maven:[^"]*?-{project_id}:){old_fid}',
                    rf'\g<1>{new_fid}',
                    line,
                )
                changes.append(
                    f"  curse project {project_id}: {old_fid} -> {new_fid}"
                    f"  ({line.strip()[:65]})"
                )
                lines[i] = new_line
            continue  # a line won't be both curse and modrinth

        # ── Modrinth ────────────────────────────────────────────────────────
        mr_match = re.search(r'maven\.modrinth:([^":]+):([^"]+)', line)
        if mr_match:
            mid = mr_match.group(1)
            old_ver = mr_match.group(2)
            new_ver_base = modrinth_map.get(mid)
            if not new_ver_base:
                continue

            # Check if the existing version has a loader suffix; preserve it.
            suffix = LOADER_SUFFIXES.get(loader)
            had_suffix = suffix is not None and old_ver.endswith(suffix)
            old_ver_base = old_ver[: -len(suffix)] if had_suffix else old_ver
            new_ver = f"{new_ver_base}{suffix}" if had_suffix else new_ver_base

            if new_ver != old_ver:
                new_line = line.replace(f":{old_ver}", f":{new_ver}", 1)
                changes.append(
                    f"  modrinth {mid}: {old_ver} -> {new_ver}"
                    f"  ({line.strip()[:65]})"
                )
                lines[i] = new_line

    if changes and not dry_run:
        path.write_text("".join(lines))

    return changes


def main():
    dry_run = "--dry-run" in sys.argv

    with open(REGISTRY_PATH) as f:
        registry = json.load(f)

    if dry_run:
        print("[DRY RUN] No files will be written.\n")

    total = 0
    for loader, path in BUILD_FILES.items():
        print(f"[{loader}] {path.name}")
        changes = process_file(path, loader, registry, dry_run)
        if changes:
            for c in changes:
                print(c)
            total += len(changes)
        else:
            print("  no changes")

    print(f"\n--- {total} line(s) updated ---")
    if dry_run and total:
        print("Run without --dry-run to apply.")


if __name__ == "__main__":
    main()
