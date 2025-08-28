package KromiPrint.process;

import KromiPrint.models.OrderStatus;
import KromiPrint.models.PhotoOrder;


public class CashierService {
private final OrderRepository repo;


public CashierService(OrderRepository repo) { this.repo = repo; }


public String pay(String id) {
PhotoOrder o = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
o.setStatus(OrderStatus.PAID);
// Issue ticket (string). We keep order until printed by employee
return o.ticket();
}
}