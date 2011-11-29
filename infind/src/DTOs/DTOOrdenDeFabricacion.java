/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import Entidades.OrdenDeFabricacion;
import Entidades.PedidoAProveedor;
import java.util.List;

/**
 *
 * @author rustu
 */
public class DTOOrdenDeFabricacion {
    
    private OrdenDeFabricacion orden;
    private List<PedidoAProveedor> listaDePedidosAProveedores;

    public List<PedidoAProveedor> getListaDePedidosAProveedores() {
        return listaDePedidosAProveedores;
    }

    public void setListaDePedidosAProveedores(List<PedidoAProveedor> listaDePedidosAProveedores) {
        this.listaDePedidosAProveedores = listaDePedidosAProveedores;
    }

    public OrdenDeFabricacion getOrden() {
        return orden;
    }

    public void setOrden(OrdenDeFabricacion orden) {
        this.orden = orden;
    }
    
    
    
    
}
