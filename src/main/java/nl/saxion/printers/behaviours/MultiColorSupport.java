package nl.saxion.printers.behaviours;

import nl.saxion.spools.Spool;
import nl.saxion.spools.SpoolManager;
import nl.saxion.utils.Color;
import nl.saxion.utils.PrinterFeature;

import java.util.List;

public class MultiColorSupport implements ColorBehaviour {
    private List<Spool> spools;
    private int maxColors;

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
        return spools.size();
    }

    @Override
    public PrinterFeature getColorBehaviour() {
        return PrinterFeature.MULTI_COLOR;
    }


    @Override
    public boolean supportsColors(List<Color> colors) {
        for(int i = 0; i < colors.size(); i++){
            // If one of the colors does not match up return false.

        }
        // If all colors match: return true.
        return true;
    }

}
