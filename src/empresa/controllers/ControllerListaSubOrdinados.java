/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Directivo;
import empresa.models.Empleado;
import empresa.models.Empresa;
import empresa.views.GUIListaSubOrdinados;
import empresa.views.GUIListarEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author CABEZ
 */
public class ControllerListaSubOrdinados implements ActionListener{
    
    private GUIListaSubOrdinados vista;
    private Empresa empresa;
    private Directivo directivo;

    public ControllerListaSubOrdinados(GUIListaSubOrdinados vista, Empresa empresa, Directivo directivo) {
        this.vista = vista;
        this.empresa = empresa;
        this.directivo = directivo;
        int pagos = obtenerListadoEmpleados();
        vista.txtpagos.setText("Pagos de directivo: "+pagos+"$");
        vista.txtDirectivo.setText(directivo.getNombre());
        vista.Volver.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent evt) {
        
        if(evt.getSource()==this.vista.Volver){
            this.vista.dispose();
            GUIListarEmpleados gui = new GUIListarEmpleados();
            ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de empleados");
            gui.setVisible(true);
        }
    }
    
    
    private int obtenerListadoEmpleados(){ 
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados = this.empresa.obtenerEmpleadosSubordinados(this.directivo);
        String matriz [][] = new String[listaEmpleados.size()][4];
        for (int i = 0; i < listaEmpleados.size(); i++) {
            matriz[i][0] = listaEmpleados.get(i).getDocumento();
            matriz[i][1] = listaEmpleados.get(i).getNombre();
            matriz[i][2] = String.valueOf(listaEmpleados.get(i).getEdad());
            matriz[i][3] = String.valueOf(listaEmpleados.get(i).getSueldo_bruto());
        }
        
        this.vista.tablaSubordinados.setModel(new javax.swing.table.DefaultTableModel(
        matriz,
        new String [] {
            "Documento", "Nombre", "Edad", "Sueldo"
        }));
        return this.empresa.totalPagoEmpleados(listaEmpleados);
    }
    
}
