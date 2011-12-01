/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.OrdenDeFabricacion;
import utilidades.formateadorfechas;

/**
 *
 * @author rustu
 */
public class ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion extends ModeloTabla{

   public ModeloTablaOrdenDeProduccionPantallaOrdenDeproduccion() {

        super("Código Producto", "Nombre Producto","Cantidad de lotes optimos","Fecha a realizar");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        OrdenDeFabricacion orden = (OrdenDeFabricacion) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return orden.getProductoFabricable().getCodigo();
            case 1:
                return orden.getProductoFabricable().getNombre();
            case 2:
                return orden.getCantidadDeLotesOptimos();
            case 3:
                return formateadorfechas.fechaAStringDDMMAAAA(orden.getFecha());    
            default:
                return "";
        }

    }



     public void quitarPedidosDeLaLista(OrdenDeFabricacion orden) {
        
        getListaElementos().remove(orden);
        
        fireTableDataChanged();
    }
    
}