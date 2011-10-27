/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author eduardo
 */
@Entity
@PrimaryKeyJoinColumn(name="maestroArtuculoId")
public class ProductoFinal extends ProductosFabricables implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Boolean eliminado;
     * 
     */
    private char categoriaABC;
           
/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
*/
    public char getCategoriaABC() {
        return categoriaABC;
    }

    public void setCategoriaABC(char categoriaABC) {
        this.categoriaABC = categoriaABC;
    }

    /*
     * public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
     * 
     */
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoFinal)) {
            return false;
        }
        ProductoFinal other = (ProductoFinal) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoFinal[ id=" + getId() + " ]";
    }
    
}
