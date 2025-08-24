class Solution {
    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        int left = 1, right = x / 2, result = 0;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (x / mid < mid) {
                right = mid - 1;
            }
            else {
                result = mid;
                left = mid + 1;
            }
        }
        return result;
    }
}
/*
right = 4
mid = 2
left = 3
mid = 3
8 / 3 = 2.6
right = 2
*/