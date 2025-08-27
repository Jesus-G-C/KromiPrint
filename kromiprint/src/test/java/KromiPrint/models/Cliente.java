package KromiPrint.models;

public class Cliente {
    private String nombre;
    private String domicilio;
    private String telefono;
    private String prioridad; // "ahora", "una hora", "varios días"

    public Cliente(String nombre, String domicilio, String telefono, String prioridad) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.prioridad = prioridad;
    }

    public Cliente(String nombre, String prioridad) {
        this(nombre, "", "", prioridad);
    }

    public String getNombre() { return nombre; }
    public String getDomicilio() { return domicilio; }
    public String getTelefono() { return telefono; }
    public String getPrioridad() { return prioridad; }
}
