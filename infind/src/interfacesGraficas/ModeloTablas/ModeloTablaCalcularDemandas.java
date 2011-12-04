/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.Demanda;

/**
 *
 * @author eduardo
 */
public class ModeloTablaCalcularDemandas extends ModeloTabla {

    public ModeloTablaCalcularDemandas() {

        super("Periodo", "Demanda real", "Demanda pronosticada", "Error promedio");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        Demanda demandas = (Demanda) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return demandas.getPeriodo();
            case 1:
                return demandas.getDemandaHistorica();
            case 2:
                return demandas.getDemandaPronosticada();
            case 3:
                return demandas.getErrorPromedio();
            default:
                return "";
        }

    }

    public void quitarDemandasLista(Demanda d) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((Demanda) getListaElementos().get(i)).getPeriodo() == (d.getPeriodo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }

    public Demanda buscarDemandas(int nombre) {
        for (Object dem : getListaElementos()) {
            if (((Demanda) dem).getPeriodo() == (nombre)) {
                return (Demanda) dem;
            }
        }
        return null;
    }
}
