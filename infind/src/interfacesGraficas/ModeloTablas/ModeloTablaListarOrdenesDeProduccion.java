/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.OrdenDeFabricacion;

/**
 *
 * @author rustu
 */
public class ModeloTablaListarOrdenesDeProduccion extends ModeloTabla {

    public ModeloTablaListarOrdenesDeProduccion() {

        super("Producto fabricable", "Cantidad", "Fecha", "Estado");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        OrdenDeFabricacion ordenes = (OrdenDeFabricacion) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return ordenes.getProductoFabricable().getNombre();
            case 1:
                return ordenes.getCantidadDeLotesOptimos();
            case 2:
                return ordenes.getFecha();
            case 3:
                return ordenes.getEstado();
            default:
                return "";
        }

    }

    public void quitarOrdenesDeLista(OrdenDeFabricacion ordenes) {
        getListaElementos().remove(ordenes);
        fireTableDataChanged();
    }

    
}
