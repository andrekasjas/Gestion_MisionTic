/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author erika
 */
public class Empleado extends Persona {
    private int Sueldo_bruto;
    
    public Empleado(int id, String nombre, Date fecha_nacimiento, int Sueldo_bruto, String documento) {
        super(id, nombre, fecha_nacimiento, documento);
        this.Sueldo_bruto= Sueldo_bruto;
    }

    /**
     * @return the Sueldo_bruto
     */
    public int getSueldo_bruto() {
        return Sueldo_bruto;
    }

    /**
     * @param Sueldo_bruto the Sueldo_bruto to set
     */
    public void setSueldo_bruto(int Sueldo_bruto) {
        this.Sueldo_bruto = Sueldo_bruto;
    }
    
    
    
}
