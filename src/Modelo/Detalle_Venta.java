/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author baygo
 */
public class Detalle_Venta {
 private String nombre;
 private int id_detalle ,cantidad,precio_unitario ,id_producto , id_venta ;

    public Detalle_Venta() {
    }

    public Detalle_Venta(String nombre, int id_detalle, int cantidad, int precio_unitario, int id_productos, int id_ventas) {
        this.nombre = nombre;
        this.id_detalle = id_detalle;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.id_producto = id_producto;
        this.id_venta = id_venta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    @Override
    public String toString() {
        return "Detalle_Venta{" + "nombre=" + nombre + ", id_detalle=" + id_detalle + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + ", id_producto=" + id_producto + ", id_venta=" + id_venta + '}';
    }


     
}
