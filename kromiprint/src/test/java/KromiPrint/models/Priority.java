package KromiPrint.models;

public enum Priority {
NOW(1.0, "NOW"),
ONE_HOUR(0.85, "1H"),
SEVERAL_DAYS(0.70, "DAYS");


private final double priceFactor;
private final String code;


Priority(double priceFactor, String code) {
this.priceFactor = priceFactor;
this.code = code;
}


public double getPriceFactor() { return priceFactor; }
public String getCode() { return code; }
}