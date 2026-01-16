package nl.saxion.spools;

import com.fasterxml.jackson.core.type.TypeReference;
import nl.saxion.Models.PrintTask;
import nl.saxion.utils.Color;
import nl.saxion.utils.FilamentType;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.saxion.utils.Utils.loadJson;

public class SpoolManager {
    private static final String SPOOL_LOCATION = "resources/spools.json";
    private static SpoolManager INSTANCE;
    private Map<Integer, Spool> spools = new HashMap<>();

    private SpoolManager() throws Exception {
        List<Spool> s =  loadJson(new File(SPOOL_LOCATION),
                new TypeReference<>(){});

        s.forEach(sp -> spools.put(sp.getId(), sp));
    }

    public static SpoolManager getInstance() {
        if (INSTANCE == null) {
            try{
                INSTANCE = new SpoolManager();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public List<String> getSpools(){
        return spools.values().stream()
                .map(Spool::toString)
                .toList();
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
