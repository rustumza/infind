/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 *
 * @author eduardo
 */
@Entity
@PrimaryKeyJoinColumn(name="maestroArticuloId")
@DiscriminatorValue(value="PFab")
public class ProductosFabricables extends MaestroDeArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean eliminado=false;
     * 
     */
    @ManyToOne
    @LazyToOne(LazyToOneOption.FALSE)
    private ProductoTipoIQE productoTipoIQE;
    
    @ManyToOne
    @LazyToOne(LazyToOneOption.FALSE)
    private MaestroDeEstructuraDeProducto maestroEstructuraDeProducto;

    @OneToMany 
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrdenDeFabricacion> ordenDeFabricacion;
    
    /*@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
     

    @Override
    public Boolean getEliminado() {
        return eliminado;
    }

    @Override
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
     * 
     */

    public ProductoTipoIQE getProductoTipoIQE() {
        return productoTipoIQE;
    }

    public void setProductoTipoIQE(ProductoTipoIQE productoTipoIQE) {
        this.productoTipoIQE = productoTipoIQE;
    }

    public MaestroDeEstructuraDeProducto getMaestroEstructuraDeProducto() {
        return maestroEstructuraDeProducto;
    }

    public void setMaestroEstructuraDeProducto(MaestroDeEstructuraDeProducto maestroEstructuraDeProducto) {
        this.maestroEstructuraDeProducto = maestroEstructuraDeProducto;
    }

    public List<OrdenDeFabricacion> getOrdenDeFabricacion() {
        return ordenDeFabricacion;
    }

    public void setOrdenDeFabricacion(List<OrdenDeFabricacion> ordenDeFabricacion) {
        this.ordenDeFabricacion = ordenDeFabricacion;
    }
    
    public void addOrden(OrdenDeFabricacion orden) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaOrden(orden)) {
            getOrdenDeFabricacion().add(orden);
            if(orden.getProductoFabricable() != null){
                orden.getProductoFabricable().getOrdenDeFabricacion().remove(orden);
            }
            orden.setProductoFabricable(this);
        }
    }
    

    private boolean estaEnLaListaOrden(OrdenDeFabricacion orden) {
        for (OrdenDeFabricacion ord : ordenDeFabricacion) {
            if (orden == ord) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosFabricables)) {
            return false;
        }
        ProductosFabricables other = (ProductosFabricables) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
