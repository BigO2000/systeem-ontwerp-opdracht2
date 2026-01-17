package nl.saxion.domain.printers.behaviours;

import nl.saxion.domain.spools.Spool;
import nl.saxion.domain.enums.Color;

import java.util.List;

public interface ColorBehaviour {
    void setCurrentSpools(List<Spool> spools);

    List<Spool> getCurrentSpools();

    String toString();

    boolean supportsColors(List<Color> colors);
}
