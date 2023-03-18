package tst;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class TodoManager {

    private static List<User> users;
    private static List<Task> tasks;
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public TodoManager() {
        this.users = new ArrayList<>();
        this.tasks = new ArrayList<>();
        
        currentUser = null;
    }
    
    public static void main(String [] args) {
    	TodoManager td = new TodoManager();
    	td.run();
    }

    public static void run() {
        System.out.println("Welcome to Todo Manager!");
        
        // Login or Register
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume new line character

            switch (choice) {
                case 1:
                    loggedIn = login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

        // Main Menu
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Search Task");
            System.out.println("5. View All Tasks");
            System.out.println("6. View Completed Tasks");
            System.out.println("7. View Incomplete Tasks");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume new line character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    updateTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    searchTask();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 6:
                    viewCompletedTasks();
                    break;
                case 7:
                    viewIncompleteTasks();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static boolean login() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Welcome, " + currentUser.getUsername() + "!");
                return true;
            }
        }

        System.out.println("Invalid email or password!");
        return false;
    }

    private static void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User newUser = new User(username, email, password);
        users.add(newUser);
        currentUser = newUser;
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
    }

    private static void addTask() {
    	System.out.println("Enter task id:");
        int taskId = scanner.nextInt();
    	scanner.nextLine();
        System.out.println("Enter task title:");
        String title = scanner.nextLine();
        System.out.println("Enter task text:");
        String text = scanner.nextLine();

        Task newTask = new Task(taskId,title, text, currentUser.getEmail());
        tasks.add(newTask);
        System.out.println("Task added!");
    }

       private static void updateTask() {
        System.out.println("Enter task ID:");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // consume new line character
        Task taskToUpdate = findTaskById(taskId);
        if (taskToUpdate == null) {
            System.out.println("Task not found!");
            return;
        }

        if (!taskToUpdate.getAssignedTo().equals(currentUser.getEmail())) {
            System.out.println("You can only update tasks assigned to you!");
            return;
        }

        System.out.println("Enter new task title:");
        String newTitle = scanner.nextLine();
        System.out.println("Enter new task text:");
        String newText = scanner.nextLine();
        System.out.println("Task completed?");
        System.out.println("Y/N");
        char ch = scanner.next().charAt(0);
        if(ch=='Y' || ch=='y'){
            taskToUpdate.setTaskCompleted(true);
        }
        taskToUpdate.setTaskTitle(newTitle);
        taskToUpdate.setTaskText(newText);
        System.out.println("Task updated!");
    }

    private static void deleteTask() {
        System.out.println("Enter task ID:");
        int taskId = scanner.nextInt();
        scanner.nextLine(); // consume new line character

        Task taskToDelete = findTaskById(taskId);
        if (taskToDelete == null) {
            System.out.println("Task not found!");
            return;
        }

        if (!taskToDelete.getAssignedTo().equals(currentUser.getEmail())) {
            System.out.println("You can only delete tasks assigned to you!");
            return;
        }

        tasks.remove(taskToDelete);
        System.out.println("Task deleted!");
    }

    private static void searchTask() {
        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine();

        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssignedTo().equals(currentUser.getEmail()) && (task.getTaskTitle().contains(searchTerm) || task.getTaskText().contains(searchTerm))) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found!");
            return;
        }

        System.out.println("Matching tasks:");
        for (Task task : matchingTasks) {
            System.out.println(task.toString());
        }
    }

    private static void viewAllTasks() {
        System.out.println("All tasks:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    private static void viewCompletedTasks() {
        System.out.println("Completed tasks:");
        for (Task task : tasks) {
            if (task.getAssignedTo().equals(currentUser.getEmail()) && task.isTaskCompleted()) {
                System.out.println(task.toString());
            }
        }
    }

    private static void viewIncompleteTasks() {
        System.out.println("Incomplete tasks:");
        for (Task task : tasks) {
            if (task.getAssignedTo().equals(currentUser.getEmail()) && !task.isTaskCompleted()) {
                System.out.println(task.toString());
            }
        }
    }

    private static Task findTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }
}