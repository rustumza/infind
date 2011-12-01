/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOCostosVariables {
    
    

    private String nombreProducto;
    private String cantidad;
    private String costoUnitario;
    private String costoTotal;
    
    public DTOCostosVariables() {
    }


    public DTOCostosVariables(String nombre, String cant, String cost, String costoTot) {
        this.nombreProducto = nombre;
        this.cantidad = cant;
        this.costoUnitario = cost;
        this.costoTotal = costoTot;
        

    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    

    public String getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(String costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    
    
}
