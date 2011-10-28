/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOHerramienta {
    
    private String eliminado;
    private String nombreHerramienta;
    private String codigoHerramienta;
    private String descripcion;
    
    public DTOHerramienta() {
    }


    public DTOHerramienta(String nombre, String eliminado, String descripcion, String codigo) {
        this.codigoHerramienta = codigo;
        this.eliminado = eliminado;
        this.nombreHerramienta = nombre;
        this.descripcion = descripcion;
        
    }

    public String getCodigoHerramienta() {
        return codigoHerramienta;
    }

    public void setCodigoHerramienta(String codigoHerramienta) {
        this.codigoHerramienta = codigoHerramienta;
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

    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }
    
    
}
