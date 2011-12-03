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
    private String costosManoObra;
    private String gastoFabricacion;
    private String costoMateriaPrima;
    
    public DTOCostosVariables() {
    }


    public DTOCostosVariables(String nombre, String cant, String cost, String costoTot) {
        this.nombreProducto = nombre;
        this.costosManoObra = cant;
        this.gastoFabricacion = cost;
        this.costoMateriaPrima = costoTot;
        

    }

    public String getCostoMateriaPrima() {
        return costoMateriaPrima;
    }

    public void setCostoMateriaPrima(String costoMateriaPrima) {
        this.costoMateriaPrima = costoMateriaPrima;
    }

    public String getCostosManoObra() {
        return costosManoObra;
    }

    public void setCostosManoObra(String costosManoObra) {
        this.costosManoObra = costosManoObra;
    }

    public String getGastoFabricacion() {
        return gastoFabricacion;
    }

    public void setGastoFabricacion(String gastoFabricacion) {
        this.gastoFabricacion = gastoFabricacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
}
