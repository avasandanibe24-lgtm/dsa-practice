class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        boolean[] alice = new boolean[366];

        int startAlice = getDay(arriveAlice);
        int endAlice = getDay(leaveAlice);

        for (int i = startAlice; i <= endAlice; i++) {
            alice[i] = true;
        }

        int startBob = getDay(arriveBob);
        int endBob = getDay(leaveBob);

        int count = 0;

        for (int i = startBob; i <= endBob; i++) {
            if (alice[i]) {
                count++;
            }
        }

        return count;
    }

    private int getDay(String date) {
        int[] monthDays = {31, 28, 31, 30, 31, 30,
                           31, 31, 30, 31, 30, 31};

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += monthDays[i];
        }

        return total;
    }
}