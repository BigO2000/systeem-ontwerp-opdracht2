package nl.saxion.utils;

import nl.saxion.printers.Dimensions;
import nl.saxion.printers.Printer;
import nl.saxion.prints.Print;

public class Utils {
    public static boolean printFitCheck(Dimensions dPrint, Dimensions dPrinter){
        return dPrint.x() <= dPrinter.x() && dPrint.y() <= dPrinter.y() && dPrint.z() <= dPrinter.z();
    }
}
