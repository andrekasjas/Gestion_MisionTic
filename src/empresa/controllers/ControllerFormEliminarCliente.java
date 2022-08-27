/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Cliente;
import empresa.models.Empresa;
import empresa.views.GUIFormEliminarPorDocumento;
import empresa.views.GUIListaClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerFormEliminarCliente implements ActionListener{
    
    private GUIFormEliminarPorDocumento vista;
    private Empresa empresa;

    public ControllerFormEliminarCliente(GUIFormEliminarPorDocumento vista, Empresa empresa) {
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
                Cliente cliente = this.empresa.BuscarCliente(documento);
                System.out.println(cliente.getId());
                if (cliente != null){
                    this.empresa.eliminarCliente(cliente.getId(), this.empresa.getId());
                    this.vista.dispose();
                    GUIListaClientes gui= new GUIListaClientes();
                    ControllerListaClientes gui2 = new ControllerListaClientes(gui, this.empresa);
                    gui.setLocationRelativeTo(null);
                    gui.setTitle("Lista de clientes");
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
            GUIListaClientes gui= new GUIListaClientes();
            ControllerListaClientes gui2 = new ControllerListaClientes(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de clientes");
            gui.setVisible(true);
        }
        
    }
    
    
    
}
