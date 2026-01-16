package nl.saxion.prints;

import java.util.List;

public record Print(String name, int id, int width, int length, int height, List<Double> filamentLength, int printTime) {
}
