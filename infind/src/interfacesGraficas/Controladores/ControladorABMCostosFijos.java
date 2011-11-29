/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.CostosFijos;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.Experto;
import expertos.ExpertoABMCostosFijos;
import interfacesGraficas.ModeloTablas.ModeloTablaCostosFijos;
import interfacesGraficas.PantallaABMCostosFijos;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class ControladorABMCostosFijos {

    PantallaABMCostosFijos pantallaABMCostosFijos;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMCostosFijos expertoABMCostosFijos;
    ModeloTablaCostosFijos modeloTablaCostosFijos;
    CostosFijos nuevoCosto = null;
    CostosFijos costoSeleccionado;

    public ControladorABMCostosFijos(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMCostosFijos = (ExpertoABMCostosFijos) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_FIJOS);

    }

    public void iniciar() throws ExpertoCostosFijosException {
        pantallaABMCostosFijos = new PantallaABMCostosFijos(controladorPantMadre.getPantalla(), true, this);
        modeloTablaCostosFijos = new ModeloTablaCostosFijos();
        pantallaABMCostosFijos.getTablaCostosFijos().setModel(modeloTablaCostosFijos);
        pantallaABMCostosFijos.getTablaCostosFijos().getColumnModel().getColumn(0).setPreferredWidth(1);
        modeloTablaCostosFijos.addAllRow(expertoABMCostosFijos.buscarCostosFijos());
        for (int i = 0; i < modeloTablaCostosFijos.getListaElementos().size(); i++) {
            modeloTablaCostosFijos.getListaElementos().get(i).toString();
        }
        pantallaABMCostosFijos.setLocationRelativeTo(null);
        pantallaABMCostosFijos.setVisible(true);




    }

    public void nuevoCostoFijo() {
        pantallaABMCostosFijos.getCampoNombreCosto().setEnabled(true);
        pantallaABMCostosFijos.getCampoValorCosto().setEnabled(true);

    }

    public void eliminarCosto() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void editarCosto() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void guardarCostoFijo() {

        if (pantallaABMCostosFijos.getCampoNombreCosto().isEnabled()) {
            if (nuevoCosto == null) {
                nuevoCosto = new CostosFijos();

            }

            nuevoCosto.setNombre(pantallaABMCostosFijos.getCampoNombreCosto().getText());
            nuevoCosto.setCosto(Double.valueOf(pantallaABMCostosFijos.getCampoValorCosto().getText()));

            expertoABMCostosFijos.guardarNuevoCosto(nuevoCosto);
            JOptionPane.showMessageDialog(pantallaABMCostosFijos, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            modeloTablaCostosFijos.addRow(nuevoCosto);
            pantallaABMCostosFijos.getCampoNombreCosto().setText("");
            pantallaABMCostosFijos.getCampoValorCosto().setText("");
            pantallaABMCostosFijos.getCampoNombreCosto().setEnabled(false);
            pantallaABMCostosFijos.getCampoValorCosto().setEnabled(false);
            nuevoCosto = null;


        } else {

            JOptionPane.showMessageDialog(pantallaABMCostosFijos, "Debe Ingresar Datos", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        }
    }

    public void imprimirCostos() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void tabla(int fila, int click) {

        costoSeleccionado = modeloTablaCostosFijos.buscarCostosFijos(pantallaABMCostosFijos.getTablaCostosFijos().getValueAt(fila, 1).toString());

        if (click == 2) {

            pantallaABMCostosFijos.getCampoNombreCosto().setEnabled(true);
            pantallaABMCostosFijos.getCampoValorCosto().setEnabled(true);
            pantallaABMCostosFijos.getCampoNombreCosto().setText(costoSeleccionado.getNombre());
            pantallaABMCostosFijos.getCampoValorCosto().setText(String.valueOf(costoSeleccionado.getCosto()));
            pantallaABMCostosFijos.getBotonActualizar().setEnabled(true);
            pantallaABMCostosFijos.getBotonGuardar().setEnabled(false);
            

        }


    }

    public void actualizarCostos() {
        costoSeleccionado.setCosto(Double.valueOf(pantallaABMCostosFijos.getCampoValorCosto().getText()));
        expertoABMCostosFijos.guardarNuevoCosto(costoSeleccionado);
        JOptionPane.showMessageDialog(pantallaABMCostosFijos, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

        //modeloTablaCostosFijos.addRow(costoSeleccionado);
        modeloTablaCostosFijos.fireTableDataChanged();
        pantallaABMCostosFijos.getCampoNombreCosto().setText("");
        pantallaABMCostosFijos.getCampoValorCosto().setText("");
        pantallaABMCostosFijos.getCampoNombreCosto().setEnabled(false);
        pantallaABMCostosFijos.getCampoValorCosto().setEnabled(false);
        pantallaABMCostosFijos.getBotonActualizar().setEnabled(false);
        pantallaABMCostosFijos.getBotonGuardar().setEnabled(true);
        
    }
}
