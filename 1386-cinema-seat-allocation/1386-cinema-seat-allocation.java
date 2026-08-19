class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // store reserved seats row wise
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.computeIfAbsent(row, k -> new HashSet<>()).add(col);
        }


        long ans = (long)(n - map.size()) * 2;


        // check rows having reservations
        for (Set<Integer> seats : map.values()) {

            int groups = 0;

            // seats 2,3,4,5
            if (!seats.contains(2) &&
                !seats.contains(3) &&
                !seats.contains(4) &&
                !seats.contains(5)) {

                groups++;
            }


            // seats 6,7,8,9
            if (!seats.contains(6) &&
                !seats.contains(7) &&
                !seats.contains(8) &&
                !seats.contains(9)) {

                groups++;
            }


            // if we couldn't place two groups,
            // try middle block
            if (groups == 0 &&
                !seats.contains(4) &&
                !seats.contains(5) &&
                !seats.contains(6) &&
                !seats.contains(7)) {

                groups++;
            }


            ans += groups;
        }


        return (int)ans;
    }
}