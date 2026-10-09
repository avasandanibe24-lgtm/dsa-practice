class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;
        int i = 0;
        
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
                i++;
            } else { // ch == ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        insertions++; // need '(' before
                    }
                    i += 2;
                } else {
                    if (open > 0) {
                        open--;
                        insertions++; // need one more ')'
                    } else {
                        insertions += 2; // need '(' before and one more ')'
                    }
                    i++;
                }
            }
        }
        
        insertions += open * 2; // each unmatched '(' needs two ')'
        return insertions;
    }
}
