class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0 || digits == null) {
            return result;
        }
        String map[] = new String[] {"0", "1", "abc", "def" , "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, "", map, result);
        return result;

    }

    public void backTracking(String next_digits, String combination, String[] map, List<String> result) {
        if (next_digits.isEmpty()) {
            result.add(combination);
        }
        else {
            String letters = map[next_digits.charAt(0) - '0'];
            for (char letter : letters.toCharArray()) {
                backTracking(next_digits.substring(1), combination + letter, map, result);
            }
        }
    }
}
