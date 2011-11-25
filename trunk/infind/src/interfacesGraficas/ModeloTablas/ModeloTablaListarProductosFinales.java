/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.ProductoFinal;



/**
 *
 * @author eduardo
 */
public class ModeloTablaListarProductosFinales extends ModeloTabla{



    public ModeloTablaListarProductosFinales() {

        super("Código", "Nombre", "Categoria", "Ubicacion en almacén","Eliminado");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        ProductoFinal productoFinal = (ProductoFinal) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return productoFinal.getCodigo();
            case 1:
                return productoFinal.getNombre();
            case 2:
                return productoFinal.getCategoria();
            case 3:
                return productoFinal.getUbicacionEnAlmacen();
            case 4:
                if(productoFinal.getEliminado()){
                    return "Sí";
                }else{
                    return "No";
                }
            default:
                return "";
        }

    }



     public void quitarArticuloLista(ProductoFinal productoFinal) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((ProductoFinal) getListaElementos().get(i)).getCodigo().equals(productoFinal.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public ProductoFinal buscarProductoFinal (String nombre){
         for (Object productoFinal : getListaElementos()) {
             if (((ProductoFinal) productoFinal).getNombre().equals(nombre)) {
                 return (ProductoFinal) productoFinal;
             }
        }
         return null;
     }

}
