package journey.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class OfferStrengthComparator implements Comparator<Offer> {

    @Override
    public int compare(Offer o1, Offer o2) {
        if(o1.getReals().length > 0 && o2.getReals().length <= 0) {
            return new CompareToBuilder()
                    .append(o1.getIntegers()[3], o2.getIntegers()[0])
                    .append(o1.getIntegers()[3], o2.getIntegers()[1])
                    .append(o1.getId(), o2.getId())
                    .toComparison();
        } else if(o2.getReals().length > 0 && o1.getReals().length <= 0){
            return new CompareToBuilder()
                    .append(o2.getIntegers()[3], o1.getIntegers()[0])
                    .append(o2.getIntegers()[3], o1.getIntegers()[1])
                    .append(o1.getId(), o2.getId())
                    .toComparison();
        }
        return new CompareToBuilder()
                .append(o1.getId(), o2.getId())
                .toComparison();

    }
}
