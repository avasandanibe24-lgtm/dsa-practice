class Solution {
    public int longestMonotonicSubarray(int[] nums) {

        int n = nums.length;

        int count = 1;
        int incmax = 1;
        int decmax = 1;

     
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                count++;
            } else {
                incmax = Math.max(incmax, count);
                count = 1;
            }
        }
        incmax = Math.max(incmax, count);

        count = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
            } else {
                decmax = Math.max(decmax, count);
                count = 1;
            }
        }
        decmax = Math.max(decmax, count);

        return Math.max(incmax, decmax);
    }
}