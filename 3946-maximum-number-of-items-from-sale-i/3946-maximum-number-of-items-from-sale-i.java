class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int MAXF = 1500;

        int[] freq = new int[MAXF + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] multiples = new int[MAXF + 1];
        for (int f = 1; f <= MAXF; f++) {
            for (int m = f; m <= MAXF; m += f) {
                multiples[f] += freq[m];
            }
        }

        int[] bonus = new int[n];
        for (int i = 0; i < n; i++) {
            bonus[i] = multiples[items[i][0]] - 1;
        }

        int NEG = -1_000_000;
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int p = items[i][1];
            int w = bonus[i];

            int[] ndp = dp.clone();

            for (int r = 0; r < p && r <= budget; r++) {
                int best = dp[r];

                int q = 1;
                for (int pos = r + p; pos <= budget; pos += p, q++) {

                    if (best > NEG) {
                        ndp[pos] = Math.max(ndp[pos], w + q + best);
                    }

                    if (dp[pos] > NEG) {
                        best = Math.max(best, dp[pos] - q);
                    }
                }
            }

            dp = ndp;
        }

        int ans = 0;
        for (int x : dp) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}