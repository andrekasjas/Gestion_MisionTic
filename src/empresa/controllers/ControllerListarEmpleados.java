/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Directivo;
import empresa.models.Empleado;
import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIFormEliminarPorDocumento;
import empresa.views.GUIFormEmpleado;
import empresa.views.GUIListaSubOrdinados;
import empresa.views.GUIListarEmpleados;
import empresa.views.GUIMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerListarEmpleados implements ActionListener {
    
    private Empresa empresa;
    private GUIListarEmpleados vista;

    public ControllerListarEmpleados(GUIListarEmpleados vista,Empresa empresa) {
        this.empresa = empresa;
        this.vista = vista;
        int pagos = obtenerListadoEmpleados();
        vista.txtpagos.setText("Total pagos: "+pagos+"$");
        vista.Volver.addActionListener(this);
        vista.btnAgregarEmpleado.addActionListener(this);
        vista.btnEliminarEmpleado.addActionListener(this);
        vista.btnModificar.addActionListener(this);
        vista.btnSubornados.addActionListener(this);
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
        if(evt.getSource() == this.vista.btnAgregarEmpleado){
            this.vista.dispose();
            GUIFormEmpleado gui= new GUIFormEmpleado();
            ControllerFormEmpleado gui2= new ControllerFormEmpleado(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Agregar empleado");
            gui.setVisible(true);
        }
        if(evt.getSource() == this.vista.btnEliminarEmpleado){
            this.vista.dispose();
            GUIFormEliminarPorDocumento gui= new GUIFormEliminarPorDocumento();
            ControllerFormEliminarEmpleado gui2 = new ControllerFormEliminarEmpleado(gui,this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Eliminar empleado");
            gui.setVisible(true);
        }
        
        if(evt.getSource() == this.vista.btnModificar){
            int row = vista.tablaEmpleados.getSelectedRow();
            if(row != -1){
                String documento = vista.tablaEmpleados.getValueAt(row, 0).toString();
                Empleado empleado = this.empresa.BuscarEmpleado(documento);
                this.vista.dispose();
                GUIFormEmpleado gui = new GUIFormEmpleado();
                ControllerFormEmpleado ctl = new ControllerFormEmpleado(gui, empresa, empleado);
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una empleado para continuar con la solicitud");
            }
        }
        
        if(evt.getSource() == this.vista.btnSubornados){
            int row = vista.tablaEmpleados.getSelectedRow();
            if(row != -1){
                String cargo = vista.tablaEmpleados.getValueAt(row, 4).toString();
                String documento = vista.tablaEmpleados.getValueAt(row, 0).toString();
                if(cargo.equals("Directivo")){
                    Directivo directivo = this.empresa.BuscarDirectivo(documento);
                    this.vista.dispose();
                    GUIListaSubOrdinados gui = new GUIListaSubOrdinados();
                    ControllerListaSubOrdinados ctl = new ControllerListaSubOrdinados(gui, empresa, directivo);
                    gui.setLocationRelativeTo(null);
                    gui.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "El empleado seleccionado no es directivo");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una empleado para continuar con la solicitud");
            }
            
        }
    }
    
    
    private int obtenerListadoEmpleados(){ 
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados = this.empresa.obtenerEmpleados(this.empresa.getId());
        int pagos = this.empresa.totalPagoEmpleados(listaEmpleados);
        String matriz [][] = new String[listaEmpleados.size()][5];
        for (int i = 0; i < listaEmpleados.size(); i++) {
            matriz[i][0] = listaEmpleados.get(i).getDocumento();
            matriz[i][1] = listaEmpleados.get(i).getNombre();
            matriz[i][2] = String.valueOf(listaEmpleados.get(i).getEdad());
            matriz[i][3] = String.valueOf(listaEmpleados.get(i).getSueldo_bruto());
            if(this.empresa.esDirectivo(listaEmpleados.get(i).getId())){
                matriz[i][4] = "Directivo";
            }else{
                matriz[i][4] = "Normal";
            }
        }
        
        this.vista.tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
        matriz,
        new String [] {
            "Documento", "Nombre", "Edad", "Sueldo","Cargo"
        }));
        return pagos;
    }
    
    
}
