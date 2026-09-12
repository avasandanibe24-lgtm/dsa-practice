import java.util.*;

class Solution {
    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            }
            return Integer.compare(x[1], y[1]);
        });

        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = a[i][0];
        }

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (starts[mid] > a[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new ArrayList<>());

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];

                State nextState = dp[next[i]][k - 1];

                List<Integer> takeList = new ArrayList<>(nextState.indices);
                takeList.add(a[i][3]);
                Collections.sort(takeList);

                State take = new State(
                    a[i][2] + nextState.score,
                    takeList
                );

                dp[i][k] = better(take, skip);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private State better(State a, State b) {
        if (a.score > b.score) {
            return a;
        }

        if (a.score < b.score) {
            return b;
        }

        int size = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < size; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i) ? a : b;
            }
        }

        if (a.indices.size() <= b.indices.size()) {
            return a;
        }

        return b;
    }
}