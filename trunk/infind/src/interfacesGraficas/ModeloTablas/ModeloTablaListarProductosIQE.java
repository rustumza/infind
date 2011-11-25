/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.ProductoTipoIQE;



/**
 *
 * @author eduardo
 */
public class ModeloTablaListarProductosIQE extends ModeloTabla{



    public ModeloTablaListarProductosIQE() {

        super("Código", "Nombre","Eliminado");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        ProductoTipoIQE productoIQE = (ProductoTipoIQE) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return productoIQE.getCodigo();
            case 1:
                return productoIQE.getNombre();
            case 2:
                if(productoIQE.getEliminado()){
                    return "Sí";
                }else{
                    return "No";
                }
            default:
                return "";
        }

    }



     public void quitarArticuloLista(ProductoTipoIQE productoIQE) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((ProductoTipoIQE) getListaElementos().get(i)).getCodigo().equals(productoIQE.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public ProductoTipoIQE buscarProductoIQE (String nombre){
         for (Object productoIQE : getListaElementos()) {
             if (((ProductoTipoIQE) productoIQE).getNombre().equals(nombre)) {
                 return (ProductoTipoIQE) productoIQE;
             }
        }
         return null;
     }

}
