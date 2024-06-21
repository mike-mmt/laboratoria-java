import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Witaj, ten program służy do obsługi ofert sprzedaży mieszkań i domów");
        OfferList offers = new OfferList();
        menu(offers);
    }

    private static void menu(OfferList offers) {
        boolean running = true;
        while (running) {
            System.out.println("\nWciśnij enter aby wyświetlić menu");
            scanner.nextLine();
            System.out.println("--------------------------------");
            System.out.println("Co chcesz teraz zrobić?");
            System.out.println("1 - dodaj ofertę sprzedaży domu");
            System.out.println("2 - dodaj ofertę sprzedaży mieszkania");
            System.out.println("3 - wyświetl aktualne oferty sprzedaży domów");
            System.out.println("4 - wyświetl aktualne oferty sprzedaży mieszkań");
            System.out.println("5 - wyświetl aktualne oferty sprzedaży domów w danej miejscowości o minimalnej podanej powierzchni");
            System.out.println("6 - wyświetl aktualne oferty sprzedaży mieszkań w danej miejscowości o maksymalnej podanej cenie i od danego piętra wzwyż");
            System.out.println("7 - dodaj po 7 przykładowych ofert domów i mieszkań");
            System.out.println("0 - Zakończ działanie programu");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addHouseOffer(offers);
                case 2 -> addFlatOffer(offers);
                case 3 -> showHouseOffers(offers);
                case 4 -> showFlatOffers(offers);
                case 5 -> showHouseOffersInCityWithMinArea(offers);
                case 6 -> showFlatOffersInCityCheaperThanFloorHigherThan(offers);
                case 7 -> addSomeOffers(offers);
                case 0 -> running = false;
                default -> System.out.println("Nieprawidłowa opcja");
            }
        }
    }

    private static String chooseCity() {
        System.out.println("Wpisz nazwę miasta: ");
        return scanner.nextLine();
    }

    private static LocalDate chooseDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        System.out.println("Podaj datę w formacie dd.MM.yyyy");
        return LocalDate.parse(scanner.nextLine(), formatter);
    }

    private static void addHouseOffer(OfferList offerList) {
        System.out.println("Wpisz ulicę");
        String street = scanner.nextLine();
        System.out.println("Wpisz numer budynku");
        int buildingNumber = Integer.parseInt(scanner.nextLine());
        String city = chooseCity();
        System.out.println("Wpisz kod pocztowy");
        String zipCode = scanner.nextLine();
        System.out.println("Wpisz cenę (PLN)");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz powierzchnię w m2");
        double area = Double.parseDouble(scanner.nextLine());
        System.out.println("Wpisz powierzchnię działki w m2");
        double landArea = Double.parseDouble(scanner.nextLine());
        System.out.println("Wpisz datę obowiązywania oferty");
        LocalDate date = chooseDate();

        House houseOffer = new House(street, buildingNumber, city, zipCode, area, price, date, landArea);
        offerList.addOffer(houseOffer);
    }

    private static void addFlatOffer(OfferList offerList) {
        System.out.println("Wpisz ulicę");
        String street = scanner.nextLine();
        System.out.println("Wpisz numer budynku");
        int buildingNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz numer mieszkania");
        int flatNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz numer piętra");
        int floorNumber = Integer.parseInt(scanner.nextLine());
        String city = chooseCity();
        System.out.println("Wpisz kod pocztowy");
        String zipCode = scanner.nextLine();
        System.out.println("Wpisz cenę (PLN)");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz powierzchnię w m2");
        double area = Double.parseDouble(scanner.nextLine());
        System.out.println("Wpisz datę obowiązywania oferty");
        LocalDate date = chooseDate();

        Flat houseOffer = new Flat(street, buildingNumber, city, zipCode, area, price, date, flatNumber, floorNumber);
        offerList.addOffer(houseOffer);
    }

    private static void showHouseOffers(OfferList offerList) {
        List<LivableEstate> filteredOffers = offerList.getOffers(estate -> estate instanceof House && !estate.getOfferDate().isBefore(LocalDate.now()));
        printOffers(filteredOffers);
    }

    private static void showHouseOffersInCityWithMinArea(OfferList offerList) {
        String city = chooseCity();
        System.out.println("Podaj powierzchnię domu w m2");
        double area = scanner.nextDouble();
        scanner.nextLine();
        List<LivableEstate> filteredOffers = offerList.getOffers(estate -> estate instanceof House && !estate.getOfferDate().isBefore(LocalDate.now()) && estate.getCity().equals(city) && estate.getArea() >= area);
        printOffers(filteredOffers);
    }

    private static void showFlatOffers(OfferList offerList) {
        List<LivableEstate> filteredOffers = offerList.getOffers(estate -> estate instanceof Flat && !estate.getOfferDate().isBefore(LocalDate.now()));
        printOffers(filteredOffers);
    }

    private static void showFlatOffersInCityCheaperThanFloorHigherThan(OfferList offerList) {
        String city = chooseCity();
        System.out.println("Podaj maksymalną cenę mieszkania");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj minimalne piętro, na którym ma być mieszkanie");
        int floor = scanner.nextInt();
        scanner.nextLine();
        List<LivableEstate> filteredOffers = offerList.getOffers(estate -> estate instanceof Flat && !estate.getOfferDate().isBefore(LocalDate.now()) && estate.getCity().equals(city) && estate.getPrice() <= price && ((Flat)estate).getFloorNumber() >= floor );
        printOffers(filteredOffers);
    }

    private static void printOffers(List<LivableEstate> offers) {
        for (LivableEstate offer : offers) {
            System.out.println(offer);
            System.out.println();
        }
    }


    private static void addSomeOffers(OfferList offerList) {
        offerList.addOffer(new House("Maple Street", 123, "Sunnyvale", "94087", 1800.0 * 0.0929, 850000,LocalDate.now(), 200));
        offerList.addOffer(new House("Oak Avenue", 77, "Mountain View", "94043", 2500.0 * 0.0929, 1200000,LocalDate.now().plusDays(5), 350));
        offerList.addOffer(new House("Elm Lane", 42, "Santa Clara", "95054", 1400.0 * 0.0929, 680000,LocalDate.now().plusWeeks(3), 150));
        offerList.addOffer(new House("Cedar Drive", 21, "Los Gatos", "95033", 3000.0 * 0.0929, 1500000,LocalDate.now().plusDays(17), 500));
        offerList.addOffer(new House("Pine Boulevard", 98, "Freemont", "94539", 2200.0 * 0.0929, 1050000,LocalDate.now().plusDays(11), 400));
        offerList.addOffer(new House("Willow Road", 55, "Cupertino", "95014", 1600.0 * 0.0929, 790000,LocalDate.now().plusMonths(3), 200));
        offerList.addOffer(new House("Birch Street", 31, "San Jose", "95112", 2000.0 * 0.0929, 980000,LocalDate.now().plusDays(8), 250));

        offerList.addOffer(new Flat("Main Street", 15, "San Francisco", "94105", 65.0, 350000,LocalDate.now(), 3, 2));
        offerList.addOffer(new Flat("Market Street", 42, "Seattle", "98104", 48.0, 280000,LocalDate.now().plusDays(5), 2, 1));
        offerList.addOffer(new Flat("Broadway Avenue", 7, "New York", "10007", 82.0, 420000,LocalDate.now().plusWeeks(2), 1, 4));
        offerList.addOffer(new Flat("Ocean Drive", 10, "Miami", "33139", 55.0, 310000,LocalDate.now().plusMonths(2), 4, 3));
        offerList.addOffer(new Flat("Michigan Avenue", 12, "Chicago", "60601", 70.0, 380000,LocalDate.now().plusDays(24), 2, 2));
        offerList.addOffer(new Flat("Elm Street", 33, "Dallas", "75201", 42.0, 240000,LocalDate.now().plusDays(12), 1, 1));
        offerList.addOffer(new Flat("Sunset Boulevard", 19, "Los Angeles", "90069", 95.0, 500000,LocalDate.now().plusWeeks(1), 3, 5));
    }
}