class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;

        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];

        f[1][0] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {

                // Not currently drawing a segment
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;

                // Currently drawing a segment
                g[i][j] = g[i - 1][j];

                if (j > 0) {
                    // Start a new segment
                    g[i][j] += f[i - 1][j - 1];
                    g[i][j] %= MOD;

                    // Extend the current segment to point i
                    g[i][j] += g[i - 1][j - 1];
                    g[i][j] %= MOD;
                }
            }
        }

        return (f[n][k] + g[n][k]) % MOD;
    }
}