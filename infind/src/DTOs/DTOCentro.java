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
    private String codigoCentro;
    private String descripcion;

    public DTOCentro() {
    }


    public DTOCentro(String oidCentro, String eliminado, String nombreCentro, String codigoCentro, String descripcion ) {
        this.codigoCentro = codigoCentro;
        this.eliminado = eliminado;
        this.nombreCentro = nombreCentro;
        this.oidCentro = oidCentro;
        this.descripcion = descripcion;
        

    }

    

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getCodigoCentro() {
        return codigoCentro;
    }

    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
