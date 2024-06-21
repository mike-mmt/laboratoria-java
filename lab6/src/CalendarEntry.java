import java.time.LocalTime;

public sealed abstract class CalendarEntry permits Meeting, Task {
    static final LocalTime EARLIEST_POSSIBLE_TIME = LocalTime.of(5, 0);
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    public CalendarEntry(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
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
        return this.description + "\n" + this.startTime + " - " + this.endTime + "\n";
    }
}
