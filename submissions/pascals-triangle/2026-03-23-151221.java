class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(Arrays.asList(1));
            return result;
        }
        List<List<Integer>> prevRow = generate(numRows - 1);
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            newRow.add(1);
        }
        for (int i = 1; i < numRows - 1; i++) {
            newRow.set(i, prevRow.get(numRows - 2).get(i - 1) + prevRow.get(numRows - 2).get(i));
        }
        prevRow.add(newRow);
        return prevRow;
    }
}