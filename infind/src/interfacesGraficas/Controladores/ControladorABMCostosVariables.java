/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosVariablesException;
import expertos.ExpertoABMCostosVariables;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxCentroDeTrabajo;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxMateriaPrima;
import interfacesGraficas.ModeloTablas.ModeloTablaCentroDeTrabajo;
import interfacesGraficas.ModeloTablas.ModeloTablaMateriasPrimas;
import interfacesGraficas.PantallaABMCostosVariables;
import java.util.List;

/**
 *
 * @author edu
 */
public class ControladorABMCostosVariables {

    PantallaABMCostosVariables pantallaABMCostosVariables;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMCostosVariables expertoABMCostosVariables;
    ModeloTablaMateriasPrimas modeloTablaMateriasPrimas;
    ModeloTablaCentroDeTrabajo modeloTablaCentroTrabajo;
    ModeloComboBoxCentroDeTrabajo modeloComboBoxCentroTrabajo;
    ModeloComboBoxMateriaPrima modeloComboBoxMateriaPrima;
    CostoVariable nuevoCosto = null;

    public ControladorABMCostosVariables(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMCostosVariables = (ExpertoABMCostosVariables) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_VARIABLES);
        
    }

    public void iniciar() throws ExpertoCostosVariablesException {
        pantallaABMCostosVariables = new PantallaABMCostosVariables(controladorPantMadre.getPantalla(), true, this);
        modeloComboBoxCentroTrabajo = new ModeloComboBoxCentroDeTrabajo();
        modeloComboBoxMateriaPrima = new ModeloComboBoxMateriaPrima();
        List<MaestroDeArticulo> maestroArticuloEncontrados = expertoABMCostosVariables.buscarMaestroArticulo();
        for (MaestroDeArticulo maestroDeArticulo : maestroArticuloEncontrados) {
            modeloComboBoxMateriaPrima.addElement(maestroDeArticulo);
        }
        
        List<MaestroDeCentroDeTrabajo> centrosEncontrados = expertoABMCostosVariables.buscarCentroDeTrabajo();
        for (MaestroDeCentroDeTrabajo maestroDeCentroDeTrabajo : centrosEncontrados) {
            modeloComboBoxCentroTrabajo.addElement(maestroDeCentroDeTrabajo);
        }
        
        modeloTablaCentroTrabajo = new ModeloTablaCentroDeTrabajo();
        modeloTablaMateriasPrimas = new ModeloTablaMateriasPrimas();
        pantallaABMCostosVariables.getTablaCentroTrabajo().setModel(modeloTablaCentroTrabajo);
        pantallaABMCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimas);
        pantallaABMCostosVariables.getComboBoxCentroTrabajo().setModel(modeloComboBoxCentroTrabajo);
        pantallaABMCostosVariables.getComboBoxMateriaPrima().setModel(modeloComboBoxMateriaPrima);
        pantallaABMCostosVariables.setLocationRelativeTo(null);
        pantallaABMCostosVariables.setVisible(true);



    }
}
