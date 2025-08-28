package KromiPrint.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhotoOrder {
private final String id;
private final Customer customer;
private final Priority priority;
private final List<PhotoItem> items = new ArrayList<>();
private OrderStatus status = OrderStatus.CREATED;
private final LocalDateTime createdAt = LocalDateTime.now();


public PhotoOrder(Customer customer, Priority priority) {
this.id = UUID.randomUUID().toString().substring(0,8);
this.customer = customer;
this.priority = priority;
}


public String getId() { return id; }
public Customer getCustomer() { return customer; }
public Priority getPriority() { return priority; }
public List<PhotoItem> getItems() { return items; }
public OrderStatus getStatus() { return status; }
public void setStatus(OrderStatus status) { this.status = status; }
public LocalDateTime getCreatedAt() { return createdAt; }


public void addItem(PhotoItem item) { items.add(item); }


public double total() {
return items.stream().mapToDouble(PhotoItem::price).sum();
}


public String ticket() {
StringBuilder sb = new StringBuilder();
sb.append("===== KromiPrint – Ticket =====\n");
sb.append("Order ID: ").append(id).append("\n");
sb.append("Customer: ").append(customer.getName()).append("\n");
if (customer.getPhone() != null) sb.append("Phone: ").append(customer.getPhone()).append("\n");
if (customer.getAddress() != null) sb.append("Address: ").append(customer.getAddress()).append("\n");
sb.append("Priority: ").append(priority.name()).append("\n\n");
sb.append("Items:\n");
for (int i = 0; i < items.size(); i++) {
PhotoItem it = items.get(i);
sb.append(String.format("%2d. %s [%s, %s] - $%.2f\n",
i + 1,
it.getCode(),
it.getSize().getLabel(),
it.getPriority().name(),
it.price()));
}
sb.append("------------------------------\n");
sb.append(String.format("TOTAL: $%.2f\n", total()));
sb.append("Status: ").append(status.name()).append("\n");
sb.append("Created: ").append(createdAt).append("\n");
sb.append("==============================\n");
return sb.toString();
}


@Override
public String toString() {
return String.format("%s | %s | items=%d | $%.2f | %s",
id.substring(0, 8), priority.name(), items.size(), total(), status.name());
}
}