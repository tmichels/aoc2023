package d1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d1", "input"));
        int numbers = lines.stream()
                .map(Part1::toList)
                .map(Part1::eliminateCharacters)
                .mapToInt(Part1::firstAndLast)
                .sum();
        System.out.println(numbers);
    }

    static ArrayList<String> toList(String line) {
        char[] charArray = line.toCharArray();
        ArrayList<String> strings = new ArrayList<>();
        for (char c : charArray) {
            strings.add("" + c);
        }
        return strings;
    }

    static List<Integer> eliminateCharacters(ArrayList<String> chars) {
        return chars.stream()
                .filter(Part1::charIsNr)
                .map(Part1::parse)
                .toList();
    }

     static boolean charIsNr(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static Integer parse(String s) {
        return Integer.parseInt(s);
    }

    static int firstAndLast(List<Integer> integers) {
        Integer first = integers.getFirst();
        Integer last = integers.getLast();
        return first * 10 + last;
    }
}