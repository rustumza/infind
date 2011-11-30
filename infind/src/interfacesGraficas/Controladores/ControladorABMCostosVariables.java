/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCostosVariables;
import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.ProductoIntermedio;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosVariablesException;
import expertos.ExpertoABMCostosVariables;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxCentroDeTrabajo;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxMateriaPrima;
import interfacesGraficas.ModeloTablas.ModeloTablaCentroDeTrabajo;
import interfacesGraficas.ModeloTablas.ModeloTablaMateriasPrimas;
import interfacesGraficas.PantallaABMCostosVariables;
import java.util.List;
import javax.swing.JOptionPane;

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
    private MaestroDeArticulo productoIntermedioBase;

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

    public void crearCostoVariable() throws ExpertoCostosVariablesException {
        pantallaABMCostosVariables.getBotonAgregarCentro().setEnabled(true);
        pantallaABMCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);

        Object Item = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();


        if (Item.toString() == "Producto Intermedio Base") {
            
            
            
        }else{
            //busco el producto intermedio base
            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();
            //busco los costos variables del producto intermedio bas
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);
            
            if (costosVariablesEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(pantallaABMCostosVariables, "El Producto Intermedio Base no tiene costos variables ingresados", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
                DTOCostosVariables dtoCostos = new DTOCostosVariables();
                //ingreso los costos variables del producto intermedio base
                for (CostoVariable costoVariable : costosVariablesEncontrados) {
                    
                    dtoCostos.setNombreProducto(costoVariable.getNombre());
                    dtoCostos.setCantidad(String.valueOf(costoVariable.getCantidad()));
                    dtoCostos.setCostoUnitario(String.valueOf(costoVariable.getCosto()));
                    modeloTablaMateriasPrimas.addRow(dtoCostos);
                }
                
                modeloTablaMateriasPrimas.fireTableDataChanged();
                
            }
        }
        
        
    }

    public void cargarTablaMateriasPrimas() {
        DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();
        MaestroDeArticulo maestroArtSeleccionado = modeloComboBoxMateriaPrima.getMaestroArtSeleccionado();
        dtoCostosACargar.setNombreProducto(maestroArtSeleccionado.getNombre());
        dtoCostosACargar.setCantidad(pantallaABMCostosVariables.getCampoCAntidad().getText());
        dtoCostosACargar.setCostoUnitario(String.valueOf(maestroArtSeleccionado.getCostoUnitarioPorOmision()));
        modeloTablaMateriasPrimas.addRow(dtoCostosACargar);
    }

    public void cargarTablaCentroDeTrabajo() {
        MaestroDeCentroDeTrabajo centrosACargar = modeloComboBoxCentroTrabajo.getCentroTrabajoSeleccionado();
        modeloTablaCentroTrabajo.addRow(centrosACargar);
    }
}
