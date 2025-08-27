package KromiPrint.ui;

import java.util.Scanner;

import KromiPrint.models.Carrito;
import KromiPrint.models.Cliente;
import KromiPrint.process.GestorPedidos;
import KromiPrint.process.TicketGenerador;

public class CLI {

    public static void runApp(){
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            showMenu();
            option = scanner.nextInt();scanner.nextLine();

            switch (option){
                case 1:
                  GestorPedidos gestor = new GestorPedidos();
        TicketGenerador ticketGen = new TicketGenerador();

        // Registrar cliente
        Cliente cliente = gestor.registrarCliente();

        // Gestionar carrito
        Carrito carrito = gestor.gestionarCarrito(cliente.getPrioridad());

        // Generar ticket
        ticketGen.generarTicket(cliente, carrito);
                    break;

                case 2:
                    
                    break;

                case 3:
                   
                    break;


                case 0:
                    System.out.print("❓ ¿Seguro que deseas salir? (s/n): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    
                    if (confirm.equals("s")) {
                        System.out.println("👋 Cerrando aplicación... ¡Hasta pronto!");
                        return; 

                    } else {
                        option = -1; 
                    }
                    break;

                default:
                    System.out.println("⚠️ Opción no válida, intenta de nuevo.");
            }
        } while (option != 0);
    }

    public static void showMenu(){
        System.out.println("""
            
            ╔═══════════════════════════════════════════════╗
            ║              🌟 Bienvenido 🌟                 ║
            ║═══════════════════════════════════════════════║
            ║  1️⃣  Acceder como Cliente                      ║
            ║  2️⃣  Acceder como Empleado                     ║
            ║  0️⃣  Salir 🚪                                  ║
            ╚═══════════════════════════════════════════════╝
            """);
    }

}