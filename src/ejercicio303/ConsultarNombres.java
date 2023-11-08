package ejercicio303;

import java.sql.*;
import java.util.Scanner;

public class ConsultarNombres {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String urljdbc = "jdbc:mysql://localhost:3306/empleados"; //metes la conexion a la base de datos
        String user = "root";
        String password = "abc123.";
        try (Connection conexion = DriverManager.getConnection(urljdbc,user,password);){

            System.out.println("Introduce la letra inicial de los nombres");
            String letra = sc.nextLine();
            Statement sentencia;
            sentencia = conexion.createStatement();
            String sql = "SELECT Nombre FROM empleado WHERE Nombre REGEXP '^" + letra + "';";
            //psentencia = conexion.prepareStatement(sql);
            //psentencia.setString(1,letra); //Con PrepareStatement habría que sustituir ? = '^letra'
            ResultSet rset = sentencia.executeQuery(sql);
            System.out.println("Nombres empleados que empiezan por: " + letra);
            while(rset.next()){
                System.out.println(rset.getString("Nombre"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
