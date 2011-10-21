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
public class DetalleDeArticuloEnEtapaDeFabricacion extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado = false;
    private float cantidad;
    private String unidadDeMedida;
    @ManyToOne
    private EtapaDeRutaDeFabricacion etapaRutaFabricacion;
    @ManyToOne
    private MaestroDeArticulo maestroArticulo;
    

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

    public EtapaDeRutaDeFabricacion getEtapaRutaFabricacion() {
        return etapaRutaFabricacion;
    }

    public void setEtapaRutaFabricacion(EtapaDeRutaDeFabricacion etapaRutaFabricacion) {
        this.etapaRutaFabricacion = etapaRutaFabricacion;
    }

    public MaestroDeArticulo getMaestroArticulo() {
        return maestroArticulo;
    }

    public void setMaestroArticulo(MaestroDeArticulo maestroArticulo) {
        this.maestroArticulo = maestroArticulo;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
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
        if (!(object instanceof DetalleDeArticuloEnEtapaDeFabricacion)) {
            return false;
        }
        DetalleDeArticuloEnEtapaDeFabricacion other = (DetalleDeArticuloEnEtapaDeFabricacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.detalleDeArticuloEnEtapaDeFabricacion[ id=" + id + " ]";
    }
    
}
