import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Witaj! Ten program oblicza silnię podanej liczby (n!).");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> evaluateFactorial();
                case 2 -> evaluateSumRange();
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowe wejście");
            }
        }
    }
    private static void evaluateFactorial() {
        System.out.println("Proszę wpisać liczbę n do obliczenia n!: ");
        int n = scanner.nextInt();
        long result = Calculations.factorial(n);
        System.out.printf("Wartość %d!: %d\n", n, result);
    }
    private static void evaluateSumRange() {
        System.out.println("Proszę podać początek zakresu: ");
        int a = scanner.nextInt();
        System.out.println("Proszę podać koniec zakresu: ");
        int b = scanner.nextInt();
        int result = Calculations.sumRange(a, b);
        System.out.printf("Suma liczb z zakresu [%d, %d] wynosi %d\n", a, b, result);
    }
    private static void showMenu() {
        System.out.println("Co chcesz zrobić? Podaj odpowiednią liczbę");
        System.out.println("1 - Oblicz silnię liczby");
        System.out.println("2 - Oblicz sumę zakresu liczb");
        System.out.println("0 - Zakończ działanie programu");
    }
}