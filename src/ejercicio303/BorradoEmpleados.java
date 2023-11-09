package ejercicio303;


import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BorradoEmpleados {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try(Connection conn = Database.getInstance()){
            System.out.println("Introduce el número del empleado a eliminar");
            int num = sc.nextInt();

            //Desactivamos las foreign keys
            Statement stm = conn.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");

            //Preparamos borrado
            PreparedStatement ps = conn.prepareStatement("DELETE FROM empleado WHERE NSS=?");
            ps.setInt(1,num);
            int numTuplas = ps.executeUpdate();
            ps.close();
            System.out.println("Sentencia: " + ps.toString());
            System.out.println("Número de tuplas afectadas " + numTuplas);

            //volvemos a activar las claves foraneas
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            stm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
