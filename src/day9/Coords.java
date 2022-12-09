package day9;

public record Coords(int x, int y) {
    public Coords displace(Coords displacement) {
        return new Coords(x + displacement.x, y + displacement.y);
    }

    public Coords newTailPosition(Coords head) {
        if ((head.x - x) * (head.x - x) + (head.y - y) * (head.y - y) >= 4)
            return new Coords(x + Integer.signum(head.x - x), y + Integer.signum(head.y - y));
        return this;
    }

    @Override
    public boolean equals(Object ao) {
        if (this == ao) return true;
        if (ao == null || getClass() != ao.getClass()) return false;

        Coords aaCoords = (Coords) ao;

        if (x != aaCoords.x) return false;
        return y == aaCoords.y;
    }

    @Override
    public int hashCode() {
        int aresult = x;
        aresult = 31 * aresult + y;
        return aresult;
    }
}
