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
public class ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion extends ModeloTabla{

   
    public ModeloTablaPedidosAProveedoresPantallaListarOrdenDeFabricacion() {

        super("Código Producto", "Nombre Producto","Cantidad","Fecha a realizar", "Estado");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        PedidoAProveedor pedido = (PedidoAProveedor) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pedido.getArticulo().getCodigo();
            case 1:
                return pedido.getArticulo().getNombre();
            case 2:
                return pedido.getCantidad();
            case 3:
                return formateadorfechas.fechaAStringDDMMAAAA(pedido.getFechaARealizarElPedido());    
            case 4:
                if(pedido.isEstaConcretado()){
                    return "Concretada";
                }    
                else{
                    return "Sin concretar";
                }
            default:
                return "";
        }

    }



     public void quitarPedidosDeLaLista(PedidoAProveedor pedido) {
        
        getListaElementos().remove(pedido);
        
        fireTableDataChanged();
    }
}
