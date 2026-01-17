package nl.saxion.domain.spools;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.saxion.domain.utils.Utils.loadJson;

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
}
