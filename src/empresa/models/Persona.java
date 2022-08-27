/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;


import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;



/**
 *
 * @author erika
 */
public class Persona {

    private int id;
    private String nombre;
    private Date fecha_nacimiento;
    private String documento;
    private int edad;
    
    public Persona(int id,String nombre,Date fecha_nacimiento,String documento){
        this.id = id;
        this.nombre=nombre;
        this.fecha_nacimiento= fecha_nacimiento; 
        this.documento = documento;
        LocalDate ahora = LocalDate.now();
        LocalDate fech = fecha_nacimiento.toLocalDate();
        Period periodo = Period.between(fech, ahora);        
        this.edad = periodo.getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fecha_nacimiento
     */
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * @param fecha_nacimiento the fecha_nacimiento to set
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
