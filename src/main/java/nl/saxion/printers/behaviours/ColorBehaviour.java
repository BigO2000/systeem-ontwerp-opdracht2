package nl.saxion.printers.behaviours;

import java.util.List;

public interface ColorBehaviour {
    void setCurrentSpools(List<Integer> spools);

    List<Integer> getCurrentSpools();

    int getMaxColors();

    String toString();
}
