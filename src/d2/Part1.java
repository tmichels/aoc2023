package d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    final static int RED = 12;
    final static int GREEN = 13;
    final static int BLUE = 14;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d2", "input"));
        int sum = lines.stream()
                .map(Part1::toGame)
                .filter(Part1::isPossible)
                .mapToInt(Game::gameNr)
                .sum();
        System.out.println(sum);
    }

    private static Game toGame(String line) {
        String[] split = line.split(":");
        int gameNumber = getGameNumber(split[0]);
        List<SubGame> subGames = getSubGames(split[1]);
        return new Game(gameNumber, subGames);
    }

    private static int getGameNumber(String s) {
        return Integer.parseInt(s.substring(5));
    }

    private static List<SubGame> getSubGames(String subGames) {
        return Arrays.stream(subGames.split(";"))
                .map(String::trim)
                .map(Part1::getSubGame)
                .toList();
    }

    private static SubGame getSubGame(String subGameAsString) {
        String[] draws = subGameAsString.split(", ");
        int red = 0;
        int green = 0;
        int blue = 0;
        for (String draw : draws) {
            int nr = Integer.parseInt(draw.split(" ")[0]);
            String colour = draw.split(" ")[1];
            switch (colour) {
                case "red" -> red = nr;
                case "green" -> green = nr;
                case "blue" -> blue = nr;
            }
        }
        return new SubGame(red, green, blue);
    }

    private static boolean isPossible(Game game) {
        for (SubGame subGame : game.subGames()) {
            if (subGame.green() > GREEN || subGame.blue() > BLUE || subGame.red() > RED) {
                return false;
            }
        }
        return true;
    }

}
