/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOMaquina {
    
    private String eliminado;
    private String nombreMaquina;
    private String codigoMaquina;
    private String descripcion;
    
    public DTOMaquina() {
    }


    public DTOMaquina(String nombre, String eliminado, String codigo, String descrip) {
        this.codigoMaquina = codigo;
        this.eliminado = eliminado;
        this.nombreMaquina = nombre;
        this.descripcion = descrip;
        
    }

    public String getCodigoMaquina() {
        return codigoMaquina;
    }

    public void setCodigoMaquina(String codigoMaquina) {
        this.codigoMaquina = codigoMaquina;
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

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }
    
    
    
}
