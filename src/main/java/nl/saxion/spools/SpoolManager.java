package nl.saxion.spools;

import nl.saxion.Models.PrintTask;
import nl.saxion.utils.Color;
import nl.saxion.utils.FilamentType;

import java.util.HashMap;
import java.util.Map;

public class SpoolManager {
    private static SpoolManager INSTANCE;
    private Map<Integer, Spool> spools = new HashMap<>();

    public static SpoolManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpoolManager();
        }
        return INSTANCE;
    }

    public void addSpool(int id, Color color, FilamentType filamentType, double length) {
        spools.put(id, new Spool(id, color, filamentType, length));
    }

    public Spool findSpoolForPrint(PrintTask task) {
        return spools.values().stream()
                .filter(s -> s.getColor().equals(task.getColors()) && s.getFilamentType().equals(task.getFilamentType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find a spool for this task"));
    }

}
