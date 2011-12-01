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
 * @author edu
 */
@Entity
public class CostoVariable extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private double cantidad;
    private double costo;
    private boolean eliminado = false;
    private boolean esCentro;
    @ManyToOne
    private MaestroDeArticulo articulo;

    public Long getId() {
        return id;
    }

    public boolean isEsCentro() {
        return esCentro;
    }

    public void setEsCentro(boolean esCentro) {
        this.esCentro = esCentro;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    

    public MaestroDeArticulo getArticulo() {
        return articulo;
    }

    public void setArticulo(MaestroDeArticulo articulo) {
        this.articulo = articulo;
    }
    

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof CostoVariable)) {
            return false;
        }
        CostoVariable other = (CostoVariable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CostoVAriable[ id=" + id + " ]";
    }
    
}
