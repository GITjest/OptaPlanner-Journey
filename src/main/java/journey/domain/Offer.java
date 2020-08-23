package journey.domain;

import java.util.Arrays;

public class Offer extends AbstractPersistable{
    private int journeyStageId;
    private long[] integers;
    private double[] reals;

    public Offer() {
    }

    public Offer(long id, int journeyStageId, long[] integers, double[] reals) {
        super(id);
        this.journeyStageId = journeyStageId;
        this.integers = integers;
        this.reals = reals;
    }

    public int getJourneyStageId() {
        return journeyStageId;
    }

    public void setJourneyStageId(int journeyStageId) {
        this.journeyStageId = journeyStageId;
    }

    public long[] getIntegers() {
        return integers;
    }

    public void setIntegers(long[] integers) {
        this.integers = integers;
    }

    public double[] getReals() {
        return reals;
    }

    public void setReals(double[] reals) {
        this.reals = reals;
    }

    @Override
    public String toString() {
        return id + "(" + journeyStageId + ") - " + Arrays.toString(integers) + " | " + Arrays.toString(reals);
    }
}