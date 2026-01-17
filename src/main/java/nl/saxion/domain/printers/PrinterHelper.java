package nl.saxion.domain.printers;

public record PrinterHelper(int id, int type, String model, String name, String manufacturer, int maxX, int maxY, int maxZ, int maxColors) {
}
