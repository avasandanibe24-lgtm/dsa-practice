class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Check if nums is I_k
        int k = nums[0];
        boolean inc = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) {
                inc = false;
                break;
            }
        }

        if (inc) {
            return Math.min((n - k) % n, k + 2);
        }

        // Check if nums is J_k
        k = (n - 1 - nums[0] + n) % n;
        boolean dec = true;

        for (int i = 0; i < n; i++) {
            int expected = n - 1 - ((i + k) % n);
            if (nums[i] != expected) {
                dec = false;
                break;
            }
        }

        if (dec) {
            return Math.min((n - k) % n + 1, k + 1);
        }

        return -1;
    }
}