package KromiPrint.models;

public enum PhotoSize {
FOUR_BY_SIX(4, 6, 1.00, "4x6"),
FIVE_BY_SEVEN(5, 7, 1.40, "5x7"),
SIX_BY_EIGHT(6, 8, 1.80, "6x8");


private final int w;
private final int h;
private final double basePrice; // base price before priority factor
private final String label;


PhotoSize(int w, int h, double basePrice, String label) {
this.w = w; this.h = h; this.basePrice = basePrice; this.label = label;
}


public int getW() { return w; }
public int getH() { return h; }
public double getBasePrice() { return basePrice; }
public String getLabel() { return label; }
}