package day9;

public enum Direction {
    R(new Coords(1, 0)),
    L(new Coords(-1, 0)),
    U(new Coords(0, -1)),
    D(new Coords(0, 1));

    public Coords coords;

    Direction(Coords coords) {
        this.coords = coords;
        this.coords = coords;
    }
}
