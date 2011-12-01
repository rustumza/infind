/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author eduardo
 */
public class DTOPuntoEquilibrio {


    private String nombreArticulo;
    private String eliminado;
    private String volumen;
    private String costoFijo;
    private String costoVariable;
    private String costoTotales;
    private String ingresoPorVenta;
    private String puntoEquilibrio;
    private String precio;
    private String cvUnidad;

    public DTOPuntoEquilibrio() {
    }


    public DTOPuntoEquilibrio(String nomArt, String eliminado, String vol, String cf, String cv, String ct, String ixv, String pe, String prec, String cvxunid ) {
        this.costoFijo = cf;
        this.costoTotales = ct;
        this.costoVariable = cv;
        this.cvUnidad = cvxunid;
        this.eliminado = eliminado;
        this.ingresoPorVenta = ixv;
        this.nombreArticulo = nomArt;
        this.precio = prec;
        this.puntoEquilibrio = pe;
        this.volumen = vol;
    }

    public String getCostoFijo() {
        return costoFijo;
    }

    public void setCostoFijo(String costoFijo) {
        this.costoFijo = costoFijo;
    }

    public String getCostoTotales() {
        return costoTotales;
    }

    public void setCostoTotales(String costoTotales) {
        this.costoTotales = costoTotales;
    }

    public String getCostoVariable() {
        return costoVariable;
    }

    public void setCostoVariable(String costoVariable) {
        this.costoVariable = costoVariable;
    }

    public String getCvUnidad() {
        return cvUnidad;
    }

    public void setCvUnidad(String cvUnidad) {
        this.cvUnidad = cvUnidad;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getIngresoPorVenta() {
        return ingresoPorVenta;
    }

    public void setIngresoPorVenta(String ingresoPorVenta) {
        this.ingresoPorVenta = ingresoPorVenta;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPuntoEquilibrio() {
        return puntoEquilibrio;
    }

    public void setPuntoEquilibrio(String puntoEquilibrio) {
        this.puntoEquilibrio = puntoEquilibrio;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }
    
    
}
