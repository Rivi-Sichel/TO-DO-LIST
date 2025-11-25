import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final String filePath = "data/tasks.json";

    public List<Task> loadFromTheFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            json = json.trim();
            if (json.length() <= 2) return tasks; // קובץ ריק או []

            // הסרת הסוגריים החיצוניים
            json = json.substring(1, json.length() - 1).trim();

            // פיצול כל אובייקט Task
            String[] objects = json.split("\\},\\s*\\{");
            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "").trim();
                String[] fields = obj.split(",");
                int id = 0;
                String title = "";
                String description = "";
                TaskStatus status = TaskStatus.NEW;

                for (String field : fields) {
                    String[] kv = field.split(":");
                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();

                    switch (key) {
                        case "id" -> id = Integer.parseInt(value);
                        case "title" -> title = value;
                        case "description" -> description = value;
                        case "status" -> status = TaskStatus.valueOf(value);
                    }
                }
                tasks.add(new Task(id, title, description, status));
            }

        } catch (IOException e) {
            System.out.println("קובץ tasks.json לא נמצא, מתחילים עם רשימה ריקה.");
        }
        return tasks;
    }

    private void saveToTheFile(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("  {")
                    .append("\"id\":").append(t.getId()).append(",")
                    .append("\"title\":\"").append(t.getTitle()).append("\",")
                    .append("\"description\":\"").append(t.getDescription()).append("\",")
                    .append("\"status\":\"").append(t.getStatus()).append("\"")
                    .append("}");
            if (i < tasks.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");

        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("שגיאה בשמירה לקובץ: " + e.getMessage());
        }
    }

    public void add(Task t) {
        List<Task> tasks = loadFromTheFile();
        tasks.add(t);
        saveToTheFile(tasks);
    }

    public void update(Task t) {
        List<Task> tasks = loadFromTheFile();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == t.getId()) {
                tasks.set(i, t);
                break;
            }
        }
        saveToTheFile(tasks);
    }

    public void delete(int id) {
        List<Task> tasks = loadFromTheFile();
        tasks.removeIf(task -> task.getId() == id);
        saveToTheFile(tasks);
    }

    public Task getById(int id) {
        List<Task> tasks = loadFromTheFile();
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public List<Task> listAll() {
        return loadFromTheFile();
    }

    public int getNextId() {
        List<Task> tasks = loadFromTheFile();
        int maxId = 0;
        for (Task t : tasks) {
            if (t.getId() > maxId) maxId = t.getId();
        }
        return maxId + 1;
    }

}
