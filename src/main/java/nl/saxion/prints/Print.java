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
