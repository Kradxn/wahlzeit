package org.wahlzeit.model;

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
