/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.CostoVAriable;
import Entidades.MaestroDeArticulo;
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
    CostoVAriable nuevoCosto = null;

    public ControladorABMCostosVariables(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        
        
        expertoABMCostosVariables = (ExpertoABMCostosVariables) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_VARIABLES);
        
        
    }

    public void iniciar() throws ExpertoCostosVariablesException {
        pantallaABMCostosVariables = new PantallaABMCostosVariables(controladorPantMadre.getPantalla(), true, this);
        modeloComboBoxCentroTrabajo = new ModeloComboBoxCentroDeTrabajo();
        modeloComboBoxMateriaPrima = new ModeloComboBoxMateriaPrima();
        List<MaestroDeArticulo> buscarMaestroArticulo = expertoABMCostosVariables.buscarMaestroArticulo();
        for (MaestroDeArticulo maestroDeArticulo : buscarMaestroArticulo) {
            modeloComboBoxMateriaPrima.addElement(maestroDeArticulo);
        }
        
        modeloTablaCentroTrabajo = new ModeloTablaCentroDeTrabajo();
        modeloTablaMateriasPrimas = new ModeloTablaMateriasPrimas();
        
        pantallaABMCostosVariables.getTablaCentroTrabajo().setModel(modeloTablaCentroTrabajo);
        pantallaABMCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimas);
        pantallaABMCostosVariables.getComboBoxCentroTrabajo().setModel(modeloComboBoxCentroTrabajo);
        pantallaABMCostosVariables.getComboBoxMateriaPrima().setModel(modeloComboBoxMateriaPrima);
        
        
        // modeloTablaCostosFijos = new ModeloTablaCostosFijos();
        // pantallaABMCostosVariables.getTablaCostosFijos().setModel(modeloTablaCostosFijos);
        // pantallaABMCostosVariables.getTablaCostosFijos().getColumnModel().getColumn(0).setPreferredWidth(1);
        // modeloTablaCostosFijos.addAllRow(expertoABMCostosVariables.buscarCostosFijos());
        pantallaABMCostosVariables.setLocationRelativeTo(null);
        pantallaABMCostosVariables.setVisible(true);



    }
}
