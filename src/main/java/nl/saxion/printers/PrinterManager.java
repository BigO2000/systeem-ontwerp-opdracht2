package nl.saxion.printers;

import com.fasterxml.jackson.core.type.TypeReference;
import nl.saxion.spools.SpoolManager;
import nl.saxion.utils.*;
import nl.saxion.Models.PrintTask;
import nl.saxion.prints.Print;
import nl.saxion.spools.Spool;

import java.io.File;
import java.util.*;

public class PrinterManager {
    private static final String PRINTER_LOCATION = "resources/printers.json";
    private static PrinterManager INSTANCE;
    private final Map<Integer, Printer> printers = new HashMap<>();
    private final List<PrintTask> pendingPrintTasks = new ArrayList<>();

    private PrinterManager() throws Exception {
        List<Printer> printersList = Utils.loadJson(new File(PRINTER_LOCATION),
                new TypeReference<>() {});

        printersList.forEach(p -> printers.put(p.getId(), p));
    }

    public static PrinterManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrinterManager();
        }
        return INSTANCE;
    }

    public void addPrinter(int id, String name, String manufacturer, Set<PrinterFeature> features, Dimensions dimensions) {
        printers.put(id, PrinterFactory.create(id, name, manufacturer, features, dimensions));
    }

    public void startPrinting() {
        // Starts printing the printqueue.
    }

    public Set<Integer> getPrinters() {
        return printers.keySet();
    }

    public Map<Integer, String> getRunningPrinters() {
        Map<Integer, String> map = new HashMap<>();

        printers.forEach((id, printer) ->
                map.put(id, printer.getName())
        );

        return map;
    }

    public void addPrintJob(Print print) {

    }

    public void registerPrinterFailure(int id) {
    }

    public registerCompletion(int id) {

    }

    public void print() {

    }

    public void selectPrintTask(Printer printer) {
        // Find print matching spool on printer.
        PrintTask chosenTask = getPrintTask(printer);

        // Start printing.
        if (chosenTask != null) {
            pendingPrintTasks.remove(chosenTask);
            System.out.println("- Started task: " + chosenTask + " on printer " + printer.getName());
        }
    }

    //TODO: fix this method.

    // Searches pending print tasks and returns the first compatible one.
    private PrintTask getPrintTask(Printer printer) {
        return pendingPrintTasks.stream()
                .filter(printer::canPrint)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not complete"));
    }

    public void startInitialQueue() {
        for (Printer printer : printers) {
            selectPrintTask(printer);
        }
    }

    public String getPrinterCurrentTask(int id) {
        return printers.get(id).getCurrentTask().getPrint().getName();
    }

    public List<PrintTask> getPendingPrintTasks() {
        return pendingPrintTasks;
    }

    public void addPrintTask(String printName, List<Color> colors, FilamentType type) {
        Print print = findPrint(printName);
        if (print == null) {
            printError("Could not find print with name " + printName);
            return;
        }
        if (colors.size() == 0) {
            printError("Need at least one color, but none given");
            return;
        }
        // TODO: Fix
        for (Color color : colors) {
            boolean found = false;
            for (Spool spool : spools) {
                if (spool.getColor().equals(color) && spool.getFilamentType() == type) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                printError("Color " + color + " (" + type + ") not found");
                return;
            }
        }

        PrintTask task = new PrintTask(print, colors, type);
        pendingPrintTasks.add(task);
        System.out.println("Added task to queue");

    }

    public void registerPrinterFailure(int id) {
        if(printers.get(id).getCurrentTask() == null){
            throw new IllegalStateException("Cannot find running task on printer with ID: " + id );
        }

        // Reenter the failed task back into pending tasks.
        PrintTask task = printers.get(id).removeCurrentTask();
        pendingPrintTasks.add(task);
        System.out.println("Task " + task + " removed from printer " + printers.get(id).getName());

        //TODO: restart failed task.
    }

    public void registerCompletion(int printerId) {
        Map.Entry<Printer, PrintTask> foundEntry = null;
        for (Map.Entry<Printer, PrintTask> entry : runningPrintTasks.entrySet()) {
            if (entry.getKey().getId() == printerId) {
                foundEntry = entry;
                break;
            }
        }
        if (foundEntry == null) {
            printError("cannot find a running task on printer with ID " + printerId);
            return;
        }
        PrintTask task = foundEntry.getValue();
        runningPrintTasks.remove(foundEntry.getKey());

        System.out.println("Task " + task + " removed from printer "
                + foundEntry.getKey().getName());

        Printer printer = foundEntry.getKey();
        Spool[] spools = printer.getCurrentSpools();
        for (int i = 0; i < spools.length && i < task.getColors().size(); i++) {
            spools[i].reduceLength(task.getPrint().getFilamentLength().get(i));
        }
        selectPrintTask(printer);
    }

    private void printError(String s) {
        System.out.println("---------- Error Message ----------");
        System.out.println("Error: " + s);
        System.out.println("--------------------------------------");
    }

}
