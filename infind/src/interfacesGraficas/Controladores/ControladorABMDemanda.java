/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Demanda;
import Entidades.MaestroDeArticulo;
import Entidades.ProductoFinal;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoABMDemandaExcepcion;
import expertos.ExpertoABMDemanda;
import interfacesGraficas.ModeloTablas.ModeloTablaDemandas;
import interfacesGraficas.PantallaABMDemanda;
import java.util.List;
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
    Demanda nuevaDemanda = null;
    Demanda demandaSeleccionada;
    List<ProductoFinal> listaproductos;

    public ControladorABMDemanda(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMDemanda = (ExpertoABMDemanda) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_DEMANDA);

    }

    public void iniciar() throws ExpertoABMDemandaExcepcion {
        pantallaABMDemanda = new PantallaABMDemanda(controladorPantMadre.getPantalla(), true, this);
        ModeloTablaDemandas modeloTablaDemandas = new ModeloTablaDemandas();
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
        pantallaABMDemanda.setLocationRelativeTo(null);
        listaproductos = expertoABMDemanda.buscarProductoFinal();
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

    public void guardarDemanda() {
        try {
            if (pantallaABMDemanda.getCampoValorDemanda().isEnabled()) {
                nuevaDemanda = new Demanda();
                nuevaDemanda.setDemandaHistorica(Double.valueOf(pantallaABMDemanda.getCampoValorDemanda().getText()));
                nuevaDemanda.setPeriodo(Integer.valueOf(pantallaABMDemanda.getjComboBoxPeriodo().getSelectedItem().toString()));
                ((MaestroDeArticulo) pantallaABMDemanda.getComboListaProducto().getSelectedItem()).addDemanda(nuevaDemanda);
                expertoABMDemanda.guardarDemanda(nuevaDemanda);
                expertoABMDemanda.guardarProductoFinal((ProductoFinal)nuevaDemanda.getArticulo());
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

    public void buscarDemandas() {
        ProductoFinal prod = (ProductoFinal) pantallaABMDemanda.getComboListaProducto().getSelectedItem();
        ModeloTablaDemandas modeloTablaDemandas = new ModeloTablaDemandas();
        modeloTablaDemandas.setListaElementos(prod.getDemanda());
        pantallaABMDemanda.getTablaDemandas().setModel(modeloTablaDemandas);
    }
}
