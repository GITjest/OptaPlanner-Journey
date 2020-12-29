package journey.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class JourneyStageDifficultyComparator implements Comparator<JourneyStage> {

    @Override
    public int compare(JourneyStage o1, JourneyStage o2) {
        return new CompareToBuilder()
                .toComparison();
    }
}


