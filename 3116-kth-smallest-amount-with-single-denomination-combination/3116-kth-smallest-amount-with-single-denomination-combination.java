class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long)25 * k;

        while(low < high){

            long mid = low + (high - low) / 2;

            if(count(mid, coins) >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }


    private long count(long x, int[] coins){

        long ans = 0;
        int n = coins.length;


        for(int mask = 1; mask < (1 << n); mask++){

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;


            for(int i = 0; i < n; i++){

                if((mask & (1 << i)) != 0){

                    bits++;

                    lcm = getLCM(lcm, coins[i]);


                    // lcm became too large
                    if(lcm > x){
                        overflow = true;
                        break;
                    }
                }
            }


            if(overflow)
                continue;


            long add = x / lcm;


            if(bits % 2 == 1)
                ans += add;
            else
                ans -= add;
        }


        return ans;
    }



    private long getLCM(long a, long b){

        return a / gcd(a, b) * b;
    }



    private long gcd(long a, long b){

        while(b != 0){

            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}