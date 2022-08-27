/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIListaClientes;
import empresa.views.GUIListarEmpleados;
import empresa.views.GUIMenu;
import empresa.views.GUIReporteDirectivos;
import empresa.views.GUISeleccionEmpresa;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author CABEZ
 */
public class ControllerMenu implements ActionListener {

    private GUIMenu vista;
    private Empresa empresa;
    
    public ControllerMenu(GUIMenu vista, Empresa empresa) {
        this.vista = vista;
        vista.tituloempresa.setText(empresa.getNombre());
        this.empresa = empresa;
        vista.ConsultarClientes.addActionListener(this);
        vista.Consultarempleado.addActionListener(this);
        vista.Volver.addActionListener(this);
        vista.btnReporte.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.vista.ConsultarClientes){
            this.vista.dispose();
            GUIListaClientes gui= new GUIListaClientes();
            ControllerListaClientes gui2 = new ControllerListaClientes(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de clientes");
            gui.setVisible(true);
        }
        if(evt.getSource() == this.vista.Consultarempleado){
            this.vista.dispose();
            GUIListarEmpleados gui= new GUIListarEmpleados();
            ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de empleados");
            gui.setVisible(true);
        }
        
        if(evt.getSource() == this.vista.btnReporte){
            this.vista.dispose();
            GUIReporteDirectivos gui = new GUIReporteDirectivos();
            ReporteGraficos modelo = new ReporteGraficos();
            ControllerReporteDirectivos crd = new ControllerReporteDirectivos(gui, modelo, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de empleados");
            gui.setVisible(true);
        }
        
        if(evt.getSource() == this.vista.Volver){
            this.vista.dispose();
            GUISeleccionEmpresa gui= new GUISeleccionEmpresa();
            ReporteGraficos modelo = new ReporteGraficos();
            ControllerSeleccionEmpresa gui2 = new ControllerSeleccionEmpresa(gui, this.empresa, modelo);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Mi empresa");
            gui.setVisible(true);
        }
    }
    
}
