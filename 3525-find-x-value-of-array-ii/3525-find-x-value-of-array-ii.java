class Solution {

    int k;
    int[][] tree;
    int[] prod;

    class Node {
        int total;
        long[] count;

        Node(int total, long[] count) {
            this.total = total;
            this.count = count;
        }
    }

    Node[] seg;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        seg = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];


            update(1, 0, n - 1, idx, val);


            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = (int)res.count[x];
        }

        return ans;
    }


    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            long[] cnt = new long[k];

            int rem = nums[l] % k;

            cnt[rem] = 1;

            seg[node] = new Node(rem, cnt);

            return;
        }


        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        seg[node] = merge(seg[node * 2], seg[node * 2 + 1]);
    }



    Node merge(Node a, Node b) {

        long[] cnt = new long[k];


        // prefixes inside left
        for(int i = 0; i < k; i++) {
            cnt[i] += a.count[i];
        }


        // left whole segment + prefixes of right
        for(int i = 0; i < k; i++) {

            int rem = (a.total * i) % k;

            cnt[rem] += b.count[i];
        }


        int total = (a.total * b.total) % k;


        return new Node(total, cnt);
    }



    void update(int node, int l, int r, int idx, int val) {

        if(l == r) {

            long[] cnt = new long[k];

            int rem = val % k;

            cnt[rem] = 1;

            seg[node] = new Node(rem,cnt);

            return;
        }


        int mid=(l+r)/2;


        if(idx<=mid)
            update(node*2,l,mid,idx,val);
        else
            update(node*2+1,mid+1,r,idx,val);


        seg[node]=merge(seg[node*2],seg[node*2+1]);
    }



    Node query(int node,int l,int r,int ql,int qr){

        if(qr<l || r<ql)
            return null;


        if(ql<=l && r<=qr)
            return seg[node];


        int mid=(l+r)/2;


        Node left=query(node*2,l,mid,ql,qr);
        Node right=query(node*2+1,mid+1,r,ql,qr);


        if(left==null)
            return right;

        if(right==null)
            return left;


        return merge(left,right);
    }
}