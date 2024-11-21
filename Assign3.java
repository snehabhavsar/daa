import java.util.Scanner;

public class Assign3 {
    final static int INF = Integer.MAX_VALUE; // Represents no direct connection

    public static void floydWarshall(int[][] graph, int numCities) {
        int[][] dist = new int[numCities][numCities];

        // Initialize the distance matrix
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Floyd-Warshall Algorithm
        for (int k = 0; k < numCities; k++) {
            for (int i = 0; i < numCities; i++) {
                for (int j = 0; j < numCities; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the result
        System.out.println("Minimum cost to connect offices:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of offices: ");
        int numCities = scanner.nextInt();

        int[][] graph = new int[numCities][numCities];

        System.out.println("Enter the cost matrix (use " + INF + " for no direct connection):");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        floydWarshall(graph, numCities);

        scanner.close();
    }
}
