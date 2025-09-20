class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] sum = new int[2];

        int n = numbers.length, left = 0, right = n - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                sum[0] = left + 1;
                sum[1] = right + 1;
                return sum;
            }
            else if (numbers[left] + numbers[right] > target) {
                right--;
            }
            else {
                left++;
            }
        }
        return sum;
    }
}
