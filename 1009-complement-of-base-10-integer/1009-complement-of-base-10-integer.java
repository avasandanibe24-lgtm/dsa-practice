class Solution {
    public int bitwiseComplement(int n) {
        int i=0;
        if (n==0) {
            return 1;
        }
        int current = n;
        while (current > 0) {
            current/=2;
            i++;
        }
        return ((int) Math.pow(2,i)-1-n);

    }
}