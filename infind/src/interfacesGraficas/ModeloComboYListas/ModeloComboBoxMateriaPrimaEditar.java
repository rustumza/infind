/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloComboYListas;

import Entidades.MaestroDeArticulo;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author eduardo
 */
public class ModeloComboBoxMateriaPrimaEditar extends DefaultComboBoxModel{

    private List<MaestroDeArticulo> listaMateriaPrima;


    public ModeloComboBoxMateriaPrimaEditar() {
        addElement("Seleccione una Materia Prima");
    }

    public ModeloComboBoxMateriaPrimaEditar(List<MaestroDeArticulo> nuevalista) {

        listaMateriaPrima = nuevalista;
        addElement("Seleccione Una Materia Prima");
        for (MaestroDeArticulo tipo : nuevalista) {
            addElement(tipo.getNombre());

        }

    }

    public void setListaCombo(List<MaestroDeArticulo> nuevalista) {
         listaMateriaPrima = nuevalista;
        for (MaestroDeArticulo tipo : nuevalista) {
            addElement(tipo.getNombre());

        }

    }

    public ModeloComboBoxMateriaPrimaEditar(ModeloComboBoxMateriaPrimaEditar modelo) {
        addElement("Seleccione un tipo");
        listaMateriaPrima = modelo.getListaMateriasPrimas();
        for (MaestroDeArticulo tipo : listaMateriaPrima) {
            addElement(tipo.getNombre());

        }
    }

    public ModeloComboBoxMateriaPrimaEditar(ComboBoxModel modelo) {
        addElement("Seleccione un tipo");
        listaMateriaPrima = ((ModeloComboBoxMateriaPrima) modelo).getListaPuesto();
        for (MaestroDeArticulo tipo : listaMateriaPrima) {
            addElement(tipo.getNombre());

        }
    }

    public MaestroDeArticulo getMaestroArtSeleccionado() {
        MaestroDeArticulo materiaPrimaEncontrado = null;
        for (MaestroDeArticulo tipoOpera : listaMateriaPrima) {
            if (tipoOpera.getNombre().equals(super.getSelectedItem().toString())) {
                materiaPrimaEncontrado = tipoOpera;
            }
        }

        return materiaPrimaEncontrado;

    }

    public List<MaestroDeArticulo> getListaMateriasPrimas() {
        return listaMateriaPrima;
    }

    public void setMaestroArtSeleccionado(MaestroDeArticulo estado) {
        if (listaMateriaPrima != null) {
            for (MaestroDeArticulo puesto : listaMateriaPrima) {
                if (puesto.getNombre().equals(estado.getNombre())) {
                    setSelectedItem(estado.getNombre());
                }
            }
        }
    }


}
