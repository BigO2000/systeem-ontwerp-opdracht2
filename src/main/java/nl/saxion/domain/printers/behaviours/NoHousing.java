package nl.saxion.domain.printers.behaviours;

import nl.saxion.domain.enums.FilamentType;

public class NoHousing implements HousingBehaviour {

    @Override
    //Supports everything except ABS.
    public boolean supportsFilament(FilamentType filamentType) {
        return !filamentType.equals(FilamentType.ABS);
    }
}
