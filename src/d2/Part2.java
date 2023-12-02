package d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d2", "input"));
        int sum = lines.stream()
                .map(Part1::toGame)
                .mapToInt(Part2::getMultipliedMinimum)
                .sum();
        System.out.println(sum);
    }

    private static int getMultipliedMinimum(Game game) {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        for (SubGame subGame : game.subGames()) {
            minRed = Integer.max(minRed, subGame.red());
            minGreen = Integer.max(minGreen, subGame.green());
            minBlue = Integer.max(minBlue, subGame.blue());
        }
        return minRed * minGreen * minBlue;
    }

}
