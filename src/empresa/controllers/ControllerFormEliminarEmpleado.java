/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Empleado;
import empresa.models.Empresa;
import empresa.views.GUIFormEliminarPorDocumento;
import empresa.views.GUIListarEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerFormEliminarEmpleado implements ActionListener{
    
    private GUIFormEliminarPorDocumento vista;
    private Empresa empresa;

    public ControllerFormEliminarEmpleado(GUIFormEliminarPorDocumento vista, Empresa empresa) {
        this.vista = vista;
        this.empresa = empresa;
        vista.Volver.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()== this.vista.btnEliminar){
            try {
                String documento = this.vista.txtdocumento.getText();
                Empleado empleado = this.empresa.BuscarEmpleado(documento);
                if (empleado != null){
                    this.empresa.eliminarEmpleado(empleado.getId(),this.empresa.getId());
                    this.vista.dispose();
                    GUIListarEmpleados gui= new GUIListarEmpleados();
                    ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
                    gui.setLocationRelativeTo(null);
                    gui.setTitle("Lista de empleados");
                    gui.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Documento no registrado");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        
        if(evt.getSource() == this.vista.Volver){
            this.vista.dispose();
            GUIListarEmpleados gui= new GUIListarEmpleados();
            ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de empleados");
            gui.setVisible(true);
        }
        
    }
    
    
    
}
