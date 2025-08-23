import os
import requests
import datetime
from pathlib import Path
import subprocess
import json

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

query = """
query submissions($offset: Int!, $limit: Int!) {
  submissionList(offset: $offset, limit: $limit) {
    submissions {
      id
      title
      titleSlug
      statusDisplay
      lang
      timestamp
      code
    }
  }
}
"""

def fetch_submissions(limit=20):
    submissions = []
    offset = 0
    while True:
        variables = {"offset": offset, "limit": limit}
        r = requests.post(graphql_url, json={"query": query, "variables": variables}, headers=headers)
        try:
            data = r.json()
        except Exception:
            print("❌ Failed to parse JSON")
            print(r.text)
            break

        if "errors" in data:
            print("❌ GraphQL error:", data["errors"])
            break

        if "data" not in data or "submissionList" not in data["data"]:
            print("❌ Unexpected response:", json.dumps(data, indent=2))
            break

        subs = data["data"]["submissionList"]["submissions"]
        if not subs:
            break
        submissions.extend(subs)
        offset += limit
    return submissions

def git_commit(filepath, message, dt):
    date_str = dt.strftime("%Y-%m-%d %H:%M:%S")
    env = os.environ.copy()
    env["GIT_AUTHOR_DATE"] = date_str
    env["GIT_COMMITTER_DATE"] = date_str
    env["GIT_AUTHOR_NAME"] = GIT_NAME
    env["GIT_COMMITTER_NAME"] = GIT_NAME
    env["GIT_AUTHOR_EMAIL"] = GIT_EMAIL
    env["GIT_COMMITTER_EMAIL"] = GIT_EMAIL

    subprocess.run(["git", "add", filepath], env=env, check=True)
    subprocess.run(["git", "commit", "-m", message], env=env, check=True)
    subprocess.run(["git", "push", "origin", "main"], env=env, check=True)

def save_submission(sub):
    title_slug = sub["titleSlug"]
    lang = sub["lang"]
    code = sub["code"]

    ext_map = {
        "python3": "py",
        "cpp": "cpp",
        "java": "java",
        "c": "c",
        "csharp": "cs",
        "javascript": "js",
        "typescript": "ts",
        "ruby": "rb",
        "go": "go",
        "swift": "swift",
        "kotlin": "kt",
        "rust": "rs",
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
