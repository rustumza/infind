/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author eduardo
 */
@Entity
@PrimaryKeyJoinColumn(name="maestroArtuculoId")
public class ProductosFabricables extends MaestroDeArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean eliminado=false;
     * 
     */
    @ManyToOne
    private ProductoTipoIQE productoTipoIQE;
    

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
        return "Entidades.ProductosFabricables[ id=" + getId() + " ]";
    }
    
}
