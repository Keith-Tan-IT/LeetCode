class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1 ; i >= 0 ; i--) {
            if (carry == 1) {
                if (digits[i] == 9) {
                digits[i] = 0;
                }
                else {
                    digits[i] = digits[i] + 1;
                    carry = 0;
                }
            }
        }
        if (carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
}
