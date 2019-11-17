package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements Coordinate {
  private double phi;
  private double theta;
  private double radius;

  public SphericCoordinate(double phi, double theta, double radius) {
    this.phi = phi;
    this.theta = theta;
    this.radius = radius;
  }

  @Override
  public CartesianCoordinate asCartesianCoordiante() {
    double x = radius * Math.sin(theta) * Math.cos(phi);
    double y = radius * Math.sin(theta) * Math.sin(phi);
    double z = radius * Math.cos(theta);
    return new CartesianCoordinate(x,y,z);
  }

  @Override
  public double getCartesianDistance(Coordinate coordiante) {
    return this.asCartesianCoordiante().getDistance(coordiante.asCartesianCoordiante());
  }

  @Override
  public SphericCoordinate asSphericCoordiante() {
    return this;
  }

  // Implemented after https://en.wikipedia.org/wiki/Great-circle_distance
  @Override
  public double getCentralAngle(Coordinate coordiante) {
    SphericCoordinate s2 = coordiante.asSphericCoordiante();

    // Check if points have same radius
    if(Math.abs(s2.radius-radius)>1.0e-5){
      throw new IllegalArgumentException("Points dont have same radius");
    }

    double delta_phi = Math.abs(s2.phi-this.phi);
    double delta_theta = Math.abs(s2.theta-this.theta);
    double x = Math.pow(Math.sin(delta_phi/2),2);
    double y = Math.pow(Math.sin(delta_theta/2),2);

    return 2 * Math.asin(Math.sqrt(y+Math.cos(this.theta)*Math.cos(s2.theta)*x));
  }

  @Override
  public boolean isEqual(Coordinate coordinate) {
    if(coordinate == null) return false;
    return coordinate.asCartesianCoordiante().isEqual(this.asCartesianCoordiante());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || ! (o instanceof Coordinate)) return false;
    Coordinate that = (Coordinate) o;
    return isEqual(that);
  }

  @Override
  public int hashCode() {
    return Objects.hash(radius, theta, phi);
  }

  @Override
  public String toString() {
    return "SphericCoordinate{" +
        "phi=" + phi +
        ", theta=" + theta +
        ", radius=" + radius +
        '}';
  }

  public double getPhi() {
    return phi;
  }

  public void setPhi(double phi) {
    this.phi = phi;
  }

  public double getTheta() {
    return theta;
  }

  public void setTheta(double theta) {
    this.theta = theta;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }
}
