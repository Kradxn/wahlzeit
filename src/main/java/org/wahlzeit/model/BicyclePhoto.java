package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BicyclePhoto extends Photo {

    private String brandname;

    public BicyclePhoto() {

    }

    public BicyclePhoto(PhotoId id) {

    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
}
