import java.util.*;

class Solution {

    // This class stores one BFS state
    static class State {
        int row;
        int col;
        int energy;
        int mask;
        int moves;


        State(int row, int col, int energy, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }


    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();


        int startRow = 0;
        int startCol = 0;


        /*
            Stores litter position -> index

            Example:

            L L
            0 1

            position of first L gets index 0
            position of second L gets index 1

        */
        Map<String, Integer> litterMap = new HashMap<>();

        int litterCount = 0;



        // Find starting point and litter positions
        for(int i = 0; i < m; i++) {

            for(int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);


                if(ch == 'S') {
                    startRow = i;
                    startCol = j;
                }


                if(ch == 'L') {

                    litterMap.put(
                        i + "," + j,
                        litterCount
                    );

                    litterCount++;
                }
            }
        }



        /*
            If there are k litters:

            k = 3

            Target mask:

            111

            Binary:

            (1<<3)-1

            = 1000-1

            = 111

        */

        int targetMask = (1 << litterCount) - 1;



        Queue<State> queue = new LinkedList<>();


        // Initially nothing collected
        queue.offer(
            new State(
                startRow,
                startCol,
                energy,
                0,
                0
            )
        );



        /*
            visited[row][col][energy][mask]

            We store whether we have already visited
            this exact situation.

        */

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];


        visited[startRow][startCol][energy][0] = true;



        int[][] directions = {

            {1,0},   // down
            {-1,0},  // up
            {0,1},   // right
            {0,-1}   // left

        };



        while(!queue.isEmpty()) {


            State current = queue.poll();



            // All litter collected
            if(current.mask == targetMask) {
                return current.moves;
            }



            // Try all 4 directions

            for(int[] dir : directions) {


                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];



                // Outside grid
                if(newRow < 0 ||
                   newCol < 0 ||
                   newRow >= m ||
                   newCol >= n) {

                    continue;
                }



                char cell = classroom[newRow].charAt(newCol);



                // Wall
                if(cell == 'X') {
                    continue;
                }



                // Moving costs one energy
                int newEnergy = current.energy - 1;



                /*
                    If energy becomes negative,
                    we cannot move.

                    Example:

                    energy = 0

                    Move needs 1 energy

                    impossible

                */

                if(newEnergy < 0) {
                    continue;
                }



                int newMask = current.mask;



                // If we found litter
                if(cell == 'L') {


                    int index =
                    litterMap.get(
                        newRow + "," + newCol
                    );


                    /*
                        Add this litter to mask

                        Example:

                        old mask:

                        001

                        collect L2:

                        010

                        Result:

                        011

                    */

                    newMask |= (1 << index);

                }



                // Recharge energy
                if(cell == 'R') {

                    newEnergy = energy;

                }




                // If this state is new
                if(!visited[newRow][newCol][newEnergy][newMask]) {


                    visited[newRow][newCol][newEnergy][newMask] = true;



                    queue.offer(
                        new State(
                            newRow,
                            newCol,
                            newEnergy,
                            newMask,
                            current.moves + 1
                        )
                    );
                }

            }
        }


        // No possible path
        return -1;
    }
}