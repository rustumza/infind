/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import DTOs.DTOPuntoEquilibrio;

/**
 *
 * @author eduardo
 */
public class ModeloTablaPuntoEquilibrio extends ModeloTabla {

    public ModeloTablaPuntoEquilibrio() {

        super("Volumen", "CF", "CV", "CT", "Ingr por Venta", "Precio ($)", "CV/Unidad");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        DTOPuntoEquilibrio puntoEquilibrio = (DTOPuntoEquilibrio) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return puntoEquilibrio.getVolumen();
            case 1:
                return puntoEquilibrio.getCostoFijo();
            case 2:
                return puntoEquilibrio.getCostoVariable();
            case 3:
                return puntoEquilibrio.getCostoTotales();
            case 4:
                return puntoEquilibrio.getIngresoPorVenta();
            case 5:
                return puntoEquilibrio.getPrecio();
            case 6:
                return puntoEquilibrio.getCvUnidad();
            
            default:
                return "";
        }

    }

    public void quitarMaquinaLista(DTOPuntoEquilibrio pe) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((DTOPuntoEquilibrio) getListaElementos().get(i)).getNombreArticulo().equals(pe.getNombreArticulo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }

    public DTOPuntoEquilibrio buscarMaquina(String nombre) {
        for (Object maquina : getListaElementos()) {
            if (((DTOPuntoEquilibrio) maquina).getNombreArticulo().equals(nombre)) {
                return (DTOPuntoEquilibrio) maquina;
            }
        }
        return null;
    }
}
