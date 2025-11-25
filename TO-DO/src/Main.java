import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskRepository repo = new TaskRepository();
        TaskService service = new TaskService(repo);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // --- תפריט ---
            System.out.println("\n--- תפריט משימות ---");
            System.out.println("1. Add task");
            System.out.println("2. Update task");
            System.out.println("3. Delete task");
            System.out.println("4. Mark task as DONE");
            System.out.println("5. Search tasks");
            System.out.println("6. List all tasks");
            System.out.println("7. List tasks sorted by status");
            System.out.println("0. Exit");
            System.out.print("בחר פעולה: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> { // Add task
                    int id = repo.getNextId(); // אוטומטי

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter status (NEW, STARTED, DONE): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());

                    Task task = new Task(id, title, desc, status);
                    repo.add(task);
                    System.out.println("Task added successfully.");
                }

                case "2" -> { // Update task
                    System.out.print("Enter task id to update: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Task task = repo.getById(id);
                    if (task == null) {
                        System.out.println("Task not found.");
                        break;
                    }
                    System.out.print("Enter new title (" + task.getTitle() + "): ");
                    String title = scanner.nextLine();
                    if (!title.isEmpty()) task.setTitle(title);

                    System.out.print("Enter new description (" + task.getDescription() + "): ");
                    String desc = scanner.nextLine();
                    if (!desc.isEmpty()) task.setDescription(desc);

                    System.out.print("Enter new status (" + task.getStatus() + "): ");
                    String st = scanner.nextLine();
                    if (!st.isEmpty()) task.setStatus(TaskStatus.valueOf(st.toUpperCase()));

                    repo.update(task);
                    System.out.println("Task updated successfully.");
                }

                case "3" -> { // Delete task
                    System.out.print("Enter task id to delete: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    repo.delete(id);
                    System.out.println("Task deleted.");
                }

                case "4" -> { // Mark as DONE
                    System.out.print("Enter task id to mark as DONE: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    service.markAsDone(id);
                    System.out.println("Task marked as DONE.");
                }

                case "5" -> { // Search
                    System.out.print("Enter text to search: ");
                    String text = scanner.nextLine();
                    List<Task> results = service.search(text);
                    if (results.isEmpty()) System.out.println("No tasks found.");
                    else results.forEach(System.out::println);
                }

                case "6" -> { // List all
                    List<Task> all = repo.listAll();
                    if (all.isEmpty()) System.out.println("No tasks found.");
                    else all.forEach(System.out::println);
                }

                case "7" -> { // List sorted
                    List<Task> sorted = service.listSortedByStatus();
                    if (sorted.isEmpty()) System.out.println("No tasks found.");
                    else sorted.forEach(System.out::println);
                }

                case "0" -> { // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

