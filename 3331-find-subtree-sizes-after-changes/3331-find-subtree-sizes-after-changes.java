
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        change(0, tree, s, last, newParent);

        List<Integer>[] newTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        count(0, newTree, ans);

        return ans;
    }

    private void change(int node, List<Integer>[] tree,
                        String s, int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';
        int prev = last[ch];

        if (prev != -1) {
            newParent[node] = prev;
        }

        last[ch] = node;

        for (int child : tree[node]) {
            change(child, tree, s, last, newParent);
        }

        last[ch] = prev;
    }

    private int count(int node, List<Integer>[] tree, int[] ans) {
        int size = 1;

        for (int child : tree[node]) {
            size += count(child, tree, ans);
        }

        ans[node] = size;
        return size;
    }
}
