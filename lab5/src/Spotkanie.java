import java.time.LocalTime;

public class Spotkanie {
    public static final LocalTime EARLIEST_POSSIBLE_TIME = LocalTime.of(5, 0);
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private int priority;
    public Spotkanie(LocalTime startTime, LocalTime endTime, String description, int priority) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return this.description + "\n" + this.startTime + " - " + this.endTime
                + "\nPriorytet: " + switch (getPriority()) {
            case 0 -> "normalny";
            case 1 -> "wysoki";
            case 2 -> "najwyższy";
            default -> "?";
        } + "\n";
    }
}
