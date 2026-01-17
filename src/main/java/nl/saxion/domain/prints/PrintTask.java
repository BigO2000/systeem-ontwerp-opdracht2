package nl.saxion.domain.prints;

import nl.saxion.domain.enums.Color;
import nl.saxion.domain.enums.FilamentType;
import nl.saxion.domain.enums.PrinterFeature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintTask {
    private Print print;
    private List<Color> colors;
    private FilamentType filamentType;

    public PrintTask(Print print, List<Color> colors, FilamentType filamentType){
        this.print = print;
        this.colors = colors;
        this.filamentType = filamentType;
    }

    public PrintTask(int printId, List<Color> colors, FilamentType filamentType){
        this(PrintManager.getInstance().getPrint(printId), colors, filamentType);
    }

    public List<Color> getColors() {
        return colors;
    }

    public FilamentType getFilamentType() {
        return filamentType;
    }

    public Print getPrint(){
        return print;
    }

    public Set<PrinterFeature> getRequirements(){
        Set<PrinterFeature> result = new HashSet<>();

        if(colors.size() > 1){
            result.add(PrinterFeature.MULTI_COLOR);
        }
        if(filamentType.equals(FilamentType.ABS)){
            result.add(PrinterFeature.HOUSED);
        }

        return result;
    }

    @Override
    public String toString() {
        return "< " + print.name() +" " + filamentType + " " + colors.toString() + " >";
    }
}
