import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] left = new int[26];
        int[] right = new int[26];

        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            left[c] = Math.min(left[c], i);
            right[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Create valid intervals
        for (int i = 0; i < 26; i++) {

            if (right[i] == -1)
                continue;

            int start = left[i];
            int end = right[i];

            boolean valid = true;

            for (int j = start; j <= end; j++) {

                int c = s.charAt(j) - 'a';

                // Character appears before this interval
                if (left[c] < start) {
                    valid = false;
                    break;
                }

                // Expand interval if needed
                end = Math.max(end, right[c]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        // Greedy selection
        for (int[] interval : intervals) {

            if (interval[0] > prevEnd) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }

        return ans;
    }
}