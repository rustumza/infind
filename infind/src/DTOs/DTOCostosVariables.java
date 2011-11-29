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
    
    public DTOCostosVariables() {
    }


    public DTOCostosVariables(String nombre, String cant, String cost) {
        this.nombreProducto = nombre;
        this.cantidad = cant;
        this.costoUnitario = cost;
        

    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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
