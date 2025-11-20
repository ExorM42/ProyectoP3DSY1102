/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author baygo
 */
public class Vendedor {
    private int run_vendedor;
    private String dv_rut_vendedor, username;
    private String nombre_vendedor,segundo_nombre_vendedor ,apellidoP_Vendedor ,apellidoM_vendedor;
    private boolean es_admin;

    

    public Vendedor() {
    }

    public Vendedor(int run_vendedor, String dv_rut_vendedor, String username, String nombre_vendedor, String segundo_nombre_vendedor, String apellidoP_Vendedor, String apellidoM_vendedor, boolean es_admin) {
        this.run_vendedor = run_vendedor;
        this.dv_rut_vendedor = dv_rut_vendedor;
        this.username = username;
        this.nombre_vendedor = nombre_vendedor;
        this.segundo_nombre_vendedor = segundo_nombre_vendedor;
        this.apellidoP_Vendedor = apellidoP_Vendedor;
        this.apellidoM_vendedor = apellidoM_vendedor;
        this.es_admin=es_admin;
        
    }
    

    public int getRun_vendedor() {
        return run_vendedor;
    }

    public void setRun_vendedor(int run_vendedor) {
        this.run_vendedor = run_vendedor;
    }

    public String getDv_rut_vendedor() {
        return dv_rut_vendedor;
    }

    public void setDv_rut_vendedor(String dv_rut_vendedor) {
        this.dv_rut_vendedor = dv_rut_vendedor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getSegundo_nombre_vendedor() {
        return segundo_nombre_vendedor;
    }

    public void setSegundo_nombre_vendedor(String segundo_nombre_vendedor) {
        this.segundo_nombre_vendedor = segundo_nombre_vendedor;
    }

    public String getApellidoP_Vendedor() {
        return apellidoP_Vendedor;
    }

    public void setApellidoP_Vendedor(String apellidoP_Vendedor) {
        this.apellidoP_Vendedor = apellidoP_Vendedor;
    }

    public String getApellidoM_vendedor() {
        return apellidoM_vendedor;
    }

    public void setApellidoM_vendedor(String apellidoM_vendedor) {
        this.apellidoM_vendedor = apellidoM_vendedor;
    }

    public boolean isEs_admin() {
        return es_admin;
    }

    public void setEs_admin(boolean es_admin) {
        this.es_admin = es_admin;
    }
    
    

    @Override
    public String toString() {
        return "Vendedor{" + "run_vendedor=" + run_vendedor + ", dv_rut_vendedor=" + dv_rut_vendedor + ", username=" + username + ", nombre_vendedor=" + nombre_vendedor + ", segundo_nombre_vendedor=" + segundo_nombre_vendedor + ", apellidoP_Vendedor=" + apellidoP_Vendedor + ", apellidoM_vendedor=" + apellidoM_vendedor + '}';
    }
    
    
    
    
    
    
    
}
