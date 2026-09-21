class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        long[] prev = new long[k];

        for (int num : nums) {

            long[] curr = new long[k];

            int val = num % k;

            // Subarray containing only this element
            curr[val]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                if (prev[r] > 0) {
                    int newRemainder = (r * val) % k;
                    curr[newRemainder] += prev[r];
                }
            }

            // Add current ending subarrays to answer
            for (int r = 0; r < k; r++) {
                result[r] += curr[r];
            }

            prev = curr;
        }

        return result;
    }
}