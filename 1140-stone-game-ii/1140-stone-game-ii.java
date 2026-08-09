class Solution {

    int[][] memo;
    int[] prefix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        prefix = new int[n+1];

        for(int i=0;i<n;i++){
            prefix[i+1] = prefix[i] + piles[i];
        }

        memo = new int[n][n+1];

        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], -1);
        }

        return solve(0,1);
    }


    int solve(int i, int M){

        if(i >= n)
            return 0;


        if(memo[i][M] != -1)
            return memo[i][M];


        int remaining = prefix[n] - prefix[i];

        int best = 0;


        for(int x=1; x<=2*M && i+x<=n; x++){

            int opponent = solve(i+x, Math.max(M,x));

            best = Math.max(best,
                    remaining - opponent);
        }


        return memo[i][M] = best;
    }
}