/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Detalle_Venta;
import Modelo.Producto;
import Modelo.Venta;
import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ventaCRUD {

    private Conexion con;
    private productoCRUD prodC;

    public ventaCRUD() {
        this.con = new Conexion();
        this.prodC = new productoCRUD();
 
    }

    public void crearVenta(Venta venta, List<Detalle_Venta> detalles) throws SQLException {
        Connection conx = null;
        String sqlVenta = "INSERT INTO VENTA (RUN_VENDEDOR, TOTAL_VENTA) VALUES (?, ?)";
        String sqlDetalle = "INSERT INTO DETALLE_VENTA (ID_VENTA, ID_PRODUCTO, CANTIDAD, PRECIO_UNITARIO) VALUES (?, ?, ?, ?)";

        try {
            conx = con.obtenerConexion();
            
            conx.setAutoCommit(false);

            
            String[] generatedColumns = {"ID_VENTA"}; 
            PreparedStatement pstmtVenta = conx.prepareStatement(sqlVenta, generatedColumns);
            venta.setRun_vendedor(Sesion.getInstancia().getRutUsuario());
            pstmtVenta.setInt(1, venta.getRun_vendedor());
            pstmtVenta.setDouble(2, venta.getTotal_Venta());

            int rowsAffected = pstmtVenta.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo crear la venta, 0 filas afectadas.");
            }

            int idVentaGenerado = -1;
            try (ResultSet generatedKeys = pstmtVenta.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idVentaGenerado = generatedKeys.getInt(1); 
                    venta.setId_venta(idVentaGenerado); 
                } else {
                    throw new SQLException("No se pudo obtener el ID de la venta generada.");
                }
            }
            pstmtVenta.close();

       
            PreparedStatement pstmtDetalle = conx.prepareStatement(sqlDetalle);

            for (Detalle_Venta detalle : detalles) {
                
     
                Producto p = prodC.productoPorId(detalle.getId_producto());
                if (p == null) {
                    throw new SQLException("Producto no existe. ID: " + detalle.getId_producto());
                }

                int stockActual = p.getStock();
                int cantidadVendida = detalle.getCantidad();
                int nuevoStock = stockActual - cantidadVendida;

                if (nuevoStock < 0) {
                  
                    throw new SQLException("Stock insuficiente para: " + p.getNombre()
                            + ". Stock actual: " + stockActual
                            + ", Venta: " + cantidadVendida);
                }

            
                detalle.setId_venta(idVentaGenerado);
                pstmtDetalle.setInt(1, detalle.getId_venta());
                pstmtDetalle.setInt(2, detalle.getId_producto());
                pstmtDetalle.setInt(3, detalle.getCantidad());
                pstmtDetalle.setDouble(4, detalle.getPrecio_unitario());
                pstmtDetalle.addBatch();


                prodC.actualizarStock(detalle.getId_producto(), nuevoStock, conx);
            }

     
            pstmtDetalle.executeBatch();
            pstmtDetalle.close();

   
            conx.commit();

        } catch (SQLException e) {
            System.err.println("Error en la transacción de venta: " + e.getMessage());
            try {
                if (conx != null) {
                    System.err.println("Haciendo rollback...");
                    conx.rollback();
                }
            } catch (SQLException e2) {
                System.err.println("Error al hacer rollback: " + e2.getMessage());
            }
            
            throw e;

        } finally {
            try {
                if (conx != null) {
                    conx.setAutoCommit(true);
                    conx.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    public List<Venta> getVentasDelDia() {
        String query = "SELECT * FROM VENTA WHERE TRUNC(FECHA_VENTA) = TRUNC(SYSDATE)";
        List<Venta> ventas = new ArrayList<>();

        try {
            Conexion con = new Conexion();
            Connection conx = con.obtenerConexion();
            PreparedStatement prep = conx.prepareStatement(query);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                Venta vent = new Venta();
                vent.setFecha_venta(rs.getDate("FECHA_VENTA"));
                vent.setId_venta(rs.getInt("ID_VENTA"));
                vent.setRun_vendedor(rs.getInt("RUN_VENDEDOR"));
                vent.setTotal_Venta(rs.getInt("TOTAL_VENTA"));
                ventas.add(vent);

            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas del día: " + e.getMessage());
        }
        return ventas;
    }

//    public List<Detalle_Venta> getDetallesPorVenta(int idVenta) {
//        String query = "SELECT d.*, p.NOMBRE "
//                + "FROM DETALLE_VENTAS d "
//                + "JOIN PRODUCTOS p ON d.ID_PRODUCTO = p.ID_PRODUCTO "
//                + "WHERE d.ID_VENTA = ?";
//        List<Detalle_Venta> detalles = new ArrayList<>();
//
//        try {
//            Conexion con = new Conexion();
//            Connection conx = con.obtenerConexion();
//            PreparedStatement prep = conx.prepareCall(query);
//
//            prep.setInt(1, idVenta);
//            try {
//                ResultSet rs = prep.executeQuery();
//                while (rs.next()) {
//                    Detalle_Venta d = new Detalle_Venta();
//                    d.setId_detalle(rs.getInt("ID_DETALLE"));
//                    d.setId_venta(rs.getInt("ID_VENTA"));
//                    d.setId_producto(rs.getInt("ID_PRODUCTO"));
//                    d.setCantidad(rs.getInt("CANTIDAD"));
//                    d.setPrecio_unitario(rs.getInt("PRECIO_UNITARIO_VENTA"));
//                    d.setNombre(rs.getString("NOMBRE"));
//                    detalles.add(d);
//                }
//            } catch (SQLException e) {
//                System.out.println("Error al consultar SQL" + e.getMessage());
//
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al obtener detalles de la venta: " + e.getMessage());
//        }
//        return detalles;
//    }

}
