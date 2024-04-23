import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kalendarz {
    private final Map<Integer, ArrayList<Spotkanie>> meetings = new HashMap<>();
    public Kalendarz() {
        this.meetings.put(0, new ArrayList<>());
        this.meetings.put(1, new ArrayList<>());
        this.meetings.put(2, new ArrayList<>());
        this.meetings.put(3, new ArrayList<>());
        this.meetings.put(4, new ArrayList<>());
        this.meetings.put(5, new ArrayList<>());
        this.meetings.put(6, new ArrayList<>());
    }
    public void addMeeting(int day, Spotkanie meeting) {
        if (day < 0 || day > 6) {
            return;
        }
        meetings.get(day).add(meeting);
    }
    public void deleteMeeting(int day, int index) {
        meetings.get(day).remove(index);
    }
    public ArrayList<Spotkanie> getDayMeetings(int day) {
        return new ArrayList<>(this.meetings.get(day));
    }
    public ArrayList<Spotkanie> getDayMeetingsByPriority(int day, int priority) {
        ArrayList<Spotkanie> filteredMeetings = new ArrayList<>();
        for (Spotkanie meeting : meetings.get(day)) {
            if (meeting.getPriority() == priority) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }
    public ArrayList<Spotkanie> getDayMeetingsAfterTime(int day, LocalTime time) {
        ArrayList<Spotkanie> filteredMeetings = new ArrayList<>();
        for (Spotkanie meeting : meetings.get(day)) {
            if (!meeting.getStartTime().isBefore(time)) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }
}
