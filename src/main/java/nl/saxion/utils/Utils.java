package nl.saxion.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.saxion.printers.Dimensions;

import java.io.File;

public class Utils {
    public static boolean printFitCheck(Dimensions dPrint, Dimensions dPrinter){
        return dPrint.x() <= dPrinter.x() && dPrint.y() <= dPrinter.y() && dPrint.z() <= dPrinter.z();
    }

    public static <T> T loadJson(File file, TypeReference<T> type) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, type);
    }
}
