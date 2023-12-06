package d6;

import java.io.IOException;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {
        List<Integer> times = List.of(53, 91, 67, 68);
        List<Integer> distances = List.of(250, 1330, 1081, 1025);
        int totalWinFactor = 1;

        for (int i = 0; i < times.size(); i++) {
            int wins = 0;
            for (int j = 0; j < times.get(i); j++) {
                Race race = new Race(j, times.get(i) - j);
                long distance = race.getDistance();
                if (distance > distances.get(i)) {
                    wins++;
                }
            }
            totalWinFactor*= wins;


        }
        System.out.println(totalWinFactor);

    }

}
