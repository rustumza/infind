/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOCostosVariables;
import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;

/**
 *
 * @author edu
 */
public class ModeloTablaMateriasPrimas extends ModeloTabla{



    public ModeloTablaMateriasPrimas() {

        super("Producto", "Costo Materia Prima", "Costo Mano de Obra", "Gastos de Fabricación");
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
                return costos.getCostoMateriaPrima();
            case 2:
                return costos.getCostosManoObra();
            case 3:
                return costos.getGastoFabricacion();
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

