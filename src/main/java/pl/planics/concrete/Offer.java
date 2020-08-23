package pl.planics.concrete;

import java.util.Arrays;
import planics.common.concrete.IOffer;

/**
 *
 * @author Arturro
 */
public class Offer implements IOffer{
    private final int id; // id oferty
    private final int sid; // id uslugi
    private final long[] integers;
    private final double[] reals;

    public Offer(int id, int sid, long[] integers, double[] reals) {
        this.id = id;
        this.sid = sid;
        this.integers = integers;
        this.reals = reals;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getServiceId() {
        return sid;
    }

    @Override
    public long[] getIntegerValues() {
        return integers;
    }

    @Override
    public double[] getDoubleValues() {
        return reals;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", sid=" + sid + ", integers=" + Arrays.toString(integers) + ", reals=" + Arrays.toString(reals) + '}';
    }
}

