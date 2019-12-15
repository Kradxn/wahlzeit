package org.wahlzeitext.model;

import java.util.HashMap;

/**
 * A 3 dimensional coordinate
 */
public abstract class AbstractCoordinate implements Coordinate {
  private static final HashMap<String,AbstractCoordinate> values = new HashMap<String,AbstractCoordinate>();

  public static AbstractCoordinate getSharedCoordinate(AbstractCoordinate coordiante){
      if(values.containsKey(coordiante.toString())){
        return values.get(coordiante.toString());
      }
      else{
        values.put(coordiante.toString(),coordiante);
        return coordiante;
      }
  }

  protected static final double PRECISION = 1.0e-5;
  protected static final double DIVPRECISION = 1 / 1.0e-5;

  protected static boolean compare(double d1, double d2) {
    return Math.abs(d1 - d2) < PRECISION;
  }


  @Override
  public double getCartesianDistance(Coordinate coordiante) throws InvalidCoordinateException {
    checkIsNonNullCoordinate(coordiante);
    assertIsNonNullArgument(coordiante);
    double distance = this.asCartesianCoordiante().getDistance(coordiante.asCartesianCoordiante());
    assertIsValidDistance(distance);
    return distance;
  }

  @Override
  public double getCentralAngle(Coordinate coordiante) throws InvalidCoordinateException {
    checkIsNonNullCoordinate(coordiante);
    assertIsNonNullArgument(coordiante);
    double distance =  this.asSphericCoordiante().getCentralAngle(coordiante);
    assertIsValidDistance(distance);
    return distance;
  }

  @Override
  public boolean isEqual(Coordinate coordiante) {
    if (coordiante == null) return false;
    return this.asCartesianCoordiante().isEqual(coordiante.asCartesianCoordiante());
  }


  protected abstract void assertClassInvariants();

  protected void assertIsNonNullArgument(Object c) {
    assert c!=null;
  }

  protected void checkIsNonNullCoordinate(Object c) throws InvalidCoordinateException {
    if (c == null) throw new InvalidCoordinateException("coordiante is null");
  }

  protected void assertIsValidDistance(double distance){
    assert !Double.isNaN(distance);
    assert distance>=0;
  }

}
