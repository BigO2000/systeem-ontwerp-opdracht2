package nl.saxion.domain;

import nl.saxion.domain.prints.PrintTask;
import nl.saxion.domain.printers.PrinterManager;
import nl.saxion.domain.prints.PrintManager;
import nl.saxion.domain.spools.SpoolManager;
import nl.saxion.domain.enums.Color;
import nl.saxion.domain.enums.FilamentType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
    public static Map<Integer, String> getRunningPrinters() {
        return PrinterManager.getInstance().getRunningPrinters();
    }

    public static void initializeManagers(){
        PrinterManager.getInstance();
        PrintManager.getInstance();
        SpoolManager.getInstance();
    }

    public static void startInitialQueue(){
        PrinterManager.getInstance().startInitialQueue();
    }

    public static void registerPrinterCompletion(int id) {
        PrinterManager.getInstance().registerCompletion(id);
    }

    public static String getCurrentPrinterTask(int id) {
        return PrinterManager.getInstance().getPrinterCurrentTask(id);
    }

    public static void registerPrinterFailure(int id) {
        PrinterManager.getInstance().registerPrinterFailure(id);
    }

    public static Map<Integer, String> getPrinters(){
        return PrinterManager.getInstance().getPrinters();
    }

    public static int getSpoolsRequired(int id){
        return PrintManager.getInstance().getPrint(id).filamentLength().size();
    }

    public static List<String> getPendingPrintTasks() {
        return PrinterManager.getInstance()
                .getPendingPrintTasks()
                .stream()
                .map(PrintTask::toString)
                .toList();
    }

    public static Map<Integer, String> getPrints() {
        return PrintManager.getInstance().getPrints();
    }

    public static void addPrintTask(int printId, List<Color> colors, FilamentType filamentType) {
        PrinterManager.getInstance().addPrintTask(new PrintTask(printId, colors, filamentType));
    }

    public static Map<Integer, String> getFilamentTypes() {
        Map<Integer, String> map = new HashMap<>();

        for (FilamentType e : FilamentType.values()) {
            map.put(e.ordinal(), e.name());
        }
        return map;
    }

    public static Map<Integer, String> getColors() {
        Map<Integer, String> map = new HashMap<>();

        for (Color c : Color.values()) {
            map.put(c.ordinal(), c.name());
        }
        return map;
    }

    public static List<String> getSpools(){
        return SpoolManager.getInstance().getSpools();
    }
}
