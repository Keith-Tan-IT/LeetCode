#!/usr/bin/env bash 
set -e

LEETCODE_FOLDER=$1
if [ -z "$LEETCODE_FOLDER" ]; then
    echo "Usage: $0 <leetcode-folder>"
    exit 1
fi

AGG_FILE="$LEETCODE_FOLDER/TIPS.md"

# Initialize aggregated file if it doesn't exist
if [ ! -f "$AGG_FILE" ]; then
    echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
    echo "_Newest tips first_" >> "$AGG_FILE"
    echo "" >> "$AGG_FILE"
fi

for folder in "$LEETCODE_FOLDER"/*/; do
    SOLUTION="$folder/solution.java"
    if [ ! -f "$SOLUTION" ]; then
        continue
    fi

    # Extract TIP block safely
    TIP_BLOCK=$(awk '
        BEGIN {inblock=0; tip_found=0; buf=""}
        /^\s*\/\*\*/ {inblock=1; tip_found=0; buf=""}
        inblock {
            buf = buf $0 "\n"
            if ($0 ~ /TIP/) tip_found=1
        }
        /^\s*\*\// {
            if (inblock && tip_found) {print buf; exit}
            inblock=0; buf=""
        }
    ' "$SOLUTION")

    if [ -z "$TIP_BLOCK" ]; then
        continue
    fi

    # Extract Problem number and title
    PROBLEM_LINE=$(echo "$TIP_BLOCK" | grep -i 'Problem:' | sed -E 's/^[[:space:]]*\*+[[:space:]]*Problem:[[:space:]]*(.*)/\1/')
    if [ -z "$PROBLEM_LINE" ]; then
        PROBLEM_LINE="Unknown. Unknown"
    fi

    # Write per-problem TIP.md
    TIP_FILE="$folder/TIP.md"
    echo "# Tip — $PROBLEM_LINE" > "$TIP_FILE"
    echo "$TIP_BLOCK" >> "$TIP_FILE"
    echo "Wrote per-problem TIP: $TIP_FILE"
    
    # 🟢 --- NEW / UPDATED SECTION BELOW ---
    # If tip for this problem already exists, remove old version first (overwrite instead of duplicate)
    if grep -Fq "# Tip — $PROBLEM_LINE" "$AGG_FILE"; then
        echo "Updating existing tip for: $PROBLEM_LINE"
        # Remove old section (from this tip header to next '---' or EOF)
        awk -v title="# Tip — $PROBLEM_LINE" '
            BEGIN {inblock=0}
            $0 == title {inblock=1; next}
            /^---$/ {if (inblock) {inblock=0; next}}
            !inblock
        ' "$AGG_FILE" > "$AGG_FILE.tmp"
        mv "$AGG_FILE.tmp" "$AGG_FILE"
    fi

    # Prepend new or updated tip (newest first), keeping header
    TMP_AGG=$(mktemp)
    {
        head -n 2 "$AGG_FILE"    # keep header
        echo ""
        echo "---"
        echo "# Tip — $PROBLEM_LINE"
        echo "$TIP_BLOCK"
        echo ""
        tail -n +3 "$AGG_FILE"  # append rest
    } > "$TMP_AGG"
    mv "$TMP_AGG" "$AGG_FILE"
done

echo "✅ Aggregated TIPS.md updated successfully at: $AGG_FILE"
