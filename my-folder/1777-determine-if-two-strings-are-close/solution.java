class Solution {
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        HashMap <Character, Integer> map1 = new HashMap <>();
        HashMap <Character, Integer> map2 = new HashMap <>();
        int newChar1 = 0, newChar2 = 0;
        for (char word : word1.toCharArray()) {
            if (map1.containsKey(word)) {
                map1.put(word, map1.get(word) + 1);
            }
            else {
                map1.put(word, 1);
                newChar1++;
            }
        }

        for (char word : word2.toCharArray()) {
            if (!map2.containsKey(word)) {
                map2.put(word, 1);
                newChar2++;
            }
            else {
                map2.put(word, map2.get(word) + 1);
            }
        }

        if (newChar1 != newChar2 || !map1.keySet().equals(map2.keySet())) {
            return false;
        }

        Integer[] freq1 = map1.values().toArray(new Integer[0]);
        Integer[] freq2 = map2.values().toArray(new Integer[0]);

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        if (!Arrays.equals(freq1, freq2)) {
            System.out.println(Arrays.toString(freq1));
            System.out.println(Arrays.toString(freq2));
            return false;
        }
 
        return true;
    }
}
