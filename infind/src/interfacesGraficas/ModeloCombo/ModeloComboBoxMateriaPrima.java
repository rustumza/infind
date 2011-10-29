/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.ModeloCombo;



import Entidades.MateriaPrima;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author eduardo
 */
public class ModeloComboBoxMateriaPrima extends DefaultComboBoxModel{

    private List<MateriaPrima> listaCategoria;


    public ModeloComboBoxMateriaPrima() {
        addElement("Seleccione una Categoria");
    }

    public ModeloComboBoxMateriaPrima(List<MateriaPrima> nuevalista) {

        listaCategoria = nuevalista;
        addElement("Seleccione una Categoria");
        for (MateriaPrima materia : nuevalista) {
            addElement(materia.getNombre());

        }

    }

    public void setListaCombo(List<MateriaPrima> nuevalista) {
         listaCategoria = nuevalista;
        for (MateriaPrima materia : nuevalista) {
            addElement(materia.getNombre());

        }

    }

    public ModeloComboBoxMateriaPrima(ModeloComboBoxMateriaPrima modelo) {
        addElement("Seleccione una Categoria");
        listaCategoria = modelo.getListaCategoria();
        for (MateriaPrima materia : listaCategoria) {
            addElement(materia.getNombre());

        }
    }

    public ModeloComboBoxMateriaPrima(ComboBoxModel modelo) {
        addElement("Seleccione una Categoria");
        listaCategoria = ((ModeloComboBoxMateriaPrima) modelo).getListaCategoria();
        for (MateriaPrima materia : listaCategoria) {
            addElement(materia.getNombre());

        }
    }

    public MateriaPrima getCategoriaSeleccionada() {
        MateriaPrima categoriaEncontrada = null;
        for (MateriaPrima materia : listaCategoria) {
            if (materia.getNombre().equals(super.getSelectedItem().toString())) {
                categoriaEncontrada = materia;
            }
        }

        return categoriaEncontrada;

    }

    public List<MateriaPrima> getListaCategoria() {
        return listaCategoria;
    }

    public void setCategoriaSeleccionada(MateriaPrima estado) {
        if (listaCategoria != null) {
            for (MateriaPrima categoria : listaCategoria) {
                if (categoria.getNombre().equals(estado.getNombre())) {
                    setSelectedItem(estado.getNombre());
                }
            }
        }
    }


}
