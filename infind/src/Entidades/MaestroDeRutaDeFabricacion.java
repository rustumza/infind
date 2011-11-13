/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class MaestroDeRutaDeFabricacion extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private String numero;
    @OneToMany
    private List<EtapaDeRutaDeFabricacion> etapasRutaFabricacion;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<EtapaDeRutaDeFabricacion> getEtapaRutaFabricacion() {
        return etapasRutaFabricacion;
    }

    public void setEtapaRutaFabricacion(List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion) {
        this.etapasRutaFabricacion = etapaRutaFabricacion;
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
        if (!(object instanceof MaestroDeRutaDeFabricacion)) {
            return false;
        }
        MaestroDeRutaDeFabricacion other = (MaestroDeRutaDeFabricacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MaestroDeRutaDeFabricacion[ id=" + id + " ]";
    }
    
    public void addDetalle(EtapaDeRutaDeFabricacion detalle) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaLista(detalle)) {
            getEtapaRutaFabricacion().add(detalle);
            if (detalle.getMaestroRutaFabricacionList() != null) {
                detalle.getMaestroRutaFabricacionList().getEtapaRutaFabricacion().remove(detalle);
            }
            detalle.setMaestroRutaFabricacionList(this);
        }
    }
    
    private boolean estaEnLaLista(EtapaDeRutaDeFabricacion detalle) {
        for (EtapaDeRutaDeFabricacion etapa : etapasRutaFabricacion) {
            if(detalle==etapa)
                return true;
        }
        return false;
    }
    
    public void quitarDetalla(EtapaDeRutaDeFabricacion detalle){
        if(etapasRutaFabricacion.contains(detalle)){
            etapasRutaFabricacion.remove(detalle);
            detalle.setMaestroRutaFabricacionList(null);
        
        }
        
    }

    
}
