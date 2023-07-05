import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;

public class NotepadFileHandler {
    private String filePath;

    public NotepadFileHandler(String fileName) {
        this.filePath = fileName + ".txt";
    }

    public void writeTasksToFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Clear the contents of the file
            writer.write("");
            writer.flush();

            // Write the updated task list
            tasks.forEach(task -> {
                try {
                    writer.write(task.getName() + ": " + task.getDescription());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> readTasksFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if (parts.length == 2) {
                    String name = parts[0];
                    String description = parts[1];
                    Task task = new Task(name, description);
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}


