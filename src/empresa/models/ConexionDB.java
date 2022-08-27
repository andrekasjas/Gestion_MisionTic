/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CABEZ
 */
public class ConexionDB {
    
    private static Connection cnx = null;
    
    public static Connection abriConexion(){
      
        if (cnx == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                        + "reto_empresa", "root", "");
                System.out.println("se conecto correctamente");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Ya esta conectado");
        }
        
        return cnx;
    }
    
    public static void cerrarConexion(){
        if(cnx != null){
            try {
                cnx.close();
                cnx = null;
                System.out.println("se cerro");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }else{
            System.out.println("Sin conexiones");
        }
    }
}
