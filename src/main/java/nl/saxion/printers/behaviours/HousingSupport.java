package nl.saxion.printers.behaviours;

import nl.saxion.utils.FilamentType;
import nl.saxion.utils.PrinterFeature;

public class HousingSupport implements HousingBehaviour {
    @Override
    public PrinterFeature getHousingBehaviour() {
        return PrinterFeature.HOUSED;
    }

    @Override
    // Supports all types.
    public boolean supportsFilament(FilamentType filamentType) {
        return true;
    }
}
