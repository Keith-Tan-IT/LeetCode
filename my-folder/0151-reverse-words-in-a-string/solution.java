class Solution {
    public String reverseWords(String s) {
        String[] splitted = s.split("\\s+");
        String reversed = "";
        for (int i = splitted.length - 1; i >= 0; i--) {
            reversed += splitted[i] + " ";
        }
        return reversed.trim();
    }
}
