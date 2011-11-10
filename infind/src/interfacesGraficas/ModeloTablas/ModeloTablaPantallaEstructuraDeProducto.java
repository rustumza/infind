/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.DetalleEstructuraDeProducto;

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
        DetalleEstructuraDeProducto detalle = (DetalleEstructuraDeProducto) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return detalle.getTipo();
            case 1:
                return detalle.getMaestroArticulo().getNombre();
            case 2:
                return String.valueOf(detalle.getCantidad());
            default:
                return "";
        }

    }



     public void quitarArticuloLista(DetalleEstructuraDeProducto detalle) {
        
       
        getListaElementos().remove(detalle);
       
       
        fireTableDataChanged();
    }


}
