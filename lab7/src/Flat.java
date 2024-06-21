import java.time.LocalDate;

public final class Flat extends LivableEstate {
    private int flatNumber;
    private int floorNumber;

    public Flat(String street, int buildingNumber, String city, String zipCode, double area, int price, LocalDate offerDate, int flatNumber, int floorNumber) {
        super(street, buildingNumber, city, zipCode, area, price, offerDate);
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "Flat " + flatNumber + " on " + street + " " + buildingNumber + ", " +
                switch ((floorNumber % 10)) {
                    case 1 -> floorNumber + "st";
                    case 2 -> floorNumber + "nd";
                    case 3 -> floorNumber + "rd";
                    default -> floorNumber + "th";
                } + " floor\n"
                + zipCode + " " + city + "\n"
                + "Area: " + area + "m2\n"
                + price + " PLN\n"
                + "Offer valid until " + offerDate.format(DATE_FORMAT_PATTERN) + "\n";
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
