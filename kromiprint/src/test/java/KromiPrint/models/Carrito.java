package KromiPrint.models;


import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Foto> fotos = new ArrayList<>();

    public void agregarFoto(Foto foto) {
        fotos.add(foto);
    }

    public double calcularTotal() {
        return fotos.stream().mapToDouble(Foto::getPrecio).sum();
    }

    public List<Foto> getFotos() {
        return fotos;
    }
}
