class Solution {
    public String[] createGrid(int m, int n) {
        String[] sarr = new String[m];
        for(int i=0;i<m;i++) {
            if(i==0) {
                String s = "";
                StringBuilder sb0 = new StringBuilder(s);
                for(int j=0;j<n;j++){
                    sb0.append(".");
                }
                sarr[i] = sb0.toString();
            }
            else {
                String s = "";
                StringBuilder sb1 = new StringBuilder(s);
                for(int j=0;j<n;j++){
                    if(j == n-1) sb1.append(".");
                    else sb1.append("#");
                    
                }
                sarr[i] = sb1.toString();
            }
        }

        return sarr;
    }
}