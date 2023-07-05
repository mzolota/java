import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;


public class Task {
    private String name;
    private String description;
    private boolean completed;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Additional methods

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }


}

