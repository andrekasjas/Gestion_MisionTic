/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Directivo;
import empresa.models.Empleado;
import empresa.models.Empresa;
import empresa.models.Persona;
import empresa.views.GUIFormEmpleado;
import empresa.views.GUIListarEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerFormEmpleado implements ActionListener {
    
    private GUIFormEmpleado vista;
    private Empresa empresa;
    private Empleado empleado;

    public ControllerFormEmpleado(GUIFormEmpleado vista, Empresa empresa) {
        this.vista = vista;
        this.empresa = empresa;
        obtenerListadoEmpleados();
        vista.tablaEmpleados.setVisible(false);
        vista.labelcategoria.setVisible(false);
        vista.txtcategoria.setVisible(false);
        vista.labelAcargo.setVisible(false);
        vista.tablaEmpleados.setVisible(false);
        vista.Volver.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
        vista.btndirectivo.addActionListener(this);
    }

    public ControllerFormEmpleado(GUIFormEmpleado vista, Empresa empresa, Empleado empleado) {
        this.vista = vista;
        this.empresa = empresa;
        this.empleado = empleado;
        this.empresa = empresa;
       
        deshabilitarControles();
        llenarControles();
        vista.Volver.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == this.vista.btnAgregar) {
            try {
                int sueldo = Integer.parseInt(this.vista.txtSueldo.getText());
                if (this.vista.btnAgregar.getText().equals("Guardar")) {
                    this.empleado.setSueldo_bruto(sueldo);
                    this.empresa.actualizarEmpleado(this.empleado);
                } else {
                    String nombre = this.vista.txtNombre.getText();
                    String documento = this.vista.txtdocumento.getText();
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fec = LocalDate.parse(this.vista.txtFec.getText(), fmt);
                    Date fecha = Date.valueOf(fec);
                    Persona persona = this.empresa.BuscarPersona(documento);
                    boolean creado_persona = false;
                    boolean creado = false;
                    if (persona == null) {
                        creado_persona = this.empresa.agregarPersona(documento, nombre, fecha);
                    }
                    try {
                        creado = this.empresa.agregarEmpleado(documento, sueldo, this.empresa.getId());
                        if (creado) {
                            if (this.vista.btndirectivo.isSelected()) {
                                int categoria = this.vista.txtcategoria.getSelectedIndex() + 1;
                                this.empresa.agregarDirectivo(categoria, documento);

                                int[] vector = this.vista.tablaEmpleados.getSelectedRows();
                                String documento_empleado;

                                if (vector.length > 0) {
                                    ArrayList<Empleado> empleados = new ArrayList<Empleado>();
                                    Empleado empleado;
                                    for (int i : vector) {
                                        documento_empleado = this.vista.tablaEmpleados.getValueAt(i, 0).toString();
                                        this.empresa.actualizarSubmpleado(documento_empleado, documento);
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo crear el empleado");
                        }

                        

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "El formato de fecha de nacimiento"
                                + "debe ser dia/mes/año");
                    }
                }
                this.vista.dispose();
                GUIListarEmpleados gui = new GUIListarEmpleados();
                ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
                gui.setLocationRelativeTo(null);
                gui.setTitle("Lista de empleados");
                gui.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        
        if(evt.getSource()==this.vista.Volver){
            this.vista.dispose();
            GUIListarEmpleados gui = new GUIListarEmpleados();
            ControllerListarEmpleados gui2 = new ControllerListarEmpleados(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de empleados");
            gui.setVisible(true);
        }
        
        if(evt.getSource() == this.vista.btndirectivo){
            if (this.vista.btndirectivo.isSelected()){
                this.vista.txtcategoria.setVisible(true);
                this.vista.labelcategoria.setVisible(true);
                this.vista.tablaEmpleados.setVisible(true);
                this.vista.labelAcargo.setVisible(true);
            }else{
                this.vista.txtcategoria.setVisible(false);
                this.vista.labelcategoria.setVisible(false);
                this.vista.tablaEmpleados.setVisible(false);
                this.vista.labelAcargo.setVisible(false);
        }
        }
    }
    
    private void deshabilitarControles() {
        vista.txtNombre.setEditable(false);
        vista.txtdocumento.setEditable(false);
        vista.txtFec.setEditable(false);
        this.vista.txtcategoria.setVisible(false);
        this.vista.labelcategoria.setVisible(false);
        this.vista.tablaEmpleados.setVisible(false);
        this.vista.labelAcargo.setVisible(false);
        this.vista.btndirectivo.setVisible(false);
        
    }

    private void llenarControles() {
        vista.txtNombre.setText(this.empleado.getNombre());
        vista.txtdocumento.setText(this.empleado.getDocumento());
        vista.txtFec.setText(this.empleado.getFecha_nacimiento().toString());
        vista.txtSueldo.setText(String.valueOf(this.empleado.getSueldo_bruto()));
        vista.btnAgregar.setText("Guardar");
    }

    
    
    private void obtenerListadoEmpleados(){ 
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        ArrayList<Empleado> listaEmpleadosNormales = new ArrayList<>();
        listaEmpleados = this.empresa.obtenerEmpleados(this.empresa.getId());
        boolean esdirectivo;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            esdirectivo = this.empresa.esDirectivo(listaEmpleados.get(i).getId());
            if(! esdirectivo){
                listaEmpleadosNormales.add(listaEmpleados.get(i));
            }
        }
        String matriz [][] = new String[listaEmpleadosNormales.size()][5];
        
        for (int i = 0; i < listaEmpleadosNormales.size(); i++) {
                matriz[i][0] = listaEmpleadosNormales.get(i).getDocumento();
                matriz[i][1] = listaEmpleadosNormales.get(i).getNombre();
                matriz[i][2] = String.valueOf(listaEmpleadosNormales.get(i).getEdad());
                matriz[i][3] = String.valueOf(listaEmpleadosNormales.get(i).getSueldo_bruto());
                matriz[i][4] = "Normal";
            
        }
        
        this.vista.tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
        matriz,
        new String [] {
            "Documento", "Nombre", "Edad", "Sueldo","Cargo"
        }));
        
    }
}
