package KromiPrint.process;

import KromiPrint.models.PhotoOrder;


import java.util.List;


public class AdminService {
private final OrderRepository repo;


public AdminService(OrderRepository repo) { this.repo = repo; }


public int totalOrders() { return repo.findAll().size(); }


public double revenueEstimate() {
return repo.findAll().stream().mapToDouble(PhotoOrder::total).sum();
}


public List<PhotoOrder> listAll() { return repo.findAll(); }
}