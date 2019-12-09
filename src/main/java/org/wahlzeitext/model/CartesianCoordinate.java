package org.wahlzeitext.model;

import java.util.Objects;


/**
 * A 3 dimensional coordinate
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private double x, y, z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        assertClassInvariants();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        assertClassInvariants();
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
        assertClassInvariants();
    }

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    /**
     * @param other Coordinate to calculate distance to
     * @return euclidian distance as double between the two coordinates
     */
    public double getDistance(CartesianCoordinate other) throws InvalidCoordinateException {
        checkIsNonNullCoordinate(other);
        assertIsNonNullArgument(other);
        if (other == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }

        double distance = Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
        assertIsValidDistance(distance);
        return distance;
    }

    public boolean isEqual(CartesianCoordinate other) {
        if (other == null) return false;

        return compare(x,other.x) && compare(y,other.y) && compare(z,other.z);
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(Math.round(x / DIVPRECISION) / DIVPRECISION, Math.round(y / DIVPRECISION) / DIVPRECISION, Math.round(z / DIVPRECISION) / DIVPRECISION);
    }

    @Override
    protected void assertClassInvariants() {
        assert !Double.isNaN(x);
        assert !Double.isNaN(y);
        assert !Double.isNaN(z);
    }

    @Override
    public CartesianCoordinate asCartesianCoordiante() {
        return this;
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
    public String toString() {
        return "CartesianCoordinate{" +
            "x=" + x +
            ", y=" + y +
            ", z=" + z +
            '}';
    }
}
