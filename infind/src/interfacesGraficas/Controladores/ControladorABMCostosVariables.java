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
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxCentroDeTrabajoEditar;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxMateriaPrima;
import interfacesGraficas.ModeloComboYListas.ModeloComboBoxMateriaPrimaEditar;
import interfacesGraficas.ModeloTablas.ModeloTablaCentroDeTrabajo;
import interfacesGraficas.ModeloTablas.ModeloTablaCentroDeTrabajoEditar;
import interfacesGraficas.ModeloTablas.ModeloTablaMateriasPrimas;
import interfacesGraficas.ModeloTablas.ModeloTablaMateriasPrimasEditar;
import interfacesGraficas.PantallaABMCostosVariables;
import interfacesGraficas.PantallaListarCostosVariables;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author edu
 */
public class ControladorABMCostosVariables {

    PantallaABMCostosVariables pantallaABMCostosVariables;
    PantallaListarCostosVariables pantallaListarCostosVariables;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMCostosVariables expertoABMCostosVariables;
    ModeloTablaMateriasPrimas modeloTablaMateriasPrimas;
    ModeloTablaMateriasPrimasEditar modeloTablaMateriasPrimasEditar;
    ModeloTablaCentroDeTrabajo modeloTablaCentroTrabajo;
    ModeloTablaCentroDeTrabajoEditar modeloTablaCentroTrabajoEditar;
    ModeloComboBoxCentroDeTrabajo modeloComboBoxCentroTrabajo;
    ModeloComboBoxCentroDeTrabajoEditar modeloComboBoxCentroTrabajoEditar;
    ModeloComboBoxMateriaPrima modeloComboBoxMateriaPrima;
    ModeloComboBoxMateriaPrimaEditar modeloComboBoxMateriaPrimaEditar;
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
        pantallaABMCostosVariables.getBotonEliminarCentro().setEnabled(true);
        pantallaABMCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);

        Object Item = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();


        if (Item.toString() == "Producto Intermedio Base") {
        } else {
            //busco el producto intermedio base
            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();
            //busco los costos variables del producto intermedio bas
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);

            if (costosVariablesEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(pantallaABMCostosVariables, "El Producto Intermedio Base no tiene costos variables ingresados", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            } else {
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

    public void guardarCostosVariables() {
        
        
        
        
        
        //guarda los costos variables del producto seleccionado
    }

    public void listarCostosVariables() throws ExpertoCostosVariablesException {
        pantallaListarCostosVariables = new PantallaListarCostosVariables(controladorPantMadre.getPantalla(), true, this);
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar();
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar();
        List<MaestroDeArticulo> maestroArticuloEncontrados = expertoABMCostosVariables.buscarMaestroArticulo();
        for (MaestroDeArticulo maestroDeArticulo : maestroArticuloEncontrados) {
            modeloComboBoxMateriaPrimaEditar.addElement(maestroDeArticulo);
        }

        List<MaestroDeCentroDeTrabajo> centrosEncontrados = expertoABMCostosVariables.buscarCentroDeTrabajo();
        for (MaestroDeCentroDeTrabajo maestroDeCentroDeTrabajo : centrosEncontrados) {
            modeloComboBoxCentroTrabajoEditar.addElement(maestroDeCentroDeTrabajo);
        }

        modeloTablaCentroTrabajoEditar = new ModeloTablaCentroDeTrabajoEditar();
        modeloTablaMateriasPrimasEditar = new ModeloTablaMateriasPrimasEditar();
        pantallaListarCostosVariables.getTablaCentroTrabajo().setModel(modeloTablaCentroTrabajoEditar);
        pantallaListarCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimasEditar);
        pantallaListarCostosVariables.getComboBoxCentroTrabajo().setModel(modeloComboBoxCentroTrabajoEditar);
        pantallaListarCostosVariables.getComboBoxMateriaPrima().setModel(modeloComboBoxMateriaPrimaEditar);

        pantallaListarCostosVariables.setLocationRelativeTo(null);
        pantallaListarCostosVariables.setVisible(true);
    }

    public void buscarCostoVariableDelProducto() {
        //busca si el producto seleccionado tiene costos variables cargados
        //sino muestra un mensaje: no tiene costos cargados
        //si tiene, los muestra en las tablas para poder editarlos
        //y habilita los botones agregar y eliminar
    }

    public void eliminarMateriaPrimaDeTabla() {

        int r = pantallaABMCostosVariables.getTablaMateriaPrima().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Debe seleccionar un Artículo a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {

            for (int i = 0; i < modeloTablaMateriasPrimas.getListaElementos().size(); i++) {
                Object valorEtapa = pantallaABMCostosVariables.getTablaMateriaPrima().getModel().getValueAt(r, 0);

                if (modeloTablaMateriasPrimas.getListaElementos().get(i).equals(valorEtapa)) {
                    modeloTablaMateriasPrimas.buscarCostosVAriables(valorEtapa.toString());
                    modeloTablaMateriasPrimas.removeRow(r);
                }
            }
        }
    }

    public void eliminarCentroTrabajoDeTabla() {


        int r = pantallaABMCostosVariables.getTablaCentroTrabajo().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Debe seleccionar un Centro de Trabajo", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {

            for (int i = 0; i < modeloTablaCentroTrabajo.getListaElementos().size(); i++) {
                Object valorCentro = pantallaABMCostosVariables.getTablaCentroTrabajo().getModel().getValueAt(r, 0);

                if (modeloTablaCentroTrabajo.getListaElementos().get(i).equals(valorCentro)) {
                    modeloTablaCentroTrabajo.buscarCostosVAriables(valorCentro.toString());
                    modeloTablaCentroTrabajo.removeRow(r);
                }
            }
        }
    }
}
