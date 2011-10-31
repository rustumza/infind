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
@DiscriminatorValue(value="MP")
public class MateriaPrima extends MaestroDeArticulo implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private Boolean eliminado;
     * 
     */
    @ManyToOne
    private Proveedor proveedorPredeterminado;
    @ManyToMany
    private List<Proveedor> Proveedores;
    
    private String tipoMateriaPrima;

    public String getTipoMateriaPrima() {
        return tipoMateriaPrima;
    }

    public void setTipoMateriaPrima(String tipoMateriaPrima) {
        this.tipoMateriaPrima = tipoMateriaPrima;
    }
    
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

    public Proveedor getProveedorPredeterminado() {
        return proveedorPredeterminado;
    }

    public void setProveedorPredeterminado(Proveedor proveedor) {
        this.proveedorPredeterminado = proveedor;
    }

    public List<Proveedor> getProveedores() {
        return Proveedores;
    }

    public void setProveedores(List<Proveedor> listaDeProveedores) {
        this.Proveedores = listaDeProveedores;
    }

    
    
    
    public void addProveedor(Proveedor proveedor) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaProveedor(proveedor)) {

            getProveedores().add(proveedor);
            if (proveedor.getMateriasPrimas() != null) {
                proveedor.getMateriasPrimas().add(this);
            }else{
                proveedor.setMateriasPrimas(new ArrayList<MateriaPrima>());
                proveedor.getMateriasPrimas().add(this);
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
        if (!(object instanceof MateriaPrima)) {
            return false;
        }
        MateriaPrima other = (MateriaPrima) object;
        if ((getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.MateriaPrima[ id=" + getId() + " ]";
    }
    
}
