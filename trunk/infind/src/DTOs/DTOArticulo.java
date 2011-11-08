/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOArticulo {


    private String oidArticulo;
    private String eliminado;
    private String nombreArticulo;
    private String codigoArticulo;
    private String descripcion;

    public DTOArticulo() {
    }


    public DTOArticulo(String oidArt, String eliminado, String nombre, String codigo, String descripcion ) {
        this.codigoArticulo = codigo;
        this.eliminado = eliminado;
        this.nombreArticulo = nombre;
        this.oidArticulo = oidArt;
        this.descripcion = descripcion;
        

    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
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

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getOidArticulo() {
        return oidArticulo;
    }

    public void setOidArticulo(String oidArticulo) {
        this.oidArticulo = oidArticulo;
    }
    
    

    }
