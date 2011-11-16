/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.MaestroDeArticulo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author rustu
 */
public class ModeloJListListaMaestroDeArticulo extends AbstractListModel{

    private List<MaestroDeArticulo> listaDeMaestroDeArticulo;

    public ModeloJListListaMaestroDeArticulo() {
        inicializarValores();
    }

    public ModeloJListListaMaestroDeArticulo(List<MaestroDeArticulo> articulo) {
        inicializarValores();
        listaDeMaestroDeArticulo.addAll(articulo);

    }

    private void inicializarValores(){
        listaDeMaestroDeArticulo = new ArrayList<MaestroDeArticulo>();
    }

    public int getSize() {
        return listaDeMaestroDeArticulo.size();

    }

    public MaestroDeArticulo getElementAt(int index) {
        return listaDeMaestroDeArticulo.get(index);

    }

    public List<MaestroDeArticulo> getMaestrosDeArticulo(){

        return listaDeMaestroDeArticulo;
    }

    public void removeMaestrosDeArticulo(int indice){

        listaDeMaestroDeArticulo.remove(indice);

    }

    public void addElement(MaestroDeArticulo articulo){

        listaDeMaestroDeArticulo.add(articulo);

    }





}
