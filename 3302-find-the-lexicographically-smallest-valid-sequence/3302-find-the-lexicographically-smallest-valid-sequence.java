import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] suffix = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suffix[i] = m - 1 - j;
        }

        int[] ans = new int[m];

        int p = 0;
        boolean mismatchUsed = false;

        for (int i = 0; i < n && p < m; i++) {

            if (word1.charAt(i) == word2.charAt(p)) {
                ans[p] = i;
                p++;
            }

           
            else if (!mismatchUsed) {

                int remaining = m - p - 1;

                if (suffix[i + 1] >= remaining) {
                    ans[p] = i;
                    p++;
                    mismatchUsed = true;
                }
            }
        }

        if (p == m) {
            return ans;
        }

        return new int[0];
    }
}