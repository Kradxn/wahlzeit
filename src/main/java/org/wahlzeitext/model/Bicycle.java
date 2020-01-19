package org.wahlzeitext.model;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;


@Subclass
public class Bicycle {

  @Ignore
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
