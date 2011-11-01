/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.Operario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author eduardo
 */
public class ModeloJListaOperarios extends AbstractListModel{

    private List<Operario> listaDeOperarios;

    public ModeloJListaOperarios() {
        inicializarValores();
    }

    public ModeloJListaOperarios(List<Operario> operarios) {
        inicializarValores();
        listaDeOperarios.addAll(operarios);

    }

    private void inicializarValores(){
        listaDeOperarios = new ArrayList<Operario>();
    }

    public int getSize() {
        return listaDeOperarios.size();

    }

    public Operario getElementAt(int index) {
        return listaDeOperarios.get(index);

    }

    public List<Operario> getOperarios(){

        return listaDeOperarios;
    }

    public void removeOperarios(int indice){

        listaDeOperarios.remove(indice);

    }

    public void addElement(Operario oper){

        listaDeOperarios.add(oper);

    }





}
