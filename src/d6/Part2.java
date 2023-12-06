package d6;

import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {
        long time = 53916768L;
        long distances = 250133010811025L;

        int wins = 0;
        for (int j = 0; j < time; j++) {
            Race race = new Race(j, time - j);
            long distance = race.getDistance();
            if (distance > distances) {
                wins++;
            }
        }
        System.out.println(wins);
    }

}
