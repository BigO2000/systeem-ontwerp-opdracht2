package nl.saxion.utils;

import nl.saxion.printers.*;
import nl.saxion.printers.behaviours.*;

import java.util.Set;

import static nl.saxion.utils.PrinterFeature.HOUSED;
import static nl.saxion.utils.PrinterFeature.MULTI_COLOR;

public class PrinterFactory {
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
