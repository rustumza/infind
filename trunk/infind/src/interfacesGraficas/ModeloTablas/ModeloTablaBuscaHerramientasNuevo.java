/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.Herramientas;

/**
 *
 * @author edu
 */
public class ModeloTablaBuscaHerramientasNuevo extends ModeloTabla{



    public ModeloTablaBuscaHerramientasNuevo() {

        super("Código", "Nombre");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        Herramientas herramientas = (Herramientas) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return herramientas.getCodigo();
            case 1:
                return herramientas.getNombreHerramientas();
            default:
                return "";
        }

    }



     public void quitarHerramientasLista(Herramientas herramientas) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((Herramientas) getListaElementos().get(i)).getCodigo().equals(herramientas.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public Herramientas buscarHerramientas (String nombre){
         for (Object herramie : getListaElementos()) {
             if (((Herramientas) herramie).getNombreHerramientas().equals(nombre)) {
                 return (Herramientas) herramie;
             }
        }
         return null;
     }

}

