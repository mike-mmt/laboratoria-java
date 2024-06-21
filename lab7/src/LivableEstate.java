import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public sealed abstract class LivableEstate permits House, Flat {
    protected final DateTimeFormatter DATE_FORMAT_PATTERN = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    protected String street;
    protected int buildingNumber;
    protected String city;
    protected String zipCode;
    protected double area;
    protected int price;
    protected LocalDate offerDate;

    public LivableEstate(String street, int buildingNumber, String city, String zipCode, double area, int price, LocalDate offerDate) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.area = area;
        this.price = price;
        this.offerDate = offerDate;
    }

    public String getStreet() {
        return street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getPrice() {
        return price;
    }

    public double getArea() {
        return area;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }
}
