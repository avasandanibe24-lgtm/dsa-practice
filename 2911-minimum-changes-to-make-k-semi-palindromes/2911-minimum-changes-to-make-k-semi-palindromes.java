import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // 1. Precompute proper divisors for every possible substring length (2 to n)
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d <= len / 2; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }
        
        // 2. Precompute min changes to make substring s[i..j] a semi-palindrome
        int INF = 1_000_000;
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // Substring length must be >= 2
                int len = j - i + 1;
                int minChanges = INF;
                for (int d : divisors[len]) {
                    minChanges = Math.min(minChanges, getCost(chars, i, j, d));
                }
                cost[i][j] = minChanges;
            }
        }
        
        // 3. DP to partition into k substrings
        // dp[p][i] = min changes to partition prefix of length i into p semi-palindromes
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[p - 1][m] != INF) {
                        dp[p][i] = Math.min(dp[p][i], dp[p - 1][m] + cost[m][i - 1]);
                    }
                }
            }
        }
        
        return dp[k][n];
    }
    
    // Helper to calculate cost for substring s[i..j] with divisor d
    private int getCost(char[] chars, int i, int j, int d) {
        int changes = 0;
        for (int start = i; start < i + d; start++) {
            int left = start;
            // Last element in this residue group
            int right = j - (j - start) % d;
            while (left < right) {
                if (chars[left] != chars[right]) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}