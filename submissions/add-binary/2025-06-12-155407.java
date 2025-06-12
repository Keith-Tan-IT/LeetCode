class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();
        StringBuilder reverseSum = new StringBuilder();
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            if (i >= 0) {
                carry += a.charAt(i--) - '0';
            } 
            if (j >= 0) {
                carry += b.charAt(j--) - '0';
            }
            sum.append(carry % 2);
            carry /= 2;
        }
        for (i = sum.length() - 1; i >= 0; i--) {
            reverseSum.append(sum.charAt(i));
        }
        return reverseSum.toString();
    }
}