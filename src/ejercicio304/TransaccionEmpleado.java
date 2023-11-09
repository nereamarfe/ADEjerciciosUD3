package ejercicio304;

import Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TransaccionEmpleado {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try(Connection conn = Database.getInstance()){
            PreparedStatement psentencia = conn.prepareStatement("INSERT INTO empleado(NSS,Nombre,Numdept) VALUES (?,?,?)");

            //iniciamos transaccion
            conn.setAutoCommit(false);
            psentencia.setInt(1,1);
            psentencia.setString(2,"Pedro");
            psentencia.setInt(3,1);
            psentencia.executeUpdate();

            psentencia.setInt(1,2);
            psentencia.setString(2,"Lucía");
            psentencia.setInt(3,1);
            psentencia.executeUpdate();

            psentencia.setInt(1,3);
            psentencia.setString(2,"Daniel");
            psentencia.setInt(3,1);
            psentencia.executeUpdate();
            //cerramos transacción
            conn.commit();
            System.out.println("Empleados introduciros");
            conn.rollback();

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.toString());
        }
    }

}
