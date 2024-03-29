/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class DetalleEstructuraDeProducto extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private float cantidad;
    @ManyToOne
    private MaestroDeArticulo maestroArticulo;
    @ManyToOne
    private MaestroDeEstructuraDeProducto maestroDeEstrucruraDeProducto;
    private String tipo;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public MaestroDeArticulo getMaestroArticulo() {
        return maestroArticulo;
    }

    public void setMaestroArticulo(MaestroDeArticulo maestroArticulo) {
        this.maestroArticulo = maestroArticulo;
    }

    public MaestroDeEstructuraDeProducto getMaestroDeEstrucruraDeProducto() {
        return maestroDeEstrucruraDeProducto;
    }

    public void setMaestroDeEstrucruraDeProducto(MaestroDeEstructuraDeProducto maestroDeEstrucruraDeProducto) {
        this.maestroDeEstrucruraDeProducto = maestroDeEstrucruraDeProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof DetalleEstructuraDeProducto)) {
            return false;
        }
        DetalleEstructuraDeProducto other = (DetalleEstructuraDeProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleEstructuraDeProducto[ id=" + id + " ]";
    }
    
}
