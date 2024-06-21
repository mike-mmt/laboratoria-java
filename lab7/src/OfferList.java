import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OfferList {
    private ArrayList<LivableEstate> offers = new ArrayList<>();

    public ArrayList<LivableEstate> getOffers(Predicate<LivableEstate> predicate) {
        ArrayList<LivableEstate> filteredOffers = new ArrayList<>();
        for (LivableEstate offer : this.offers) {
            if (predicate.test(offer)) {
                filteredOffers.add(offer);
            }
        }
        return filteredOffers;
    }

    public void addOffer(LivableEstate estate) {
        offers.add(estate);
    }
}
