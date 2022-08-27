/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Cliente;
import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIFormCliente;
import empresa.views.GUIFormEliminarPorDocumento;
import empresa.views.GUIListaClientes;
import empresa.views.GUIMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerListaClientes implements ActionListener{
    
    private Empresa empresa;
    
    private GUIListaClientes vista;

    public ControllerListaClientes( GUIListaClientes vista,Empresa empresa) {
        this.empresa = empresa;
        this.vista = vista;
        int promedio_edad = obtenerListadoClientes();
        vista.txtPromEdad.setText("Promedio de edad: "+promedio_edad+" Años");
        vista.btnAgregarCliente.addActionListener(this);
        vista.btnEliminarCliente.addActionListener(this);
        vista.Volver.addActionListener(this);
        vista.btnModificar.addActionListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.vista.Volver){
            this.vista.dispose();
            GUIMenu gui= new GUIMenu();
            ControllerMenu gui2 = new ControllerMenu(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Mi empresa");
            gui.setVisible(true);
        }
        if(evt.getSource() == this.vista.btnAgregarCliente){
            this.vista.dispose();
            GUIFormCliente gui= new GUIFormCliente();
            ControllerFormCliente gui2= new ControllerFormCliente(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Agregar Cliente");
            gui.setVisible(true);
        }
        if(evt.getSource() == this.vista.btnEliminarCliente){
            this.vista.dispose();
            GUIFormEliminarPorDocumento gui= new GUIFormEliminarPorDocumento();
            ControllerFormEliminarCliente gui2 = new ControllerFormEliminarCliente(gui,this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Eliminar cliente");
            gui.setVisible(true);
        }
        
        if(evt.getSource() == this.vista.btnModificar){
            int row = vista.tablaClientes.getSelectedRow();
            if(row != -1){
                String documento = vista.tablaClientes.getValueAt(row, 0).toString();
                Cliente cliente = this.empresa.BuscarCliente(documento);
                this.vista.dispose();
                GUIFormCliente gui = new GUIFormCliente();
                ControllerFormCliente ctl = new ControllerFormCliente(gui, empresa, cliente);
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para continuar con la solicitud");
            }
        }
    }
    
    private int obtenerListadoClientes(){ 
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        listaClientes = this.empresa.obtenerClientes(this.empresa.getId());
        int promedio_edad = this.empresa.promedioEdadClientes(listaClientes);
        String matriz [][] = new String[listaClientes.size()][4];
        for (int i = 0; i < listaClientes.size(); i++) {
            matriz[i][0] = listaClientes.get(i).getDocumento();
            matriz[i][1] = listaClientes.get(i).getNombre();
            matriz[i][2] = String.valueOf(listaClientes.get(i).getEdad());
            matriz[i][3] = listaClientes.get(i).getTelefono();
        }
        
        this.vista.tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
        matriz,
        new String [] {
            "Documento", "Nombre", "Edad", "Telefono"
        }));
        return promedio_edad;
    }
    
}
