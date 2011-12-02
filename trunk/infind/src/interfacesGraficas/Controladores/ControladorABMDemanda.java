/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Demanda;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.ExpertoABMDemanda;
import interfacesGraficas.ModeloTablas.ModeloTablaDemandas;
import interfacesGraficas.PantallaABMDemanda;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorABMDemanda {

    PantallaABMDemanda pantallaABMDemanda;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMDemanda expertoABMDemanda;
    ModeloTablaDemandas modeloTablaDemandas;
    Demanda nuevaDemanda = null;
    Demanda demandaSeleccionada;

    public ControladorABMDemanda(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMDemanda = (ExpertoABMDemanda) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_DEMANDA);

    }

    public void iniciar() throws ExpertoCostosFijosException {
        pantallaABMDemanda = new PantallaABMDemanda(controladorPantMadre.getPantalla(), true, this);
        modeloTablaDemandas = new ModeloTablaDemandas();
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
        pantallaABMDemanda.getTablaDemandas().getColumnModel().getColumn(0).setPreferredWidth(1);
        modeloTablaDemandas.addAllRow(expertoABMDemanda.buscarDemandas());
        pantallaABMDemanda.setLocationRelativeTo(null);
        pantallaABMDemanda.setVisible(true);
    }

    public void nuevaDemanda() {
        pantallaABMDemanda.getCampoValorDemanda().setEnabled(true);
        pantallaABMDemanda.getjComboBoxPeriodo().setEnabled(true);
    }

    public void eliminarDemanda() {
        demandaSeleccionada.setEliminado(true);
        expertoABMDemanda.guardarDemanda(demandaSeleccionada);
        JOptionPane.showMessageDialog(pantallaABMDemanda, "Demanda eliminada Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        modeloTablaDemandas.removeElement(demandaSeleccionada);
        modeloTablaDemandas.fireTableDataChanged();
        pantallaABMDemanda.getCampoValorDemanda().setText("");
        pantallaABMDemanda.getBotonGuardar().setEnabled(true);
    }

    public void editarDemanda() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void guardarDemanda() {

        if (pantallaABMDemanda.getCampoValorDemanda().isEnabled()) {
            if (nuevaDemanda == null) {
                nuevaDemanda = new Demanda();
            }
            nuevaDemanda.setDemandaHistorica(Double.valueOf(pantallaABMDemanda.getCampoValorDemanda().getText()));
            nuevaDemanda.setPeriodo(Integer.valueOf(pantallaABMDemanda.getjComboBoxPeriodo().getSelectedItem().toString()));
            expertoABMDemanda.guardarDemanda(nuevaDemanda);
            JOptionPane.showMessageDialog(pantallaABMDemanda, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
            modeloTablaDemandas.addRow(nuevaDemanda);
            pantallaABMDemanda.getCampoValorDemanda().setText("");
        } else {
            JOptionPane.showMessageDialog(pantallaABMDemanda, "Debe Ingresar Datos", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void imprimirDemanda() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void tabla(int fila, int click) {
        Object periodo = pantallaABMDemanda.getTablaDemandas().getModel().getValueAt(fila, 1);
        
        demandaSeleccionada = modeloTablaDemandas.buscarDemandas(Integer.valueOf(periodo.toString()));

        if (click == 2) {
            pantallaABMDemanda.getCampoValorDemanda().setEnabled(true);
            pantallaABMDemanda.getBotonGuardar().setEnabled(false);
        }
    }

    public void actualizarDemandas() {
        demandaSeleccionada.setDemandaHistorica(Double.valueOf(pantallaABMDemanda.getCampoValorDemanda().getText()));
        demandaSeleccionada.setPeriodo(Integer.valueOf(pantallaABMDemanda.getjComboBoxPeriodo().getSelectedItem().toString()));
        expertoABMDemanda.guardarDemanda(demandaSeleccionada);
        JOptionPane.showMessageDialog(pantallaABMDemanda, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        modeloTablaDemandas.fireTableDataChanged();
        pantallaABMDemanda.getCampoValorDemanda().setText("");
        pantallaABMDemanda.getBotonGuardar().setEnabled(true);
    }
}
