/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.Demanda;
import Entidades.Parametros;
import Entidades.ProductoFinal;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCalcularDemandaException;
import expertos.ExpertoCalcularDemanda;
import interfacesGraficas.ModeloTablas.ModeloTablaCalcularDemandas;
import interfacesGraficas.PantallaCalcularDemanda;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class ControladorCalcularDemanda {

    PantallaCalcularDemanda pantallaCalcularDemanda;
    PantallaMadre pantallaMadre;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoCalcularDemanda expertoCalcularDemanda;
    Demanda nuevaDemanda = null;
    List<Parametros> parametros = null;

    public ControladorCalcularDemanda(ControladorPantallaMadre controlador) {
        controladorPantMadre = controlador;
        expertoCalcularDemanda = (ExpertoCalcularDemanda) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.CALCULAR_DEMANDA);
        pantallaMadre = controladorPantMadre.getPantalla();
        pantallaCalcularDemanda = new PantallaCalcularDemanda(pantallaMadre, false, this);

        pantallaCalcularDemanda.getBotonCalcular().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buscarDemandas();
            }
        });
    }

    public void iniciar() throws ExpertoCalcularDemandaException {
        int seleccion = JOptionPane.showOptionDialog(null,
                "Establecio los parametros del sistema de prediccion??",
                "Calcular Demanda",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Si", "No"}, // null para YES, NO y CANCEL
                "Cancelar");
        if (seleccion == 0) {
            pantallaCalcularDemanda = new PantallaCalcularDemanda(controladorPantMadre.getPantalla(), false, this);
            pantallaCalcularDemanda.setLocationRelativeTo(null);
            pantallaCalcularDemanda.setVisible(true);
        }
        buscarParametros();
    }

    public void guardar() {
//        nuevoParametros = new Parametros();
//        nuevoParametros.setAlfa(Double.valueOf(pantallaCalcularDemanda.getjComboBoxAlfa().getSelectedItem().toString()));
//        nuevoParametros.setBeta(Double.valueOf(pantallaCalcularDemanda.getjComboBoxBeta().getSelectedItem().toString()));
//        nuevoParametros.setGama(Double.valueOf(pantallaCalcularDemanda.getjComboBoxGama().getSelectedItem().toString()));
//        nuevoParametros.setPeriodosAPredecir(Integer.valueOf(pantallaCalcularDemanda.getComboBoxPeriodos().getSelectedItem().toString()));
//        nuevoParametros.setErrorAceptable(Double.valueOf(pantallaCalcularDemanda.getjComboBoxError().getSelectedItem().toString()));
//        nuevoParametros.setMetodo(pantallaCalcularDemanda.getjComboBoxMetodo().getSelectedItem().toString());
//        expertoCalcularDemanda.guardarParametros(nuevoParametros);
//        JOptionPane.showMessageDialog(pantallaCalcularDemanda, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscarParametros() throws ExpertoCalcularDemandaException {
        parametros = new ArrayList<Parametros>();
        parametros = expertoCalcularDemanda.buscarParametros();
        int ultimo = parametros.size()-1;
        pantallaCalcularDemanda.getjLabelAlfa2().setText(String.valueOf(parametros.get(ultimo).getAlfa()));
        pantallaCalcularDemanda.getjLabelBeta2().setText(String.valueOf(parametros.get(ultimo).getBeta()));
        pantallaCalcularDemanda.getjLabelGama2().setText(String.valueOf(parametros.get(ultimo).getGama()));
        pantallaCalcularDemanda.getjLabelPeriodos2().setText(String.valueOf(parametros.get(ultimo).getPeriodosAPredecir()));
        pantallaCalcularDemanda.getjLabelError2().setText(String.valueOf(parametros.get(ultimo).getErrorAceptable()));
        pantallaCalcularDemanda.getjLabelMetodo2().setText(String.valueOf(parametros.get(ultimo).getMetodo()));
        
    }

    public void buscarDemandas() {
        ProductoFinal prod = (ProductoFinal) pantallaCalcularDemanda.getjComboBoxListaProductos().getSelectedItem();
        ModeloTablaCalcularDemandas modeloTablaCalcularDemandas = new ModeloTablaCalcularDemandas();
        modeloTablaCalcularDemandas.setListaElementos(prod.getDemanda());
        pantallaCalcularDemanda.getTablaCalculosDemanda().setModel(modeloTablaCalcularDemandas);
    }
}
