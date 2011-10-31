/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author eduardo
 */
@Entity
@PrimaryKeyJoinColumn(name="maestroArticuloId")
@DiscriminatorValue(value="PC")
public class ProductoComponente extends MaestroDeArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Boolean eliminado;
     * 
     */
    @ManyToOne
    private Proveedor proveedor;
    @ManyToMany
    private List<Proveedor> proveedores;

    /*@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
     

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
     * 
     */

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
    
    
        public void addProveedor(Proveedor proveedor) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaProveedor(proveedor)) {

            getProveedores().add(proveedor);
            if (proveedor.getProductosComponentes() != null) {
                proveedor.getProductosComponentes().add(this);
            }else{
                proveedor.setProductosComponentes(new ArrayList<ProductoComponente>());
                proveedor.getProductosComponentes().add(this);
            }

        }
    }

    private boolean estaEnLaListaProveedor(Proveedor proveedor) {
        for (Proveedor prov : getProveedores()) {
            if (proveedor == prov) {
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
        if (!(object instanceof ProductoComponente)) {
            return false;
        }
        ProductoComponente other = (ProductoComponente) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoComponente[ id=" + getId() + " ]";
    }
    
}
