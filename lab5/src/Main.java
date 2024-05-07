import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Witaj, ten program służy do obsługi kalendarza tygodniowego spotkań");
        Kalendarz kalendarz = new Kalendarz();
        menu(kalendarz);
    }
    private static void menu(Kalendarz kalendarz) {
        boolean running = true;
        while (running) {
            System.out.println("Co chcesz teraz zrobić?");
            System.out.println("1 - dodaj spotkanie");
            System.out.println("2 - usuń spotkanie");
            System.out.println("3 - wyświetl wszystkie spotkania w danym dniu");
            System.out.println("4 - wyświetl spotkania w wybranym dniu o podanym priorytecie");
            System.out.println("5 - wyświetl spotkania w wybranym dniu od danej godziny");
            System.out.println("6 - wyświetl spotkania w wybranym dniu pomiędzy podanymi czasami");
            System.out.println("7 - wyświetl spotkania w wybranym dniu o podanym priorytecie od danej godziny");
            System.out.println("8 - dodaj 7 przykładowych spotkań w poniedziałek");
            System.out.println("0 - Zakończ działanie programu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addMeeting(kalendarz);
                case 2 -> deleteMeeting(kalendarz);
                case 3 -> showAllDayMeetings(kalendarz);
                case 4 -> showDayMeetingsWithPriority(kalendarz);
                case 5 -> showDayMeetingsAfterTime(kalendarz);
                case 6 -> showDayMeetingsBetweenTimes(kalendarz);
                case 7 -> showDayMeetingsWithPriorityAfterTime(kalendarz);
                case 8 -> addSomeMeetings(kalendarz);
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
            System.out.println("Podaj godzinę:");
            int hour = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Podaj minuty:");
            int minutes = scanner.nextInt();
            scanner.nextLine();
            LocalTime time = LocalTime.of(hour, minutes);
            if (time.isBefore(Spotkanie.EARLIEST_POSSIBLE_TIME)) {
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
    private static void addMeeting(Kalendarz kalendarz) {
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
            int priority = choosePriority();
            Spotkanie spotkanie = new Spotkanie(startTime,endTime, description, priority);
            kalendarz.addMeeting(day, spotkanie);

    }
    private static void deleteMeeting(Kalendarz kalendarz) {
        int day = chooseDay();
        if (kalendarz.getMeetings(day, spotkanie -> true).isEmpty()) {
            System.out.println("Nie ma spotkań do usunięcia");
            return;
        }
        showAllDayMeetingsWithIndex(kalendarz, day);
        System.out.println("Wybierz spotkanie do usunięcia po indeksie:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        kalendarz.deleteMeeting(day, choice);
    }
    private static void showAllDayMeetings(Kalendarz kalendarz) {
        int day = chooseDay();
        printMeetings(kalendarz.getMeetings(day, spotkanie -> true));
    }
    private static void showAllDayMeetingsWithIndex(Kalendarz kalendarz, int day) {
        ArrayList<Spotkanie> meetings = kalendarz.getMeetings(day, spotkanie -> true);
        for (int i = 0; i < meetings.size(); i++) {
            System.out.printf("%d - %s\n", i, meetings.get(i));
        }
    }
    private static void showDayMeetingsWithPriority(Kalendarz kalendarz) {
        int day = chooseDay();
        int priority = choosePriority();
        ArrayList<Spotkanie> meetings = kalendarz.getMeetings(day, spotkanie -> spotkanie.getPriority() == priority);
        printMeetings(meetings);
    }
    private static void showDayMeetingsAfterTime(Kalendarz kalendarz) {
        int day = chooseDay();
        LocalTime time = chooseHour();
        ArrayList<Spotkanie> meetings = kalendarz.getMeetings(day, spotkanie -> !spotkanie.getStartTime().isBefore(time));
        printMeetings(meetings);
    }
    private static void showDayMeetingsBetweenTimes(Kalendarz kalendarz) {
        int day = chooseDay();
        System.out.println("Podaj początek zakresu");
        LocalTime startTime = chooseHour();
        System.out.println("Podaj koniec zakresu");
        LocalTime endTime = chooseHour();
        ArrayList<Spotkanie> meetings = kalendarz.getMeetings(day, spotkanie ->
                !spotkanie.getStartTime().isBefore(startTime) && !spotkanie.getEndTime().isAfter(endTime));
        printMeetings(meetings);
    }
    private static void showDayMeetingsWithPriorityAfterTime(Kalendarz kalendarz) {
        int day = chooseDay();
        int priority = choosePriority();
        LocalTime time = chooseHour();
        ArrayList<Spotkanie> meetings = kalendarz.getMeetings(day, spotkanie ->
                spotkanie.getPriority() == priority && !spotkanie.getStartTime().isBefore(time));
        printMeetings(meetings);
    }

    private static void printMeetings(ArrayList<Spotkanie> meetings) {
        for (Spotkanie meeting : meetings) {
            System.out.println(meeting);
        }
    }

    private static void addSomeMeetings(Kalendarz kalendarz) {
        kalendarz.addMeeting(0, new Spotkanie(LocalTime.of(9, 0), LocalTime.of(10, 30), "Spotkanie jeden", 0));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(12, 15), LocalTime.of(13, 30), "Spotkanie dwa", 2));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(14, 20), LocalTime.of(14, 40), "Spotkanie trzy", 0));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(15, 5), LocalTime.of(16, 20), "Spotkanie cztery", 1));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(17, 30), LocalTime.of(18, 45), "Spotkanie pięć", 1));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(20, 0), LocalTime.of(21, 0), "Spotkanie sześć", 0));
        kalendarz.addMeeting(0,new Spotkanie(LocalTime.of(22, 15), LocalTime.of(22, 45), "Spotkanie siedem", 2));
    }
}