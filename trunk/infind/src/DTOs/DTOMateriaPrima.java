/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author eduardo
 */
public class DTOMateriaPrima {
    
    private String eliminado;
    private String nombreMateriaPrima;
    private String codigoMateriaPrima;
    private String descripcion;
    private String precioBase;
    private String loteEstandar;
    private String costoEstandar;
    private String ubicacion;
    private String categoria;

    public DTOMateriaPrima() {
    }


    public DTOMateriaPrima(String nombre, String eliminado, String descripcion, String codigo, String precio, String loteEstandar, String ubicacion, String costoEstandar, String categoria) {
        this.codigoMateriaPrima = codigo;
        this.eliminado = eliminado;
        this.nombreMateriaPrima = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.precioBase = precio;
        this.loteEstandar = loteEstandar;
        this.costoEstandar = costoEstandar;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoMateriaPrima() {
        return codigoMateriaPrima;
    }

    public void setCodigoMateriaPrima(String codigoMateriaPrima) {
        this.codigoMateriaPrima = codigoMateriaPrima;
    }

    public String getCostoEstandar() {
        return costoEstandar;
    }

    public void setCostoEstandar(String costoEstandar) {
        this.costoEstandar = costoEstandar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getLoteEstandar() {
        return loteEstandar;
    }

    public void setLoteEstandar(String loteEstandar) {
        this.loteEstandar = loteEstandar;
    }

    public String getNombreMateriaPrima() {
        return nombreMateriaPrima;
    }

    public void setNombreMateriaPrima(String nombreMateriaPrima) {
        this.nombreMateriaPrima = nombreMateriaPrima;
    }

    public String getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(String precioBase) {
        this.precioBase = precioBase;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

  
}
