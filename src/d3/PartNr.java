package d3;

import java.util.ArrayList;
import java.util.List;

public class PartNr {

    private int nr;
    private Coordinate firstCoordinate;

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public Coordinate getFirstCoordinate() {
        return firstCoordinate;
    }

    public void setFirstCoordinate(Coordinate firstCoordinate) {
        this.firstCoordinate = firstCoordinate;
    }

    public List<Coordinate> getAllCoordinates() {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(firstCoordinate);
        if (nr < 10) {
            return coordinates;
        }
        coordinates.add(new Coordinate(firstCoordinate.y(), firstCoordinate.x() + 1, "unknown"));
        if (nr < 100) {
            return coordinates;
        }
        coordinates.add(new Coordinate(firstCoordinate.y(), firstCoordinate.x() + 2, "unknown"));
        if (nr < 1000) {
            return coordinates;
        }
        coordinates.add(new Coordinate(firstCoordinate.y(), firstCoordinate.x() + 3, "unknown"));
        return coordinates;
    }

    @Override
    public String toString() {
        return firstCoordinate.toString() +": " + nr;
    }
}
