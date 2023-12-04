package d4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d4", "input"));
        List<List<Integer>> winningList = Part1.getWinningList(lines);
        List<List<Integer>> ownList = Part1.getOwnList(lines);

        Map<Integer, Integer> cardsPerGame = getInitialCardMap(ownList);

        for (int gameNr = 0; gameNr < ownList.size(); gameNr++) {
            int winningRange = Part1.getNrWinsPerCard(ownList.get(gameNr), winningList.get(gameNr));
            int nrOfCopiesToAdd = cardsPerGame.get(gameNr);
            for (int i = 1; i <= winningRange; i++) {
                int gameNrInRange = gameNr + i;
                int currentNrOfCards = cardsPerGame.get(gameNrInRange);
                cardsPerGame.put(gameNrInRange, currentNrOfCards + nrOfCopiesToAdd);
            }
        }
        int sum = cardsPerGame.values().stream().mapToInt(n -> n).sum();
        System.out.println(sum);
    }

    private static Map<Integer, Integer> getInitialCardMap(List<List<Integer>> ownList) {
        Map<Integer, Integer> cardsPerGame = new HashMap<>();
        for (int i = 0; i < ownList.size(); i++) {
            cardsPerGame.put(i, 1);
        }
        return cardsPerGame;
    }

}
