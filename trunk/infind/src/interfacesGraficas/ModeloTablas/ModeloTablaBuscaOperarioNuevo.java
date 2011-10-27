/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloTablas;

import Entidades.Operario;

/**
 *
 * @author edu
 */
public class ModeloTablaBuscaOperarioNuevo extends ModeloTabla{



    public ModeloTablaBuscaOperarioNuevo() {

        super("Código", "Nombre", "Apellido");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(getListaElementos() == null){
            return  null;
        }
        Operario operario = (Operario) getListaElementos().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return operario.getCodigoOperario();
            case 1:
                return operario.getNombre();
            case 2:
                return operario.getApellido();
            default:
                return "";
        }

    }



     public void quitarOperarioLista(Operario operario) {
        for (int i = 0; i < getListaElementos().size(); i++) {
            if (((Operario) getListaElementos().get(i)).getCodigoOperario().equals(operario.getCodigoOperario())) {
                getListaElementos().remove(i);
            }
        }
        fireTableDataChanged();
    }



     public Operario buscarOperario (String nombre){
         for (Object operario : getListaElementos()) {
             if (((Operario) operario).getNombre().equals(nombre)) {
                 return (Operario) operario;
             }
        }
         return null;
     }

}

