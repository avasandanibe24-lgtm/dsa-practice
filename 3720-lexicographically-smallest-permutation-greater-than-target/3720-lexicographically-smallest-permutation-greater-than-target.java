class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        if (solve(0, target, freq, ans)) {
            return ans.toString();
        }

        return "";
    }


    private boolean solve(int index, String target, int[] freq, StringBuilder ans) {

        // all characters matched
        if(index == target.length()) {
            return false; // equal is not allowed
        }


        char ch = target.charAt(index);


        // Case 1: keep same character
        if(freq[ch - 'a'] > 0) {

            freq[ch - 'a']--;
            ans.append(ch);

            if(solve(index + 1, target, freq, ans)) {
                return true;
            }

            // backtrack
            ans.deleteCharAt(ans.length() - 1);
            freq[ch - 'a']++;
        }


        // Case 2: make this position bigger
        for(char c = (char)(ch + 1); c <= 'z'; c++) {

            if(freq[c - 'a'] > 0) {

                freq[c - 'a']--;
                ans.append(c);


                // remaining characters should be smallest possible
                for(int i = 0; i < 26; i++) {
                    while(freq[i] > 0) {
                        ans.append((char)(i + 'a'));
                        freq[i]--;
                    }
                }

                return true;
            }
        }


        return false;
    }
}