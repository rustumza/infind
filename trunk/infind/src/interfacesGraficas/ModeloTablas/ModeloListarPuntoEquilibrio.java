/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOPE;
import DTOs.DTOPuntoEquilibrio;

/**
 *
 * @author edu
 */
public class ModeloListarPuntoEquilibrio extends ModeloTabla {

    public ModeloListarPuntoEquilibrio() {

        super("Producto Estandard", "Total demanda Periodo");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        DTOPE puntoEquilibrio = (DTOPE) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return puntoEquilibrio.getPePRodEstandar();
            case 1:
                return puntoEquilibrio.getTotalDemandaPeriodo();
           
            default:
                return "";
        }

    }

    public void quitarMaquinaLista(DTOPE pe) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((DTOPE) getListaElementos().get(i)).getPePRodEstandar().equals(pe.getPePRodEstandar())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }

    public DTOPE buscarMaquina(String nombre) {
        for (Object maquina : getListaElementos()) {
            if (((DTOPE) maquina).getPePRodEstandar().equals(nombre)) {
                return (DTOPE) maquina;
            }
        }
        return null;
    }
}
