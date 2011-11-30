/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.ModeloComboYListas;

import Entidades.MaestroDeCentroDeTrabajo;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author eduardo
 */
public class ModeloComboBoxCentroDeTrabajoEditar extends DefaultComboBoxModel{

    private List<MaestroDeCentroDeTrabajo> listaPuesto;


    public ModeloComboBoxCentroDeTrabajoEditar() {
        addElement("Seleccione un Centro de Trabajo");
    }

    public ModeloComboBoxCentroDeTrabajoEditar(List<MaestroDeCentroDeTrabajo> nuevalista) {

        listaPuesto = nuevalista;
        addElement("Seleccione Un Centro de Trabajo");
        for (MaestroDeCentroDeTrabajo tipo : nuevalista) {
            addElement(tipo.getNombreCentro());

        }

    }

    public void setListaCombo(List<MaestroDeCentroDeTrabajo> nuevalista) {
         listaPuesto = nuevalista;
        for (MaestroDeCentroDeTrabajo tipo : nuevalista) {
            addElement(tipo.getNombreCentro());

        }

    }

    public ModeloComboBoxCentroDeTrabajoEditar(ModeloComboBoxCentroDeTrabajoEditar modelo) {
        addElement("Seleccione un tipo");
        listaPuesto = modelo.getListaCentrosTrabajo();
        for (MaestroDeCentroDeTrabajo tipo : listaPuesto) {
            addElement(tipo.getNombreCentro());

        }
    }

    public ModeloComboBoxCentroDeTrabajoEditar(ComboBoxModel modelo) {
        addElement("Seleccione un tipo");
        listaPuesto = ((ModeloComboBoxCentroDeTrabajo) modelo).getListaPuesto();
        for (MaestroDeCentroDeTrabajo tipo : listaPuesto) {
            addElement(tipo.getNombreCentro());

        }
    }

    public MaestroDeCentroDeTrabajo getCentroTrabajoSeleccionado() {
        MaestroDeCentroDeTrabajo puestoEncontrado = null;
        for (MaestroDeCentroDeTrabajo tipoOpera : listaPuesto) {
            if (tipoOpera.getNombreCentro().equals(super.getSelectedItem().toString())) {
                puestoEncontrado = tipoOpera;
            }
        }

        return puestoEncontrado;

    }

    public List<MaestroDeCentroDeTrabajo> getListaCentrosTrabajo() {
        return listaPuesto;
    }

    public void setCentroTrabajoSeleccionado(MaestroDeCentroDeTrabajo estado) {
        if (listaPuesto != null) {
            for (MaestroDeCentroDeTrabajo puesto : listaPuesto) {
                if (puesto.getNombreCentro().equals(estado.getNombreCentro())) {
                    setSelectedItem(estado.getNombreCentro());
                }
            }
        }
    }


}

