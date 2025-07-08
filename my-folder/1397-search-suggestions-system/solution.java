class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> suggested = new ArrayList<>();
        Arrays.sort(products);
        for (int i = 1; i <= searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i);
            List<String> matches = new ArrayList<>();
            for (String product : products) {
                if (product.startsWith(prefix)) {
                    matches.add(product);
                }
            }
            if (matches.size() > 3) {
                matches = matches.subList(0, 3);
            }
            suggested.add(matches);
        }
        return suggested;
    }
}
