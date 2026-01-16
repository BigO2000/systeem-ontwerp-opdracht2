package nl.saxion.prints;

import nl.saxion.printers.Dimensions;

import java.util.List;

public class Print {
    private String name;
    private int id;
    private Dimensions dimensions;
    private List<Double> filamentLength;
    private int printTime;

    public Print(String name, int id, Dimensions dimensions, List<Double> filamentLength, int printTime) {
        this.name = name;
        this.dimensions = dimensions;
        this.filamentLength = filamentLength;
        this.printTime = printTime;
        this.id = id;
    }

    @Override
    public String toString() {
        return "--------" + System.lineSeparator() +
                "- Name: " + name + System.lineSeparator() +
                "- Height: " + dimensions.y()+ System.lineSeparator() +
                "- Width: " + dimensions.x() + System.lineSeparator() +
                "- Length: " + dimensions.z() + System.lineSeparator() +
                "- FilamentLength: " + filamentLength + System.lineSeparator() +
                "- Print Time: " + printTime + System.lineSeparator() +
                "--------";
    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

    public Dimensions getDimensions(){
        return dimensions;
    }

    public List<Double> getFilamentLength() {
        return filamentLength;
    }
}
