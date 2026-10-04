class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                low++;
                high++;
            } else if (ch == ')') {
                low--;
                high--;
            } else { // '*'
                low--;   // treat '*' as ')'
                high++;  // treat '*' as '('
            }
            
            if (high < 0) return false; // too many ')'
            if (low < 0) low = 0;       // clamp low
        }
        return low == 0;
    }
}
