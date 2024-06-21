import java.time.LocalTime;

public final class Task extends CalendarEntry {
    private TaskStatus status;
    public Task(LocalTime startTime, LocalTime endTime, String description, TaskStatus status) {
        super(description, startTime, endTime);
        this.status = status;
    }
    public TaskStatus getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return this.getDescription() + "\n" + this.getStartTime() + " - " + this.getEndTime()
                + "\nPriorytet: " + switch (this.getStatus()) {
            case TaskStatus.PLANNED -> "planowane";
            case TaskStatus.CONFIRMED -> "potwierdzone";
            case TaskStatus.IN_PROGRESS -> "realizowane";
            case TaskStatus.DONE -> "wykonane";
        } + "\n";
    }
}
