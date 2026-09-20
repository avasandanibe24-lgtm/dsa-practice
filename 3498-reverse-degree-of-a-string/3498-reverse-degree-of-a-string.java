class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            // Normal alphabet position: a = 1, b = 2, ..., z = 26
            int normalValue = s.charAt(i) - 'a' + 1;

            // Reverse alphabet position: a = 26, b = 25, ..., z = 1
            int reverseValue = 27 - normalValue;

            // Position in string is i + 1 because indexing starts from 0
            sum += reverseValue * (i + 1);
        }

        return sum;
    }
}