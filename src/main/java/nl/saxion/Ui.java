package nl.saxion;

import nl.saxion.domain.Facade;
import nl.saxion.domain.enums.Color;
import nl.saxion.domain.enums.FilamentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void mainMenu() {
        while (running) {
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
                case "0" -> running = false;
                default ->
                        throw new IllegalStateException("Invalid choice: " + choice + " enter a number between 1 and 9");
            }
        }
    }

    private void showSpools() {
        Facade.getSpools().forEach(System.out::println);
    }

    private void showPrinters() {
        printIndexedObjects(Facade.getPrinters(), "All Printers");
    }

    private void showPrints() {
        printIndexedObjects(Facade.getPrints(), "All Prints");
    }

    public static void printMenu() {
        System.out.println("------------- Menu ----------------");
        System.out.println("- 1) Add new Print Task");
        System.out.println("- 2) Register Printer Completion");
        System.out.println("- 3) Register Printer Failure");
        System.out.println("- 5) Start Print Queue");
        System.out.println("- 6) Show prints");
        System.out.println("- 7) Show printers");
        System.out.println("- 8) Show spools");
        System.out.println("- 9) Show pending print tasks");
        System.out.println("- 0) Exit");
    }

    private void startPrintQueue() {
        System.out.println("---------- Starting Print Queue ----------");
        Facade.startInitialQueue();
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

        // Select the filament type.
        Map<Integer, String> filaments = Facade.getFilamentTypes();

        printIndexedObjects(filaments, "Filament Type");
        int input = Integer.parseInt(getUserInput("- Filament Type Number: "));
        System.out.println("-----------------------------------");

        FilamentType filamentType = FilamentType.valueOf(filaments.get(input));

        // Select a color for every required spool.
        int spoolsRequired = Facade.getSpoolsRequired(print);
        Map<Integer, String> colors = Facade.getColors();
        List<Color> colorList = new ArrayList<>();

        for (int i = 0; i < spoolsRequired; i++) {
            printIndexedObjects(colors, "Colors");
            input = Integer.parseInt(getUserInput("- Color number: "));
            System.out.println("-----------------------------------");

            colorList.add(Color.valueOf(colors.get(input)));
        }

        Facade.addPrintTask(print, colorList, filamentType);
    }

    private void showPendingPrintTasks() {
        System.out.println("-------------- Pending PrintTask -----------------");
        Facade.getPendingPrintTasks()
                .forEach(System.out::println);
    }

    private String getUserInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
