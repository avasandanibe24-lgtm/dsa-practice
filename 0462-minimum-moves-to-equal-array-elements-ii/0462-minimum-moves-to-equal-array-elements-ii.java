class Solution {
    public int minMoves2(int[] nums) {

        Arrays.sort(nums);
        int x=nums.length/2;
        int mid=nums[x];

        int res=0;
        for (int i=0;i<nums.length;i++){
            res+=Math.abs(mid-nums[i]);
        }
        return res;

        
    }
}