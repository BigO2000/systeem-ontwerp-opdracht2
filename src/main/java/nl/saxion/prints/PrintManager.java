package nl.saxion.prints;

import com.fasterxml.jackson.core.type.TypeReference;
import nl.saxion.printers.Dimensions;
import nl.saxion.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintManager {
    private static final String PRINTS_LOCATION = "src/main/resources/prints.json";
    private static PrintManager INSTANCE;
    private final Map<Integer, Print> prints = new HashMap<>();

    private PrintManager() throws Exception {
        List<Print> l = Utils.loadJson(new File(PRINTS_LOCATION),
                new TypeReference<>() {});

        l.forEach(print -> prints.put(print.getId(), print));
    }

    public static PrintManager getInstance() {
        if (INSTANCE == null) {
            try{
                INSTANCE = new PrintManager();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public void addPrint(String name, int id, Dimensions dimensions, List<Double> filamentLength, int printTime) {
        prints.put(id, new Print(name, id, dimensions, filamentLength, printTime));
    }

    public Map<Integer, String> getPrints() {
        Map<Integer, String> map = new HashMap<>();

        prints.forEach((id, print) ->
                map.put(id, print.getName()));

        return map;
    }


}
