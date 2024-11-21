import java.util.Scanner;

public class Assign5 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input size of the chessboard
        System.out.print("Enter the size of the chessboard (N): ");
        int n = sc.nextInt();

        // Input starting position
        System.out.print("Enter the starting position (row and column, 0-based index): ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        int[][] board = new int[n][n];

        // Initialize the board with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }
        board[x][y] = 0; // Starting position

        // Solve the Knight's Tour
        if (solve(board, x, y, n, 1)) {
            // Print the solution
            System.out.println("Knight's tour solution:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Knight's tour is not possible from the given starting position.");
        }

        sc.close();
    }

    public static boolean solve(int[][] board, int x, int y, int n, int count) {
        // All squares are visited
        if (count == (n * n)) {
            return true;
        }

        // Knight's possible moves
        int[] move_x = {1, -1, 2, -2, 1, -1, 2, -2};
        int[] move_y = {2, 2, 1, 1, -2, -2, -1, -1};

        // Try all next moves
        for (int i = 0; i < 8; i++) {
            int nx = x + move_x[i];
            int ny = y + move_y[i];

            if (isSafe(board, nx, ny, n)) {
                board[nx][ny] = count; // Make the move
                if (solve(board, nx, ny, n, count + 1)) {
                    return true; // If successful, return true
                } else {
                    board[nx][ny] = -1; // Backtrack
                }
            }
        }
        return false; // No solution found
    }

    public static boolean isSafe(int[][] board, int i, int j, int n) {
        // Check if the position is within bounds and not visited
        return i >= 0 && i < n && j >= 0 && j < n && board[i][j] == -1;
    }
}
