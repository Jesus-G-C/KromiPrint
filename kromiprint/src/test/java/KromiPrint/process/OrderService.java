package KromiPrint.process;

import KromiPrint.models.*;


import java.util.List;
import java.util.Optional;


public class OrderService {
private final OrderRepository repo;


public OrderService(OrderRepository repo) { this.repo = repo; }


public PhotoOrder createOrder(Customer customer, Priority priority) {
PhotoOrder order = new PhotoOrder(customer, priority);
return repo.save(order);
}


public Optional<PhotoOrder> find(String id) { return repo.findById(id); }


public PhotoOrder addItem(String orderId, PhotoSize size, Priority priority) {
PhotoOrder order = repo.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
order.addItem(new PhotoItem(size, priority));
return order;
}


public List<PhotoOrder> all() { return repo.findAll(); }


public void delete(String id) { repo.delete(id); }
}