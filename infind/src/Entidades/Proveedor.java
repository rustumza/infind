/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import persistencia.ObjetoPersitente;

/**
 *
 * @author eduardo
 */
@Entity
public class Proveedor extends ObjetoPersitente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean eliminado;
    private String nombre;
    private int telefono;
    private String direccion;
    private String correoElectronico;
    @ManyToMany
    List<MateriaPrima> materiasPrimas; 
    @ManyToMany
    List<ProductoComponente> productosComponentes; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<MateriaPrima> getMateriasPrimas() {
        return materiasPrimas;
    }


    public List<ProductoComponente> getProductosComponentes() {
        return productosComponentes;
    }

    public void setMateriasPrimas(List<MateriaPrima> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public void setProductosComponentes(List<ProductoComponente> productosComponentes) {
        this.productosComponentes = productosComponentes;
    }

    public void addMateriaPrima(MateriaPrima materiaPrima) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaMateriaPrima(materiaPrima)) {

            getMateriasPrimas().add(materiaPrima);
            if (materiaPrima.getProveedores() != null) {
                materiaPrima.getProveedores().add(this);
            }else{
                materiaPrima.setProveedores(new ArrayList<Proveedor>());
                materiaPrima.getProveedores().add(this);
            }

        }
    }

    private boolean estaEnLaListaMateriaPrima(MateriaPrima matPrim) {
        for (MateriaPrima mat : getMateriasPrimas()) {
            if (matPrim == mat) {
                return true;
            }
        }
        return false;
    }
    
    
    public void addProductoComponente(ProductoComponente productoComponente) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaProdComp(productoComponente)) {

            getProductosComponentes().add(productoComponente);
            if (productoComponente.getProveedores() != null) {
                productoComponente.getProveedores().add(this);
            }else{
                productoComponente.setProveedores(new ArrayList<Proveedor>());
                productoComponente.getProveedores().add(this);
            }

        }
    }

    private boolean estaEnLaListaProdComp(ProductoComponente prodComp) {
        for (ProductoComponente prod : getProductosComponentes()) {
            if (prodComp == prod) {
                return true;
            }
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.proveedor[ id=" + id + " ]";
    }
    
}
