class Solution {
    public int rob(int[] nums) {

        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        int i=n-1;
       int ans=solve(dp,i,nums);
        return ans;

        
    }
    public int solve(int[] dp,int i,int []nums)
    {
        if(i<0) return 0;
        if(dp[i]!=-1) return dp[i];
        if (i == 0) return nums[0];
        int pick=nums[i]+solve(dp,i-2,nums);
        int notpick=0+solve(dp,i-1,nums);
        dp[i]=Math.max(pick,notpick);
        return dp[i];

    }

}