import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Calendar {
    private final Map<Integer, ArrayList<CalendarEntry>> entries = new HashMap<>();
    public Calendar() {
        this.entries.put(0, new ArrayList<>());
        this.entries.put(1, new ArrayList<>());
        this.entries.put(2, new ArrayList<>());
        this.entries.put(3, new ArrayList<>());
        this.entries.put(4, new ArrayList<>());
        this.entries.put(5, new ArrayList<>());
        this.entries.put(6, new ArrayList<>());
    }
    public void addEntry(int day, CalendarEntry entry) {
        if (day < 0 || day > 6) {
            return;
        }
        entries.get(day).add(entry);
    }
//    public void deleteEntry(int day, int index) {
//        entries.get(day).remove(index);
//    }
    public void deleteEntry(int day, CalendarEntry entry) {
        entries.get(day).remove(entry);
    }
    public ArrayList<CalendarEntry> getEntries(int day, Predicate<CalendarEntry> filter) {
        ArrayList<CalendarEntry> filteredEntries = new ArrayList<>();
        for (CalendarEntry meeting : this.entries.get(day)) {
            if (filter.test(meeting)) {
                filteredEntries.add(meeting);
            }
        }
        return filteredEntries;
    }
}
