package d4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d4", "input"));
        List<List<Integer>> winningList = getWinningList(lines);
        List<List<Integer>> ownList = getOwnList(lines);
        int totalPoints = 0;
        for (int cardNr = 0; cardNr < ownList.size(); cardNr++) {
            int nrWins = getNrWinsPerCard(ownList.get(cardNr), winningList.get(cardNr));
            totalPoints += getPoints(nrWins);
        }
        System.out.println(totalPoints);
    }

    private static List<List<Integer>> getWinningList(List<String> lines) {
        return lines.stream()
                .map(s -> s.split("\\|")[0])
                .map(s -> s.substring(10))
                .map(Part1::getNumberList)
                .toList();
    }

    private static List<List<Integer>> getOwnList(List<String> lines) {
        return lines.stream()
                .map(s -> s.split("\\|")[1])
                .map(Part1::getNumberList)
                .toList();
    }

    private static List<Integer> getNumberList(String onlyNrs) {
        String[] split = onlyNrs.split(" ");
        return Arrays.stream(split)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private static int getNrWinsPerCard(List<Integer> own, List<Integer> winning) {
        int nrWins = 0;
        for (Integer nrOwn : own) {
            if (winning.contains(nrOwn)) {
                nrWins++;
            }
        }
        return nrWins;
    }

    private static int getPoints(int nrWins) {
        if (nrWins == 0) {
            return 0;
        }
        int points = 1;
        for (int i = 0; i < nrWins - 1; i++) {
            points = points * 2;
        }
        return points;
    }

}
