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
import javax.persistence.OneToMany;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class EtapaDeRutaDeFabricacion extends ObjetoPersitente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private int cantidadDeOperarios;
    private int nroEtapa;
    private int tiempoDeTrabajoDeMaquinas;
    private int tiempoDeTrabajoDeOperarios;
    private int tiempoDeTrabajoTotal;
    @ManyToOne
    private MaestroDeCentroDeTrabajo maestroCentroTrabajo;
    @OneToMany
    private DetalleDeArticuloEnEtapaDeFabricacion detalleArtEnEtapaFabList;
    @ManyToOne
    private MaestroDeRutaDeFabricacion maestroRutaFabricacionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadDeOperarios() {
        return cantidadDeOperarios;
    }

    public void setCantidadDeOperarios(int cantidadDeOperarios) {
        this.cantidadDeOperarios = cantidadDeOperarios;
    }

    public DetalleDeArticuloEnEtapaDeFabricacion getDetalleArtEnEtapaFabList() {
        return detalleArtEnEtapaFabList;
    }

    public void setDetalleArtEnEtapaFabList(DetalleDeArticuloEnEtapaDeFabricacion detalleArtEnEtapaFabList) {
        this.detalleArtEnEtapaFabList = detalleArtEnEtapaFabList;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public MaestroDeCentroDeTrabajo getMaestroCentroTrabajo() {
        return maestroCentroTrabajo;
    }

    public void setMaestroCentroTrabajo(MaestroDeCentroDeTrabajo maestroCentroTrabajo) {
        this.maestroCentroTrabajo = maestroCentroTrabajo;
    }

    public MaestroDeRutaDeFabricacion getMaestroRutaFabricacionList() {
        return maestroRutaFabricacionList;
    }

    public void setMaestroRutaFabricacionList(MaestroDeRutaDeFabricacion maestroRutaFabricacionList) {
        this.maestroRutaFabricacionList = maestroRutaFabricacionList;
    }

    public int getNroEtapa() {
        return nroEtapa;
    }

    public void setNroEtapa(int nroEtapa) {
        this.nroEtapa = nroEtapa;
    }

    public int getTiempoDeTrabajoDeMaquinas() {
        return tiempoDeTrabajoDeMaquinas;
    }

    public void setTiempoDeTrabajoDeMaquinas(int tiempoDeTrabajoDeMaquinas) {
        this.tiempoDeTrabajoDeMaquinas = tiempoDeTrabajoDeMaquinas;
    }

    public int getTiempoDeTrabajoDeOperarios() {
        return tiempoDeTrabajoDeOperarios;
    }

    public void setTiempoDeTrabajoDeOperarios(int tiempoDeTrabajoDeOperarios) {
        this.tiempoDeTrabajoDeOperarios = tiempoDeTrabajoDeOperarios;
    }

    public int getTiempoDeTrabajoTotal() {
        return tiempoDeTrabajoTotal;
    }

    public void setTiempoDeTrabajoTotal(int tiempoDeTrabajoTotal) {
        this.tiempoDeTrabajoTotal = tiempoDeTrabajoTotal;
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
        if (!(object instanceof EtapaDeRutaDeFabricacion)) {
            return false;
        }
        EtapaDeRutaDeFabricacion other = (EtapaDeRutaDeFabricacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EtapaDeRutaDeFabricacion[ id=" + id + " ]";
    }
}
