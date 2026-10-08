class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                if (balance > 0) {   // skip the very first '(' of each primitive
                    result.append(ch);
                }
                balance++;
            } else { // ch == ')'
                balance--;
                if (balance > 0) {   // skip the very last ')' of each primitive
                    result.append(ch);
                }
            }
        }
        
        return result.toString();
    }
}
