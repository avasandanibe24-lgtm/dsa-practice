class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n=nums.length;
        int min = Integer.MAX_VALUE; 
        int[] minel=new int[n];
         
        for(int i=n-1;i>=0;i--)
        {
            min = Math.min(min, nums[i]);  
            minel[i]=min;

        }

        int  max=Integer.MIN_VALUE;
        
        for(int j=0;j<n;j++)
        {
            max = Math.max(max, nums[j]);  
         if(max-minel[j] <=k)
            return j;
         

        }

        return -1;
    }
}