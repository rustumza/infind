/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author eduardo
 */
public class DTOOperario {
    
    private String eliminado;
    private String nombreOperario;
    private String codigoOperario;
    private String apellidoOperario;
    private String tipoOperario;
    private String dni;
    private String direccion;
    private String correo;

    public DTOOperario() {
    }


    public DTOOperario(String nombre, String eliminado, String apellido, String codigo, String tipoOpera, String dni, String correo, String direccion  ) {
        this.codigoOperario = codigo;
        this.eliminado = eliminado;
        this.nombreOperario = nombre;
        this.correo = correo;
        this.apellidoOperario = apellido;
        this.tipoOperario = tipoOpera;
        this.dni = dni;
        this.direccion = direccion;
        
    }

    public String getApellidoOperario() {
        return apellidoOperario;
    }

    public void setApellidoOperario(String apellidoOperario) {
        this.apellidoOperario = apellidoOperario;
    }

    public String getCodigoOperario() {
        return codigoOperario;
    }

    public void setCodigoOperario(String codigoOperario) {
        this.codigoOperario = codigoOperario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public String getTipoOperario() {
        return tipoOperario;
    }

    public void setTipoOperario(String tipoOperario) {
        this.tipoOperario = tipoOperario;
    }
    
    
    
}
