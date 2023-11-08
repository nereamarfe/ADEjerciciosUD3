import java.sql.*;
import java.util.Scanner;

public class Ejercicio302 {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String urljdbc = "jdbc:mysql://localhost:3306/empleados"; //metes la conexion a la base de datos
        String user = "root";
        String password = "abc123.";
        try (Connection conexion = DriverManager.getConnection(urljdbc,user,password);){
            Statement smt = conexion.createStatement();
            //Mostrar la información de la base de datos
            mostrarInfoBD(conexion);
            //Mostrar la información de la tabla proyectos.
            mostrarInfoTablaProyectos(smt);
            //Insertar un nuevo proyecto con los siguientes datos (11, 'Acceso a datos', 'Lugo', 3) en la tabla proyecto.
            insertarNuevoProyecto(conexion, 11, "Acceso a datos", "Lugo", 3);
            //Eliminar una fila de la tabla proyecto a partir de su número de proyecto numproy.
            //eliminarTuplaTablaProyectos(conexion, 11);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void mostrarInfoBD(Connection con){
        try {
            DatabaseMetaData dbmd = con.getMetaData();
            String gestor = dbmd.getDatabaseProductName();
            String conector = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("Información de la base de datos Empleados");
            System.out.println("-----------------------------------------");
            System.out.println("Gestor: " + gestor);
            System.out.println("Conector: " + conector);
            System.out.println("URL: " + url);
            System.out.println("Usuario: " + usuario);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarInfoTablaProyectos(Statement sentencia){
        System.out.println("-----------------------------------------");
        ResultSet rset;
        String sql= "SELECT * FROM proyecto;";
        try {
            rset = sentencia.executeQuery(sql);
            System.out.println("PROYECTOS:");
            while(rset.next()){
                System.out.println("\tNúmero: " + rset.getInt("Numproy") +
                                ", Nombre: " + rset.getString("Nombreproy") +
                                ", Lugar: " + rset.getString("Lugarproy") +
                                ", Nºdepartamento: " + rset.getInt("departamento_Numdep"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarNuevoProyecto(Connection con, int num, String nombre, String lugar, int numdepart){
        System.out.println("-----------------------------------------");
        PreparedStatement psentencia;
        String sql = "INSERT INTO proyecto VALUES (?,?,?,?);";
        try {
            psentencia = con.prepareStatement(sql);
            psentencia.setInt(1,num); //empieza por el valor 1
            psentencia.setString(2,nombre);
            psentencia.setString(3,lugar);
            psentencia.setInt(4,numdepart);

            int numTuplas = psentencia.executeUpdate(); //devuelve el número de tuplas afectadas
            System.out.println("Sentencia: " + psentencia.toString());
            System.out.println("Número de tuplas afectadas: " + numTuplas);

            psentencia.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarTuplaTablaProyectos( Connection con, int num){
        System.out.println("-----------------------------------------");
        PreparedStatement psentencia;
        String sql = "DELETE FROM proyecto WHERE Numproy = ?;";
        try {
            psentencia = con.prepareStatement(sql);
            psentencia.setInt(1,num);

            int numTuplas = psentencia.executeUpdate();
            System.out.println("Sentencia: " + psentencia.toString());
            System.out.println("Número de tuplas afectadas: " + numTuplas);

            psentencia.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
