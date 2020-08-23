package journey.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class JourneySolution extends AbstractPersistable{
    private List<Offer> offerList;
    private List<JourneyStage> journeyStage;
    private HardSoftScore score;

    public JourneySolution() {
    }

    public JourneySolution(long id, List<Offer> offerList, List<JourneyStage> journeyStage) {
        super(id);
        this.offerList = offerList;
        this.journeyStage = journeyStage;
    }

    @ValueRangeProvider(id = "offer")
    @ProblemFactCollectionProperty
    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }

    @PlanningEntityCollectionProperty
    public List<JourneyStage> getJourneyStage() {
        return journeyStage;
    }

    public void setJourneyStage(List<JourneyStage> journeyStage) {
        this.journeyStage = journeyStage;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
