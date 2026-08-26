class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        String ans = "";

        int l = 0;
        int count = 0;

        for (int r = 0; r < n; r++) {

            if (s.charAt(r) == '1') {
                count++;
            }

            while (count == k) {

                String sub = s.substring(l, r + 1);

                if (ans.equals("")) {
                    ans = sub;
                }
                else if (sub.length() < ans.length()) {
                    ans = sub;
                }
                else if (sub.length() == ans.length()
                         && sub.compareTo(ans) < 0) {
                    ans = sub;
                }

                if (s.charAt(l) == '1') {
                    count--;
                }

                l++;
            }
        }

        return ans;
    }
}