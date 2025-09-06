#!/usr/bin/env bash
set -euo pipefail

# Directory containing LeetCode problem folders
BASE_DIR="${1:-my-folder}"
AGG_FILE="$BASE_DIR/TIPS.md"

echo "Starting tip extraction from: $BASE_DIR"

# Ensure tips folder exists (optional)
mkdir -p "$BASE_DIR/tips"

# Loop through all Java files
find "$BASE_DIR" -type f -name "*.java" | while read -r file; do
    # Extract TIP comment blocks only
    awk '
    BEGIN {in_comment=0; in_tip=0; buf=""}
    /\/\*\*/ {in_comment=1; buf=""}
    in_comment {buf=buf $0 "\n"}
    /TIP/ && in_comment {in_tip=1; tip_text=buf}
    /\*\// {
        if(in_tip){
            print tip_text
            in_tip=0
        }
        in_comment=0
    }
    ' "$file" | while IFS= read -r tip_block || [ -n "$tip_block" ]; do
        # Skip empty blocks
        [[ -z "$tip_block" ]] && continue

        # Extract problem number and title robustly
        problem_line=$(echo "$tip_block" | grep -i "Problem:" || true)
        if [[ -n "$problem_line" ]]; then
            problem_number=$(echo "$problem_line" | grep -oP 'Problem:\s*\K[0-9]+')
            problem_title=$(echo "$problem_line" | grep -oP 'Problem:\s*[0-9]+\.\s*\K.*')
        else
            problem_number="Unknown"
            problem_title="Unknown"
        fi

        # Slug for file/folder name
        slug=$(echo "$problem_title" | tr '[:upper:]' '[:lower:]' | tr -cs 'a-z0-9' '-')
        PROBLEM_FOLDER=$(dirname "$file")
        TIP_FILE="$PROBLEM_FOLDER/TIP.md"

        # Write per-problem TIP.md
        echo -e "# Tip — $problem_number. $problem_title\n\n$tip_block" > "$TIP_FILE"
        echo "Wrote per-problem TIP: $TIP_FILE"
    done
done

# Build aggregated TIPS.md, newest first
echo "# 📘 LeetCode Tips Cheat Sheet" > "$A
