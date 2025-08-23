import os
import requests
import json
import datetime
from pathlib import Path

# --- Config ---
LEETCODE_SESSION = os.getenv("LEETCODE_SESSION")
LEETCODE_CSRF_TOKEN = os.getenv("LEETCODE_CSRF_TOKEN")
OUTPUT_DIR = Path("submissions")

# --- Headers for auth ---
headers = {
    "cookie": f"LEETCODE_SESSION={LEETCODE_SESSION}; csrftoken={LEETCODE_CSRF_TOKEN};",
    "x-csrftoken": LEETCODE_CSRF_TOKEN,
    "referer": "https://leetcode.com",
    "content-type": "application/json",
}

# --- GraphQL query to fetch submissions ---
graphql_url = "https://leetcode.com/graphql"
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
        data = r.json()
        subs = data["data"]["submissionList"]["submissions"]
        if not subs:
            break
        submissions.extend(subs)
        offset += limit
    return submissions

def save_submission(sub):
    title_slug = sub["titleSlug"]
    lang = sub["lang"]
    code = sub["code"]

    # File extension mapping
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

    # Timestamped filename
    dt = datetime.datetime.utcfromtimestamp(int(sub["timestamp"]))
    filename = f"{dt.strftime('%Y-%m-%d-%H%M%S')}.{ext}"

    folder = OUTPUT_DIR / title_slug
    folder.mkdir(parents=True, exist_ok=True)

    filepath = folder / filename
    with open(filepath, "w", encoding="utf-8") as f:
        f.write(code)

def main():
    OUTPUT_DIR.mkdir(exist_ok=True)
    submissions = fetch_submissions(limit=50)
    print(f"Fetched {len(submissions)} submissions")
    for sub in submissions:
        save_submission(sub)

if __name__ == "__main__":
    main()
