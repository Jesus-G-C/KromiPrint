package KromiPrint.DBconections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDB {
    public void insertarUsuario(String numero, String nombre, String direccion) {
        String sql = "INSERT INTO usuario (telefono, nombre, direccion) VALUES (?, ?, ?)";
        try (Connection conn = (Connection) DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numero);
            stmt.setString(2, nombre);
            stmt.setString(3, direccion);
            stmt.executeUpdate();

            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }
}
