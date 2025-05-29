class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        while (a > 0 || b > 0 || c > 0) {
            if ((c & 1) == 1) {
                if ((a & 1) == 0 && (b & 1) == 0) {
                    flips++;
                }
            }
            else {
                if ((a & 1) == 1 && (b & 1) == 1) {
                    flips += 2;
                }
                else if ((a & 1) == 1 || (b & 1) == 1) {
                    flips++;
                }
            }
            a >>= 1; 
            b >>= 1;
            c >>= 1;
        }
        return flips;
    }
}

/*
a  b  c  Flip                  Number of Flips (d)
--------------------------------------------------
0  0  0  -                     0
0  0  1  Flip any of a or b    1
0  1  0  Flip b                1
0  1  1  -                     0
1  0  0  Flip a                1
1  0  1  -                     0
1  1  0  Flip a and b          2
1  1  1  -                     0
*/