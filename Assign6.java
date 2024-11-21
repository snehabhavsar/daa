import java.util.Scanner;

class Assign6 {
    static int minCost = Integer.MAX_VALUE; // Use Integer.MAX_VALUE for clarity
    static int[] best;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input the number of students/clubs
        System.out.print("Enter the number of students and clubs (N): ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];

        // Input the cost matrix
        System.out.println("Enter the cost matrix (NxN):");
        System.out.println("For each row, enter " + n + " space-separated integers representing the cost of assigning a student to clubs.");
        for (int i = 0; i < n; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }   


        // Initialize arrays for assignment and tracking the best solution
        int[] assigned = new int[n];
        best = new int[n];

        for (int i = 0; i < n; i++) {
            assigned[i] = -1; // No club assigned initially
            best[i] = -1;     // Best assignment is unknown
        }

        // Solve the problem using branch and bound
        bb(cost, n, assigned, 0, 0);

        // Output the results
        System.out.println("\nOptimal club assignment:");
        for (int i = 0; i < n; i++) {
            System.out.println("Club " + i + " is assigned to Student " + best[i]);
        }
        System.out.println("Minimum cost is " + minCost);

        sc.close();
    }

    public static void bb(int[][] cost, int n, int[] assigned, int student, int currentCost) {
        if (student == n) { // All students have been assigned
            if (currentCost < minCost) { // Check if the current assignment is better
                minCost = currentCost;
                System.arraycopy(assigned, 0, best, 0, n); // Save the best assignment
            }
            return; // Backtrack
        }

        for (int i = 0; i < n; i++) {
            if (assigned[i] == -1) { // If the club is not yet assigned
                assigned[i] = student; // Assign the student to the club
                currentCost += cost[student][i]; // Add the cost

                // Proceed only if the cost is less than the current minimum
                if (currentCost < minCost) {
                    bb(cost, n, assigned, student + 1, currentCost);
                }

                // Backtrack
                currentCost -= cost[student][i];
                assigned[i] = -1;
            }
        }
    }
}
