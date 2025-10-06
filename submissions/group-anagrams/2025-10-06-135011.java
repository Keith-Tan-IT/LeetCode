class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map <String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            }
            else {
                map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }
        result = new ArrayList<>(map.values());
        return result;
    }
}