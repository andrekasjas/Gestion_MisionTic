/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIFormEmpresa;
import empresa.views.GUISeleccionEmpresa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerFormEmpresa implements ActionListener{
    
    private Empresa empresa;
    private GUIFormEmpresa vista;

    public ControllerFormEmpresa( GUIFormEmpresa vista, Empresa empresa) {
        this.empresa = empresa;
        this.vista = vista;
        vista.Volver.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.vista.btnAgregar){
            try {
                String nombre = this.vista.txtNombre.getText();
                String CIF = this.vista.txtCIF.getText();
                
                boolean creado = this.empresa.agregarEmpresa(nombre, CIF);
                if (creado){
                    this.vista.dispose();
                    GUISeleccionEmpresa gui= new GUISeleccionEmpresa();
                    ReporteGraficos modelo = new ReporteGraficos();
                    ControllerSeleccionEmpresa gui2 = new ControllerSeleccionEmpresa(gui, this.empresa, modelo);
                    gui.setLocationRelativeTo(null);
                    gui.setTitle("Lista de clientes");
                    gui.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo crear la empresa");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
        
        
        if(evt.getSource()==this.vista.Volver){
            this.vista.dispose();
            GUISeleccionEmpresa gui= new GUISeleccionEmpresa();
            ReporteGraficos modelo = new ReporteGraficos();
            ControllerSeleccionEmpresa gui2 = new ControllerSeleccionEmpresa(gui, this.empresa, modelo);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de clientes");
            gui.setVisible(true);
        }
    }
    
}
