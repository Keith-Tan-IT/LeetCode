class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < m; i++) {
            nums1[i] = nums1[i];
        }
        for (int j = 0; j < n; j++) {
            nums1[m+j] = nums2[j];
        }
        int temp;
        for (int k = 0; k < m+n; k++) {
            for (int a = k+1; a < m+n; a++) {
                if (nums1[k] > nums1[a]) {
                    temp = nums1[k];
                    nums1[k] = nums1[a];
                    nums1[a] = temp;
                }
            }
        }
    }
}
