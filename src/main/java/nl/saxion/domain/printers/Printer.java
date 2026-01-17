package nl.saxion.domain.printers;

import nl.saxion.domain.prints.PrintTask;
import nl.saxion.domain.printers.behaviours.ColorBehaviour;
import nl.saxion.domain.printers.behaviours.HousingBehaviour;
import nl.saxion.domain.spools.Spool;
import nl.saxion.domain.utils.Dimensions;
import nl.saxion.domain.utils.Utils;

import java.util.List;

public class Printer {
    private final ColorBehaviour colorBehaviour;
    private final HousingBehaviour housingBehaviour;

    private final int id;
    private final String name;
    private final String manufacturer;
    private final Dimensions dimensions;

    private PrintTask currentTask;

    public Printer(int id, String printerName, String manufacturer, ColorBehaviour colorBehaviour, HousingBehaviour housingBehaviour, Dimensions dimensions) {
        this.id = id;
        this.name = printerName;
        this.manufacturer = manufacturer;
        this.colorBehaviour = colorBehaviour;
        this.housingBehaviour = housingBehaviour;
        this.dimensions = dimensions;
    }

    public int getId() {
        return id;
    }

    public void setCurrentTask(PrintTask printTask){
        if(canPrint(printTask)){
            this.currentTask = printTask;
        }
        else{
            throw new IllegalStateException("Printer not compatible with this task");
        }
    }

    public boolean isBusy(){
        return currentTask != null;
    }

    public PrintTask getCurrentTask(){
        return currentTask;
    }

    public PrintTask removeCurrentTask(){
        if(currentTask == null){
            throw new IllegalStateException("Printer does not have a task to remove");
        }

        PrintTask task = currentTask;

        System.out.println("Task: " + currentTask + " removed from printer");
        currentTask = null;
        return task;
    }

    public List<Spool> getCurrentSpools() {
        return colorBehaviour.getCurrentSpools();
    }

    public void setCurrentSpools(List<Spool> spools) {
        colorBehaviour.setCurrentSpools(spools);
    }

    public boolean canPrint(PrintTask printTask){
        return colorBehaviour.supportsColors(printTask.getColors())
                && housingBehaviour.supportsFilament(printTask.getFilamentType())
                && Utils.printFitCheck(printTask.getPrint().getDimensions(), dimensions);
    }

    @Override
    public String toString() {
        return "--------" + System.lineSeparator() +
                "- ID: " + id + System.lineSeparator() +
                "- Name: " + name + System.lineSeparator() +
                "- Manufacturer: " + manufacturer + System.lineSeparator() +
                colorBehaviour.toString() +
                "--------";
    }

    public String getName() {
        return name;
    }
}
