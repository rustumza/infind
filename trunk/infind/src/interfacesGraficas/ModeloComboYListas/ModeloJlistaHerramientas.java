/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.Herramientas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author eduardo
 */
public class ModeloJlistaHerramientas extends AbstractListModel{

    private List<Herramientas> listaDeHerramientas;

    public ModeloJlistaHerramientas() {
        inicializarValores();
    }

    public ModeloJlistaHerramientas(List<Herramientas> herramientas) {
        inicializarValores();

        listaDeHerramientas.addAll(herramientas);

    }

    private void inicializarValores(){
        listaDeHerramientas = new ArrayList<Herramientas>();
    }

    public int getSize() {
        return listaDeHerramientas.size();

    }

    public Herramientas getElementAt(int index) {
        return listaDeHerramientas.get(index);

    }

    public List<Herramientas> getHerramientas(){

        return listaDeHerramientas;
    }

    public void removeHerramientas(int indice){

        listaDeHerramientas.remove(indice);

    }

    public void addElement(Herramientas matPrim){

        listaDeHerramientas.add(matPrim);

    }





}
