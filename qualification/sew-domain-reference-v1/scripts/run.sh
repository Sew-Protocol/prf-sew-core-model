#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
EXTENSION_ROOT="$(cd "$ROOT/../../extensions/sew-reference-validation" && pwd)"
cd "$EXTENSION_ROOT"
mkdir -p "$ROOT/actual"
LOG_FILE="$ROOT/actual/run.log"
# The pinned PRF provenance code may call `jj diff`. In CI or workspaces where
# Watchman is configured but unavailable, that subprocess blocks during
# filesystem-monitor startup. Disable only the monitor for this suite process.
JJ_TEMP_CONFIG="$(mktemp "${TMPDIR:-/tmp}/sew-reference-validation-jj.XXXXXX")"
printf 'fsmonitor.backend = "none"\n' >"$JJ_TEMP_CONFIG"
if [[ -n "${JJ_CONFIG:-}" ]]; then
  export JJ_CONFIG="${JJ_CONFIG}:$JJ_TEMP_CONFIG"
else
  export JJ_CONFIG="$JJ_TEMP_CONFIG"
fi
trap 'rm -f "$JJ_TEMP_CONFIG"' EXIT
if clojure -Srepro -M:reference-validation --suite-root "$ROOT" --protocol sew "$@" >"$LOG_FILE" 2>&1; then
  grep -E '^(PASS |[0-9]+ scenarios|[0-9]+ failures|[0-9]+ inconclusive|[0-9]+ simulator-backed)' \
    "$LOG_FILE" | tail -n 8
else
  tail -n 80 "$LOG_FILE" | cut -c1-500
  exit 1
fi
