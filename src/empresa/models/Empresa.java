/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.models;

import empresa.models.Cliente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author erika
 */
public class Empresa {
    private String Nombre;
    private String CIF;
    private ArrayList<Empleado> empleados; 
    private ArrayList<Cliente> clientes;
    private int id;
    
    public Empresa(int id,String Nombre, String CIF){
        this.Nombre=Nombre;
        this.CIF= CIF;
        this.id = id;
        this.empleados= new ArrayList<Empleado>();;
        this.clientes = new ArrayList<Cliente>(); 
    }

    public Empresa() {}

    /**
     * @return the Nombre
     */
    
    public String getNombre() {
        return Nombre;
    }
    
    
    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the CIF
     */
    public String getCIF() {
        return CIF;
    }

    /**
     * @param CIF the CIF to set
     */
    public void setCIF(String CIF) {
        this.CIF = CIF;
    }
    
    
   

    /**
     * @return the empleados
     */
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public int promedioEdadClientes(ArrayList<Cliente> clientes){
        int suma = 0;
        for (Cliente cliente : clientes) {
            suma += cliente.getEdad();
        }
        return suma/clientes.size();
    }
    
    public int totalPagoEmpleados(ArrayList<Empleado> empleados){
        int suma = 0;
        for (Empleado empleado : empleados) {
            suma += empleado.getSueldo_bruto();
        }
        return suma;
    }
     
     public boolean agregarCliente(Cliente cliente){
         try {
             this.clientes.add(cliente);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     public boolean eliminarCliente(Cliente cliente){
         try {
             this.clientes.remove(cliente);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     public boolean actualizarCliente(int index, Cliente cliente){
         try {
             this.clientes.set(index, cliente);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     
    public boolean agregarEmpleado(Empleado empleado){
         try {
             this.empleados.add(empleado);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     public boolean eliminarEmpleado(Empleado empleado){
         try {
             this.empleados.remove(empleado);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
     public boolean actualizarEmpleado(int index, Empleado empleado){
         try {
             this.empleados.set(index, empleado);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }
     }
     
    public Cliente buscarClientePorDocumento(String documento){
        for (Cliente cliente : this.clientes) {
            if(cliente.getDocumento().equals(documento)){
                return cliente;
            }
        }
        return null;
    }
    
    public Empleado buscarEmpleadoPorDocumento(String documento){
        for (Empleado empleado : this.empleados) {
            if(empleado.getDocumento().equals(documento)){
                return empleado;
            }
        }
        return null;
    }
    
    public Empleado buscarEmpleadoPorindice(int indice){
        return this.empleados.get(indice);
    }
    
    
    public ArrayList<Cliente> obtenerClientes(int id){
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT c.id,p.nombre,p.fecha_nacimiento,c.telefono, p.documento "
                    + " FROM cliente c "
                    + " INNER JOIN persona p ON p.id = c.persona_id "
                    + " INNER JOIN cliente_empresa ce ON ce.cliente_id = c.id "
                    + " INNER JOIN empresa e ON e.id = ce.empresa_id "
                    + " WHERE e.id = ? ");
            
            consulta.setInt(1, id);
            ResultSet resultado = consulta.executeQuery();
            
            while (resultado.next()){
                Cliente cliente = new Cliente(resultado.getInt("id"),
                                            resultado.getString("nombre"),
                                            resultado.getDate("fecha_nacimiento"),
                                            resultado.getString("telefono"),
                                            resultado.getString("documento"));
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaClientes;
    }
    
    public ArrayList<Empleado> obtenerEmpleados(int id){
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT e.id,p.nombre,p.fecha_nacimiento,e.sueldo, p.documento "
                    + " FROM empleado e "
                    + " INNER JOIN persona p ON p.id = e.persona_id "
                    + " INNER JOIN empresa_empleado ee ON ee.empleado_id = e.id "
                    + " INNER JOIN empresa em ON em.id = ee.empresa_id "
                    + " WHERE em.id = ? ");
            
            consulta.setInt(1, id);
            ResultSet resultado = consulta.executeQuery();
            
            while (resultado.next()){
                Empleado empleado = new Empleado(resultado.getInt("id"),
                                            resultado.getString("nombre"),
                                            resultado.getDate("fecha_nacimiento"),
                                            resultado.getInt("sueldo"),
                                            resultado.getString("documento"));
                listaEmpleados.add(empleado);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaEmpleados;
    }
    
    public ArrayList<Empleado> obtenerEmpleadosSubordinados( Directivo directivo){
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + " SELECT e.id,p.nombre,p.fecha_nacimiento,e.sueldo, p.documento "
                    + " FROM empleado e "
                    + " INNER JOIN persona p ON p.id = e.persona_id "
                    + " INNER JOIN empresa_empleado ee ON ee.empleado_id = e.id "
                    + " INNER JOIN empresa em ON em.id = ee.empresa_id "
                    + " WHERE em.id = ? AND e.directivo_id = ? ");
            
            consulta.setInt(1, id);
            consulta.setInt(2, directivo.getId());
            ResultSet resultado = consulta.executeQuery();
            
            while (resultado.next()){
                Empleado empleado = new Empleado(resultado.getInt("id"),
                                            resultado.getString("nombre"),
                                            resultado.getDate("fecha_nacimiento"),
                                            resultado.getInt("sueldo"),
                                            resultado.getString("documento"));
                listaEmpleados.add(empleado);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaEmpleados;
    }
    
    public boolean agregarCliente(String documento, String telefono, int empresa_id){
        try {
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement("SELECT p.id FROM "
                    + "persona p "
                    + "WHERE documento = ?");
            consulta1.setString(1, documento);
            
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while (resultado_documento.next()) {
                Cliente cliente = BuscarCliente(documento);
                if (cliente == null) {
                    PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement("INSERT INTO "
                            + "cliente(telefono, persona_id) VALUES(?,?)");
                    consulta2.setString(1, telefono);
                    consulta2.setInt(2, resultado_documento.getInt("id"));
                    consulta2.execute();
                    System.out.println("agrego cliente");
                    agregarClienteEmpresa(documento, empresa_id);
                } else {
                    PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement(""
                            + "SELECT * FROM cliente_empresa WHERE cliente_id = ? AND"
                            + " empresa_id = ?");
                    consulta2.setInt(1, cliente.getId());
                    consulta2.setInt(2, empresa_id);

                    ResultSet resultado_cli_emp = consulta2.executeQuery();
                    
                    if(!resultado_cli_emp.next()){
                        agregarClienteEmpresa(documento, empresa_id);
                    }
                }

            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean actualizarCliente(Cliente cliente){
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + " UPDATE cliente set telefono = ? where id = ?");
            consulta.setString(1, cliente.getTelefono());
            consulta.setInt(2, cliente.getId());
            consulta.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEmpleado(Empleado empleado){
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + " UPDATE empleado set sueldo = ? where id = ?");
            consulta.setInt(1, empleado.getSueldo_bruto());
            consulta.setInt(2, empleado.getId());
            consulta.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean agregarClienteEmpresa(String documento, int empresa_id){
        try {
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement("SELECT c.id FROM "
                    + "persona p "
                    + "INNER JOIN cliente c ON c.persona_id = p.id AND p.documento = ?");
            consulta1.setString(1, documento);
            
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while(resultado_documento.next()){
                PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement("INSERT INTO "
                        + "cliente_empresa(cliente_id,empresa_id) VALUES(?,?)");
                consulta2.setInt(1, resultado_documento.getInt("id"));
                consulta2.setInt(2, empresa_id);
                consulta2.execute();
                System.out.println("agrego cliente a empresa");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean agregarEmpleado(String documento, int sueldo, int empresa_id){
        try {
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement("SELECT p.id FROM "
                    + "persona p "
                    + "WHERE documento = ?");
            consulta1.setString(1, documento);
            
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while(resultado_documento.next()){
                Empleado empleado = BuscarEmpleado(documento);
                if(empleado == null){
                    PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement("INSERT INTO "
                            + "empleado(sueldo,persona_id) VALUES(?,?)");
                    consulta2.setInt(1, sueldo);
                    consulta2.setInt(2, resultado_documento.getInt("id"));
                    consulta2.execute();
                    agregarEmpresaEmpleado(documento, empresa_id);
                }else {
                    PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement(""
                            + "SELECT * FROM empresa_empleado WHERE empleado_id = ? AND"
                            + " empresa_id = ?");
                    consulta2.setInt(1, empleado.getId());
                    consulta2.setInt(2, empresa_id);

                    ResultSet resultado_cli_emp = consulta2.executeQuery();
                    
                    if(!resultado_cli_emp.next()){
                        agregarEmpresaEmpleado(documento, empresa_id);
                    }
                }
                
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean agregarEmpresaEmpleado(String documento, int empresa_id){
        try {
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement("SELECT e.id FROM "
                    + "persona p "
                    + "INNER JOIN empleado e ON e.persona_id = p.id AND p.documento = ?");
            consulta1.setString(1, documento);
            
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while(resultado_documento.next()){
                PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement("INSERT INTO "
                        + "empresa_empleado(empleado_id,empresa_id) VALUES(?,?)");
                consulta2.setInt(1, resultado_documento.getInt("id"));
                consulta2.setInt(2, empresa_id);
                consulta2.execute();
                System.out.println("agrego empleado a empresa");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean esDirectivo(int empleado_id){
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT * "
                    + "FROM directivo d "
                    + " WHERE d.empleado_id = ?");
            consulta.setInt(1, empleado_id);
            
            ResultSet resultado_documento = consulta.executeQuery();
            if(resultado_documento.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean actualizarSubmpleado(String documento_empleado, String documento_directivo){
        
        try {
            
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT d.id "
                    + "FROM directivo d "
                    + "INNER JOIN empleado e "
                    + "ON d.empleado_id = e.id "
                    + "INNER JOIN persona p "
                    + "ON e.persona_id = p.id AND p.documento = ?");
            consulta1.setString(1, documento_directivo);
            
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while(resultado_documento.next()){
                PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "UPDATE empleado e "
                    + "INNER JOIN persona p "
                    + "ON e.persona_id = p.id and p.documento = ? "
                    + "SET e.directivo_id = ?");
                consulta.setString(1, documento_empleado);
                consulta.setInt(2, resultado_documento.getInt("id"));
                consulta.executeUpdate();
                System.out.println("actualizo");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean agregarPersona(String documento,String nombre,Date fecha_nacimiento){
        
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "INSERT INTO persona(documento,fecha_nacimiento,nombre) VALUES(?,?,?)");
            consulta.setString(1, documento);
            consulta.setDate(2, fecha_nacimiento);
            consulta.setString(3, nombre);
            consulta.executeUpdate();
            System.out.println("agrego persona");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean agregarEmpresa(String nombre,String CIF){
        
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "INSERT INTO empresa(nombre,cif) VALUES(?,?)");
            consulta.setString(1, nombre);
            consulta.setString(2, CIF);
            consulta.executeUpdate();
            System.out.println("agrego empresa");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean agregarDirectivo(int categoria,String documento){
        try {
            PreparedStatement consulta1 = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT e.id "
                    + "FROM empleado e "
                    + "INNER JOIN persona p "
                    + "ON e.persona_id = p.id AND p.documento = ?");
            consulta1.setString(1, documento);
            ResultSet resultado_documento = consulta1.executeQuery();
            
            while(resultado_documento.next()){
                PreparedStatement consulta2 = ConexionDB.abriConexion().prepareStatement("INSERT INTO "
                        + "directivo(categoria,empleado_id) VALUES(?,?)");
                consulta2.setInt(1, categoria);
                consulta2.setInt(2, resultado_documento.getInt("id"));
                consulta2.execute();
                System.out.println("agrego directivo");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public Persona BuscarPersona(String documento){
        try {
            Persona persona = null;
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT * FROM persona WHERE documento = ?");
            consulta.setString(1, documento);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                persona = new Persona(resultado.getInt("id"),resultado.getString("nombre"),
                resultado.getDate("fecha_nacimiento"),documento);
            }
            return persona;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Cliente BuscarCliente(String documento){
        try {
            Cliente cliente = null;
            
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT c.id, p.nombre, p.fecha_nacimiento, c.telefono "
                    + "FROM cliente c "
                    + " INNER JOIN persona p ON p.id = c.persona_id AND p.documento = ?");
            consulta.setString(1, documento);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                cliente = new Cliente(resultado.getInt("id"),resultado.getString("nombre"),
                                    resultado.getDate("fecha_nacimiento"),resultado.getString("telefono"),
                                    documento);
            }
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    public Empleado BuscarEmpleado(String documento){
        try {
            Empleado empleado = null;
            
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT e.id, p.nombre, p.fecha_nacimiento, e.sueldo "
                    + "FROM empleado e "
                    + " INNER JOIN persona p ON p.id = e.persona_id AND p.documento = ?");
            consulta.setString(1, documento);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                empleado = new Empleado(resultado.getInt("id"),resultado.getString("nombre"),
                                    resultado.getDate("fecha_nacimiento"),resultado.getInt("sueldo"),
                                    documento);
            }
            return empleado;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public Directivo BuscarDirectivo(String documento){
        try {
            Directivo directivo = null;
            
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT d.id, p.nombre, p.fecha_nacimiento, e.sueldo, d.categoria, p.documento "
                    + " FROM directivo d "
                    + " INNER JOIN empleado e ON e.id = d.empleado_id "
                    + " INNER JOIN persona p ON p.id = e.persona_id AND p.documento = ?");
            consulta.setString(1, documento);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                directivo = new Directivo(resultado.getInt("id"), resultado.getString("nombre"), 
                        resultado.getDate("fecha_nacimiento"), resultado.getInt("sueldo"), 
                        resultado.getInt("categoria"), documento);
            }
            return directivo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean eliminarCliente(int cliente_id, int empresa_id){
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "DELETE FROM cliente_empresa WHERE cliente_id = ? AND empresa_id = ?");
            consulta.setInt(1, cliente_id);
            consulta.setInt(2, empresa_id);
            consulta.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean eliminarEmpleado(int empleado_id, int empresa_id){
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "DELETE FROM empresa_empleado WHERE empleado_id = ? AND empresa_id = ?");
            consulta.setInt(1, empleado_id);
            consulta.setInt(2, empresa_id);
            consulta.execute();
            System.out.println("elimino empleado empresa");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public ArrayList<Empresa> obtenerEmpresas() {
        ArrayList<Empresa> listaEmpresas = new ArrayList<>();
        try {
            PreparedStatement consulta = ConexionDB.abriConexion().prepareStatement(""
                    + "SELECT * FROM empresa");
            
            ResultSet resultado = consulta.executeQuery();
            
            while (resultado.next()) {
                listaEmpresas.add(new Empresa(resultado.getInt("id"),resultado.getString("nombre"),
                        resultado.getString("cif")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaEmpresas;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
