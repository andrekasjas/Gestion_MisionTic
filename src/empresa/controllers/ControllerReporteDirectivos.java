/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Empresa;
import empresa.models.ReporteGraficos;
import empresa.views.GUIMenu;
import empresa.views.GUIReporteDirectivos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author CABEZ
 */
public class ControllerReporteDirectivos implements ActionListener{
    private GUIReporteDirectivos vista;
    private ReporteGraficos modelo;
    private Empresa empresa;

    public ControllerReporteDirectivos(GUIReporteDirectivos vista, ReporteGraficos modelo, Empresa empresa) {
        this.vista = vista;
        this.modelo = modelo;
        this.empresa = empresa;
        vista.Volver.addActionListener(this);
        graficar();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.vista.Volver){
            this.vista.dispose();
            GUIMenu vista = new GUIMenu();
            ControllerMenu ctl = new ControllerMenu(vista, this.empresa);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            vista.setTitle("Menu");
        }
    }
    
    
    private void graficar(){
        ArrayList<ReporteGraficos> listaReporte = new ArrayList<>();
        listaReporte = this.modelo.obtenerDatos_directivos(this.empresa);
        
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (ReporteGraficos reporteDirectivos : listaReporte) {
            datos.addValue(reporteDirectivos.getCantidad(), 
                    reporteDirectivos.getNombre(), 
                    String.valueOf(reporteDirectivos.getCantidad()));
        }
        JFreeChart torta = ChartFactory.createBarChart("Grafico de cantidad de "
                + "empleados de cada directivo", 
                "Nombre", 
                "Cantidad", 
                datos);
        ChartPanel panel = new ChartPanel(torta);
        panel.setMouseWheelEnabled(true);
        //panel.setPreferredSize(new Dimension(500,500));
        this.vista.PanelDirect.setLayout(new BorderLayout());
        this.vista.PanelDirect.add(panel,BorderLayout.CENTER);
        this.vista.validate();
        
        
    }
    
    
    
}
