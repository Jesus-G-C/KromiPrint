package KromiPrint.process;

import KromiPrint.models.PhotoOrder;
import KromiPrint.models.Priority;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class QueueManager {
private final OrderRepository repo;


public QueueManager(OrderRepository repo) { this.repo = repo; }


public List<PhotoOrder> listByPriority() {
return repo.findAll().stream()
.sorted(Comparator
.comparing((PhotoOrder o) -> o.getPriority() == Priority.NOW ? 0 : (o.getPriority() == Priority.ONE_HOUR ? 1 : 2))
.thenComparing(PhotoOrder::getCreatedAt))
.collect(Collectors.toList());
}
}