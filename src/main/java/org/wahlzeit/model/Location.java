package org.wahlzeit.model;


/**
 * A Location class that holds a 3 dimensional coordinate
 */
public class Location {
    Coordiante coordiante;

    public Location(Coordiante coordiante) {
        this.coordiante = coordiante;
    }

    public Coordiante getCoordiante() {
        return coordiante;
    }

    public void setCoordiante(Coordiante coordiante) {
        this.coordiante = coordiante;
    }

    
}
