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
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class Stock extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private float cantidadRealFisica;
    private float cantidadPorEntrar;
    private float cantidadReservada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCantidadFisicaReal() {
        return cantidadRealFisica;
    }

    public void setCantidadFisicaReal(float cantidad) {
        this.cantidadRealFisica = cantidad;
    }

    public float getCantidadPorEntrar() {
        return cantidadPorEntrar;
    }

    public void setCantidadPorEntrar(float cantidad) {
        this.cantidadPorEntrar = cantidad;
    }

    public float getCantidadReservada() {
        return cantidadReservada;
    }

    public void setCantidadReservada(float cantidadReservada) {
        this.cantidadReservada = cantidadReservada;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Stock[ id=" + id + " ]";
    }
}
