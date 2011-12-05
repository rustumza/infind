/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.indices;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import persistencia.ObjetoPersitente;

/**
 *
 * @author edu
 */
@Entity
public class VolumenDeCompra extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean eliminado = false;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private int nroCuentasACobrar;
    private int ventasTOtalesPorDia;

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNroCuentasACobrar() {
        return nroCuentasACobrar;
    }

    public void setNroCuentasACobrar(int nroCuentasACobrar) {
        this.nroCuentasACobrar = nroCuentasACobrar;
    }

    public int getVentasTOtalesPorDia() {
        return ventasTOtalesPorDia;
    }

    public void setVentasTOtalesPorDia(int ventasTOtalesPorDia) {
        this.ventasTOtalesPorDia = ventasTOtalesPorDia;
    }
    
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof VolumenDeCompra)) {
            return false;
        }
        VolumenDeCompra other = (VolumenDeCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.indices.VolumenDeCompra[ id=" + id + " ]";
    }
    
}
