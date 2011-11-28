/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class Herramientas extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private String codigo;
    private String nombreHerramientas;
    private String descripcion;
    @ManyToMany 
    
    private List<MaestroDeCentroDeTrabajo> maestroCentroTrabajo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<MaestroDeCentroDeTrabajo> getMaestroCentroTrabajo() {
        return maestroCentroTrabajo;
    }

    public void setMaestroCentroTrabajo(List<MaestroDeCentroDeTrabajo> maestroCentroTrabajo) {
        this.maestroCentroTrabajo = maestroCentroTrabajo;
    }

   

    public String getNombreHerramientas() {
        return nombreHerramientas;
    }

    public void setNombreHerramientas(String nombreHerramientas) {
        this.nombreHerramientas = nombreHerramientas;
    }
    
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Herramientas)) {
            return false;
        }
        Herramientas other = (Herramientas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombreHerramientas();
    }
}
