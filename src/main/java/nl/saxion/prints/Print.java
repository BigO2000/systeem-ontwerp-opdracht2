package nl.saxion.prints;

import nl.saxion.printers.Dimensions;

import java.util.ArrayList;
import java.util.List;

public class Print {
    private String name;
    private Dimensions dimensions;
    private List<Double> filamentLength;
    private int printTime;

    public Print(String name, Dimensions dimensions, List<Double> filamentLength, int printTime) {
        this.name = name;
        this.dimensions = dimensions;
        this.filamentLength = filamentLength;
        this.printTime = printTime;
    }

    @Override
    public String toString() {
        return "--------" + System.lineSeparator() +
                "- Name: " + name + System.lineSeparator() +
                "- Height: " + height + System.lineSeparator() +
                "- Width: " + width + System.lineSeparator() +
                "- Length: " + length + System.lineSeparator() +
                "- FilamentLength: " + filamentLength + System.lineSeparator() +
                "- Print Time: " + printTime + System.lineSeparator() +
                "--------";
    }

    public String getName() {
        return name;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    public ArrayList<Double> getFilamentLength() {
        return filamentLength;
    }
}
