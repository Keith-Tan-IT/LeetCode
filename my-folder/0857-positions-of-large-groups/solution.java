class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0, j = 0; i < s.length(); i = j) {
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                j++;
            }
            if (j - i >= 3) {
                result.add(Arrays.asList(i, j - 1));
            }
        }
        return result;
    }
}
