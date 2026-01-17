package nl.saxion.domain.printers.behaviours;

import nl.saxion.domain.enums.FilamentType;

public class HousingSupport implements HousingBehaviour {
    @Override
    // Supports all types.
    public boolean supportsFilament(FilamentType filamentType) {
        return true;
    }
}
