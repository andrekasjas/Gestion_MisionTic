/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author erika
 */
public class Directivo extends Empleado{
    private int Categoria;
    private ArrayList<Empleado> empleados;
    
    public Directivo(int id,String nombre, Date fecha_nacimiento, int Sueldo_bruto, int Categoria, String documento) {
        super(id, nombre, fecha_nacimiento, Sueldo_bruto,documento);
        this.Categoria= Categoria;
        this.empleados = new ArrayList<Empleado>();
    }

    /**
     * @return the Categoria
     */
    public int getCategoria() {
        return Categoria;
    }

    /**
     * @param Categoria the Categoria to set
     */
    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    /**
     * @return the empleados
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    
    public boolean eliminarEmpleado(Empleado empleado){
        try {
            this.empleados.remove(empleado);
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean agregarEmpleado(Empleado empleado){
        try {
            this.empleados.add(empleado);
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        }
    }
}
