class Solution {
    public int distinctSubseqII(String s) {

        long MOD = 1000000007;

        long[] end = new long[26];

        for(char ch : s.toCharArray()) {

            int idx = ch - 'a';

            long total = 0;

            for(long x : end) {
                total = (total + x) % MOD;
            }

            // subsequences ending with this character
            end[idx] = (total + 1) % MOD;
        }


        long ans = 0;

        for(long x : end) {
            ans = (ans + x) % MOD;
        }

        return (int)ans;
    }
}