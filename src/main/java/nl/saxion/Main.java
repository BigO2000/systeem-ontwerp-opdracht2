package nl.saxion;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.saxion.spools.Spool;
import nl.saxion.utils.FilamentType;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final File PRINTS = new File("resources/prints.json");
    private static final File SPOOLS = new File("resources/spools.json");
    private static final File PRINTERS = new File("resources/printers.json");

    private String getUserInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void mainMenu() {
        printMenu();
        String choice = getUserInput("Option: ");

        switch (choice) {
            case "1" -> addNewPrintTask();
            case "2" -> registerPrintCompletion();
            case "3" -> registerPrinterFailure();
            case "5" -> startPrintQueue();
            case "6" -> showPrints();
            case "7" -> showPrinters();
            case "8" -> showSpools();
            case "9" -> showPendingPrintTasks();
            default -> throw new IllegalStateException("Invalid choice: " + choice + " enter a number between 1 and 9");
        }
    }

    public static void printMenu() {
        System.out.println("------------- Menu ----------------");
        System.out.println("- 1) Add new Print Task");
        System.out.println("- 2) Register Printer Completion");
        System.out.println("- 3) Register Printer Failure");
        System.out.println("- 4) Change printing style");
        System.out.println("- 5) Start Print Queue");
        System.out.println("- 6) Show prints");
        System.out.println("- 7) Show printers");
        System.out.println("- 8) Show spools");
        System.out.println("- 9) Show pending print tasks");
        System.out.println("- 0) Exit");
    }

    private void startPrintQueue() {
        System.out.println("---------- Starting Print Queue ----------");
        manager.startInitialQueue();
        System.out.println("-----------------------------------");
    }

    private void registerPrintCompletion() {
        Facade.registerPrinterCompletion(indexedMenu(
                Facade.getRunningPrinters(),
                "Display Running Printers",
                "- Printer that is done (ID): "
        ));
    }

    private void registerPrinterFailure() {
        Facade.registerPrinterFailure(indexedMenu(
                Facade.getRunningPrinters(),
                "Display Running Printers",
                "- Printer that has Failed (ID): "
        ));
    }

    private static void printIndexedObjects(Map<Integer, String> map, String displayName) {
        System.out.printf("---------- %s ---------- \n", displayName);
        map.forEach((id, name) ->
                System.out.println("- " + id + ": " + name + " - "));
    }

    private int indexedMenu(Map<Integer, String> map, String displayName, String prompt) {
        printIndexedObjects(map, displayName);
        int input = Integer.parseInt(getUserInput(prompt));
        System.out.println("-----------------------------------");
        return input;
    }

    private void addNewPrintTask() {
        int print = indexedMenu(
                Facade.getPrints(),
                "Displaying Available Prints",
                "- Print that you want to print (ID): "
        );

        int filamentType = indexedMenu(
                Facade.getFilamentTypes(),
                "Filament Type",
                "- Filament type number: "
        );

        int color = indexedMenu(
                Facade.getColors(),
                "Colors",
                "- Color number: "
        );

        // TODO: afmaken.
        Facade.addPrintTask(print, color, filamentType);
        System.out.println("----------------------------");
    }

    private void showPendingPrintTasks() {
        System.out.println("-------------- Pending PrintTask -----------------");
        Facade.getPendingPrintTasks()
                .forEach(System.out::println);
    }
}