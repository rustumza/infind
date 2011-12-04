/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOPromedioPonderadoMovil;
import Entidades.Demanda;
import Entidades.MaestroDeArticulo;
import Entidades.Parametros;
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
import javax.swing.DefaultComboBoxModel;
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
    List<MaestroDeArticulo> articulos;
    

    public ControladorCalcularDemanda(ControladorPantallaMadre controlador) {
        controladorPantMadre = controlador;
        expertoCalcularDemanda = (ExpertoCalcularDemanda) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.CALCULAR_DEMANDA);
        pantallaMadre = controladorPantMadre.getPantalla();
        pantallaCalcularDemanda = new PantallaCalcularDemanda(pantallaMadre, false, this);

        pantallaCalcularDemanda.getBotonCalcular().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buscarDemandas();
                calcularPPMovil();
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
        articulos = expertoCalcularDemanda.buscarProductos();
        pantallaCalcularDemanda.getjComboBoxListaProductos().setModel(new DefaultComboBoxModel(articulos.toArray()));
    }

    public void buscarDemandas() {
        MaestroDeArticulo prod = (MaestroDeArticulo) pantallaCalcularDemanda.getjComboBoxListaProductos().getSelectedItem();
        ModeloTablaCalcularDemandas modeloTablaCalcularDemandas = new ModeloTablaCalcularDemandas();
        modeloTablaCalcularDemandas.setListaElementos(prod.getDemanda());
        pantallaCalcularDemanda.getTablaCalculosDemanda().setModel(modeloTablaCalcularDemandas);
    }
    
    public void calcularPPMovil(){
        MaestroDeArticulo productoSeleccionado = (MaestroDeArticulo) pantallaCalcularDemanda.getjComboBoxListaProductos().getSelectedItem();
        List<Demanda> demanda = productoSeleccionado.getDemanda();
        double factor = 0.1;// Todavia falta esto (Double.parseDouble(pantallaCalcularDemanda.getjLabelFactor2().getText()));
        int incremento = (int) factor;
        int periodoAPredecir = (Integer.parseInt(pantallaCalcularDemanda.getjLabelPeriodos2().getText()));
        int periodoInicial = demanda.size() - periodoAPredecir;
        int periodoFinal = demanda.size();
        List<DTOPromedioPonderadoMovil> dtoPPMovil = new ArrayList<DTOPromedioPonderadoMovil>();
        for (int i = periodoInicial; i < periodoFinal; i++) {
            //obtener las demandas de los periodos y multiplicrlas por 
            // el factor, dar una vuelta mas del for e incrementar el factor
            dtoPPMovil.get(i).setDemandaPronosticada(incremento *demanda.get(i).getDemandaHistorica());
            incremento+= factor;
            System.out.println("Demanda con Promedio Movil Ponderado: " + dtoPPMovil.get(i).getDemandaPronosticada());
        }
    }
}
