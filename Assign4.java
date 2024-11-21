
import java.util.Scanner;

public class Assign4{
    public static void main(String args[]){
        int [][] graph = {
            {0,6,7,1000},
            {8,0,1000,5},
            {5,7,0,6},
            {1000, 1000, 2, 0}
        };
        Scanner sc= new Scanner(System.in);

        int n = 4;
        int src =0;

        /*
        System.out.println("Enter the no of nodes: ");
        int n= sc.nextInt(); 

        int[][] graph= new int[n][n];
        for (int i = 0; i < 10; i++) {
            for(int j=0; j<n; j++){
                graph[i][j]= sc.nextInt();
            }
        }
        System.out.println("Enter the src node: ");
        int src= sc.nextInt();*/

        dj(graph,n, src);

    }

    public static void dj(int[][] graph, int n, int src){
        int[] dist = new int[n];
        int[] visited = new int[n];

        for(int i=0; i<n; i++){
            dist[i] = 1000;
            visited[i] = 0;
        }

        dist[src] =0;

        for(int it =0; it <n; it++){
            int u = -1;
            int min = 1000;

            for(int i=0; i<n; i++){
                if(visited[i] == 0 && dist[i]<min){
                    u=i;
                    min = dist[i];
                }
            }

            visited[u] =1;

            for(int v =0; v<n; v++){
                if(visited[v] ==0 && v!=u && dist[v]> dist[u] + graph[u][v]){
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        for(int i=0; i<n; i++){
            System.out.println("Distance between scr and " + i + " is " + dist[i]);
        }

    }
}