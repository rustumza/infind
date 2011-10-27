/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.Maquina;

/**
 *
 * @author edu
 */
public class ModeloTablaBuscaMaquinasNuevo extends ModeloTabla{



    public ModeloTablaBuscaMaquinasNuevo() {

        super("Código", "Nombre");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        Maquina maquina = (Maquina) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return maquina.getCodigo();
            case 1:
                return maquina.getNombreMaquina();
            default:
                return "";
        }

    }



     public void quitarMaquinaLista(Maquina maquina) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((Maquina) getListaElementos().get(i)).getCodigo().equals(maquina.getCodigo())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public Maquina buscarMaquina (String nombre){
         for (Object maquina : getListaElementos()) {
             if (((Maquina) maquina).getNombreMaquina().equals(nombre)) {
                 return (Maquina) maquina;
             }
        }
         return null;
     }

}

