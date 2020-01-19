package org.wahlzeitext.model;

public class BicycleType {

  protected String superType = null;

  protected String name;


  protected BicycleType(BicycleType superType, String name) {
    this.superType = superType.getName();
    this.name = name;
  }

  public boolean isSubtype() {
    return superType != null;
  }

  public Bicycle createInstance(Location location) {
    return new Bicycle(location, this);
  }

  public BicycleType getSuperType() {
    return BicycleManager.getInstance().getBicycleType(superType);
  }

  public String getName() {
    return name;
  }

}
