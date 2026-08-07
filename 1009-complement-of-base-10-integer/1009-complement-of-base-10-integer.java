class Solution {
    public int bitwiseComplement(int n) {
       String s="";
       int num=0;
       int rem=0;
       if(n==0)
       return 1;

       while(n!=0)
       {
        rem=n%2;
        n=n/2;
        s=Integer.toString(rem)+s;
       }
       int l=s.length();
       char c;
       int ans=0;
       for(int i=0;i<l;i++)
       {
        c=s.charAt(i);
        if(c=='0')
        {
           ans=ans+(int)Math.pow(2, l-i-1);
        }
        
       }
       return ans;

        
    }
}