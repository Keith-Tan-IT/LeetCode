class Solution {
    public boolean isPalindrome(String s) {
        if(s.isEmpty()) {
            return true;
        }
        int first = 0;
        int last = s.length() - 1;
        while (last >= first) {
            char firstLetter = s.charAt(first);
            char lastLetter = s.charAt(last);
            if (!Character.isLetterOrDigit(firstLetter)) {
                first++;
            }
            else if (!Character.isLetterOrDigit(lastLetter)) {
                last--;
            }
            else if (Character.toLowerCase(firstLetter) != Character.toLowerCase(lastLetter)) {
                return false;
            }
            else {
                first++;
                last--;
            }
        }
        return true;
    }
}
