/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.MaestroDeArticulo;
import expertos.ExpertoStock;

/**
 *
 * @author rustu
 */
public class ModeloTablaProductosPorDebajoDelPuntoDePedido extends ModeloTabla{
    
        public ModeloTablaProductosPorDebajoDelPuntoDePedido() {

        super("Código", "Nombre", "Punto de pedido", "Stock disponible");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        MaestroDeArticulo articulo = (MaestroDeArticulo) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return articulo.getCodigo();
            case 1:
                return articulo.getNombre();
            case 2:
                return articulo.getPuntoDePedido();
            case 3:
                return new ExpertoStock().getStockDisponibleAFuturo(articulo);
            default:
                return "";
        }

    }

    
}
