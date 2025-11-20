/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author shazk
 */
public class Sesion {

    // 1. Instancia estática única (Singleton)
    private static Sesion instancia;

    // 2. Variables para guardar los datos
    private int rutUsuario;
    private boolean admin;
    private String admin1;

    // 3. Constructor privado para evitar que se creen nuevas instancias
    private Sesion() {
        // Inicialización
    }

    // 4. Método estático para obtener la única instancia
    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    // 5. Métodos Getter y Setter
    public int getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(int rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }
    
   
    }
