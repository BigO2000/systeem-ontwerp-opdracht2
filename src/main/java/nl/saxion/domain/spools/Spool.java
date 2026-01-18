package nl.saxion.domain.spools;

import nl.saxion.domain.enums.Color;
import nl.saxion.domain.enums.FilamentType;

public class Spool {
    private int id;
    private Color color;
    private FilamentType filamentType;
    private double length;

    // For Jackson
    public Spool(){}

    public Spool(int id, Color color, FilamentType filamentType, double length) {
        this.id = id;
        this.color = color;
        this.filamentType = filamentType;
        this.length = length;
    }

    public int getId() {
        return this.id;
    }

    public double getLength() {
        return length;
    }

    /**
     * This method will try to reduce the length of the spool.
     *
     * @param byLength
     * @return boolean which tells you if it is possible or not.
     */
    public boolean reduceLength(double byLength) {
        boolean success = true;
        this.length -= byLength;
        if (this.length < 0) {
            this.length -= byLength;
            success = false;
        }
        return success;
    }

    public Color getColor() {
        return color;
    }

    public FilamentType getFilamentType(){
        return filamentType;
    }

    @Override
    public String toString() {
        return  "--------" + System.lineSeparator() +
                "- id: " + id + System.lineSeparator() +
                "- color: " + color + System.lineSeparator() +
                "- filamentType: " + filamentType + System.lineSeparator() +
                "- length: " + length + System.lineSeparator() +
                "--------";
    }
}
