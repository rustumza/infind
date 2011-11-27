/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author eduardo
 */
public class DTOCantidades {
    
    private String nombre;
    private String cantidad;
    
    public DTOCantidades() {
    }


    public DTOCantidades(String nom, String cant) {
        this.cantidad = cant;
        this.nombre = nom;
        

    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
