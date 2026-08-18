class Solution {
    public int fib(int n) {
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        int ans=dyp(dp,n);
        return ans;
        
    }
    private int dyp(int[] dp,int n)
    {
        if(n<=1)
        return n;
        if(dp[n]!=-1)
        return dp[n];
        dp[n]=dyp(dp,n-1)+dyp(dp,n-2);
        return dp[n];
    }

}