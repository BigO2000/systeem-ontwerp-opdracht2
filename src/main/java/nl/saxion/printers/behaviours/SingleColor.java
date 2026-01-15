package nl.saxion.printers.behaviours;

import java.util.List;

public class SingleColor implements ColorBehaviour {

    @Override
    public void setCurrentSpools(List<Integer> spools) {

    }

    @Override
    public List<Integer> getCurrentSpools() {
        return List.of();
    }

    @Override
    public int getMaxColors() {
        return 0;
    }
}
