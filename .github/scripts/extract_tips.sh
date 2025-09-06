#!/usr/bin/env bash
set -e

LEETCODE_FOLDER=$1
if [ -z "$LEETCODE_FOLDER" ]; then
    echo "Usage: $0 <leetcode-folder>"
    exit 1
fi

AGG_FILE="$LEETCODE_FOLDER/TIPS.md"
echo "# 📘 LeetCode Tips Cheat Sheet" > "$AGG_FILE"
echo "_Newest tips first_" >> "$AGG_FILE"
echo "" >> "$AGG_FILE"

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

    # Append to aggregated TIPS.md
    echo "---" >> "$AGG_FILE"
    echo "# Tip — $PROBLEM_LINE" >> "$AGG_FILE"
    echo "$TIP_BLOCK" >> "$AGG_FILE"
    echo "" >> "$AGG_FILE"

done

echo "Aggregated TIPS.md generated successfully at: $AGG_FILE"
