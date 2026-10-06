class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;    // unmatched '('
        int insertions = 0; // needed insertions

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else { // ch == ')'
                if (balance > 0) {
                    balance--; // match with an earlier '('
                } else {
                    insertions++; // need to insert '('
                }
            }
        }

        return insertions + balance;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minAddToMakeValid(")))")); // Output: 3
        System.out.println(sol.minAddToMakeValid("(((")); // Output: 3
        System.out.println(sol.minAddToMakeValid("())")); // Output: 1
    }
}
