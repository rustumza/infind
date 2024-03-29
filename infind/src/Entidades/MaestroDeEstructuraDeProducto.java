/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class MaestroDeEstructuraDeProducto extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private String estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEnLaQueEntroEnVigencia;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DetalleEstructuraDeProducto> detallesEstructuraProductoList;
    @OneToOne
    private MaestroDeRutaDeFabricacion maestroRutaFabricacion;
    @ManyToOne
    private ProductoFinal productoFinal;
    @ManyToOne
    private ProductoTipoIQE productoTipoIQE;
    @ManyToOne
    private ProductoIntermedio productoIntermedio;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DetalleEstructuraDeProducto> getDetalleEstructuraProductoList() {
        return detallesEstructuraProductoList;
    }

    public void setDetalleEstructuraProductoList(List<DetalleEstructuraDeProducto> detalleEstructuraProductoList) {
        this.detallesEstructuraProductoList = detalleEstructuraProductoList;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEnLaQueEntroEnVigencia() {
        return fechaEnLaQueEntroEnVigencia;
    }

    public void setFechaEnLaQueEntroEnVigencia(Date fechaEnLaQueEntroEnVigencia) {
        this.fechaEnLaQueEntroEnVigencia = fechaEnLaQueEntroEnVigencia;
    }

    public MaestroDeRutaDeFabricacion getMaestroRutaFabricacion() {
        return maestroRutaFabricacion;
    }

    public void setMaestroRutaFabricacion(MaestroDeRutaDeFabricacion maestroRutaFabricacion) {
        this.maestroRutaFabricacion = maestroRutaFabricacion;
    }

    public ProductoFinal getProductoFinal() {
        return productoFinal;
    }

    public void setProductoFinal(ProductoFinal productoFinal) {
        this.productoFinal = productoFinal;
    }

    public ProductoIntermedio getProductoIntermedio() {
        return productoIntermedio;
    }

    public void setProductoIntermedio(ProductoIntermedio productoIntermedio) {
        this.productoIntermedio = productoIntermedio;
    }

    public ProductoTipoIQE getProductoTipoIQE() {
        return productoTipoIQE;
    }

    public void setProductoTipoIQE(ProductoTipoIQE productoTipoIQE) {
        this.productoTipoIQE = productoTipoIQE;
    }

     public void addDetalle(DetalleEstructuraDeProducto detalle) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaLista(detalle)) {
            getDetalleEstructuraProductoList().add(detalle);
            if (detalle.getMaestroDeEstrucruraDeProducto() != null) {
                detalle.getMaestroDeEstrucruraDeProducto().getDetalleEstructuraProductoList().remove(detalle);
            }
            detalle.setMaestroDeEstrucruraDeProducto(this);
        }
    }
    
    private boolean estaEnLaLista(DetalleEstructuraDeProducto detalle) {
        for (DetalleEstructuraDeProducto detalleEstructura : detallesEstructuraProductoList) {
            if(detalle==detalleEstructura)
                return true;
        }
        return false;
    }
    
    public void quitarDetalla(DetalleEstructuraDeProducto detalle){
        if(detallesEstructuraProductoList.contains(detalle)){
            detallesEstructuraProductoList.remove(detalle);
            detalle.setMaestroDeEstrucruraDeProducto(null);
        
        }
        
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
        if (!(object instanceof MaestroDeEstructuraDeProducto)) {
            return false;
        }
        MaestroDeEstructuraDeProducto other = (MaestroDeEstructuraDeProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.NewEntity[ id=" + id + " ]";
    }
    
}
