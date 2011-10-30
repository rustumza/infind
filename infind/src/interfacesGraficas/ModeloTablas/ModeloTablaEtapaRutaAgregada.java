/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloTablas;

import Entidades.EtapaDeRutaDeFabricacion;

/**
 *
 * @author eduardo
 */
public class ModeloTablaEtapaRutaAgregada extends ModeloTabla{



    public ModeloTablaEtapaRutaAgregada() {

        super("Número", "Nombre", "Tiempo Total de Trabajo");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        EtapaDeRutaDeFabricacion etapaRuta = (EtapaDeRutaDeFabricacion) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return etapaRuta.getNroEtapa();
            case 1:
                return etapaRuta.getNombreEtapa();
            case 2:
                return etapaRuta.getTiempoDeTrabajoTotal();

            default:
                return "";
        }

    }







     public void quitarEtapaRutaLista(EtapaDeRutaDeFabricacion etapaRuta) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((EtapaDeRutaDeFabricacion) getListaElementos().get(i)).getNroEtapa() == etapaRuta.getNroEtapa()) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public EtapaDeRutaDeFabricacion buscarEtapasRutas (String nombre){
         for (Object etapaRuta : getListaElementos()) {
             if (((EtapaDeRutaDeFabricacion) etapaRuta).getNombreEtapa() == nombre) {

                 return (EtapaDeRutaDeFabricacion) etapaRuta;
             }
        }
         return null;
     }
}
