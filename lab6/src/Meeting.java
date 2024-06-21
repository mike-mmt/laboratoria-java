import java.time.LocalTime;

public final class Meeting extends CalendarEntry {
    private int priority;
    public Meeting(LocalTime startTime, LocalTime endTime, String description, int priority) {
        super(description, startTime, endTime);
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return this.getDescription() + "\n" + this.getStartTime() + " - " + this.getEndTime()
                + "\nPriorytet: " + switch (this.getPriority()) {
            case 0 -> "normalny";
            case 1 -> "wysoki";
            case 2 -> "najwyższy";
            default -> "?";
        } + "\n";
    }
}
