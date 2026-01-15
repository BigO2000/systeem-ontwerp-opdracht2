package nl.saxion.printers;

import nl.saxion.printers.behaviours.ColorBehaviour;
import nl.saxion.printers.behaviours.HousingBehaviour;
import nl.saxion.prints.Print;

import java.util.List;

public class Printer {
    private final ColorBehaviour colorBehaviour;
    private final HousingBehaviour housingBehaviour;

    private final int id;
    private final String name;
    private final String manufacturer;
    private final Dimensions dimensions;

    public Printer(int id, String printerName, String manufacturer, ColorBehaviour colorBehaviour, HousingBehaviour housingBehaviour, Dimensions dimensions) {
        this.id = id;
        this.name = printerName;
        this.manufacturer = manufacturer;
        this.colorBehaviour = colorBehaviour;
        this.housingBehaviour = housingBehaviour;
        this.dimensions = dimensions;
    }

    public int getId() {
        return id;
    }

    public int CalculatePrintTime(String filename) {
        return 0;
    }

    public List<Integer> getCurrentSpools() {
        return colorBehaviour.getCurrentSpools();
    }

    public void setCurrentSpools(List<Integer> spools) {
        colorBehaviour.setCurrentSpools(spools);
    }

    public boolean printFits(Print print) {
        return false;
    }

    @Override
    public String toString() {
        return "--------" + System.lineSeparator() +
                "- ID: " + id + System.lineSeparator() +
                "- Name: " + name + System.lineSeparator() +
                "- Manufacturer: " + manufacturer + System.lineSeparator() +
                colorBehaviour.toString() +
                // TODO: fix to String

                "--------";
    }

    public String getName() {
        return name;
    }
}
