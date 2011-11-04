/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.Maquina;
import Entidades.Operario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author eduardo
 */
public class ModeloJListaMaquinas extends AbstractListModel{

    private List<Maquina> listaDeMaquinas;

    public ModeloJListaMaquinas() {
        inicializarValores();
    }

    public ModeloJListaMaquinas(List<Maquina> maquinas) {
        inicializarValores();
        listaDeMaquinas.addAll(maquinas);

    }
    
    
    private void inicializarValores(){
        listaDeMaquinas = new ArrayList<Maquina>();
    }

    public int getSize() {
        return listaDeMaquinas.size();

    }

    public Maquina getElementAt(int index) {
        return listaDeMaquinas.get(index);

    }

    public List<Maquina> getMaquinas(){

        return listaDeMaquinas;
    }

    public void removeMaquina(int indice){

        listaDeMaquinas.remove(indice);

    }

    public void addElement(Maquina maq){

        listaDeMaquinas.add(maq);

    }





}
