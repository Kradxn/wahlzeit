package org.wahlzeitext.model;

import com.googlecode.objectify.annotation.Subclass;


/**
 * A 3 dimensional coordinate
 */
@Subclass
public class CartesianCoordinate extends AbstractCoordinate {
    private double x, y, z;

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public double getZ() {
        return z;
    }


    private CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariants();
    }

    public static CartesianCoordinate constructCartesianCoordinate(double x, double y, double z) {
         CartesianCoordinate cartesianCoordinate1 = new CartesianCoordinate(x,y,z);
         return (CartesianCoordinate) getSharedCoordinate(cartesianCoordinate1);
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
        return SphericCoordinate.constructSphericCoordinate(phi,theta,radius);
    }

    @Override
    public String toString() {
        return "CartesianCoordinate{" +
            "x=" + x +
            ", y=" + y +
            ", z=" + z +
            '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this;
    }
}
