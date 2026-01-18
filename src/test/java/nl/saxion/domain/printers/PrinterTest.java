package nl.saxion.domain.printers;

import nl.saxion.domain.enums.Color;
import nl.saxion.domain.enums.FilamentType;
import nl.saxion.domain.prints.Print;
import nl.saxion.domain.prints.PrintTask;
import nl.saxion.domain.printers.behaviours.*;
import nl.saxion.domain.spools.Spool;
import nl.saxion.domain.utils.Dimensions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    private Printer singleColorPrinter(Color spoolColor, boolean housed, Dimensions dimensions) {
        SingleColor singleColor = new SingleColor();
        singleColor.setCurrentSpools(List.of(new Spool(1, spoolColor, FilamentType.PLA, 9999)));

        HousingBehaviour housingBehaviour;
        if (housed) {
            housingBehaviour = new HousingSupport();
        } else {
            housingBehaviour = new NoHousing();
        }

        return new Printer(1, "P1", "Test", singleColor, housingBehaviour, dimensions);
    }

    private Printer multiColorPrinter(List<Color> spoolColors, boolean housed, Dimensions dimensions) {
        MultiColorSupport multiColorSupport = new MultiColorSupport();
        multiColorSupport.setCurrentSpools(List.of(
                new Spool(1, spoolColors.get(0), FilamentType.PLA, 9999),
                new Spool(2, spoolColors.get(1), FilamentType.PLA, 9999),
                new Spool(3, spoolColors.get(2), FilamentType.PLA, 9999),
                new Spool(4, spoolColors.get(3), FilamentType.PLA, 9999)
        ));

        HousingBehaviour housingBehaviour;
        if (housed) {
            housingBehaviour = new HousingSupport();
        } else {
            housingBehaviour = new NoHousing();
        }

        return new Printer(1, "P1", "Test", multiColorSupport, housingBehaviour, dimensions);
    }


    private Print makePrint(String name, int id, int x, int y, int z, int colorCount) {
        List<Double> lengths = java.util.Collections.nCopies(colorCount, 1.0);
        return new Print(name, id, x, y, z, lengths, 1);
    }

    private PrintTask task(Print print, List<Color> colors, FilamentType type) {
        return new PrintTask(print, colors, type);
    }


    @Test
    void canPrintReturnsTrueWhenSingleColorPrinterMatchesPrintColor() {
        Printer printer = singleColorPrinter(Color.RED, false, new Dimensions(300, 300, 300));
        Print print = makePrint("test", 1, 50, 100, 75, 1);

        PrintTask task = task(print, List.of(Color.RED), FilamentType.PLA);

        assertTrue(printer.canPrint(task));
    }

    @Test
    void canPrintReturnsFalseWhenPrintDoesntFitPrinter() {
        Printer printer = singleColorPrinter(Color.BLUE, true, new Dimensions(200, 200, 200));
        Print print = makePrint("test", 1, 400, 100, 200, 1);

        PrintTask task = task(print, List.of(Color.BLUE), FilamentType.PLA);

        assertFalse(printer.canPrint(task));
    }

    @Test
    void canPrintReturnsTrueWhenPrintFitsPrinter() {
        Printer printer = singleColorPrinter(Color.BLUE, true, new Dimensions(350, 350, 350));
        Print print = makePrint("test", 1, 100, 100, 30, 1);

        PrintTask task = task(print, List.of(Color.BLUE), FilamentType.PLA);

        assertTrue(printer.canPrint(task));
    }

    @Test
    void canPrintReturnsFalseGivenABSPrintWhenPrinterHasNoHousing() {
        Printer printer = singleColorPrinter(Color.BLUE, false, new Dimensions(300, 300, 300));
        Print print = makePrint("test", 1, 100, 100, 100, 1);

        PrintTask task = task(print, List.of(Color.BLUE), FilamentType.ABS);

        assertFalse(printer.canPrint(task));
    }

    @Test
    void canPrintReturnsFalseForMultiColorPrintOnSingleColorPrinter() {
        Printer printer = singleColorPrinter(Color.BLUE, true, new Dimensions(300, 300, 300));
        Print print = makePrint("test", 1, 200, 200, 200, 4);

        PrintTask task = task(print,
                List.of(Color.BLUE, Color.RED, Color.GREEN, Color.PINK),
                FilamentType.PLA);

        assertFalse(printer.canPrint(task));
    }

    @Test
    void canPrintReturnsTrueForMultiColorPrintOnMultiColorPrinter() {
        Printer printer = multiColorPrinter(
                List.of(Color.BLUE, Color.RED, Color.GREEN, Color.PINK),
                true,
                new Dimensions(300, 300, 300)
        );

        Print print = makePrint("test", 1, 200, 200, 200, 4);

        PrintTask task = task(print,
                List.of(Color.BLUE, Color.RED, Color.GREEN, Color.PINK),
                FilamentType.PLA);

        assertTrue(printer.canPrint(task));
    }


}
