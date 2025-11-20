/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class productoCRUD {

    public boolean crearProducto(Producto producto) {
        String query = "INSERT INTO producto (nombre, tipo_producto, expansion, rareza, precio_venta, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();
            PreparedStatement prep = conx.prepareStatement(query);

            prep.setString(1, producto.getNombre());
            prep.setString(2, producto.getTipo_producto());
            prep.setString(3, producto.getExpansion());
            prep.setString(4, producto.getRareza());
            prep.setInt(5, producto.getPrecio_venta());
            prep.setInt(6, producto.getStock());

            prep.executeUpdate();
            prep.close();
            conx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear producto! " + e.getMessage());
            return false;
        }

    }

    public boolean actualizarStock(int id_producto, int ncantidad) {

        boolean flag = true;

        String query = "UPDATE producto SET stock = ? WHERE id_producto = ?";
        if (ncantidad < 0) {
            System.out.println("Stock negativo");
            flag = false;
        }

        if (flag) {
            try {
                Conexion con = new Conexion();
                Connection conx = con.obtenerConexion();
                PreparedStatement prep = conx.prepareStatement(query);

                prep.setInt(1, ncantidad);
                prep.setInt(2, id_producto);

                prep.executeUpdate();
                prep.close();
                conx.close();
                return true;

            } catch (SQLException e) {
                System.out.println("Error al actualizar stock" + e.getMessage());
                return false;
            }

        } else {
            return false;
        }
    }

    public boolean actualizarStock(int idProducto, int nuevaCantidad, Connection conn) throws SQLException {
        if (nuevaCantidad < 0) {
            throw new SQLException("Stock no puede ser negativo. Falla al actualizar ID: " + idProducto);
        }

        String sql = "UPDATE PRODUCTO SET STOCK = ? WHERE ID_PRODUCTO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nuevaCantidad);
            pstmt.setInt(2, idProducto);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public Producto productoPorId(int id) {
        String query = "SELECT * FROM producto WHERE id_producto = ?";
        Producto producto = new Producto();

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();
            PreparedStatement prep = conx.prepareStatement(query);

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {

                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setTipo_producto(rs.getString("tipo_producto"));
                producto.setExpansion(rs.getString("expansion"));
                producto.setRareza(rs.getString("rareza"));
                producto.setPrecio_venta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));

            }
            rs.close();
            prep.close();
            conx.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar consulta SQL " + e.getMessage());
        }
        return producto;

    }

    public List<Producto> obtenerProductos() {
        String query = "SELECT * FROM producto";
        List<Producto> prods = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();
            PreparedStatement prep = conx.prepareStatement(query);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setTipo_producto(rs.getString("tipo_producto"));
                producto.setExpansion(rs.getString("expansion"));
                producto.setRareza(rs.getString("rareza"));
                producto.setPrecio_venta(rs.getInt("precio_venta"));
                producto.setStock(rs.getInt("stock"));

                prods.add(producto);

            }

        } catch (SQLException e) {
            System.out.println("Error al realizar consulta SQL " + e.getMessage());
        }
        return prods;
    }

    public boolean eliminarProducto(int id) {

        String query = "DELETE FROM producto WHERE id_producto = ?";
        try {
            Conexion con = new Conexion();
            Connection connec = con.obtenerConexion();
            PreparedStatement pres = connec.prepareStatement(query);
            pres.setInt(1, id);

            pres.executeUpdate();
            pres.close();
            connec.close();
            return true;

        } catch (SQLException e) {
            System.out.println("error al eliminar tabla de sql " + e.getMessage());
            return false;
        }

    }

//    public boolean productoPorIdBoolean(int id) {
//        String query = "SELECT * FROM producto WHERE id_producto = ?";
//        Producto producto = new Producto();
//
//        try {
//            Conexion con = new Conexion();
//            Connection conx = con.obtenerConexion();
//            PreparedStatement prep = conx.prepareStatement(query);
//
//            prep.setInt(1, id);
//            ResultSet rs = prep.executeQuery();
//            if (rs.next()) {
//
//                producto.setId_producto(rs.getInt("id_producto"));
//                producto.setNombre(rs.getString("nombre"));
//                producto.setTipo_producto(rs.getString("tipo_producto"));
//                producto.setExpansion(rs.getString("expansion"));
//                producto.setRareza(rs.getString("rareza"));
//                producto.setPrecio_venta(rs.getInt("precio_venta"));
//                producto.setStock(rs.getInt("stock"));
//
//            }
//            rs.close();
//            prep.close();
//            conx.close();
//        } catch (SQLException e) {
//            System.out.println("Error al realizar consulta SQL " + e.getMessage());
//            return false;
//        }
//        return true;
//
//    }

}
