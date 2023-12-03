package d3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d3", "input"));
        List<Coordinate> coordinatesWithAsterisk = Part1.getCoordinates(lines, c -> c == '*');
        List<PartNr> potentialPartNumbers = Part1.getPotentialPartNumbers(lines);

        System.out.println(getAdjacentPartNrValue(coordinatesWithAsterisk, lines, potentialPartNumbers));
    }

    private static int getAdjacentPartNrValue(List<Coordinate> coordinatesWithAsterisk, List<String> lines, List<PartNr> potentialPartNumbers) {
        int nr = 0;
        for (Coordinate coordinate : coordinatesWithAsterisk) {
            if (asteriskSurroundedByPartNumber(coordinate, lines)) {
                Set<PartNr> adjacentPartNumbers = getAdjacentPartNumbers(potentialPartNumbers, coordinate);
                Integer multiply = adjacentPartNumbers.stream()
                        .map(PartNr::getNr)
                        .reduce((a, b) -> a * b)
                        .get();
                nr += multiply;
            }
        }
        return nr;
    }

    private static boolean asteriskSurroundedByPartNumber(Coordinate coordinate, List<String> lines) {
        int x = coordinate.x();
        int y = coordinate.y();

        boolean left = Part1.getCoordinate(lines, x - 1, y).isDigit();
        boolean leftAbove = Part1.getCoordinate(lines, x - 1, y - 1).isDigit();
        boolean above = Part1.getCoordinate(lines, x, y - 1).isDigit();
        boolean rightAbove = Part1.getCoordinate(lines, x + 1, y - 1).isDigit();
        boolean right = Part1.getCoordinate(lines, x + 1, y).isDigit();
        boolean rightBelow = Part1.getCoordinate(lines, x + 1, y + 1).isDigit();
        boolean below = Part1.getCoordinate(lines, x, y + 1).isDigit();
        boolean leftBelow = Part1.getCoordinate(lines, x - 1, y + 1).isDigit();

        int nrOfPartsAbove = getAboveOrBelow(leftAbove, above, rightAbove);
        int nrOfPartsBelow = getAboveOrBelow(leftBelow, below, rightBelow);
        int nrOfPartsRight = right ? 1 : 0;
        int nrOfPartsLeft = left ? 1 : 0;
        return nrOfPartsAbove + nrOfPartsBelow + nrOfPartsRight + nrOfPartsLeft > 1;
    }

    private static int getAboveOrBelow(boolean left, boolean middle, boolean right) {
        if (left && !middle && right) {
            return 2;
        }
        if (left || middle || right) {
            return 1;
        }
        return 0;
    }

    private static Set<PartNr> getAdjacentPartNumbers(List<PartNr> potentialPartNumbers, Coordinate coordinateWithAsterisk) {
        Set<PartNr> partNrs= new HashSet<>();
        for (PartNr potentialPartNumber : potentialPartNumbers) {
            List<Coordinate> coordinatesInPart = potentialPartNumber.getAllCoordinates();
            for (Coordinate coordinate : coordinatesInPart) {
                if (coordinatesAreAdjacent(coordinate, coordinateWithAsterisk)) {
                    partNrs.add(potentialPartNumber);
                }
            }
        }
        return partNrs;
    }

    private static boolean coordinatesAreAdjacent(Coordinate coordinate1, Coordinate coordinate2) {
        int x1 = coordinate1.x();
        int y1 = coordinate1.y();

        boolean horizontalMatch = x1 == coordinate2.x() || x1 == coordinate2.x() - 1 || x1 == coordinate2.x() + 1;
        boolean verticalMatch = y1 == coordinate2.y() || y1 == coordinate2.y() - 1 || y1 == coordinate2.y() + 1;
        return horizontalMatch && verticalMatch;
    }
}
