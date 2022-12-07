package day2;

public enum RPS {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private int points;

    RPS(int points) {
        this.points = points;
    }


    public int getPoints() {
        return points;
    }
}
