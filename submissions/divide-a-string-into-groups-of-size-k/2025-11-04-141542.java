class Solution {
    public String[] divideString(String s, int k, char fill) {
        int group = (s.length() + k - 1) / k;
        String[] result = new String[group];
        for (int i = 0; i < group; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < k; j++) {
                int index = i * k + j;
                if (index < s.length()) {
                    str.append(s.charAt(index));
                }
                else {
                    str.append(fill);
                }
            }
            result[i] = str.toString();
        }
        return result;
    }
}