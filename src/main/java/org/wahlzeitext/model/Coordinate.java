package org.wahlzeitext.model;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordiante();

    double getCartesianDistance(Coordinate coordiante) throws InvalidCoordinateException;
    SphericCoordinate asSphericCoordiante();

    double getCentralAngle(Coordinate coordiante) throws InvalidCoordinateException;
    boolean isEqual(Coordinate coordiante);
}
