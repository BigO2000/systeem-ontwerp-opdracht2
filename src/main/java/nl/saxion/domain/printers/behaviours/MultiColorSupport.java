package nl.saxion.domain.printers.behaviours;

import nl.saxion.domain.spools.Spool;
import nl.saxion.domain.enums.Color;

import java.util.List;

public class MultiColorSupport implements ColorBehaviour {
    private List<Spool> spools;

    @Override
    public void setCurrentSpools(List<Spool> spools) {
        this.spools = spools;
    }

    @Override
    public List<Spool> getCurrentSpools() {
        return spools;
    }

    @Override
    public boolean supportsColors(List<Color> colors) {
        return spools.stream()
                .map(Spool::getColor)
                .toList()
                .equals(colors);
    }
}
