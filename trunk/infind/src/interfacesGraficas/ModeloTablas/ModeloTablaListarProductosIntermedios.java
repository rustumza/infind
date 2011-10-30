/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.ProductoIntermedio;



/**
 *
 * @author eduardo
 */
public class ModeloTablaListarProductosIntermedios extends ModeloTabla{



    public ModeloTablaListarProductosIntermedios() {

        super("Código", "Nombre", "Categoria", "Ubicacion en almacén");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        ProductoIntermedio productoIntermedio = (ProductoIntermedio) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return productoIntermedio.getCodigo();
            case 1:
                return productoIntermedio.getNombre();
            case 2:
                return productoIntermedio.getCategoria();
            case 3:
                return productoIntermedio.getUbicacionEnAlmacen();
            default:
                return "";
        }

    }



     public void quitarArticuloLista(ProductoIntermedio productoIntermedio) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((ProductoIntermedio) getListaElementos().get(i)).getCodigo().equals(productoIntermedio.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public ProductoIntermedio buscarProductoIntermedio (String nombre){
         for (Object productoIntermedio : getListaElementos()) {
             if (((ProductoIntermedio) productoIntermedio).getNombre().equals(nombre)) {
                 return (ProductoIntermedio) productoIntermedio;
             }
        }
         return null;
     }

}
