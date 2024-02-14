class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counter = new int[26];
        char chm[] = magazine.toCharArray();
        for (int i = 0; i < magazine.length(); i++) {
            counter[chm[i]-'a']++;
        }
        char chr[] = ransomNote.toCharArray();
        for (int i = 0; i < ransomNote.length(); i++) {
            if(counter[chr[i]-'a']-- == 0)
            return false;
        }
        return true;
    }
}
