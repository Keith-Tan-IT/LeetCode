class Solution {
    public int compress(char[] chars) {
        int write = 0, start = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read == chars.length - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[start];
                if (read > start) {
                    int count = read - start + 1;
                    for (char c : Integer.toString(count).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                start = read + 1;
            }
        }
        return write;
    }
}
