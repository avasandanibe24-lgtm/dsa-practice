import java.util.*;

class Solution {
    public boolean uniformArray(int[] nums1) {
        Arrays.sort(nums1);

        int odd = 0;
        int even = 0;

        boolean canMakeOdd = true;
        boolean canMakeEven = true;

        for (int x : nums1) {
            boolean oddPossible;
            boolean evenPossible;

            if (x % 2 == 0) {
                // x itself is even
                evenPossible = true;

                // Need a smaller odd number to make x - y odd
                oddPossible = odd > 0;
            } else {
                // x itself is odd
                oddPossible = true;

                // Need a smaller odd number to make x - y even
                evenPossible = odd > 0;
            }

            canMakeOdd &= oddPossible;
            canMakeEven &= evenPossible;

            // Add current number for future elements
            if (x % 2 == 0)
                even++;
            else
                odd++;
        }

        return canMakeOdd || canMakeEven;
    }
}