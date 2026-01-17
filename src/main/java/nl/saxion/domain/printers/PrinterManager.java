package nl.saxion.domain.printers;

import com.fasterxml.jackson.core.type.TypeReference;
import nl.saxion.domain.prints.PrintTask;
import nl.saxion.domain.utils.PrinterFactory;
import nl.saxion.domain.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrinterManager {
    private static final String PRINTER_LOCATION = "resources/printers.json";
    private static PrinterManager INSTANCE;
    private final Map<Integer, Printer> printers = new HashMap<>();
    private final List<PrintTask> pendingPrintTasks = new ArrayList<>();

    private String printStrategy; // For future use.

    private PrinterManager() throws Exception {
        // Uses a printerHelper record for Jackson parsing.
        List<PrinterHelper> printersList = Utils.loadJson(new File(PRINTER_LOCATION),
                new TypeReference<>() {
                });

        // Uses the PrinterHelper to create the actual printers.
        printersList.forEach(p -> printers.put(p.id(), PrinterFactory.create(p)));
    }

    public static PrinterManager getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new PrinterManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
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

    public void startInitialQueue() {
        for (Printer printer : printers.values()) {
            selectPrintTask(printer);
        }
    }

    private void selectPrintTask(Printer printer) {
        boolean found = false;
        for (PrintTask printTask : pendingPrintTasks) {
            if (!printer.isBusy() && printer.canPrint(printTask)) {
                printer.setCurrentTask(printTask);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Could not find suitable task for printer: " + printer.getName());
        }
    }

    public String getPrinterCurrentTask(int id) {
        return printers.get(id).getCurrentTask().getPrint().name();
    }

    public List<PrintTask> getPendingPrintTasks() {
        return pendingPrintTasks;
    }

    public void registerPrinterFailure(int id) {
        if (printers.get(id).getCurrentTask() == null) {
            throw new IllegalStateException("Cannot find running task on printer with ID: " + id);
        }

        Printer printer = printers.get(id);
        PrintTask task = printer.removeCurrentTask();
        System.out.println("Task " + task + " removed from printer " + printer.getName());

        // Restart the failed task.
        printer.setCurrentTask(task);
    }

    public void registerCompletion(int printerId) {
        if (!printers.containsKey(printerId)) {
            throw new IllegalStateException("Printer with id: " + printerId + " does not exits");
        }

        Printer printer = printers.get(printerId);

        printer.removeCurrentTask();
        selectPrintTask(printer);
    }
}
