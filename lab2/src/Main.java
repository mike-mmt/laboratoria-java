import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Witaj, ten program służy do obliczeń matematycznych związanych z walcem");
        Walec walec = new Walec();
        menu(walec);
    }
    private static void menu(Walec walec) {
        boolean running = true;
        while (running) {
            System.out.println("Co chcesz teraz zrobić? Wpisz odpowiednią liczbę");
            System.out.println("1 - Wyświetl podstawowe wymiary walca");
            System.out.println("2 - Ustaw podstawowe wymiary walca");
            System.out.println("3 - Oblicz pola powierzchni walca");
            System.out.println("4 - Oblicz objętość walca");
            System.out.println("0 - Zakończ działanie programu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showBasicParameters(walec);
                case 2 -> setParametersMenu(walec);
                case 3 -> showSurfaceAreas(walec);
                case 4 -> showVolume(walec);
                case 0 -> running = false;
            }
            System.out.println();
        }
    }

    private static void showBasicParameters(Walec walec) {
        double baseRadius = walec.getBaseRadius();
        double height = walec.getHeight();
        System.out.printf("Promień podstawy walca wynosi %f\nWysokość walca wynosi %f\n", baseRadius, height);
    }

    private static void setParametersMenu(Walec walec) {
        System.out.println("Który parametr chcesz ustawić?");
        System.out.println("1 - promień podstawy");
        System.out.println("2 - wysokość");
        int choice = scanner.nextInt();
        System.out.println("Podaj nową wartość parametru: ");
        double newValue = scanner.nextDouble();
        switch (choice) {
            case 1 -> walec.setBaseRadius(newValue);
            case 2 -> walec.setHeight(newValue);
        }
    }

    private static void showSurfaceAreas(Walec walec) {
        System.out.printf("Pole powierzchni podstawy wynosi %f\n", walec.calculateBaseSurfaceArea());
        System.out.printf("Pole powierzchni bocznej wynosi %f\n", walec.calculateSideSurfaceArea());
        System.out.printf("Pole powierzchni całkowitej wynosi %f\n", walec.calculateTotalSurfaceArea());
    }

    private static void showVolume(Walec walec) {
        System.out.printf("Objętość walca wynosi %f\n", walec.calculateVolume());
    }
}