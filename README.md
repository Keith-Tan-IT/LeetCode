# 🚀 LeetCode Daily Grind — Automated Submissions Tracker

Welcome!  
This repository contains my **daily LeetCode problem-solving journey**, automatically synchronized from LeetCode using a custom GitHub Actions workflow.

Every time I solve a new problem on LeetCode, the solution is:

1. Fetched via the LeetCode GraphQL API  
2. Organized into a clean folder structure  
3. Auto-committed with the **original submission timestamp**  
4. Updated into a central `TIPS.md` file when the solution includes a `/** TIP` block

---

## 📁 Repository Structure

- Each problem has its **own folder** based on `titleSlug`
- Each submission file is timestamped with the **actual submission time**
- `TIPS.md` aggregates insights across all problems (automatically kept up to date)

---

## 🤖 Automation Workflow

This repository uses a GitHub Action to automatically:

✔ Fetch all accepted LeetCode submissions  
✔ Commit them using the original timestamp  
✔ Update problem-level `TIP.md` files  
✔ Update the global `TIPS.md` cheat sheet  
✔ Avoid duplicates (commit only if new)

All authentication is done via:

- `LEETCODE_SESSION`
- `LEETCODE_CSRF_TOKEN`

(Stored as GitHub repository secrets.)

---

## 🛠 Technologies Used

- **Python** (for fetching & organizing submissions)
- **GraphQL API** (LeetCode)
- **GitHub Actions** (for automation)
- **Bash** (for TIP extraction)

---

## ✨ Why this exists

This repo acts as:

- My **daily coding log**
- A structured archive of my **LeetCode progression**
- A centralized place for reusable **tips**, patterns, and insights
- An automated system so I never lose any submission again

---

## 📌 TIP Extraction Format

Inside any solution, if I include this block:

```java
/**
 * TIP
 * Problem: 1234. Problem Title
 * Explanation, tricks, observations...
 */

