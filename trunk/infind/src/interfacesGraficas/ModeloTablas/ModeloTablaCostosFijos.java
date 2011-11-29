/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.CostosFijos;

/**
 *
 * @author eduardo
 */
public class ModeloTablaCostosFijos extends ModeloTabla{



    public ModeloTablaCostosFijos() {

        super("Código", "Nombre", "Costo Mensual ($)");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        CostosFijos costos = (CostosFijos) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return costos.getNroCosto();
            case 1:
                return costos.getNombre();
            case 2:
                return costos.getCosto();
            
            default:
                return "";
        }

    }



     public void quitarCostosFijosLista(CostosFijos cost) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((CostosFijos) getListaElementos().get(i)).getNroCosto().equals(cost.getNroCosto())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public CostosFijos buscarCostosFijos (String nombre){
         for (Object cost : getListaElementos()) {
             if (((CostosFijos) cost).getNombre().equals(nombre)) {
                 return (CostosFijos) cost;
             }
        }
         return null;
     }

}

