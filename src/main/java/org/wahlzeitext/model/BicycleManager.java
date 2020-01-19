package org.wahlzeitext.model;


import java.util.HashMap;

@PatternInstance(
    patternName = "Singleton",
    participants = {"Singleton"}
)
public class BicycleManager {

  private HashMap<String, BicycleType> bicycleTypes;

  public BicycleManager() {
    bicycleTypes = new HashMap<>();
    BicycleType twoWheeler = addBicycleType("2 Wheeler", null);
    BicycleType i17inch = addBicycleType("17inch", twoWheeler);

  }

  private BicycleType addBicycleType(String typeName, BicycleType superType) {
    BicycleType type = new BicycleType(superType, typeName);
    bicycleTypes.put(typeName, type);
    return type;
  }

  protected static final BicycleManager instance = new BicycleManager();

  public static final BicycleManager getInstance() {
    return instance;
  }


  public Bicycle createBicycle(String typeName, Location location) {
    assertIsValidBicycleTypeName(typeName);
    BicycleType type = getBicycleType(typeName);
    Bicycle result = type.createInstance(location);
    return result;
  }

  protected BicycleType getBicycleType(String typeName) {
    return bicycleTypes.get(typeName);
  }

  private void assertIsValidBicycleTypeName(String typeName) {
    assert typeName != null;
    assert !typeName.isEmpty();
    assert bicycleTypes.containsKey(typeName);
  }
}
