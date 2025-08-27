package KromiPrint.process;


import KromiPrint.models.Carrito;
import KromiPrint.models.Cliente;
import KromiPrint.models.Foto;

public class TicketGenerador {
    public void generarTicket(Cliente cliente, Carrito carrito) {
        System.out.println("\n===== TICKET DE COMPRA =====");
        System.out.println("Cliente: " + cliente.getNombre());
        if (!cliente.getPrioridad().equals("ahora")) {
            System.out.println("Domicilio: " + cliente.getDomicilio());
            System.out.println("Teléfono: " + cliente.getTelefono());
        }
        System.out.println("Prioridad: " + cliente.getPrioridad());
        System.out.println("\nFotos:");
        for (Foto foto : carrito.getFotos()) {
            System.out.println(" - " + foto);
        }
        System.out.println("\nTOTAL A PAGAR: $" + carrito.calcularTotal());
        System.out.println("============================");
    }
}
