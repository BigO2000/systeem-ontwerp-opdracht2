package nl.saxion.printers.behaviours;

import nl.saxion.spools.Spool;
import nl.saxion.utils.Color;
import nl.saxion.utils.PrinterFeature;

import java.util.List;

public class SingleColor implements ColorBehaviour {
    private Color color;
    private int maxColors;
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
    public int getMaxColors() {
        return 0;
    }

    @Override
    public PrinterFeature getColorBehaviour() {
        return null;
    }

    @Override
    public boolean supportsColors(List<Color> color) {
        return color.get(0).equals(color);
    }
}
