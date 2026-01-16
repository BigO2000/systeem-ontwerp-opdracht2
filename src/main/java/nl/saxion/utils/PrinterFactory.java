package nl.saxion.utils;

import nl.saxion.printers.Dimensions;
import nl.saxion.printers.Printer;
import nl.saxion.printers.PrinterHelper;
import nl.saxion.printers.behaviours.*;

import java.util.HashSet;
import java.util.Set;

import static nl.saxion.utils.PrinterFeature.HOUSED;
import static nl.saxion.utils.PrinterFeature.MULTI_COLOR;

public class PrinterFactory {
    // Method for creating printers using a PrinterHelper (Used when reading printers from file.)
    public static Printer create(PrinterHelper p) {
        Set<PrinterFeature> features = new HashSet<>();

        if (p.maxColors() > 1) {
            features.add(MULTI_COLOR);
        }

        return create(p.id(),
                p.name(),
                p.manufacturer(),
                features,
                new Dimensions(p.maxX(), p.maxY(), p.maxZ())
        );
    }

    public static Printer create(int id, String name, String manufacturer, Set<PrinterFeature> features, Dimensions dimensions) {
        // Defaults
        ColorBehaviour colorBehaviour;
        HousingBehaviour housingBehaviour;

        if (features.contains(MULTI_COLOR)) {
            colorBehaviour = new MultiColorSupport();
        } else {
            colorBehaviour = new SingleColor();
        }

        if (features.contains(HOUSED)) {
            housingBehaviour = new HousingSupport();
        } else {
            housingBehaviour = new NoHousing();
        }

        return new Printer(id, name, manufacturer, colorBehaviour, housingBehaviour, dimensions);
    }
}
