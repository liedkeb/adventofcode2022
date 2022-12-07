package day4;

public class Range {
    private final int low;
    private final int high;

    public Range(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public boolean contains(Range range) {
        return range.low >= low && range.high <= high;
    }

    public boolean overlaps(Range range) {
        return low <= range.high && high >= range.low;
    }
}
