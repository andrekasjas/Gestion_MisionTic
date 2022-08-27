/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import empresa.controllers.ControllerMenu;
import empresa.controllers.ControllerReporteDirectivos;
import empresa.controllers.ControllerSeleccionEmpresa;
import empresa.views.GUIMenu;
import empresa.models.ConexionDB;
import empresa.models.Empresa;
import empresa.models.Directivo;
import empresa.models.Empleado;
import empresa.models.Cliente;
import empresa.models.ReporteGraficos;
import empresa.views.GUIReporteDirectivos;
import empresa.views.GUISeleccionEmpresa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Erika
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        GUISeleccionEmpresa vista = new GUISeleccionEmpresa();
        Empresa empresa = new Empresa();
        ReporteGraficos modelo = new ReporteGraficos();
        ControllerSeleccionEmpresa ctl = new ControllerSeleccionEmpresa(vista, empresa, modelo);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setTitle("Seleccionar empresa");
        
        
    }
    
}
