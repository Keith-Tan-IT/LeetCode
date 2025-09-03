class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new LinkedList<>();
        for (int i = num.length - 1; i >= 0; i--) {
            k = num[i] + k;
           result.add(0, k % 10); 
            k /= 10;
        }
        while (k > 0) {
            result.add(0, k % 10);
            k /= 10;
        }
        return result;
    }
}
