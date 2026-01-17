package nl.saxion.domain.prints;

import nl.saxion.domain.utils.Dimensions;

import java.util.List;

public record Print(String name, int id, int width, int length, int height, List<Double> filamentLength, int printTime) {
    public Dimensions getDimensions(){
        return new Dimensions(width, height, length);
    }
}
