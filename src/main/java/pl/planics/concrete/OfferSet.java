package pl.planics.concrete;

import java.util.List;
import planics.common.concrete.IOffer;
import planics.common.concrete.IOfferSet;

/**
 *
 * @author Arturro
 */
public class OfferSet implements IOfferSet{
    private List<IOffer> offers;
    private String sName;

    public OfferSet(List<IOffer> offers, String sName) {
        this.offers = offers;
        this.sName = sName;
    }

    @Override
    public IOffer getOffer(int i) { //zakladam, ze indeksy ofert odpowiadaja pozycji w liscie
        return offers.get(i);
    }

    @Override
    public List<IOffer> getOffers() {
        return offers;
    }

    @Override
    public String getServiceTypeName() {
        return sName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OfferSet{ sName=" + sName +"\n");
        for (IOffer iOffer : offers) {
            sb.append(iOffer).append("\n");
        }
        sb.append("\n}");
        return sb.toString();
    }
}
