package nl.saxion.printers.behaviours;

import nl.saxion.utils.FilamentType;
import nl.saxion.utils.PrinterFeature;

public class NoHousing implements HousingBehaviour {
    @Override
    public PrinterFeature getHousingBehaviour() {
        return null;
    }

    @Override
    //Supports everything except ABS.
    public boolean supportsFilament(FilamentType filamentType) {
        return !filamentType.equals(FilamentType.ABS);
    }
}
