class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0) {
                n -= 1;
                if(n <= 0) {
                    return true;
                }
            }
            else {
                return n == 0;
            }
        }
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            n -= 1;
        }
        for (int i = 1; i < flowerbed.length - 1; i++) {
            if (flowerbed[i-1] == 0 && flowerbed[i] == 0 && flowerbed[i+1] == 0) {
                flowerbed[i] = 1;
                n -= 1;
                if (n == 0) {
                    return true;
                }
            }
        }
        if (flowerbed[flowerbed.length - 1] == 0 && flowerbed[flowerbed.length - 2] == 0) {
            flowerbed[flowerbed.length - 1] = 1;
            n -= 1;    
        }
        return n <= 0;
    }
}
