import javafx.scene.control.ListCell;

public class TaskCell extends ListCell<Task> {
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (empty || task == null) {
            setText(null);
            setGraphic(null);
            setStyle(null);
        } else {
            setText(task.getName() + ": " + task.getDescription());
            setGraphic(null);

            if (task.isCompleted()) {
                setStyle("-fx-background-color: lightgreen;");
            } else {
                setStyle(null);
            }
        }
    }
}
