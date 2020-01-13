package org.wahlzeitext.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@Subclass
@PatternInstance(
    patternName = "Abstract Factory",
    participants = {"ConcreteProduct"}
)
public class BicyclePhoto extends Photo {

    private String brandname;

    public BicyclePhoto() {

    }

    public BicyclePhoto(PhotoId id) {

    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) throws InvalidBrandnameException {
        if (brandname == null || brandname.isEmpty()) throw new InvalidBrandnameException("Brandname is null or empty");
        this.brandname = brandname;
    }
}
