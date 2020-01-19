package org.wahlzeitext.model;

import org.wahlzeit.services.DataObject;

public class Bicycle extends DataObject {
  protected Location location;
  protected BicycleType type;

  public Bicycle(Location location, BicycleType type) {
    this.location = location;
    this.type = type;
  }

  public Location getLocation() {
    return location;
  }

  public BicycleType getType() {
    return type;
  }
}
