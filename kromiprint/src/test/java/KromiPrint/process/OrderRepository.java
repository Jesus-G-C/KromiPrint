package KromiPrint.process;
import KromiPrint.models.PhotoOrder;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class OrderRepository {
private final Map<String, PhotoOrder> data = new ConcurrentHashMap<>();


public PhotoOrder save(PhotoOrder order) {
data.put(order.getId(), order);
return order;
}


public Optional<PhotoOrder> findById(String id) {
return Optional.ofNullable(data.get(id));
}


public List<PhotoOrder> findAll() { return new ArrayList<>(data.values()); }


public void delete(String id) { data.remove(id); }
}
