package d6;

public record Race(int speed, int duration) {

    int getDistance() {
        return speed * duration;
    }
}
