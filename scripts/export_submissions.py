import os
import requests
import datetime
from pathlib import Path
import subprocess
import json
import time

# --- Config ---
LEETCODE_SESSION = os.getenv("LEETCODE_SESSION")
LEETCODE_CSRF_TOKEN = os.getenv("LEETCODE_CSRF_TOKEN")
OUTPUT_DIR = Path("submissions")
GIT_NAME = os.getenv("GITHUB_NAME")
GIT_EMAIL = os.getenv("GITHUB_EMAIL")

graphql_url = "https://leetcode.com/graphql"

headers = {
    "cookie": f"LEETCODE_SESSION={LEETCODE_SESSION}; csrftoken={LEETCODE_CSRF_TOKEN};",
    "x-csrftoken": LEETCODE_CSRF_TOKEN,
    "referer": "https://leetcode.com",
    "content-type": "application/json",
}

# --- Query 1: Get list of submissions (no code) ---
query_list = """
query submissions($offset: Int!, $limit: Int!) {
  submissionList(offset: $offset, limit: $limit) {
    hasNext
    submissions {
      id
      title
      titleSlug
      statusDisplay
      lang
      timestamp
    }
  }
}
"""

# --- Query 2: Get submission code ---
query_detail = """
query submissionDetails($id: Int!) {
  submissionDetails(submissionId: $id) {
    id
    code
    timestamp
  }
}
"""

def fetch_submissions(limit=50):
    submissions = []
    offset = 0
    while True:
        variables = {"offset": offset, "limit": limit}
        r = requests.post(graphql_url, json={"query": query_list, "variables": variables}, headers=headers)
        data = r.json()

        if "errors" in data:
            print("❌ GraphQL error:", data["errors"])
            break

        sublist = data.get("data", {}).get("submissionList", {})
        subs = sublist.get("submissions", [])
        if not subs:
            break

        submissions.extend(subs)
        if not sublist.get("hasNext"):
            break
        offset += limit
        time.sleep(0.2)  # avoid hitting rate limit
    return submissions

def fetch_code(sub_id):
    """Fetch the full code for a specific submission."""
    r = requests.post(graphql_url, json={"query": query_detail, "variables": {"id": int(sub_id)}}, headers=headers)
    data = r.json()
    if "errors" in data:
        print("⚠️ Failed to fetch code for ID", sub_id, ":", data["errors"])
        return None
    return data.get("data", {}).get("submissionDetails", {}).get("code")

def git_commit(filepath, message, dt):
    date_str = dt.strftime("%Y-%m-%d %H:%M:%S")
    env = os.environ.copy()
    env.update({
        "GIT_AUTHOR_DATE": date_str,
        "GIT_COMMITTER_DATE": date_str,
        "GIT_AUTHOR_NAME": GIT_NAME,
        "GIT_COMMITTER_NAME": GIT_NAME,
        "GIT_AUTHOR_EMAIL": GIT_EMAIL,
        "GIT_COMMITTER_EMAIL": GIT_EMAIL,
    })

    subprocess.run(["git", "add", filepath], env=env, check=True)
    subprocess.run(["git", "commit", "-m", message], env=env, check=True)
    subprocess.run(["git", "push", "origin", "main"], env=env, check=True)

def save_submission(sub):
    title_slug = sub["titleSlug"]
    lang = sub["lang"]
    code = fetch_code(sub["id"])
    if not code:
        return

    ext_map = {
        "python3": "py", "cpp": "cpp", "java": "java", "c": "c",
        "csharp": "cs", "javascript": "js", "typescript": "ts",
        "ruby": "rb", "go": "go", "swift": "swift",
        "kotlin": "kt", "rust": "rs"
    }
    ext = ext_map.get(lang.lower(), "txt")

    dt = datetime.datetime.utcfromtimestamp(int(sub["timestamp"]))
    filename = f"{dt.strftime('%Y-%m-%d-%H%M%S')}.{ext}"

    folder = OUTPUT_DIR / title_slug
    folder.mkdir(parents=True, exist_ok=True)

    filepath = folder / filename
    if not filepath.exists():
        with open(filepath, "w", encoding="utf-8") as f:
            f.write(code)
        git_commit(str(filepath), f"LeetCode submission: {title_slug}", dt)

def main():
    OUTPUT_DIR.mkdir(exist_ok=True)
    submissions = fetch_submissions(limit=50)
    print(f"✅ Fetched {len(submissions)} submissions")
    for sub in submissions:
        if sub["statusDisplay"] == "Accepted":
            save_submission(sub)

if __name__ == "__main__":
    main()
