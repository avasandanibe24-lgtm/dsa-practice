class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;

        for(int n : nums) {
            xor ^= n;
        }

        // whole array works
        if(xor != 0) {
            return nums.length;
        }

        // find first element that can change xor
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                return nums.length - 1;
            }
        }

        return 0;
    }
}