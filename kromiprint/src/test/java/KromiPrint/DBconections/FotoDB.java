package KromiPrint.DBconections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FotoDB {
    public void insertarOrden(String idOrder, String Prioridad, String Items, double Precio, String Estatus, String Tamanio) {
        String sql = "INSERT INTO foto (IdOrder, Peoridad, Items, Precio, Estatus, Tamanio) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = (Connection) DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idOrder);
            stmt.setString(2, Prioridad);
            stmt.setString(3, Items);
            stmt.setDouble(4, Precio);
            stmt.setString(5, Estatus);
            stmt.setString(6, Tamanio);
            stmt.executeUpdate();

            System.out.println("Orden registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar orden: " + e.getMessage());
        }
    }
}
