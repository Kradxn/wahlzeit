package org.wahlzeitext.model;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

@PatternInstance(
    patternName = "Abstract Factory",
    participants = {"ConcreteFactory"}
)
@PatternInstance(
    patternName = "Singleton",
    participants = {"Singleton"}
)
public class BicyclePhotoFactory extends PhotoFactory {
    protected static final BicyclePhotoFactory instance = new BicyclePhotoFactory();

    /**
     *
     */
    public static final BicyclePhotoFactory getInstance() {
        return instance;
    }

    @Override
    public BicyclePhoto createPhoto() {
        return new BicyclePhoto();
    }

    @Override
    public BicyclePhoto createPhoto(PhotoId id) {
        return new BicyclePhoto(id);
    }

    @Override
    public BicyclePhoto loadPhoto(PhotoId id) {
        return null;
    }


}
