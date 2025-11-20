/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author baygo
 */
public class Producto {
    private int id_producto, precio_venta ,stock ;
    private String nombre, tipo_producto,expansion, rareza;

    public Producto() {
    }

    public Producto(int id_producto, int precio_venta, int stock, String nombre, String tipo_producto, String expansion, String rareza) {;
        this.id_producto = id_producto;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.nombre = nombre;
        this.tipo_producto = tipo_producto;
        this.expansion = expansion;
        this.rareza = rareza;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }


    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto="  + ", precio_venta=" + precio_venta + ", stock=" + stock + ", nombre=" + nombre + ", tipo_producto=" + tipo_producto + ", expansion=" + expansion + ", rareza=" + rareza + '}';
    }
    
    
    
}
