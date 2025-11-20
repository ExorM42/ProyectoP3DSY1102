/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author baygo
 */
public class Venta {
    private int id_venta , total_Venta , run_vendedor;
    private Date fecha_venta;

    public Venta() {
    }

    public Venta(int id_venta, int total_Venta, int run_vendedor, Date fecha_venta) {
        this.id_venta = id_venta;
        this.total_Venta = total_Venta;
        this.run_vendedor = run_vendedor;
        this.fecha_venta = fecha_venta;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getTotal_Venta() {
        return total_Venta;
    }

    public void setTotal_Venta(int total_Venta) {
        this.total_Venta = total_Venta;
    }

    public int getRun_vendedor() {
        return run_vendedor;
    }

    public void setRun_vendedor(int run_vendedor) {
        this.run_vendedor = run_vendedor;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    @Override
    public String toString() {
        return "Venta{" + "id_venta=" + id_venta + ", total_Venta=" + total_Venta + ", run_vendedor=" + run_vendedor + ", fecha_venta=" + fecha_venta + '}';
    }
    
    
}
