class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        String[] map = new String [] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits.isEmpty()) {
            return result; 
        }
        result.add(0, "");
        for (int i = 0; i < digits.length(); i++) {
            int a = Character.getNumericValue(digits.charAt(i));
            int k = result.size();
            for (int j = 0; j < k; j++) {
                String firstLetter = result.remove(0);
                for (char letter : map[a].toCharArray()) {
                    result.add(firstLetter + letter);
                }
            }
        }
        return result;
    }
}
