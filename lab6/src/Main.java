import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public enum calendarEntryType {
        MEETING,
        TASK
    }
    public static void main(String[] args) {
        System.out.println("Witaj, ten program służy do obsługi kalendarza tygodniowego spotkań");
        Calendar calendar = new Calendar();
        menu(calendar);
    }

    private static void menu(Calendar calendar) {
        boolean running = true;
        while (running) {
            System.out.println("\nWciśnij enter aby wyświetlić menu");
            scanner.nextLine();
            System.out.println("--------------------------------");
            System.out.println("Co chcesz teraz zrobić?");
            System.out.println("1 - dodaj spotkanie");
            System.out.println("2 - dodaj zadanie");
            System.out.println("3 - usuń spotkanie");
            System.out.println("4 - usuń zadanie");
            System.out.println("5 - wyświetl wszystkie spotkania w danym dniu");
            System.out.println("6 - wyświetl wszystkie zadania w danym dniu");
            System.out.println("7 - wyświetl spotkania w wybranym dniu o podanym priorytecie");
            System.out.println("8 - wyświetl zadania w wybranym dniu o podanym statusie");
            System.out.println("9 - wyświetl spotkania w wybranym dniu o podanym priorytecie od danej godziny");
            System.out.println("10 - wyświetl zadania w wybranym dniu o podanym statusie do danej godziny");
            System.out.println("11 - dodaj po 7 przykładowych spotkań i zadań w poniedziałek");
            System.out.println("0 - Zakończ działanie programu");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addEntry(calendar, calendarEntryType.MEETING);
                case 2 -> addEntry(calendar, calendarEntryType.TASK);
                case 3 -> deleteEntry(calendar, calendarEntryType.MEETING);
                case 4 -> deleteEntry(calendar, calendarEntryType.TASK);
                case 5 -> showAllDayEntries(calendar, calendarEntryType.MEETING);
                case 6 -> showAllDayEntries(calendar, calendarEntryType.TASK);
                case 7 -> showDayMeetingsWithPriority(calendar);
                case 8 -> showDayTasksWithStatus(calendar);
                case 9 -> showDayMeetingsWithPriorityAfterTime(calendar);
                case 10 -> showDayTasksWithStatusBeforeTime(calendar);
                case 11 -> addSomeMeetingsAndTasks(calendar);
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowa opcja");
            }
        }
    }

    private static int chooseDay() {
        while (true) {
            System.out.println("Wybierz dzień - podaj numer dnia tygodnia od 1 do 7");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 7) {
                return choice - 1;
            }
        }
    }

    private static LocalTime chooseHour() {
        while (true) {
            System.out.println("Podaj godzinę (tylko godzina bez minut):");
            int hour = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Podaj minuty (tylko minuty):");
            int minutes = scanner.nextInt();
            scanner.nextLine();
            LocalTime time = LocalTime.of(hour, minutes);
            if (time.isBefore(Meeting.EARLIEST_POSSIBLE_TIME)) {
                System.out.println("Godzina spotkania jest wcześniejsza od najwcześniejszej możliwej godziny rozpoczęcia spotkania");
                continue;
            }
            return LocalTime.of(hour, minutes);
        }
    }

    private static int choosePriority() {
        while (true) {
            System.out.println("Podaj priorytet: 0 - normalny, 1 - wysoki, 2 - najwyższy:");
            int priority = scanner.nextInt();
            scanner.nextLine();
            if (priority < 0 || priority > 2) {
                System.out.println("Niepoprawny priorytet");
                continue;
            }
            return priority;
        }
    }

    private static TaskStatus chooseStatus() {
        while (true) {
            System.out.println("Podaj status zadania: 0 - planowane, 1 - potwierdzone, 2 - realizowane, 3 - wykonane:");
            int status = scanner.nextInt();
            scanner.nextLine();
            if (status < 0 || status > 3) {
                System.out.println("Niepoprawny status");
                continue;
            }
            return switch (status) {
                case 1 -> TaskStatus.CONFIRMED;
                case 2 -> TaskStatus.IN_PROGRESS;
                case 3 -> TaskStatus.DONE;
                default -> TaskStatus.PLANNED;

            };
        }
    }

    private static void addEntry(Calendar calendar, calendarEntryType entryType) {
        int day = chooseDay();
        System.out.println("Podaj opis:");
        String description = scanner.nextLine();
        LocalTime startTime, endTime;
        do {
            System.out.println("Podaj czas rozpoczęcia spotkania");
            startTime = chooseHour();
            System.out.println("Podaj czas zakończenia spotkania");
            endTime = chooseHour();
        } while (!endTime.isAfter(startTime));

        switch (entryType) {
            case MEETING -> {
                int priority = choosePriority();
                Meeting meeting = new Meeting(startTime, endTime, description, priority);
                calendar.addEntry(day, meeting);
            }
            case TASK -> {
                TaskStatus status = chooseStatus();
                Task task = new Task(startTime, endTime, description, status);
                calendar.addEntry(day, task);
            }
        }
    }

    private static void deleteEntry(Calendar calendar, calendarEntryType entryType) {
        int day = chooseDay();
        switch (entryType) {
            case MEETING -> {
                ArrayList<CalendarEntry> meetings = calendar.getEntries(day, entry -> entry instanceof Meeting);
                if (meetings.isEmpty()) {
                    System.out.println("Nie ma spotkań do usunięcia");
                    return;
                }
                printEntriesWithIndexPlus1(meetings);
                System.out.println("Wybierz spotkanie do usunięcia po indeksie:");
                int choice = scanner.nextInt();
                scanner.nextLine();
                choice -= 1;
                calendar.deleteEntry(day, meetings.get(choice));

            }
            case TASK -> {
                ArrayList<CalendarEntry> tasks = calendar.getEntries(day, entry -> entry instanceof Task);
                if (tasks.isEmpty()) {
                    System.out.println("Nie ma zadań do usunięcia");
                    return;
                }
                printEntriesWithIndexPlus1(tasks);
                System.out.println("Wybierz spotkanie do usunięcia po indeksie:");
                int choice = scanner.nextInt();
                scanner.nextLine();
                choice -= 1;
                calendar.deleteEntry(day, tasks.get(choice));
            }
        }

//        showAllDayMeetingsWithIndex(calendar, day);
//
//        calendar.deleteEntry(day, choice);
    }

    private static void showAllDayEntries(Calendar calendar, calendarEntryType entryType) {
        int day = chooseDay();
        ArrayList<CalendarEntry> entries = switch (entryType) {
            case calendarEntryType.MEETING -> calendar.getEntries(day, entry -> entry instanceof Meeting);
            case calendarEntryType.TASK -> calendar.getEntries(day, entry -> entry instanceof Task);
        };
        printEntries(entries);
    }

    private static void showDayMeetingsWithPriority(Calendar calendar) {
        int day = chooseDay();
        int priority = choosePriority();
        ArrayList<CalendarEntry> meetings = calendar.getEntries(day, entry -> entry instanceof Meeting && ((Meeting) entry).getPriority() == priority);
        printEntries(meetings);
    }

    private static void showDayTasksWithStatus(Calendar calendar) {
        int day = chooseDay();
        TaskStatus status = chooseStatus();
        ArrayList<CalendarEntry> tasks = calendar.getEntries(day, entry -> entry instanceof Task && ((Task) entry).getStatus() == status);
        printEntries(tasks);
    }

    private static void showDayMeetingsWithPriorityAfterTime(Calendar calendar) {
        int day = chooseDay();
        int priority = choosePriority();
        LocalTime time = chooseHour();
        ArrayList<CalendarEntry> meetings = calendar.getEntries(day, entry -> entry instanceof Meeting &&
                ((Meeting) entry).getPriority() == priority && !entry.getStartTime().isBefore(time));
        printEntries(meetings);
    }

    private static void showDayTasksWithStatusBeforeTime(Calendar calendar) {
        int day = chooseDay();
        TaskStatus status = chooseStatus();
        LocalTime time = chooseHour();
        ArrayList<CalendarEntry> tasks = calendar.getEntries(day, entry -> entry instanceof Task &&
                ((Task) entry).getStatus() == status && !entry.getEndTime().isAfter(time));
        printEntries(tasks);
    }

    private static void printEntries(ArrayList<CalendarEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Nie ma żadnych elementów do wyświetlenia");
            return;
        }
        for (CalendarEntry entry : entries) {
            System.out.println(entry);
        }
    }

    private static void printEntriesWithIndexPlus1(ArrayList<CalendarEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Nie ma żadnych elementów do wyświetlenia");
            return;
        }
        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("# %d - %s\n", i+1, entries.get(i));
        }
    }

    private static void addSomeMeetingsAndTasks(Calendar calendar) {
        calendar.addEntry(0, new Meeting(LocalTime.of(9, 0), LocalTime.of(10, 30), "Spotkanie jeden", 0));
        calendar.addEntry(0,new Meeting(LocalTime.of(11, 15), LocalTime.of(12, 30), "Spotkanie dwa", 2));

        calendar.addEntry(0,new Task(LocalTime.of(10, 45), LocalTime.of(11, 30), "Zadanie jeden", TaskStatus.DONE));

        calendar.addEntry(0,new Meeting(LocalTime.of(13, 20), LocalTime.of(14, 40), "Spotkanie trzy", 0));

        calendar.addEntry(0,new Task(LocalTime.of(12, 45), LocalTime.of(13, 30), "Zadanie dwa", TaskStatus.DONE));
        calendar.addEntry(0,new Task(LocalTime.of(14, 55), LocalTime.of(15, 15), "Zadanie trzy", TaskStatus.IN_PROGRESS));

        calendar.addEntry(0,new Meeting(LocalTime.of(15, 5), LocalTime.of(16, 20), "Spotkanie cztery", 1));

        calendar.addEntry(0,new Task(LocalTime.of(15, 45), LocalTime.of(16, 0), "Zadanie cztery", TaskStatus.CONFIRMED));
        calendar.addEntry(0,new Task(LocalTime.of(16, 10), LocalTime.of(16, 20), "Zadanie pięć", TaskStatus.PLANNED));


        calendar.addEntry(0,new Meeting(LocalTime.of(17, 30), LocalTime.of(18, 45), "Spotkanie pięć", 1));
        calendar.addEntry(0,new Meeting(LocalTime.of(20, 0), LocalTime.of(21, 0), "Spotkanie sześć", 0));

        calendar.addEntry(0,new Task(LocalTime.of(21, 0), LocalTime.of(21, 5), "Zadanie sześć", TaskStatus.CONFIRMED));

        calendar.addEntry(0,new Meeting(LocalTime.of(22, 15), LocalTime.of(22, 45), "Spotkanie siedem", 2));

        calendar.addEntry(0,new Task(LocalTime.of(23, 10), LocalTime.of(23, 30), "Zadanie siedem", TaskStatus.PLANNED));
    }
}