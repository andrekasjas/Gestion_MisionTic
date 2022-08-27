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
public class Cliente extends Persona{
    
    private String telefono;
    private Empresa empresa[];
    
    public Cliente(int id,String nombre, Date fecha_nacimiento, String telefono, String documento) {
        super(id,nombre, fecha_nacimiento, documento);
        this.telefono= telefono;
        
    }

    

    /**
     * @return the empresa
     */
    public Empresa[] getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa[] empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
