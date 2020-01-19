package org.wahlzeitext.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BicycleType  {

  protected String superType = null;

  protected String name;


  protected BicycleType(BicycleType superType, String name) {
    this.superType = superType == null? null: superType.getName();
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
