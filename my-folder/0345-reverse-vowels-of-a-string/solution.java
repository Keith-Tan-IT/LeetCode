class Solution {
    public String reverseVowels(String s) {
        String vowels ="aeiouAEIOU";
        int start = 0;
        int end = s.length() - 1;
        StringBuilder modifiableString = new StringBuilder (s);
        while (start < end) {
            while (start < end && !vowels.contains(String.valueOf(modifiableString.charAt(start)))) {
                start++;
            }
            while (start < end && !vowels.contains(String.valueOf(modifiableString.charAt(end)))) {
                end--;
            }
            char temp = modifiableString.charAt(start);
            modifiableString.setCharAt(start, modifiableString.charAt(end));
            modifiableString.setCharAt(end, temp);

            start++;
            end--;
        }
        return modifiableString.toString();
    }
}
