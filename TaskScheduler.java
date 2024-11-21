import java.util.Scanner;    // Import Scanner class for taking user input

// Define Task class to store properties of each task
class Task {
    int id;         // To store task number/ID
    int deadline;   // To store task deadline
    int profit;     // To store task profit
    
    // Constructor to initialize Task object
    Task(int id, int deadline, int profit) {
        this.id = id;           // Assign task ID
        this.deadline = deadline; // Assign deadline
        this.profit = profit;     // Assign profit
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Create Scanner object for input
        
        // Get number of tasks from user
        System.out.print("Enter number of tasks: ");
        int n = scan.nextInt();  // Store number of tasks in n
        
        // Create array to store Task objects
        Task[] tasks = new Task[n];
        
        // Input loop - Get details for each task
        for(int i = 0; i < n; i++) {
            System.out.println("\nFor Task " + (i+1) + ":");
            System.out.print("Enter deadline: ");
            int deadline = scan.nextInt();  // Get deadline
            System.out.print("Enter profit: ");
            int profit = scan.nextInt();    // Get profit
            
            // Create new Task object and store in array
            tasks[i] = new Task(i+1, deadline, profit);
        }
        
        /*
                for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-1-i; j++) {
                if(tasks[j].profit < tasks[j+1].profit) {
                    // Swap tasks
                    Task temp = tasks[j];
                    tasks[j] = tasks[j+1];
                    tasks[j+1] = temp;
                }
            }
        }
        
            */
        java.util.Arrays.sort(tasks, (a, b) -> b.profit - a.profit);
        
        // Find maximum deadline among all tasks
        int maxDeadline = tasks[0].deadline;  // Start with first task's deadline
        for(int i = 1; i < n; i++) {
            if(tasks[i].deadline > maxDeadline) {
                maxDeadline = tasks[i].deadline;  // Update if bigger deadline found
            }
        }
        
        // Create array for final schedule
        int[] result = new int[maxDeadline];
        // Initialize all slots as empty (-1)
        for(int i = 0; i < maxDeadline; i++) {
            result[i] = -1;
        }
        
        // Schedule tasks one by one
        int totalProfit = 0;  // To keep track of total profit
        for(int i = 0; i < n; i++) {  // For each task
            // Try to place task as late as possible but before its deadline
            for(int j = tasks[i].deadline - 1; j >= 0; j--) {
                if(result[j] == -1) {  // If slot is empty
                    result[j] = tasks[i].id;  // Assign task to this slot
                    totalProfit += tasks[i].profit;  // Add profit
                    break;  // Move to next task
                }
            }
        }
        
        // Print final schedule
        System.out.println("\nSchedule:");
        System.out.println("Time Slot | Task");
        System.out.println("----------------");
        // For each time slot
        for(int i = 0; i < maxDeadline; i++) {
            System.out.print("    " + (i+1) + "     |");  // Print time slot
            if(result[i] != -1) {  // If slot has a task
                System.out.println("  Task " + result[i]);  // Print task number
            } else {  // If slot is empty
                System.out.println("  Empty");
            }
        }
        
        // Print total profit
        System.out.println("\nTotal Profit: " + totalProfit);
        
        scan.close();  // Close Scanner object
    }
}