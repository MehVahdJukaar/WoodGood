import subprocess
import re
from datetime import datetime
from pathlib import Path

# === HARD-CODED CONFIGURATION ===
NUM_RUNS = 5
LOG_FILE_PATH = "load_times.txt"
PROJECT_DIR = r"C:\Users\Matteo\IdeaProjects\WoodGood"
COMMAND = ["gradlew.bat", ":neoforge:runClient"]
# =================================

# Regex patterns
PATTERN_LOAD_TIME = re.compile(r"GAME LOADED IN:\s*([\d.]+)\s*s", re.IGNORECASE)
PATTERN_PACK_GEN = re.compile(
    r"Generated runtime CLIENT_RESOURCES for pack everycomp:dynamic_resources in (\d+)\s*ms",
    re.IGNORECASE
)

def run_once(cmd, cwd: Path):
    process = subprocess.run(
        cmd,
        cwd=cwd,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True
    )

    output = process.stdout or ""
    load_time = None
    pack_time = None

    for line in output.splitlines():
        m1 = PATTERN_LOAD_TIME.search(line)
        m2 = PATTERN_PACK_GEN.search(line)
        if m1:
            load_time = m1.group(1)  # float in string, e.g., '16.73'
        if m2:
            pack_time = m2.group(1)  # int in string, e.g., '3787'

    return process.returncode, load_time, pack_time or "0"

def append_separator(log_path: Path, cmd_display: str, times: int):
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    sep = "=" * 72
    with log_path.open("a", encoding="utf-8") as f:
        f.write(f"{sep}\n[{ts}] New run: times={times} cmd={cmd_display}\n{sep}\n")

def append_entry(log_path: Path, idx: int, load_time: str | None, pack_time: str, exit_code: int):
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    status = "OK" if (exit_code == 0 and load_time is not None) else f"ERR({exit_code})"
    load_val = load_time if load_time is not None else "N/A"
    with log_path.open("a", encoding="utf-8") as f:
        f.write(
            f"[{ts}] #{idx:03d} GAME LOADED IN: {load_val} s, PACK GEN TIME: {pack_time} ms  [{status}]\n"
        )

def main():
    log_path = Path(PROJECT_DIR) / LOG_FILE_PATH
    cwd = Path(PROJECT_DIR)
    cmd_display = " ".join(COMMAND)

    append_separator(log_path, cmd_display, NUM_RUNS)

    for i in range(1, NUM_RUNS + 1):
        print(f">>> Run #{i}")
        exit_code, load_time, pack_time = run_once(COMMAND, cwd)
        append_entry(log_path, i, load_time, pack_time, exit_code)

if __name__ == "__main__":
    main()
