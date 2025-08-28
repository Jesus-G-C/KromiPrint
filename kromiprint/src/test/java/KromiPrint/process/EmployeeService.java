package KromiPrint.process;

import KromiPrint.models.OrderStatus;
import KromiPrint.models.PhotoOrder;


public class EmployeeService {
private final OrderRepository repo;


public EmployeeService(OrderRepository repo) { this.repo = repo; }


public PhotoOrder open(String id) {
PhotoOrder o = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
o.setStatus(OrderStatus.OPENED_BY_EMPLOYEE);
return o;
}


public PhotoOrder print(String id) {
PhotoOrder o = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
o.setStatus(OrderStatus.PRINTED);
// Remove from active queue after printing
repo.delete(id);
return o;
}


public PhotoOrder cancel(String id) {
PhotoOrder o = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
o.setStatus(OrderStatus.CANCELLED);
repo.delete(id);
return o;
}
}