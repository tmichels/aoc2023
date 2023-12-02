package d1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src", "d1", "input"));
        int numbers = lines.stream()
                .map(Part2::replaceWrittenNumbers)
                .map(Part1::toList)
                .map(Part1::eliminateCharacters)
                .mapToInt(Part1::firstAndLast)
                .sum();
        System.out.println(numbers);
    }

    private static String replaceWrittenNumbers(String s) {
        String newSequence = "";
        for (int i = 0; i < s.length(); i++) {
            if (Part1.charIsNr("" + s.charAt(i))) {
                newSequence += s.charAt(i);
            } else {
                if (s.substring(i).startsWith("one")) {
                    newSequence += 1;
                } else if (s.substring(i).startsWith("two")) {
                    newSequence += 2;
                } else if (s.substring(i).startsWith("three")) {
                    newSequence += 3;
                } else if (s.substring(i).startsWith("four")) {
                    newSequence += 4;
                } else if (s.substring(i).startsWith("five")) {
                    newSequence += 5;
                } else if (s.substring(i).startsWith("six")) {
                    newSequence += 6;
                } else if (s.substring(i).startsWith("seven")) {
                    newSequence += 7;
                } else if (s.substring(i).startsWith("eight")) {
                    newSequence += 8;
                } else if (s.substring(i).startsWith("nine")) {
                    newSequence += 9;
                } else {
                    newSequence += s.charAt(i);
                }
            }
        }
        return newSequence;
    }

}