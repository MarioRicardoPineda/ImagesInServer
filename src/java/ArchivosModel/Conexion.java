
package ArchivosModel;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    
    static String user = "root";
    static String pass = "database";
    static String url = "jdbc:mysql://localhost:3306/imagenes";
    
    Connection con = null;
    
    public Conexion (){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Tu conexion fue un exito");
            }
            
        } catch (Exception e) {
            System.out.println("La conexion fallo! "+e);
        }
    }
    
    public Connection getConection(){
        return con;
    }
    
    public void desconectar() throws Exception{
        con.close();
    }
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
    }
    
}
