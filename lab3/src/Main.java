import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Witaj! Ten program służy do przechowywania ocen cząstkowych studenta i ich wyświetlania.");
        GradeList gradeList = new GradeList();
        menu(gradeList);
    }
    private static void menu(GradeList gradeList) {
        boolean running = true;
        while (running) {
            System.out.println("Co chcesz teraz zrobić?");
            System.out.println("1 - Dodaj nową ocenę");
            System.out.println("2 - Wyświetl średnią ocen");
            System.out.println("3 - Wyświetl najwyższą ocenę");
            System.out.println("4 - Wyświetl najniższą ocenę");
            System.out.println("0 - Zakończ działanie programu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addGrade(gradeList);
                case 2 -> getAverageGrade(gradeList);
                case 3 -> getHighestGrade(gradeList);
                case 4 -> getLowestGrade(gradeList);
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowa opcja");
            }
        }
    }
    private static void addGrade(GradeList gradeList) {
        System.out.println("Wprowadź nową ocenę (użyj przecinka, nie kropki):");
        double nowaOcena = scanner.nextDouble();
        gradeList.addGrade(nowaOcena);
    }
    private static void getAverageGrade(GradeList gradeList) {
        if (gradeList.isEmpty()) {
            System.out.println("Nie zostały jeszcze wprowadzone żadne oceny");
        } else {
        System.out.printf("Średnia wszystkich wprowadzonych ocen wynosi %f\n", gradeList.getAverageGrade());
        }
    }
    private static void getHighestGrade(GradeList gradeList) {
        if (gradeList.isEmpty()) {
            System.out.println("Nie zostały jeszcze wprowadzone żadne oceny");
        } else {
            System.out.printf("Najwyższa ocena cząstkowa wynosi %.2f\n", gradeList.getHighestGrade());
        }
    }
    private static void getLowestGrade(GradeList gradeList) {
        if (gradeList.isEmpty()) {
            System.out.println("Nie zostały jeszcze wprowadzone żadne oceny");
        } else {
            System.out.printf("Najniższa ocena cząstkowa wynosi %.2f\n", gradeList.getLowestGrade());
        }
    }
}
