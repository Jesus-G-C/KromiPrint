package KromiPrint.ui;

import java.util.List;
import java.util.Scanner;

import KromiPrint.models.Customer;
import KromiPrint.models.PhotoOrder;
import KromiPrint.models.PhotoSize;
import KromiPrint.models.Priority;
import KromiPrint.process.AdminService;
import KromiPrint.process.CashierService;
import KromiPrint.process.EmployeeService;
import KromiPrint.process.OrderRepository;
import KromiPrint.process.OrderService;
import KromiPrint.process.QueueManager;

public class CLI {

    private final OrderRepository repo = new OrderRepository();
    private final OrderService orderService = new OrderService(repo);
    private final EmployeeService employeeService = new EmployeeService(repo);
    private final CashierService cashierService = new CashierService(repo);
    private final AdminService adminService = new AdminService(repo);
    private final QueueManager queueManager = new QueueManager(repo);

    private final Scanner sc = new Scanner(System.in);
    
   


    public static void main(String[] args) {
        new CLI().runApp();
    }

    private void runApp() {

        System.out.println("Welcome to KromiPrint");
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> clientFlow();
                case "2" -> employeeFlow();
                case "3" -> cashierFlow();
                case "4" -> adminFlow();
                case "0" -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        System.out.println("Bye!");
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("==== Main Menu ====");
        System.out.println("1) Client");
        System.out.println("2) Pharmacy Employee");
        System.out.println("3) Cashier");
        System.out.println("4) Kromi Admin");
        System.out.println("0) Exit");
        System.out.print("> ");
    }

    // ---------------- CLIENT ----------------
    private void clientFlow() {
        System.out.println("-- Client --");
        Priority priority = askPriority();

        String name = ask("Name: ");
        String phone = null;
        String address = null;
        if (priority != Priority.NOW) {
            phone = ask("Phone: ");
            address = ask("Address: ");
        }
        Customer c = new Customer(name, phone, address);
        PhotoOrder order = orderService.createOrder(c, priority);

        boolean done = false;
        while (!done) {
            System.out.println("Add photo item:");
            PhotoSize size = askSize();
            orderService.addItem(order.getId(), size, priority);
            String more = ask("Add another? (y/n): ");
            if (!more.equalsIgnoreCase("y")) done = true;
        }

        System.out.println("Order created: " + order);
        System.out.println("Ticket preview:\n" + order.ticket());
    }

    private Priority askPriority() {
        System.out.println("Select priority: 1) NOW  2) ONE HOUR  3) SEVERAL DAYS");
        while (true) {
            System.out.print("> ");
            String s = sc.nextLine().trim();
            switch (s) {
                case "1": return Priority.NOW;
                case "2": return Priority.ONE_HOUR;
                case "3": return Priority.SEVERAL_DAYS;
                default: System.out.println("Invalid. Try 1/2/3.");
            }
        }
    }

    private PhotoSize askSize() {
        System.out.println("Select size: 1) 4x6  2) 5x7  3) 6x8");
        while (true) {
            System.out.print("> ");
            String s = sc.nextLine().trim();
            switch (s) {
                case "1": return PhotoSize.FOUR_BY_SIX;
                case "2": return PhotoSize.FIVE_BY_SEVEN;
                case "3": return PhotoSize.SIX_BY_EIGHT;
                default: System.out.println("Invalid. Try 1/2/3.");
            }
        }
    }

    private String ask(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
        
    }

    // -------------- EMPLOYEE ---------------
    private void employeeFlow() {
        System.out.println("-- Pharmacy Employee --");
        boolean back = false;
        while (!back) {
            System.out.println("1) View queue by priority");
            System.out.println("2) Open order");
            System.out.println("3) Print order (removes from queue)");
            System.out.println("4) Cancel order (removes from queue)");
            System.out.println("0) Back");
            System.out.print("> ");
            String s = sc.nextLine().trim();
            switch (s) {
                case "1" -> showQueue();
                case "2" -> {
                    String id = ask("Order ID: ");
                    try { System.out.println(employeeService.open(id)); }
                    catch (Exception e) { System.out.println(e.getMessage()); }
                }
                case "3" -> {
                    String id = ask("Order ID: ");
                    try { System.out.println("Printed: \n" + employeeService.print(id).ticket()); }
                    catch (Exception e) { System.out.println(e.getMessage()); }
                }
                case "4" -> {
                    String id = ask("Order ID: ");
                    try { System.out.println("Cancelled: " + employeeService.cancel(id)); }
                    catch (Exception e) { System.out.println(e.getMessage()); }
                }
                case "0" -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void showQueue() {
        List<PhotoOrder> list = queueManager.listByPriority();
        if (list.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("-- Queue (by priority, then time) --");
        list.forEach(o -> System.out.println(o));
    }

    // --------------- CASHIER ----------------
    private void cashierFlow() {
        System.out.println("-- Cashier --");
        String id = ask("Order ID to pay: ");
        try {
            String ticket = cashierService.pay(id);
            System.out.println("Payment completed. Ticket:\n" + ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------------- ADMIN -----------------
    private void adminFlow() {
        System.out.println("-- Kromi Admin --");
        System.out.println("Active orders: " + adminService.totalOrders());
        System.out.printf("Revenue estimate (active orders): $%.2f\n", adminService.revenueEstimate());
        System.out.println("List all active orders:");
        adminService.listAll().forEach(System.out::println);
    }
}
