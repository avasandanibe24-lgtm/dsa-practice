class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int product=1;
        int a=n;
        while(a!=0)
        {
            sum=sum+a%10;
            product=product*(a%10);
            a=a/10;
        }
        if(n%(sum+product)==0)
        return true;
        else
        return false;

        
        
    }
}