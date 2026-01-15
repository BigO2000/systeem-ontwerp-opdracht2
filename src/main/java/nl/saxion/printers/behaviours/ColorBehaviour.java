package nl.saxion.printers.behaviours;

import nl.saxion.spools.Spool;
import nl.saxion.utils.Color;
import nl.saxion.utils.PrinterFeature;

import java.util.List;

public interface ColorBehaviour {
    void setCurrentSpools(List<Spool> spools);

    List<Spool> getCurrentSpools();

    int getMaxColors();

    String toString();

    PrinterFeature getColorBehaviour();

    boolean supportsColors(List<Color> colors);
}
