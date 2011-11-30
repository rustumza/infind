/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.MaestroDeCentroDeTrabajo;

/**
 *
 * @author eduardo
 */
public class ModeloTablaCentroDeTrabajoEditar extends ModeloTabla{



    public ModeloTablaCentroDeTrabajoEditar() {

        super("Componente", "Cantidad", "Costo/Cantidad");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        MaestroDeCentroDeTrabajo centros =  (MaestroDeCentroDeTrabajo) getListaElementos().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return centros.getNombreCentro();
            case 1:
                return centros.getCostoCentroDeTrabajo();
            default:
                return "";
        }

    }

     public void quitarCostosVariablesLista(MaestroDeCentroDeTrabajo costos) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((MaestroDeCentroDeTrabajo) getListaElementos().get(i)).getNombreCentro().equals(costos.getNombreCentro())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public MaestroDeCentroDeTrabajo buscarCostosVAriables (String nombre){
         for (Object costos : getListaElementos()) {
             if (((MaestroDeCentroDeTrabajo) costos).getNombreCentro().equals(nombre)) {
                 return (MaestroDeCentroDeTrabajo) costos;
             }
        }
         return null;
     }

}