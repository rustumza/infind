/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOIndices;

/**
 *
 * @author rustu
 */
public class ModeloTablaIndicesAnormales extends ModeloTabla {
    
   public ModeloTablaIndicesAnormales() {

        super("Índice", "Mínimo", "Máximo", "Valor", "Es normal");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        DTOIndices ind =  (DTOIndices) getListaElementos().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return ind.getInd().getNombre();
            case 1:
                return ind.getInd().getMinimo();
            case 2:
                return ind.getInd().getMaximo();
            case 3:
                return ind.getValor();
            case 4:
                return ind.getNormalidad();
            default:
                return "";
        }

    }



}
