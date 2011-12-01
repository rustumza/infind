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

        super("Producto", "Volumen", "CF", "CV", "CT", "Ingr por Venta", "PE", "Precio ($)", "CV/Unidad");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (getListaElementos() == null) {
            return null;
        }
        DTOPuntoEquilibrio puntoEquilibrio = (DTOPuntoEquilibrio) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return puntoEquilibrio.getNombreArticulo();
            case 1:
                return puntoEquilibrio.getVolumen();
            case 2:
                return puntoEquilibrio.getCostoFijo();
            case 3:
                return puntoEquilibrio.getCostoVariable();
            case 4:
                return puntoEquilibrio.getCostoTotales();
            case 5:
                return puntoEquilibrio.getIngresoPorVenta();
            case 6:
                return puntoEquilibrio.getPuntoEquilibrio();
            case 7:
                return puntoEquilibrio.getPrecio();
            case 8:
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
