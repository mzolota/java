import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class Main extends Application {
    private TaskManager taskManager;
    private ListView<Task> taskListView;
    private TextField nameField;
    private TextField descriptionField;

    private NotepadFileHandler fileHandler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager();

        // Create UI elements
        taskListView = new ListView<>();
        nameField = new TextField();
        descriptionField = new TextField();
        Button addButton = new Button("Add Task");
        Button completeButton = new Button("Mark Completed");
        Button deleteButton = new Button("Delete task");
        Button saveButton = new Button("Save tasks");
        fileHandler = new NotepadFileHandler("tasks.txt");




        // Set event handlers for buttons
        addButton.setOnAction(e -> addTask());
        completeButton.setOnAction(e -> markTaskAsCompleted());

        deleteButton.setOnAction(e -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                taskManager.removeTask(selectedIndex);
                taskListView.getItems().remove(selectedIndex);
                fileHandler.writeTasksToFile(taskManager.getTasks());
                System.out.println("Task deleted successfully.");
            }
        });

        saveButton.setOnAction(e -> {
            if (fileHandler != null) {
                fileHandler.writeTasksToFile(taskManager.getTasks());
                System.out.println("Tasks saved successfully.");
            } else {
                System.out.println("No file selected.");
            }
        });


        try {
            List<Task> loadedTasks = fileHandler.readTasksFromFile();
            taskManager.setTasks(loadedTasks);
            taskListView.getItems().addAll(loadedTasks);
            System.out.println("Tasks loaded successfully.");
        } catch (IOException ex) {
            System.out.println("Error loading tasks: " + ex.getMessage());
        }

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(taskListView, nameField, descriptionField, addButton, completeButton, deleteButton, saveButton);

        // Set up the scene and show the stage
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        Task task = new Task(name, description);
        taskManager.addTask(task);
        taskListView.getItems().add(task);
        nameField.clear();
        descriptionField.clear();
    }

    private void markTaskAsCompleted() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        taskManager.markTaskAsCompleted(selectedIndex);
        taskListView.refresh();

        // Change the color of the completed task item
        if (selectedIndex >= 0 && selectedIndex < taskListView.getItems().size()) {
            Task completedTask = taskListView.getItems().get(selectedIndex);
            taskListView.setCellFactory(param -> new TaskCell());
        }
    }
}

