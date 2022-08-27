/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CABEZ
 */
public class ReporteGraficos {
    private String nombre;
    private int cantidad;

    public ReporteGraficos(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public ReporteGraficos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<ReporteGraficos> obtenerDatos_directivos(Empresa empresa) {
        ArrayList<ReporteGraficos> listaReporte = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT COUNT(e.directivo_id) AS cuenta,("
                    + "     SELECT pp.nombre "
                    + "     FROM persona pp, empleado ee "
                    + "     WHERE ee.id = d.empleado_id AND pp.id= ee.persona_id) AS directivo\n"
                    + " FROM empleado e"
                    + " inner join empresa_empleado empemp on e.id = empemp.empleado_id and empemp.empresa_id = ?"
                    + " INNER JOIN directivo d ON d.id = e.directivo_id"
                    + " WHERE directivo_id IS NOT NULL"
                    + " GROUP by directivo_id");
            consulta.setInt(1, empresa.getId());
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                listaReporte.add(new ReporteGraficos(resultado.getString("directivo"),
                                                       resultado.getInt("cuenta"))); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaReporte;
    }
    
    public ArrayList<ReporteGraficos> obtenerDatos_cliente_empresa() {
        ArrayList<ReporteGraficos> listaReporte = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT e.nombre, COUNT(cm.empresa_id) as cuenta"
                    + " FROM cliente_empresa cm"
                    + " INNER JOIN empresa e ON e.id = cm.empresa_id"
                    + " GROUP BY empresa_id");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                listaReporte.add(new ReporteGraficos(resultado.getString("nombre"),
                                                       resultado.getInt("cuenta"))); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaReporte;
    }
     
                                 
}
