/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.DetalleDeArticuloEnEtapaDeFabricacion;

/**
 *
 * @author rustu
 */
public class ModeloTablaPantallaEstructuraDeProducto extends ModeloTabla {

    public ModeloTablaPantallaEstructuraDeProducto() {

        super("Tipo", "Nombre", "Cantidad");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        DetalleDeArticuloEnEtapaDeFabricacion detalle = (DetalleDeArticuloEnEtapaDeFabricacion) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return detalle.getMaestroArticulo().getTipo();
            case 1:
                return detalle.getMaestroArticulo().getNombre();
            case 2:
                return String.valueOf(detalle.getCantidad());
            default:
                return "";
        }

    }



     public void quitarArticuloLista(DetalleDeArticuloEnEtapaDeFabricacion detalle) {
        
       
        getListaElementos().remove(detalle);
       
       
        fireTableDataChanged();
    }


}
