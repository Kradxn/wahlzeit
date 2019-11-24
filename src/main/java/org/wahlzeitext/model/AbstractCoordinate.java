package org.wahlzeitext.model;

/**
 * A 3 dimensional coordinate
 */
public abstract class AbstractCoordinate implements Coordinate {


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Coordinate)) return false;
    Coordinate that = (Coordinate) o;
    return isEqual(that);
  }

  @Override
  public int hashCode() {
    return this.asCartesianCoordiante().hashCode();
  }

  protected static final double PRECISION = 1.0e-5;
  protected static final double DIVPRECISION = 1 / 1.0e-5;

  protected static boolean compare(double d1, double d2) {
    return Math.abs(d1 - d2) < PRECISION;
  }


  @Override
  public double getCartesianDistance(Coordinate coordiante) {
    return this.asCartesianCoordiante().getDistance(coordiante.asCartesianCoordiante());
  }

  @Override
  public double getCentralAngle(Coordinate coordiante) {
    return this.asSphericCoordiante().getCentralAngle(coordiante);
  }

  @Override
  public boolean isEqual(Coordinate coordiante) {
    if (coordiante == null) return false;
    return this.asCartesianCoordiante().isEqual(coordiante.asCartesianCoordiante());
  }

}
