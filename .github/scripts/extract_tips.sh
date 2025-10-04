#!/usr/bin/env bash
set -euo pipefail

# usage: extract_tips.sh <leetcode-folder>
LEETCODE_FOLDER=${1:-}
if [[ -z "$LEETCODE_FOLDER" ]]; then
  echo "Usage: $0 <leetcode-folder>"
  exit 1
fi

AGG_FILE="$LEETCODE_FOLDER/TIPS.md"

# Initialize aggregated file if it doesn't exist
if [[ ! -f "$AGG_FILE" ]]; then
  printf "%s\n%s\n\n" "# 📘 LeetCode Tips Cheat Sheet" "_Newest tips first_" > "$AGG_FILE"
fi

# iterate problem folders
for folder in "$LEETCODE_FOLDER"/*/; do
  [[ -d "$folder" ]] || continue

  # collect all TIP blocks found in any .java file in this folder
  declare -a BLOCKS=()

  shopt -s nullglob
  for file in "$folder"/*.java; do
    [[ -f "$file" ]] || continue

    # awk prints each matching block followed by a NUL character
    while IFS= read -r -d '' block; do
      # trim trailing newlines (just in case)
      block="${block%%$'\n'}"
      BLOCKS+=("$block")
    done < <(awk '
      BEGIN { inblock=0; buf=""; hasTip=0 }
      /^\s*\/\*\*/ {
        inblock=1; buf=$0 "\n"; hasTip=0
        next
      }
      inblock {
        buf = buf $0 "\n"
        # detect a TIP line inside the block: typically " * TIP" but we accept anywhere in the block
        if (tolower($0) ~ /(^|\s)\*\s*tip\b/ || tolower($0) ~ /tip/) hasTip=1
      }
      /^\s*\*\// {
        if (inblock && hasTip) {
          # output block followed by NUL so bash can split safely
          printf "%s\0", buf
        }
        inblock=0; buf=""; hasTip=0
      }
    ' "$file")
  done
  shopt -u nullglob

  # if no TIP blocks in this folder, continue
  if [[ ${#BLOCKS[@]} -eq 0 ]]; then
    continue
  fi

  # determine PROBLEM_LINE from first block, fallback to folder name
  PROBLEM_LINE=$(printf "%s\n" "${BLOCKS[0]}" | grep -i 'problem' | head -n1 | sed -E 's/^[[:space:]]*\**[[:space:]]*[Pp]roblem:[[:space:]]*(.*)/\1/')
  if [[ -z "$PROBLEM_LINE" ]]; then
    PROBLEM_LINE="$(basename "$folder" | sed 's/[_-]/ /g')"
  fi

  # Build per-problem TIP.md by combining all blocks found for this folder
  TIP_FILE="$folder/TIP.md"
  {
    printf "%s\n\n" "# Tip — $PROBLEM_LINE"
    for b in "${BLOCKS[@]}"; do
      printf "%s\n\n" "$b"
    done
  } > "$TIP_FILE"
  echo "Wrote per-problem TIP: $TIP_FILE"

  # Remove any existing entry for this problem from aggregated file (if present)
  if grep -Fq "# Tip — $PROBLEM_LINE" "$AGG_FILE"; then
    awk -v title="# Tip — $PROBLEM_LINE" '
      BEGIN {skip=0}
      $0 == title { skip=1; next }
      /^---$/ { if (skip) { skip=0; next } }
      { if (!skip) print }
    ' "$AGG_FILE" > "$AGG_FILE.tmp"
    mv "$AGG_FILE.tmp" "$AGG_FILE"
    echo "Removed old aggregated entry for: $PROBLEM_LINE"
  fi

  # Prepend the new tip (newest-first) keeping the file header (first 3 lines)
  TMP_AGG=$(mktemp)
  {
    head -n 3 "$AGG_FILE"
    echo ""
    echo "---"
    echo "# Tip — $PROBLEM_LINE"
    cat "$TIP_FILE"
    echo ""
    tail -n +4 "$AGG_FILE"
  } > "$TMP_AGG"
  mv "$TMP_AGG" "$AGG_FILE"
done

echo "✅ Aggregated TIPS.md updated successfully at: $AGG_FILE"
