/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIFormEmpresa;
import empresa.views.GUIMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import empresa.views.GUISeleccionEmpresa;
import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author jnata
 */
public class ControllerSeleccionEmpresa implements ActionListener {

    private GUISeleccionEmpresa vista;
    private ReporteGraficos modelo;
    private Empresa empresa;

    public ControllerSeleccionEmpresa(GUISeleccionEmpresa vista, Empresa empresa, ReporteGraficos modelo) {
        this.vista = vista;
        this.empresa = empresa;
        this.modelo = modelo;
        obtenerListaEmpresas();
        vista.BtnSeleccionar.addActionListener(this);
        vista.btnCrear.addActionListener(this);
        graficar();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == this.vista.BtnSeleccionar) {

            if (vista.tablaEmpresas.getSelectedRow() != -1) {

                setEmpresa();
                this.vista.dispose();
                GUIMenu vista = new GUIMenu();
                ControllerMenu ctl = new ControllerMenu(vista, this.empresa);
                vista.setLocationRelativeTo(null);
                vista.setVisible(true);
                vista.setTitle("Menu");
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una empresa "
                        + "para continuar con la solicitud");
            }
        }
        
        if(evt.getSource() == this.vista.btnCrear){
            this.vista.dispose();
            GUIFormEmpresa gui= new GUIFormEmpresa();
            ControllerFormEmpresa gui2 = new ControllerFormEmpresa(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de clientes");
            gui.setVisible(true);
        }
    }

    private void obtenerListaEmpresas() {
        ArrayList<Empresa> listaEmpresas = new ArrayList<>();

        listaEmpresas = this.empresa.obtenerEmpresas();

        String matriz[][] = new String[listaEmpresas.size()][3];

        for (int i = 0; i < listaEmpresas.size(); i++) {
            matriz[i][0] = String.valueOf(listaEmpresas.get(i).getId());
            matriz[i][1] = listaEmpresas.get(i).getNombre();
            matriz[i][2] = listaEmpresas.get(i).getCIF();

        }

        vista.tablaEmpresas.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "Id", "Nombre", "Cif"
                }
        ));

        vista.tablaEmpresas.getColumnModel().getColumn(0).setMaxWidth(0);
        vista.tablaEmpresas.getColumnModel().getColumn(0).setMinWidth(0);
        vista.tablaEmpresas.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    private void setEmpresa() {
        int row = vista.tablaEmpresas.getSelectedRow();

        empresa.setId(Integer.parseInt(vista.tablaEmpresas.getValueAt(row, 0).toString()));
        empresa.setNombre(vista.tablaEmpresas.getValueAt(row, 1).toString());
        empresa.setCIF(vista.tablaEmpresas.getValueAt(row, 2).toString());;

    }
    
    private void graficar() {
        ArrayList<ReporteGraficos> listaReporte = new ArrayList<>();
        listaReporte = this.modelo.obtenerDatos_cliente_empresa();
        DefaultPieDataset datos = new DefaultPieDataset();
        for (ReporteGraficos reporteDirectivos : listaReporte) {
            datos.setValue(reporteDirectivos.getNombre(), reporteDirectivos.getCantidad());
        }
        JFreeChart torta = ChartFactory.createPieChart("Grafico de cantidad de "
                + "clientes por empresa", datos);
        ChartPanel panel = new ChartPanel(torta);
        panel.setMouseWheelEnabled(true);
        this.vista.panelEmp.setLayout(new BorderLayout());
        this.vista.panelEmp.add(panel,BorderLayout.CENTER);
        this.vista.validate();

    }

}
