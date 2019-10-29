package org.wahlzeit.model;

import java.util.Objects;


/**
 * A 3 dimensional coordinate
 */
public class Coordiante {
    private double x, y, z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Coordiante(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @param other Coordinate to calculate distance to
     * @return euclidian distance as double between the two coordinates
     */
    public double getDistance(Coordiante other) {
        if (other == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }

        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    public boolean isEqual(Coordiante other) {
        if (other == null) return false;

        return compare(x,other.x) && compare(y,other.y) && compare(z,other.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordiante that = (Coordiante) o;
        return isEqual(that);
    }

    private static final double PRECISION = 1.0e-5;
    private static boolean compare(double d1, double d2){
        return Math.abs(d1-d2)<PRECISION;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
