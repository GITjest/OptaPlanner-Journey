package journey.guifx.offerfx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import journey.domain.Offer;

public class OfferFX {
    private final SimpleStringProperty name;
    private final SimpleLongProperty localization;
    private final SimpleLongProperty endLocalization;
    private final SimpleLongProperty startDate;
    private final SimpleLongProperty endDate;
    private final SimpleLongProperty price;
    private final SimpleDoubleProperty comfort;

    public OfferFX(String name, Offer offer) {
        this.name = new SimpleStringProperty(name);
        if(offer.getReals().length > 0) {
            this.localization = new SimpleLongProperty(offer.getIntegers()[3]);
            this.endLocalization = new SimpleLongProperty(offer.getIntegers()[3]);
            this.startDate = new SimpleLongProperty(offer.getIntegers()[1]);
            this.endDate = new SimpleLongProperty(offer.getIntegers()[2]);
            this.price = new SimpleLongProperty(offer.getIntegers()[0]);
            this.comfort = new SimpleDoubleProperty(offer.getReals()[0]);
        } else {
            this.localization = new SimpleLongProperty(offer.getIntegers()[0]);
            this.endLocalization = new SimpleLongProperty(offer.getIntegers()[1]);
            this.startDate = new SimpleLongProperty(offer.getIntegers()[2]);
            this.endDate = new SimpleLongProperty(offer.getIntegers()[3]);
            this.price = new SimpleLongProperty(offer.getIntegers()[4]);
            this.comfort = new SimpleDoubleProperty();
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getLocalization() {
        return localization.get();
    }

    public void setLocalization(long localization) {
        this.localization.set(localization);
    }

    public long getEndLocalization() {
        return endLocalization.get();
    }

    public void setEndLocalization(long endLocalization) {
        this.endLocalization.set(endLocalization);
    }

    public long getStartDate() {
        return startDate.get();
    }

    public void setStartDate(long startDate) {
        this.startDate.set(startDate);
    }

    public long getEndDate() {
        return endDate.get();
    }

    public void setEndDate(long endDate) {
        this.endDate.set(endDate);
    }

    public long getPrice() {
        return price.get();
    }

    public void setPrice(long price) {
        this.price.set(price);
    }

    public double getComfort() {
        return comfort.get();
    }

    public void setComfort(double comfort) {
        this.comfort.set(comfort);
    }
}
