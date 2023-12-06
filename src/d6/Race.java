package d6;

public record Race(long speed, long duration) {

    long getDistance() {
        return speed * duration;
    }
}
