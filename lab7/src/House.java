import java.time.LocalDate;

public final class House extends LivableEstate {
    private double landArea;

    public House(String street, int buildingNumber, String city, String zipCode, double area, int price, LocalDate offerDate, double landArea) {
        super(street, buildingNumber, city, zipCode, area, price, offerDate);
        this.landArea = landArea;
    }

    public double getLandArea() {
        return landArea;
    }

    @Override
    public String toString() {
        return "House on " + street + " " + buildingNumber + "\n"
                + zipCode + " " + city + "\n"
                + "Area: " + Math.round(area) + "m2, Land area: " + Math.round(landArea) + "m2\n"
                + price + " PLN\n"
                + "Offer valid until " + offerDate.format(DATE_FORMAT_PATTERN) + "\n";
    }
}
