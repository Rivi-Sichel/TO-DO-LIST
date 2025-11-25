import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // סימון משימה כ-DONE
    public void markAsDone(int taskId) {
        Task task = repository.getById(taskId);
        if (task != null) {
            task.setStatus(TaskStatus.DONE);
            repository.update(task);
        }
    }

    // חיפוש משימות לפי טקסט בכותרת או בתיאור
    public List<Task> search(String text) {
        List<Task> result = new ArrayList<>();
        for (Task t : repository.listAll()) {
            if (t.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    t.getDescription().toLowerCase().contains(text.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    // רשימת כל המשימות ממוינת לפי סטטוס: NEW → STARTED → DONE
    public List<Task> listSortedByStatus() {
        List<Task> tasks = new ArrayList<>(repository.listAll());
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
