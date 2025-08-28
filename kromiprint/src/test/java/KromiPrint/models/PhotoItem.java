package KromiPrint.models;

    
public class PhotoItem {
private final PhotoSize size;
private final Priority priority;
private final String code; // size + priority + short-uuid


public PhotoItem(PhotoSize size, Priority priority) {
this.size = size;
this.priority = priority;
this.code = generateCode(size, priority);
}


private static String generateCode(PhotoSize size, Priority priority) {

return "" ;
}


public double price() {
return size.getBasePrice() * priority.getPriceFactor();
}


public PhotoSize getSize() { return size; }
public Priority getPriority() { return priority; }
public String getCode() { return code; }


@Override
public String toString() {
return code + " ($" + String.format("%.2f", price()) + ")";
}
}