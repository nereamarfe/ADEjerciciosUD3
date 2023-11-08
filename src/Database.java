import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conexion;
    private final String usuario = "root";
    private final String password = "abc123.";
    private final String url = "jdbc:mysql://localhost:3306/empleados";

    private Database(){
        try {
            this.conexion = DriverManager.getConnection(url,usuario,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
