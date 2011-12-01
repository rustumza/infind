/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.PedidoAProveedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import persistencia.ObjetoPersitente;

/**
 *
 * @author rustu
 */
@Entity
public class OrdenDeFabricacion extends ObjetoPersitente implements Serializable {
    public OrdenDeFabricacion(){
        listaDePedido = new ArrayList<PedidoAProveedor>();
        listaDeOrdenes = new ArrayList<OrdenDeFabricacion>();
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private int CantidadDeLotesOptimos;
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PedidoAProveedor> listaDePedido;
    
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrdenDeFabricacion> listaDeOrdenes;
    
    @ManyToOne
    private ProductosFabricables productoFabricable;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadDeLotesOptimos() {
        return CantidadDeLotesOptimos;
    }

    public void setCantidadDeLotesOptimos(int CantidadDeLotesOptimos) {
        this.CantidadDeLotesOptimos = CantidadDeLotesOptimos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<PedidoAProveedor> getListaDePedido() {
        return listaDePedido;
    }

    public void setListaDePedido(List<PedidoAProveedor> listaDePedido) {
        this.listaDePedido = listaDePedido;
    }

    public List<OrdenDeFabricacion> getListaDeOrdenes() {
        return listaDeOrdenes;
    }

    public void setListaDeOrdenes(List<OrdenDeFabricacion> listaDeOrdenes) {
        this.listaDeOrdenes = listaDeOrdenes;
    }

    public ProductosFabricables getProductoFabricable() {
        return productoFabricable;
    }

    public void setProductoFabricable(ProductosFabricables prod) {
        this.productoFabricable = prod;
    }
    
    
    
    
    public void addPedido(PedidoAProveedor pedido) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaPedido(pedido)) {
            getListaDePedido().add(pedido);
        }
    }

    private boolean estaEnLaListaPedido(PedidoAProveedor pedido) {
        for (PedidoAProveedor ped : listaDePedido) {
            if (pedido == ped) {
                return true;
            }
        }
        return false;
    }
    
    public void addOrden(OrdenDeFabricacion orden) {
        //if (!getDetallesDeFactura().contains(detalle)) {
        if (!estaEnLaListaOrden(orden)) {
            getListaDeOrdenes().add(orden);
        }
    }

    private boolean estaEnLaListaOrden(OrdenDeFabricacion orden) {
        for (OrdenDeFabricacion ord : listaDeOrdenes) {
            if (orden == ord) {
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
        if (!(object instanceof OrdenDeFabricacion)) {
            return false;
        }
        OrdenDeFabricacion other = (OrdenDeFabricacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.OrdenDeFabricacion[ id=" + id + " ]";
    }
    
}
