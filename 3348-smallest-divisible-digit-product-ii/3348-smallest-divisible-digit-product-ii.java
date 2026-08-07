import java.util.HashMap;

class Solution {
    HashMap<Long, Integer> memo = new HashMap<>();

    // Utility for finding Greatest Common Divisor
    long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Memoized method to get the minimum number of digits required to achieve a multiple of `req`
    int getMinLen(long req) {
        if (req == 1) return 0;
        if (memo.containsKey(req)) return memo.get(req);

        int min = (int) 1e9; // act as infinity
        // Try all digits 2 through 9
        for (int d = 2; d <= 9; d++) {
            long g = gcd(req, d);
            if (g > 1) {
                min = Math.min(min, 1 + getMinLen(req / g));
            }
        }
        memo.put(req, min);
        return min;
    }

    // Greedily builds the lexicographically smallest zero-free string of `length` for target `req`
    String buildSmallest(int length, long req) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            for (int d = 1; d <= 9; d++) {
                long nextReq = req / gcd(req, d);
                // Can we satisfy the remaining factors in the trailing indices?
                if (getMinLen(nextReq) <= length - i) {
                    sb.append(d);
                    req = nextReq;
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public String smallestNumber(String num, long t) {
        // Step 1: Check prime factors of t. Must be bounded to 2, 3, 5, 7.
        long tempT = t;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (tempT % p == 0) {
                tempT /= p;
            }
        }
        if (tempT > 1) return "-1"; // Unreachable using digits 1-9

        int n = num.length();
        long[] req = new long[n + 1];
        req[0] = t;
        
        // Track valid exact prefix bounds before hitting a zero 
        int maxL = n;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '0') {
                maxL = i;
                break;
            }
            req[i + 1] = req[i] / gcd(req[i], c - '0');
        }

        // Step 2: Traverse downwards to find the longest exact matching prefix
        for (int L = maxL; L >= 0; L--) {
            // Can we match the full string perfectly?
            if (L == n) {
                if (req[n] == 1) return num;
                continue;
            }

            char c = num.charAt(L);
            int startD = (c == '0') ? 1 : (c - '0' + 1);
            
            // Branch to a larger digit & greedily complete suffix
            for (int d = startD; d <= 9; d++) {
                long nextReq = req[L] / gcd(req[L], d);
                if (getMinLen(nextReq) <= n - 1 - L) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, L));
                    sb.append(d);
                    sb.append(buildSmallest(n - 1 - L, nextReq));
                    return sb.toString();
                }
            }
        }

        // Step 3: If no valid combination bounds inside length 'n', expand string size
        int len = Math.max(n + 1, getMinLen(t));
        return buildSmallest(len, t);
    }
}