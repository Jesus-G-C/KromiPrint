package KromiPrint.models;

public class Foto {
    private String codigo;
    private String tamaño;
    private double precio;

    public Foto(String tamaño, String prioridad) {
        this.tamaño = tamaño;
        this.codigo = obtenerCodigo(tamaño, prioridad);
        this.precio = calcularPrecio(tamaño, prioridad);
    }

    // Mapeo de códigos fijos
    private String obtenerCodigo(String tamaño, String prioridad) {
        switch (prioridad.toLowerCase()) {
            case "ahora":
                switch (tamaño) {
                    case "4x6": return "555";
                    case "5x7": return "556";
                    case "6x8": return "557";
                }
                break;
            case "una hora":
                switch (tamaño) {
                    case "4x6": return "5055";
                    case "5x7": return "5056";
                    case "6x8": return "5057";
                }
                break;
            case "varios días":
                switch (tamaño) {
                    case "4x6": return "5155";
                    case "5x7": return "5156";
                    case "6x8": return "5157";
                }
                break;
        }
        return "0000"; // Por defecto si hay error
    }

    // Cálculo de precios con base en prioridad y tamaño
    private double calcularPrecio(String tamaño, String prioridad) {
        double base;
        switch (tamaño) {
            case "4x6": base = 7; break;
            case "5x7": base = 14; break;
            case "6x8": base = 18; break;
            default: base = 10;
        }

        switch (prioridad.toLowerCase()) {
            case "ahora": return base * 1.5;
            case "una hora": return base * 1.2;
            default: return base; // varios días
        }
    }

    public double getPrecio() { return precio; }
    public String getCodigo() { return codigo; }
    public String getTamaño() { return tamaño; }

    @Override
    public String toString() {
        return "Foto " + tamaño + " | Código: " + codigo + " | $" + precio;
    }
}
