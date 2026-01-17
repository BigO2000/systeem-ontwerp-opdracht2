package nl.saxion.printers;

import com.fasterxml.jackson.core.type.TypeReference;
import nl.saxion.utils.*;
import nl.saxion.Models.PrintTask;
import nl.saxion.prints.Print;

import java.io.File;
import java.util.*;

public class PrinterManager {
    private static final String PRINTER_LOCATION = "resources/printers.json";
    private static PrinterManager INSTANCE;
    private final Map<Integer, Printer> printers = new HashMap<>();
    private final List<PrintTask> pendingPrintTasks = new ArrayList<>();

    private PrinterManager() throws Exception {
        // Uses a printerHelper record for Jackson parsing.
        List<PrinterHelper> printersList = Utils.loadJson(new File(PRINTER_LOCATION),
                new TypeReference<>() {});

        // Uses the PrinterHelper to create the actual printers.
        printersList.forEach(p -> printers.put(p.id(), PrinterFactory.create(p)));
    }

    public static PrinterManager getInstance(){
        if (INSTANCE == null) {
            try{
                INSTANCE = new PrinterManager();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public void addPrinter(int id, String name, String manufacturer, Set<PrinterFeature> features, Dimensions dimensions) {
        printers.put(id, PrinterFactory.create(id, name, manufacturer, features, dimensions));
    }

    public void startPrinting() {
        // Starts printing the printqueue.
    }

    public Map<Integer, String> getPrinters() {
        Map<Integer, String> map = new HashMap<>();
        printers.forEach((integer, printer) -> map.put(integer, printer.getName()));
        return map;
    }

    public Map<Integer, String> getRunningPrinters() {
        Map<Integer, String> map = new HashMap<>();

        printers.forEach((id, printer) ->
                map.put(id, printer.getName())
        );

        return map;
    }

    public void addPrintTask(PrintTask printTask) {
        pendingPrintTasks.add(printTask);
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
        pendingPrintTasks.forEach(t -> selectPrintTask());
    }

    public String getPrinterCurrentTask(int id) {
        return printers.get(id).getCurrentTask().getPrint().name();
    }

    public List<PrintTask> getPendingPrintTasks() {
        return pendingPrintTasks;
    }

    public void registerPrinterFailure(int id) {
        if(printers.get(id).getCurrentTask() == null){
            throw new IllegalStateException("Cannot find running task on printer with ID: " + id );
        }

        Printer printer = printers.get(id);
        PrintTask task = printer.removeCurrentTask();
        System.out.println("Task " + task + " removed from printer " + printer.getName());

        // Restart the failed task.
        printer.setCurrentTask(task);
    }

    public void registerCompletion(int printerId) {
        if(!printers.containsKey(printerId)){
            throw new IllegalStateException("Printer with id: " + printerId + " does not exits");
        }

        Printer printer = printers.get(printerId);

        printer.removeCurrentTask();
        selectPrintTask(printer);
    }
}
