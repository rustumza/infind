/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.MateriaPrima;



/**
 *
 * @author eduardo
 */
public class ModeloTablaListarMateriasPrimas extends ModeloTabla{



    public ModeloTablaListarMateriasPrimas() {

        super("Código", "Nombre", "Categoria", "Ubicacion en almacén", "Eliminado");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        MateriaPrima materiaPrima = (MateriaPrima) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return materiaPrima.getCodigo();
            case 1:
                return materiaPrima.getNombre();
            case 2:
                return materiaPrima.getCategoria();
            case 3:
                return materiaPrima.getUbicacionEnAlmacen();
            case 4:
                if(materiaPrima.getEliminado()){
                    return "Sí";
                }else{
                    return "No";
                }
            default:
                return "";
        }

    }



     public void quitarArticuloLista(MateriaPrima materiaPrima) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((MateriaPrima) getListaElementos().get(i)).getCodigo().equals(materiaPrima.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public MateriaPrima buscarMateriaPrima (String nombre){
         for (Object materiaPrima : getListaElementos()) {
             if (((MateriaPrima) materiaPrima).getNombre().equals(nombre)) {
                 return (MateriaPrima) materiaPrima;
             }
        }
         return null;
     }

}
