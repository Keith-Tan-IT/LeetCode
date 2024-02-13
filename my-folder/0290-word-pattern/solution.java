class Solution {
    public boolean wordPattern(String pattern, String s) {
        String [] words = s.split(" ");
        if (pattern.length() != words.length) 
            return false;
        HashMap <Character,String> charToWord = new HashMap<>();
        HashMap <String,Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            
            if (charToWord.get(pattern.charAt(i)) == null) {
                charToWord.put(pattern.charAt(i), words[i]);
            }

            if(wordToChar.get(words[i]) == null) {
                wordToChar.put(words[i],pattern.charAt(i));
            }

            if (!charToWord.get(pattern.charAt(i)).equals(words[i]) || !wordToChar.get(words[i]).equals(pattern.charAt(i))) {
                return false;
            }
        }
        return true;

    }
}
