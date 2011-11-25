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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    private String nombreEtapa;
    private int tiempoDeTrabajoDeMaquinas;
    private int tiempoDeTrabajoDeOperarios;
    private int tiempoDeTrabajoTotal;
    private boolean tipoIQE;
    @ManyToOne
    private MaestroDeCentroDeTrabajo maestroCentroTrabajo;
    @OneToMany @LazyCollection(LazyCollectionOption.FALSE)
    private List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList;
    @ManyToOne
    private MaestroDeRutaDeFabricacion maestroRutaFabricacionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getNombreEtapa() {
        return nombreEtapa;
    }

    public void setNombreEtapa(String nombreEtapa) {
        this.nombreEtapa = nombreEtapa;
    }

    

    public int getCantidadDeOperarios() {
        return cantidadDeOperarios;
    }

    public void setCantidadDeOperarios(int cantidadDeOperarios) {
        this.cantidadDeOperarios = cantidadDeOperarios;
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

    public List<DetalleDeArticuloEnEtapaDeFabricacion> getDetallesArtEnEtapaFabList() {
        return detallesArtEnEtapaFabList;
    }

    public void setDetallesArtEnEtapaFabList(List<DetalleDeArticuloEnEtapaDeFabricacion> detallesArtEnEtapaFabList) {
        this.detallesArtEnEtapaFabList = detallesArtEnEtapaFabList;
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

    public boolean isTipoIQE() {
        return tipoIQE;
    }

    public void setTipoIQE(boolean tipoIQE) {
        this.tipoIQE = tipoIQE;
    }
    
    
    
    
    public void addDetalle(DetalleDeArticuloEnEtapaDeFabricacion detalle) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaLista(detalle)) {
            getDetallesArtEnEtapaFabList().add(detalle);
            if (detalle.getEtapaRutaFabricacion() != null) {
                detalle.getEtapaRutaFabricacion().getDetallesArtEnEtapaFabList().remove(detalle);
            }
            detalle.setEtapaRutaFabricacion(this);
        }
    }


    private boolean estaEnLaLista(DetalleDeArticuloEnEtapaDeFabricacion detalle) {
        for (DetalleDeArticuloEnEtapaDeFabricacion detalleEtapa : detallesArtEnEtapaFabList) {
            if(detalle==detalleEtapa)
                return true;
        }
        return false;
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
        return getNombreEtapa();
    }
}
