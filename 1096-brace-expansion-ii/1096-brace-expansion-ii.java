import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        return sorted;
    }

    private Set<String> parse(String expr) {
        List<Set<String>> stack = new ArrayList<>();
        Set<String> cur = new HashSet<>();
        cur.add(""); // start with empty string for concatenation
        int i = 0;

        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (Character.isLetter(c)) {
                cur = combine(cur, new HashSet<>(Arrays.asList(String.valueOf(c))));
            } else if (c == '{') {
                int j = i + 1, bal = 1;
                while (bal > 0) {
                    if (expr.charAt(j) == '{') bal++;
                    else if (expr.charAt(j) == '}') bal--;
                    j++;
                }
                Set<String> sub = parse(expr.substring(i + 1, j - 1));
                cur = combine(cur, sub);
                i = j - 1; // move past closing brace
            } else if (c == ',') {
                stack.add(cur);
                cur = new HashSet<>();
                cur.add(""); // reset for next union part
            }
            i++;
        }

        stack.add(cur);
        Set<String> res = new HashSet<>();
        for (Set<String> s : stack) res.addAll(s);
        return res;
    }

    private Set<String> combine(Set<String> set1, Set<String> set2) {
        Set<String> res = new HashSet<>();
        for (String a : set1) {
            for (String b : set2) {
                res.add(a + b);
            }
        }
        return res;
    }
}
