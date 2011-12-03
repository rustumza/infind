/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOCostosVariables;

/**
 *
 * @author eduardo
 */
public class ModeloTablaMateriasPrimasEditar extends ModeloTabla{



    public ModeloTablaMateriasPrimasEditar() {

        super("Componente", "Cantidad", "Costo/Cantidad ($)");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        DTOCostosVariables costos =  (DTOCostosVariables) getListaElementos().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return costos.getNombreProducto();
            case 1:
                return "1";
            case 2:
                return costos.getCostoMateriaPrima();
            
            default:
                return "";
        }

    }



     public void quitarCostosVariablesLista(DTOCostosVariables costos) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((DTOCostosVariables) getListaElementos().get(i)).getNombreProducto().equals(costos.getNombreProducto())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public DTOCostosVariables buscarCostosVAriables (String nombre){
         for (Object costos : getListaElementos()) {
             if (((DTOCostosVariables) costos).getNombreProducto().equals(nombre)) {
                 return (DTOCostosVariables) costos;
             }
        }
         return null;
     }

}

