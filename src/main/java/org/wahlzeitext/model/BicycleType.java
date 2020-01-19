package org.wahlzeitext.model;

public class BicycleType {

  protected BicycleType superType = null;

  protected String name;


  protected BicycleType(BicycleType superType, String name) {
    this.superType = superType;
    this.name = name;
  }

  public boolean isSubtype() {
    return superType != null;
  }

  public Bicycle createInstance(Location location) {
    return new Bicycle(location, this);
  }

  public BicycleType getSuperType() {
    return superType;
  }

  public String getName() {
    return name;
  }

}
