/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Demanda;
import Entidades.ProductoFinal;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.ExpertoABMDemanda;
import interfacesGraficas.ModeloTablas.ModeloTablaDemandas;
import interfacesGraficas.PantallaABMDemanda;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorABMDemanda {

    PantallaABMDemanda pantallaABMDemanda;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMDemanda expertoABMDemanda;
    //ModeloTablaDemandas modeloTablaDemandas;
    Demanda nuevaDemanda = null;
    Demanda demandaSeleccionada;

    public ControladorABMDemanda(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMDemanda = (ExpertoABMDemanda) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_DEMANDA);

    }

    public void iniciar() throws ExpertoCostosFijosException {
        pantallaABMDemanda = new PantallaABMDemanda(controladorPantMadre.getPantalla(), true, this);
        ModeloTablaDemandas modeloTablaDemandas = new ModeloTablaDemandas();
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
        pantallaABMDemanda.setLocationRelativeTo(null);
        List<ProductoFinal> listaproductos = expertoABMDemanda.buscarProductoFinal();
        pantallaABMDemanda.getComboListaProducto().setModel(new DefaultComboBoxModel(listaproductos.toArray()));
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
        ModeloTablaDemandas modeloTablaDemandas = (ModeloTablaDemandas) pantallaABMDemanda.getTablaDemandas().getModel();
        modeloTablaDemandas.removeElement(demandaSeleccionada);
        modeloTablaDemandas.fireTableDataChanged();
        pantallaABMDemanda.getCampoValorDemanda().setText("");
        pantallaABMDemanda.getBotonGuardar().setEnabled(true);
    }

    public void editarDemanda() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void guardarDemanda() {
        try {
            if (pantallaABMDemanda.getCampoValorDemanda().isEnabled()) {
                nuevaDemanda.setDemandaHistorica(Double.valueOf(pantallaABMDemanda.getCampoValorDemanda().getText()));
                expertoABMDemanda.guardarDemanda(nuevaDemanda);
                JOptionPane.showMessageDialog(pantallaABMDemanda, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                ModeloTablaDemandas modeloTablaDemandas = (ModeloTablaDemandas) pantallaABMDemanda.getTablaDemandas().getModel();
                modeloTablaDemandas.addRow(nuevaDemanda);
                pantallaABMDemanda.getCampoValorDemanda().setText("");
            } else {
                JOptionPane.showMessageDialog(pantallaABMDemanda, "Debe Ingresar Datos", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(pantallaABMDemanda, "El valor ingresado es incorrecto", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void imprimirDemanda() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void tabla(int fila, int click) {
        demandaSeleccionada = (Demanda) ((ModeloTablaDemandas) pantallaABMDemanda.getTablaDemandas().getModel()).getRow(fila);
        if (click == 2) {
            pantallaABMDemanda.getCampoValorDemanda().setEnabled(true);
            Demanda dem = ((Demanda) ((ModeloTablaDemandas) pantallaABMDemanda.getTablaDemandas().getModel()).getRow(fila));
            pantallaABMDemanda.getCampoValorDemanda().setText(String.valueOf(dem.getDemandaHistorica()));
            pantallaABMDemanda.getBotonGuardar().setEnabled(true);
        }
    }

    public void actualizarDemandas() {
        demandaSeleccionada.setDemandaHistorica(Double.valueOf(pantallaABMDemanda.getCampoValorDemanda().getText()));
        expertoABMDemanda.guardarDemanda(demandaSeleccionada);
        JOptionPane.showMessageDialog(pantallaABMDemanda, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        List<Demanda> dem = ((ProductoFinal) pantallaABMDemanda.getComboListaProducto().getSelectedItem()).getDemanda();
        ModeloTablaDemandas mod = new ModeloTablaDemandas();
        mod.setListaElementos(dem);
        pantallaABMDemanda.getTablaDemandas().setModel(mod);
        pantallaABMDemanda.getCampoValorDemanda().setText("");
        pantallaABMDemanda.getBotonGuardar().setEnabled(true);
    }

    public void buscarDemandas() {
        ProductoFinal prod = (ProductoFinal) pantallaABMDemanda.getComboListaProducto().getSelectedItem();
        ModeloTablaDemandas modeloTablaDemandas = new ModeloTablaDemandas();
        modeloTablaDemandas.setListaElementos(prod.getDemanda());
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
    }
}
