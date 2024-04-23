public class Walec {
    private double baseRadius;
    private double height;

    public Walec(double baseRadius, double height) {
        this.baseRadius = baseRadius;
        this.height = height;
    }
    public Walec() {}

    public double getBaseRadius() {
        return this.baseRadius;
    }

    public double getHeight() {
        return height;
    }

    public void setBaseRadius(double baseRadius) {
        this.baseRadius = baseRadius;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateBaseSurfaceArea() {
        return Math.PI * Math.pow(getBaseRadius(), 2);
    }

    public double calculateSideSurfaceArea() {
        return 2 * Math.PI * getBaseRadius() * getHeight();
    }

    public double calculateTotalSurfaceArea() {
        return 2 * calculateBaseSurfaceArea() + calculateSideSurfaceArea();
    }

    public double calculateVolume() {
        return calculateBaseSurfaceArea() * getHeight();
    }
}
