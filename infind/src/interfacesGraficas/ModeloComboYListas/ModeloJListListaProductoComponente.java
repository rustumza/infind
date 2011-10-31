/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloComboYListas;

import Entidades.ProductoComponente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author rustu
 */
public class ModeloJListListaProductoComponente extends AbstractListModel{

    private List<ProductoComponente> listaDeProductosComponentes;

    public ModeloJListListaProductoComponente() {
        inicializarValores();
    }

    public ModeloJListListaProductoComponente(List<ProductoComponente> productosComponentes) {
        inicializarValores();
        listaDeProductosComponentes.addAll(productosComponentes);

    }

    private void inicializarValores(){
        listaDeProductosComponentes = new ArrayList<ProductoComponente>();
    }

    public int getSize() {
        return listaDeProductosComponentes.size();

    }

    public ProductoComponente getElementAt(int index) {
        return listaDeProductosComponentes.get(index);

    }

    public List<ProductoComponente> getProductosComponentes(){

        return listaDeProductosComponentes;
    }

    public void removeProductoComponente(int indice){

        listaDeProductosComponentes.remove(indice);

    }

    public void addElement(ProductoComponente prodComp){

        listaDeProductosComponentes.add(prodComp);

    }





}
