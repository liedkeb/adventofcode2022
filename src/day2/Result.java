package day2;

public enum Result {
    LOSE(0),
    DRAW(3),
    WIN(6);

    private int points;

    Result(int points) {
        this.points = points;
    }


    public int getPoints() {
        return points;
    }
}
