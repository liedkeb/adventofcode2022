package day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static day2.RPS.ROCK;
import static day2.RPS.PAPER;
import static day2.RPS.SCISSORS;
import static day2.Result.DRAW;
import static day2.Result.LOSE;
import static day2.Result.WIN;

public class Day2 {
    public static void main(String[] args) {
        Map<String, RPS> letterToRPS = Map.of("A", ROCK, "B", PAPER, "C", SCISSORS,
                "X", ROCK, "Y", PAPER, "Z", SCISSORS);

        Map<String, Result> letterToResult = Map.of("X", LOSE, "Y", DRAW, "Z", WIN);

        Map<Game, Result> gameToPoints = Map.of(
                new Game(ROCK, ROCK), DRAW,
                new Game(ROCK, PAPER), WIN,
                new Game(PAPER, ROCK), LOSE,
                new Game(PAPER, PAPER), DRAW,
                new Game(PAPER, SCISSORS), WIN,
                new Game(SCISSORS, PAPER), LOSE,
                new Game(SCISSORS, SCISSORS), DRAW,
                new Game(SCISSORS, ROCK), WIN,
                new Game(ROCK, SCISSORS), LOSE
        );

        Map<GameResult, RPS> gameToRPS = Map.of(
                new GameResult(ROCK, DRAW), ROCK,
                new GameResult(ROCK, WIN), PAPER,
                new GameResult(PAPER, LOSE), ROCK,
                new GameResult(PAPER, DRAW), PAPER,
                new GameResult(PAPER, WIN), SCISSORS,
                new GameResult(SCISSORS, LOSE), PAPER,
                new GameResult(SCISSORS, DRAW), SCISSORS,
                new GameResult(SCISSORS, WIN), ROCK,
                new GameResult(ROCK, LOSE), SCISSORS
        );

        URL url = Day2.class.getClassLoader().getResource("day2/example.txt");
        if (url == null) {
            System.out.println("file not found...");
            return;
        }
        try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
            long myTotalPoints = 0;
            long round2TotalPoints = 0;
            for (String line : lines.toList()) {
                String[] splitLine = line.split(" ");
                RPS opponent = letterToRPS.get(splitLine[0]);
                RPS me = letterToRPS.get(splitLine[1]);
                Result result = letterToResult.get(splitLine[1]);

                myTotalPoints += me.getPoints();
                myTotalPoints += gameToPoints.get(new Game(opponent, me)).getPoints();

                round2TotalPoints += result.getPoints();
                round2TotalPoints += gameToRPS.get(new GameResult(opponent, result)).getPoints();
            }
            System.out.println("Total points = " + myTotalPoints);
            System.out.println("Total points round 2= " + round2TotalPoints);

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
