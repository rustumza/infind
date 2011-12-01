/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOCentro;
import Entidades.MaestroDeCentroDeTrabajo;

/**
 *
 * @author edu
 */
public class ModeloTablaCentroDeTrabajo extends ModeloTabla {

    public ModeloTablaCentroDeTrabajo() {

        super("Componente", "Cantidad", "Costo/Cantidad ($)");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        DTOCentro centros = (DTOCentro) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return centros.getNombreCentro();
            case 1:
                return "1";
            case 2:
                return centros.getCosto();
            default:
                return "";
        }

    }

    public void quitarCostosVariablesLista(DTOCentro costos) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((DTOCentro) getListaElementos().get(i)).getNombreCentro().equals(costos.getNombreCentro())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }

    public DTOCentro buscarCostosVAriables(String nombre) {
        for (Object costos : getListaElementos()) {
            if (((DTOCentro) costos).getNombreCentro().equals(nombre)) {
                return (DTOCentro) costos;
            }
        }
        return null;
    }
}