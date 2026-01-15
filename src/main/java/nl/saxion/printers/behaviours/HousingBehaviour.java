package nl.saxion.printers.behaviours;

import nl.saxion.utils.FilamentType;
import nl.saxion.utils.PrinterFeature;

public interface HousingBehaviour {
    PrinterFeature getHousingBehaviour();

    boolean supportsFilament(FilamentType filamentType);
}
