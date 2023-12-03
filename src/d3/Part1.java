package d3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d3", "input"));
        List<PartNr> potentialPartNumbers = getPotentialPartNumbers(lines);
        int sum = potentialPartNumbers.stream()
                .filter(partNr -> partNumberIsValid(partNr, lines))
                .mapToInt(PartNr::getNr)
                .sum();
        System.out.println(sum);
    }

    static List<PartNr> getPotentialPartNumbers(List<String> lines) {
        List<Coordinate> coordinatesWithNumber = getCoordinates(lines, Character::isDigit);
        List<PartNr> partNrs = new ArrayList<>();
        String nr = "";
        PartNr partNr = new PartNr();
        partNr.setFirstCoordinate(coordinatesWithNumber.get(0));
        for (int i = 0; i < coordinatesWithNumber.size(); i++) {
            Coordinate coordinate = coordinatesWithNumber.get(i);
            if (i == 0) {
                nr += coordinate.content();
            } else {
                if (coordinatesWithNumber.get(i - 1).x() == coordinate.x() - 1) {
                    nr += coordinate.content();
                } else {
                    partNr.setNr(Integer.parseInt(nr));
                    partNrs.add(partNr);
                    partNr = new PartNr();
                    partNr.setFirstCoordinate(coordinate);
                    nr = coordinate.content();
                }
            }
        }
        partNr.setNr(Integer.parseInt(nr));
        partNrs.add(partNr);
        return partNrs;
    }

    private static boolean coordinateIsSurroundedBySymbol(Coordinate coordinate, List<String> lines) {
        int x = coordinate.x();
        int y = coordinate.y();

        Coordinate left = getCoordinate(lines, x - 1, y);
        Coordinate leftAbove = getCoordinate(lines, x - 1, y - 1);
        Coordinate above = getCoordinate(lines, x, y - 1);
        Coordinate rightAbove = getCoordinate(lines, x + 1, y - 1);
        Coordinate right = getCoordinate(lines, x + 1, y);
        Coordinate rightBelow = getCoordinate(lines, x + 1, y + 1);
        Coordinate below = getCoordinate(lines, x, y + 1);
        Coordinate leftBelow = getCoordinate(lines, x - 1, y + 1);

        return isSymbol(left) || isSymbol(leftAbove) || isSymbol(above) || isSymbol(rightAbove) || isSymbol(right) || isSymbol(rightBelow) || isSymbol(below) || isSymbol(leftBelow);
    }

    static Coordinate getCoordinate(List<String> lines, int x, int y) {
        try {
            String line = lines.get(y);
            char[] charArray = line.toCharArray();
            return new Coordinate(y, x, Character.toString(charArray[x]));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static boolean isSymbol(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        return coordinate.isSymbol();
    }

    static List<Coordinate> getCoordinates(List<String> lines, Predicate<Character> predicate) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int lineNr = 0; lineNr < lines.size(); lineNr++) {
            String line = lines.get(lineNr);
            char[] charArray = line.toCharArray();
            for (int charNr = 0; charNr < charArray.length; charNr++) {
                char c = charArray[charNr];
                if (predicate.test(c)) {
                    coordinates.add(new Coordinate(lineNr, charNr, Character.toString(c)));
                }
            }
        }
        return coordinates;
    }

    private static boolean partNumberIsValid(PartNr partNr, List<String> lines) {
        List<Coordinate> allCoordinates = partNr.getAllCoordinates();
        for (Coordinate coordinate : allCoordinates) {
            if (coordinateIsSurroundedBySymbol(coordinate, lines)) {
                return true;
            }
        }
        return false;
    }
}
