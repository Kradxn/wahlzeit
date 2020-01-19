package org.wahlzeitext.model;

import com.googlecode.objectify.annotation.IgnoreSave;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@Subclass
@PatternInstance(
    patternName = "Abstract Factory",
    participants = {"ConcreteProduct"}
)

public class BicyclePhoto extends Photo {

    private Bicycle bicycle;

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycle) {
        this.bicycle = bicycle;
    }

    public BicyclePhoto() {

    }

    public BicyclePhoto(PhotoId id) {

    }
}
