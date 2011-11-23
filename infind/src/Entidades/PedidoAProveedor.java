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
 * @author rustu
 */
@Entity
public class PedidoAProveedor extends ObjetoPersitente implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    MaestroDeArticulo articulo;
    Proveedor proveedor;
    float cantidad;
    private boolean estaConcretado;

    public MaestroDeArticulo getArticulo() {
        return articulo;
    }

    public void setArticulo(MaestroDeArticulo articulo) {
        this.articulo = articulo;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstaConcretado() {
        return estaConcretado;
    }

    public void setEstaConcretado(boolean estaConcretado) {
        this.estaConcretado = estaConcretado;
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
        if (!(object instanceof PedidoAProveedor)) {
            return false;
        }
        PedidoAProveedor other = (PedidoAProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getArticulo().getNombre() + " = " + getCantidad();
    }
    
}
