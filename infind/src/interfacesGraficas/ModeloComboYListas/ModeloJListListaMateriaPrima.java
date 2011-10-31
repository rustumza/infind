/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.MateriaPrima;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author rustu
 */
public class ModeloJListListaMateriaPrima extends AbstractListModel{

    private List<MateriaPrima> listaDeMateriasPrimas;

    public ModeloJListListaMateriaPrima() {
        inicializarValores();
    }

    public ModeloJListListaMateriaPrima(List<MateriaPrima> materiasPrimas) {
        inicializarValores();
        listaDeMateriasPrimas.addAll(materiasPrimas);

    }

    private void inicializarValores(){
        listaDeMateriasPrimas = new ArrayList<MateriaPrima>();
    }

    public int getSize() {
        return listaDeMateriasPrimas.size();

    }

    public MateriaPrima getElementAt(int index) {
        return listaDeMateriasPrimas.get(index);

    }

    public List<MateriaPrima> getMateriasPrimas(){

        return listaDeMateriasPrimas;
    }

    public void removeMateriasPrimas(int indice){

        listaDeMateriasPrimas.remove(indice);

    }

    public void addElement(MateriaPrima matPrim){

        listaDeMateriasPrimas.add(matPrim);

    }





}
