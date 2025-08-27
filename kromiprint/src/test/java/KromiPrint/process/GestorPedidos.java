package KromiPrint.process;

import java.util.Scanner;

import KromiPrint.models.Carrito;
import KromiPrint.models.Cliente;
import KromiPrint.models.Foto;

public class GestorPedidos {
    private Scanner sc = new Scanner(System.in);

    public Cliente registrarCliente() {
        System.out.println("\n--- Registro de cliente ---");
        System.out.print("Ingrese prioridad (ahora/una hora/varios días): ");
        String prioridad = sc.nextLine().toLowerCase();

        if (prioridad.equals("ahora")) {
            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            return new Cliente(nombre, prioridad);
        } else {
            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese domicilio: ");
            String domicilio = sc.nextLine();
            System.out.print("Ingrese teléfono: ");
            String telefono = sc.nextLine();
            return new Cliente(nombre, domicilio, telefono, prioridad);
        }
    }

    public Carrito gestionarCarrito(String prioridad) {
        Carrito carrito = new Carrito();
        boolean agregarOtra;
        do {
            System.out.println("\n--- Agregar foto ---");
            System.out.print("Seleccione tamaño (4x6, 5x7, 6x8): ");
            String tamaño = sc.nextLine();
            Foto foto = new Foto(tamaño, prioridad);
            carrito.agregarFoto(foto);
            System.out.println("Foto agregada: " + foto);

            System.out.print("¿Agregar otra foto? (s/n): ");
            agregarOtra = sc.nextLine().equalsIgnoreCase("s");
        } while (agregarOtra);

        return carrito;
    }
}
