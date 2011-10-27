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
public class ModeloTablaAgregaHerramientasNuevo extends ModeloTabla{



    public ModeloTablaAgregaHerramientasNuevo() {

        super("Código", "Nombre", "Descripción");
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
            case 2:
                return herramientas.getDescripcion();
            
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
         for (Object herramientas : getListaElementos()) {
             if (((Herramientas) herramientas).getNombreHerramientas().equals(nombre)) {
                 return (Herramientas) herramientas;
             }
        }
         return null;
     }

}

