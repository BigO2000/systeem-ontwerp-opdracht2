package nl.saxion.domain.printers.behaviours;

import nl.saxion.domain.enums.FilamentType;

public interface HousingBehaviour {
    boolean supportsFilament(FilamentType filamentType);
}
