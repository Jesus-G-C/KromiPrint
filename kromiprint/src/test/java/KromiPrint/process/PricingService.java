package KromiPrint.process;

import KromiPrint.models.PhotoItem;
import KromiPrint.models.PhotoOrder;


public class PricingService {
public double calculateTotal(PhotoOrder order) { return order.total(); }


public double priceOf(PhotoItem item) { return item.price(); }
}