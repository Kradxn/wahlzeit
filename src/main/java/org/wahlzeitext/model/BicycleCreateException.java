package org.wahlzeitext.model;

public class BicycleCreateException extends Exception {

  public BicycleCreateException(Throwable cause) {
    super(cause);
  }

  @Override
  public String toString() {
    return "BicycleCreateException caused by " + super.toString();
  }
}
