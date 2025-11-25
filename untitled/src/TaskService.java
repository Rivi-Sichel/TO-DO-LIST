import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    //Creates a new instance of TaskService using the provided task repository.
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    //change the status of the task to be marks
    //get id's task and change the status
    public void markAsDone(int taskId) {
        Task task = repository.getById(taskId);
        if (task != null) {
            task.setStatus(TaskStatus.DONE);
            repository.update(task);
        }
    }

    //sort the tasks by text
    //get text - the word to search
    //return the result of the search
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

    //sort the tasks by status
    //return all the tasks by status
    public List<Task> listSortedByStatus() {
        List<Task> tasks = new ArrayList<>(repository.listAll());
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
