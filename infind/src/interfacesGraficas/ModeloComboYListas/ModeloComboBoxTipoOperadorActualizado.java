/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloComboYListas;

import Entidades.TipoOperario;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author edu
 */
public class ModeloComboBoxTipoOperadorActualizado extends DefaultComboBoxModel{

    private List<TipoOperario> listaPuesto;


    public ModeloComboBoxTipoOperadorActualizado() {
        addElement("Seleccione un Puesto");
    }

    public ModeloComboBoxTipoOperadorActualizado(List<TipoOperario> nuevalista) {

        listaPuesto = nuevalista;
        addElement("Seleccione Un Puesto");
        for (TipoOperario tipo : nuevalista) {
            addElement(tipo.getNombre());

        }

    }

    public void setListaCombo(List<TipoOperario> nuevalista) {
         listaPuesto = nuevalista;
        for (TipoOperario tipo : nuevalista) {
            addElement(tipo.getNombre());

        }

    }

    public ModeloComboBoxTipoOperadorActualizado(ModeloComboBoxTipoOperador modelo) {
        addElement("Seleccione un tipo");
        listaPuesto = modelo.getListaPuesto();
        for (TipoOperario tipo : listaPuesto) {
            addElement(tipo.getNombre());

        }
    }

    public ModeloComboBoxTipoOperadorActualizado(ComboBoxModel modelo) {
        addElement("Seleccione un tipo");
        listaPuesto = ((ModeloComboBoxTipoOperador) modelo).getListaPuesto();
        for (TipoOperario tipo : listaPuesto) {
            addElement(tipo.getNombre());

        }
    }

    public TipoOperario getPuestoSeleccionado() {
        TipoOperario puestoEncontrado = null;
        for (TipoOperario tipoOpera : listaPuesto) {
            if (tipoOpera.getNombre().equals(super.getSelectedItem().toString())) {
                puestoEncontrado = tipoOpera;
            }
        }

        return puestoEncontrado;

    }

    public List<TipoOperario> getListaPuesto() {
        return listaPuesto;
    }

    public void setPuestoSeleccionado(TipoOperario estado) {
        if (listaPuesto != null) {
            for (TipoOperario puesto : listaPuesto) {
                if (puesto.getNombre().equals(estado.getNombre())) {
                    setSelectedItem(estado.getNombre());
                }
            }
        }
    }


    
}
