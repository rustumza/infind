/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.PedidoAProveedor;
import utilidades.formateadorfechas;

/**
 *
 * @author rustu
 */
public class ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion extends ModeloTabla{

   
    public ModeloTablaPedidosAProveedoresPantallaOrdenDeFabricacion() {

        super("Nro","Código Producto", "Nombre Producto","Cantidad","Fecha a realizar");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        PedidoAProveedor pedido = (PedidoAProveedor) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                if(pedido.getId() == null){
                    return "";
                }else{
                    return pedido.getId();
                }
            case 1:
                return pedido.getArticulo().getCodigo();
            case 2:
                return pedido.getArticulo().getNombre();
            case 3:
                return pedido.getCantidad();
            case 4:
                return formateadorfechas.fechaAStringDDMMAAAA(pedido.getFechaARealizarElPedido());    
            default:
                return "";
        }

    }



     public void quitarPedidosDeLaLista(PedidoAProveedor pedido) {
        
        getListaElementos().remove(pedido);
        
        fireTableDataChanged();
    }
}
