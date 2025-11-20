/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import bd.Conexion;
import Modelo.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author diego
 */
public class vendedorCRUD {

    public Vendedor vendedorLogin(String username, String password) {
        String query = "SELECT RUN_VENDEDOR, DV_RUT_VENDEDOR, HASH_PASSWORD, USERNAME, NOMBRE_VENDEDOR, SNOMBRE_VENDEDOR, APPATERNO_VENDEDOR, APMATERNO_VENDEDOR, ES_ADMIN FROM VENDEDOR WHERE USERNAME = ?";
        Vendedor vendedor = null;
        Conexion con = new Conexion();
        try {
            Connection conn = con.obtenerConexion();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String passwordBD = rs.getString("HASH_PASSWORD");

                if (BCrypt.checkpw(password, passwordBD)) {

                    vendedor = new Vendedor(
                            rs.getInt("RUN_VENDEDOR"),
                            rs.getString("DV_RUT_VENDEDOR"),
                            rs.getString("USERNAME"),
                            rs.getString("NOMBRE_VENDEDOR"),
                            rs.getString("SNOMBRE_VENDEDOR"),
                            rs.getString("APPATERNO_VENDEDOR"),
                            rs.getString("APMATERNO_VENDEDOR"),
                            rs.getBoolean("ES_ADMIN")
                    );
                    Sesion.getInstancia().setRutUsuario(rs.getInt("RUN_VENDEDOR"));
                    String verificacion_admin = rs.getString("ES_ADMIN");
                    if (verificacion_admin.equals("Y")) {
                        Sesion.getInstancia().setAdmin(true);
                        
                    }
                        
                    }
  
                
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar vendedor: " + e.getMessage());

        }
        return vendedor;
    }

    public boolean agregarVendedor(Vendedor vendedor, String pass_vendedor) {
        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();
            String hashPW = BCrypt.hashpw(pass_vendedor, BCrypt.gensalt(12));

            String query = "INSERT INTO vendedor(RUN_VENDEDOR, DV_RUT_VENDEDOR, USERNAME, HASH_PASSWORD, NOMBRE_VENDEDOR, SNOMBRE_VENDEDOR, APPATERNO_VENDEDOR, APMATERNO_VENDEDOR, ES_ADMIN)VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement prep = conx.prepareStatement(query);
            prep.setInt(1, vendedor.getRun_vendedor());
            prep.setString(2, vendedor.getDv_rut_vendedor());
            prep.setString(3, vendedor.getUsername());
            prep.setString(4, hashPW);
            prep.setString(5, vendedor.getNombre_vendedor());
            prep.setString(6, vendedor.getSegundo_nombre_vendedor());
            prep.setString(7, vendedor.getApellidoP_Vendedor());
            prep.setString(8, vendedor.getApellidoM_vendedor());
            prep.setString(9, vendedor.isEs_admin() ? "Y" : "N");

            prep.executeUpdate();
            prep.close();
            conx.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear vendedor " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVendedor(int rut) {

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();

            String query = "DELETE FROM vendedor WHERE run_vendedor = ?";
            PreparedStatement prep = conx.prepareStatement(query);
            prep.setInt(1, rut);

            prep.executeUpdate();
            prep.close();
            conx.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar vendedor. " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarVendedor(Vendedor vendedor, String pass_vendedor) {

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();

            String query = "UPDATE vendedor set USERNAME = ?, HASH_PASSWORD = ?, NOMBRE_VENDEDOR = ?, SNOMBRE_VENDEDOR = ?, APPATERNO_VENDEDOR = ?, APMATERNO_VENDEDOR = ? WHERE run_vendedor = ?";
            PreparedStatement prep = conx.prepareStatement(query);
            prep.setInt(1, vendedor.getRun_vendedor());
            prep.setString(2, vendedor.getDv_rut_vendedor());
            prep.setString(3, vendedor.getUsername());
            prep.setString(4, pass_vendedor);
            prep.setString(5, vendedor.getNombre_vendedor());
            prep.setString(6, vendedor.getSegundo_nombre_vendedor());
            prep.setString(7, vendedor.getApellidoP_Vendedor());
            prep.setString(8, vendedor.getApellidoM_vendedor());

            prep.executeUpdate();
            prep.close();
            conx.close();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar vendedor. " + e.getMessage());
            return false;
        }
    }

    public List<Vendedor> listarVendedores() {

        List<Vendedor> lista = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();

            String query = "SELECT * FROM vendedor ORDER BY run_vendedor";
            PreparedStatement prep = conx.prepareStatement(query);

            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setRun_vendedor(rs.getInt("run_vendedor"));
                vendedor.setDv_rut_vendedor(rs.getString("dv_rut_vendedor"));
                vendedor.setUsername(rs.getString("username"));
                vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));
                vendedor.setSegundo_nombre_vendedor(rs.getString("snombre_vendedor"));
                vendedor.setApellidoP_Vendedor(rs.getString("appaterno_vendedor"));
                vendedor.setApellidoM_vendedor(rs.getString("apmaterno_vendedor"));
                if (rs.getString("ES_ADMIN").equals("Y")) {
                    vendedor.setEs_admin(true);
                    
                }else{
                    vendedor.setEs_admin(false);
                }

                lista.add(vendedor);

            }
            rs.close();
            prep.close();
            conx.close();
        } catch (SQLException e) {
            System.out.println("Error en SQL al obtener vendedores. " + e.getMessage());
        }
        return lista;

    }

//    public int capturarID (){
//        int rut = 0;
//        Vendedor vend = new Vendedor();
//        List<Vendedor> lista = new ArrayList<>();
//        lista = this.listarVendedores();
//        
//        for (Vendedor vendedor : lista) {
//            if (user.equals(vendedor.getUsername())) {
//                rut = vendedor.getRun_vendedor();
//                break;
//                
//            }
//            
//        }
//        return rut;
//        
//        
//        
//    }
}
