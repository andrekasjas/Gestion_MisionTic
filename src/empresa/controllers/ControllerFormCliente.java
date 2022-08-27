/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.controllers;

import empresa.models.Cliente;
import empresa.models.Empresa;
import empresa.models.Persona;
import empresa.views.GUIFormCliente;
import empresa.views.GUIListaClientes;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author CABEZ
 */
public class ControllerFormCliente implements ActionListener {
    
    private Empresa empresa;
    private GUIFormCliente vista;
    private Cliente cliente;

    public ControllerFormCliente(GUIFormCliente vista,Empresa empresa) {
        this.empresa = empresa;
        this.vista = vista;
        vista.Volver.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
    }

    ControllerFormCliente(GUIFormCliente vista, Empresa empresa, Cliente cliente) {
        this.empresa = empresa;
        this.vista = vista;
        this.cliente = cliente;
        
        
        deshabilitarControles();
        llenarControles();
        vista.Volver.addActionListener(this);
        vista.btnAgregar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        
                    
        if(evt.getSource() == this.vista.btnAgregar){
            String telefono = this.vista.txtTel.getText();
            try {
                if(this.vista.btnAgregar.getText().equals("Guardar")){
                    this.cliente.setTelefono(telefono);
                    this.empresa.actualizarCliente(this.cliente);
                }else{
                    String nombre = this.vista.txtNombre.getText();
                    String documento = this.vista.txtdocumento.getText();
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fec = LocalDate.parse(this.vista.txtFec.getText(), fmt);
                    Date fecha = Date.valueOf(fec);
                    Persona persona = this.empresa.BuscarPersona(documento);
                    boolean creado_persona = false;
                    boolean creado;
                    if (persona == null) {
                        creado_persona = this.empresa.agregarPersona(documento, nombre, fecha);
                    }
                    creado = this.empresa.agregarCliente(documento, 
                                                    telefono, this.empresa.getId());
                }
                
                this.vista.dispose();
                GUIListaClientes gui= new GUIListaClientes();
                ControllerListaClientes gui2 = new ControllerListaClientes(gui, this.empresa);
                gui.setLocationRelativeTo(null);
                gui.setTitle("Lista de clientes");
                gui.setVisible(true);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "El formato de fecha de nacimiento"
                        + "debe ser dia/mes/año & recuerde llenar todos los campos");
            }
        }
        
        
        if(evt.getSource()==this.vista.Volver){
            this.vista.dispose();
            GUIListaClientes gui= new GUIListaClientes();
            ControllerListaClientes gui2 = new ControllerListaClientes(gui, this.empresa);
            gui.setLocationRelativeTo(null);
            gui.setTitle("Lista de clientes");
            gui.setVisible(true);
        }
        
    }
    
    private void deshabilitarControles() {
        vista.txtNombre.setEditable(false);
        vista.txtdocumento.setEditable(false);
        vista.txtFec.setEditable(false);
    }

    private void llenarControles() {
        vista.txtNombre.setText(this.cliente.getNombre());
        vista.txtdocumento.setText(this.cliente.getDocumento());
        vista.txtFec.setText(this.cliente.getFecha_nacimiento().toString());
        vista.txtTel.setText(this.cliente.getTelefono());
        vista.btnAgregar.setText("Guardar");
    }
    
}
