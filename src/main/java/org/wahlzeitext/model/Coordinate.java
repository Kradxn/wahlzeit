package org.wahlzeitext.model;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordiante();
    double getCartesianDistance(Coordinate coordiante);
    SphericCoordinate asSphericCoordiante();
    double getCentralAngle(Coordinate coordiante);
    boolean isEqual(Coordinate coordiante);
}
