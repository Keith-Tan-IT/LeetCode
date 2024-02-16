class Solution {
    public boolean wordPattern(String pattern, String s) {
        String []sChar = s.split(" ");
        Map wordMap = new HashMap<>();
        if (sChar.length != pattern.length()) {
            return false;
        }
        for (Integer i = 0; i < sChar.length; i++) {
            if (wordMap.put(pattern.charAt(i),i) != wordMap.put(sChar[i],i)) {
                return false;
            }
        }
        return true;
    }
}
