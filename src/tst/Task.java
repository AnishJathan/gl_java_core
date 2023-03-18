package tst;

public class Task {
    private int taskId;
    private String taskTitle;
    private String taskText;
    private String assignedTo;
    private boolean taskCompleted;

    public Task(int taskId, String taskTitle, String taskText, String assignedTo) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
        this.assignedTo = assignedTo;
        this.taskCompleted = false; // Default value for new tasks
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskText='" + taskText + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", taskCompleted=" + taskCompleted +
                '}';
    }
}