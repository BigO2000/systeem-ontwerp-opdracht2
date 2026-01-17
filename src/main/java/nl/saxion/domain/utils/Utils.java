package nl.saxion.domain.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Utils {
    public static boolean printFitCheck(Dimensions dPrint, Dimensions dPrinter){
        return dPrint.x() <= dPrinter.x() && dPrint.y() <= dPrinter.y() && dPrint.z() <= dPrinter.z();
    }

    public static <T> T loadJson(File file, TypeReference<T> type) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        return mapper.readValue(file, type);
    }
}
