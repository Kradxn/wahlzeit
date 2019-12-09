package org.wahlzeitext.model;


/**
 * A Location class that holds a 3 dimensional coordinate
 */
public class Location {
    private Coordinate coordinate;

    public Location(Coordinate coordinate) throws InvalidCoordinateException {
        if (coordinate == null) throw new InvalidCoordinateException("Coordinate is null");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    
}
