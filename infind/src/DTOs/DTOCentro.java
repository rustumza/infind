/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author eduardo
 */
public class DTOCentro {

    private String oidCentro;
    private String eliminado;
    private String nombreCentro;
    private String cantidad;
    private String descripcion;
    private String costo;
    private String codigoCentro;

    public DTOCentro() {
    }


    public DTOCentro(String oidCentro, String eliminado, String nombreCentro, String cantidad, String descripcion , String costoCentro, String codigo) {
        this.cantidad = cantidad;
        this.eliminado = eliminado;
        this.nombreCentro = nombreCentro;
        this.oidCentro = oidCentro;
        this.descripcion = descripcion;
        this.costo = costoCentro;
        this.codigoCentro = codigo;
        

    }

    public String getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(String codigo) {
        this.codigoCentro = codigo;
    }

    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
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

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getOidCentro() {
        return oidCentro;
    }

    public void setOidCentro(String oidCentro) {
        this.oidCentro = oidCentro;
    }


    



    
}
