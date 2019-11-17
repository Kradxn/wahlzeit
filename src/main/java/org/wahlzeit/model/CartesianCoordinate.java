package org.wahlzeit.model;

import java.util.Objects;


/**
 * A 3 dimensional coordinate
 */
public class CartesianCoordinate implements Coordinate {
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

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @param other Coordinate to calculate distance to
     * @return euclidian distance as double between the two coordinates
     */
    public double getDistance(CartesianCoordinate other) {
        if (other == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }

        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    public boolean isEqual(CartesianCoordinate other) {
        if (other == null) return false;

        return compare(x,other.x) && compare(y,other.y) && compare(z,other.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || ! (o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
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

    @Override
    public CartesianCoordinate asCartesianCoordiante() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordiante) {
        return getDistance(coordiante.asCartesianCoordiante());
    }

    @Override
    public SphericCoordinate asSphericCoordiante() {
        double radius = Math.sqrt(this.x*this.x + this.y*this.y +this.z*this.z);
        double phi = Math.atan(this.y/this.x);
        if(this.x == 0) phi = Math.PI /2; //This fixes x==0-> NAAAAN
        double theta = Math.acos(z/radius);
        if(radius == 0) theta = 0; //This fixes radius==0-> NAAAAN
        return new SphericCoordinate(phi,theta,radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordiante) {
        return this.asSphericCoordiante().getCentralAngle(coordiante);
    }

    @Override
    public boolean isEqual(Coordinate coordiante) {
        if(coordiante == null) return false;
        return this.isEqual(coordiante.asCartesianCoordiante());
    }

    @Override
    public String toString() {
        return "CartesianCoordinate{" +
            "x=" + x +
            ", y=" + y +
            ", z=" + z +
            '}';
    }
}
