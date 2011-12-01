/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import DTOs.DTOCentro;
import DTOs.DTOCostosVariables;
import Entidades.CostoVariable;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.ProductoFinal;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
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
    ModeloTablaMateriasPrimasEditar modeloTablaMateriasPrimasEditarEnMemoria;
    ModeloTablaCentroDeTrabajo modeloTablaCentroTrabajo;
    ModeloTablaCentroDeTrabajoEditar modeloTablaCentroTrabajoEditar;
    ModeloTablaCentroDeTrabajoEditar modeloTablaCentroTrabajoEditarEnMemoria;
    ModeloComboBoxCentroDeTrabajo modeloComboBoxCentroTrabajo;
    ModeloComboBoxCentroDeTrabajoEditar modeloComboBoxCentroTrabajoEditar;
    ModeloComboBoxMateriaPrima modeloComboBoxMateriaPrima;
    ModeloComboBoxMateriaPrimaEditar modeloComboBoxMateriaPrimaEditar;
    CostoVariable nuevoCosto = null;
    List<CostoVariable> costosVariablesAEditar = new ArrayList<CostoVariable>();
    private MaestroDeArticulo productoIntermedioBase;
    private List<MaestroDeArticulo> productoFinal;

    public ControladorABMCostosVariables(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMCostosVariables = (ExpertoABMCostosVariables) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_VARIABLES);

    }

    public void iniciar() throws ExpertoCostosVariablesException {
        pantallaABMCostosVariables = new PantallaABMCostosVariables(controladorPantMadre.getPantalla(), true, this);

        modeloComboBoxMateriaPrima = new ModeloComboBoxMateriaPrima();
        modeloComboBoxMateriaPrima = new ModeloComboBoxMateriaPrima(expertoABMCostosVariables.buscarMaestroArticulo());
        modeloComboBoxCentroTrabajo = new ModeloComboBoxCentroDeTrabajo();
        modeloComboBoxCentroTrabajo = new ModeloComboBoxCentroDeTrabajo(expertoABMCostosVariables.buscarCentroDeTrabajo());
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
//        

        Object Item = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();


        if (Item.toString() == "Producto Intermedio Base") {
            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);
            if (costosVariablesEncontrados.size() != 0) {
                JOptionPane.showMessageDialog(pantallaABMCostosVariables, "El Producto Intermedio Base ya tiene costos variables ingresados", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            } else {
                pantallaABMCostosVariables.getBotonAgregarCentro().setEnabled(true);
                pantallaABMCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                pantallaABMCostosVariables.getBotonEliminarCentro().setEnabled(true);
                pantallaABMCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
                pantallaABMCostosVariables.getComboBoxCentroTrabajo().setEnabled(true);
                pantallaABMCostosVariables.getComboBoxMateriaPrima().setEnabled(true);

            }
        } else {
            //busco el producto intermedio base

            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();

            //busco los costos variables del producto intermedio bas
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);

            if (costosVariablesEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(pantallaABMCostosVariables, "El Producto Intermedio Base no tiene costos variables ingresados", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);

            } else {
                pantallaABMCostosVariables.getBotonAgregarCentro().setEnabled(true);
                pantallaABMCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                pantallaABMCostosVariables.getBotonEliminarCentro().setEnabled(true);
                pantallaABMCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
                pantallaABMCostosVariables.getComboBoxCentroTrabajo().setEnabled(true);
                pantallaABMCostosVariables.getComboBoxMateriaPrima().setEnabled(true);

//                for (CostoVariable costoVariable : costosVariablesEncontrados) {
//                    DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();
//                    DTOCentro dtocentrosACargar = new DTOCentro();
//                    
//                    if (costoVariable.isEsCentro()) {
//                        dtocentrosACargar.setNombreCentro(costoVariable.getNombre());
//                        dtocentrosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
//                        dtocentrosACargar.setCosto(String.valueOf(costoVariable.getCosto()));
//                        modeloTablaMateriasPrimas.addRow(dtocentrosACargar);
//                        //cargo tabla centros
//                        
//                    }else{
//                        dtoCostosACargar.setNombreProducto(costoVariable.getNombre());
//                        dtoCostosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
//                        dtoCostosACargar.setCostoTotal(String.valueOf(costoVariable.getCosto()));
//                        modeloTablaMateriasPrimas.addRow(dtoCostosACargar);
//                        //cargo tabla materias primas
//                    }
//                }

            }


        }

    }

    public void cargarTablaMateriasPrimas() throws ExpertoCostosVariablesException {

        Object Item = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();
        DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

        String nombreArticulo = "Producto Intermedio Base";
        if (Item.toString() == "Producto Intermedio Base") {
        }
        MaestroDeArticulo maestroArtSelecc = modeloComboBoxMateriaPrima.getMaestroArtSeleccionado();
        if (maestroArtSelecc.getNombre().equals(nombreArticulo)) {

            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);
            double costo = 0.0;
            for (CostoVariable costoVariable : costosVariablesEncontrados) {
                costo = costo + costoVariable.getCosto();

            }
            double cantidadpr = Double.valueOf(pantallaABMCostosVariables.getCampoCAntidad().getText());
            double cossssto = (cantidadpr * costo) / 500;
            dtoCostosACargar.setNombreProducto("Producto Intermedio Base");
            dtoCostosACargar.setCantidad(pantallaABMCostosVariables.getCampoCAntidad().getText());
            dtoCostosACargar.setCostoUnitario(String.valueOf(cossssto));
            dtoCostosACargar.setCostoTotal(String.valueOf(cossssto));
            modeloTablaMateriasPrimas.addRow(dtoCostosACargar);

        } else {
            dtoCostosACargar.setNombreProducto(maestroArtSelecc.getNombre());
            dtoCostosACargar.setCantidad(pantallaABMCostosVariables.getCampoCAntidad().getText());
            dtoCostosACargar.setCostoUnitario(String.valueOf(maestroArtSelecc.getCostoUnitarioPorOmision()));
            dtoCostosACargar.setCostoTotal(String.valueOf((maestroArtSelecc.getCostoUnitarioPorOmision() * Double.valueOf(pantallaABMCostosVariables.getCampoCAntidad().getText()))));
            modeloTablaMateriasPrimas.addRow(dtoCostosACargar);
        }




    }

    public void cargarTablaCentroDeTrabajo() {
        DTOCentro dtocentrosACargar = new DTOCentro();

        MaestroDeCentroDeTrabajo centrosACargar = modeloComboBoxCentroTrabajo.getCentroTrabajoSeleccionado();
        dtocentrosACargar.setCantidad("1");
        dtocentrosACargar.setCosto(String.valueOf(centrosACargar.getCostoCentroDeTrabajo()));
        dtocentrosACargar.setNombreCentro(centrosACargar.getNombreCentro());

        modeloTablaCentroTrabajo.addRow(dtocentrosACargar);
    }

    public void guardarCostosVariables() {

        List<MaestroDeArticulo> productoFinal = expertoABMCostosVariables.buscarProductoFinal();

        Object Item = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();

        for (int i = 0; i < modeloTablaMateriasPrimas.getListaElementos().size(); i++) {
            if (nuevoCosto == null) {
                nuevoCosto = new CostoVariable();
            }

            Object nombreComponente = modeloTablaMateriasPrimas.getValueAt(i, 0);
            Object cantidad = modeloTablaMateriasPrimas.getValueAt(i, 1);
            Object costoCantidad = modeloTablaMateriasPrimas.getValueAt(i, 2);
            nuevoCosto.setNombre(nombreComponente.toString());
            nuevoCosto.setCantidad(Double.valueOf(cantidad.toString()));
            nuevoCosto.setCosto(Double.valueOf(costoCantidad.toString()));
            nuevoCosto.setEsCentro(false);

            if (Item.toString() == "Producto Intermedio Base") {
                nuevoCosto.setArticulo(productoIntermedioBase);
            } else {
                for (MaestroDeArticulo productoFinal1 : productoFinal) {

                    if (productoFinal1.getNombre().equals(Item.toString())) {
                        nuevoCosto.setArticulo(productoFinal1);
                    }

                }
            }

            expertoABMCostosVariables.guardarCostoVariable(nuevoCosto);
            nuevoCosto = null;

        }

        for (int i = 0; i < modeloTablaCentroTrabajo.getListaElementos().size(); i++) {
            if (nuevoCosto == null) {
                nuevoCosto = new CostoVariable();
            }

            Object nombreCentro = modeloTablaCentroTrabajo.getValueAt(i, 0);
            Object cantidad = modeloTablaCentroTrabajo.getValueAt(i, 1);
            Object costoCantidad = modeloTablaCentroTrabajo.getValueAt(i, 2);
            nuevoCosto.setNombre(nombreCentro.toString());
            nuevoCosto.setCantidad(Double.valueOf(cantidad.toString()));
            nuevoCosto.setCosto(Double.valueOf(costoCantidad.toString()));
            nuevoCosto.setEsCentro(true);

            if (Item.toString() == "Producto Intermedio Base") {
                nuevoCosto.setArticulo(productoIntermedioBase);
            } else {
                for (MaestroDeArticulo productoFinal1 : productoFinal) {

                    if (productoFinal1.getNombre().equals(Item.toString())) {
                        nuevoCosto.setArticulo(productoFinal1);
                    }

                }
            }

            expertoABMCostosVariables.guardarCostoVariable(nuevoCosto);
            nuevoCosto = null;

        }
        limpiarPantalla();
        JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Costos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarCostosVariables() throws ExpertoCostosVariablesException {
        pantallaListarCostosVariables = new PantallaListarCostosVariables(controladorPantMadre.getPantalla(), true, this);
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar();
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar(expertoABMCostosVariables.buscarMaestroArticulo());
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar();
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar(expertoABMCostosVariables.buscarCentroDeTrabajo());
        modeloTablaCentroTrabajoEditar = new ModeloTablaCentroDeTrabajoEditar();
        modeloTablaMateriasPrimasEditar = new ModeloTablaMateriasPrimasEditar();
        modeloTablaCentroTrabajoEditarEnMemoria = new ModeloTablaCentroDeTrabajoEditar();
        modeloTablaMateriasPrimasEditarEnMemoria = new ModeloTablaMateriasPrimasEditar();
        pantallaListarCostosVariables.getTablaCentroTrabajo().setModel(modeloTablaCentroTrabajoEditar);
        pantallaListarCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimasEditar);
        pantallaListarCostosVariables.getComboBoxCentroTrabajo().setModel(modeloComboBoxCentroTrabajoEditar);
        pantallaListarCostosVariables.getComboBoxMateriaPrima().setModel(modeloComboBoxMateriaPrimaEditar);

        pantallaListarCostosVariables.setLocationRelativeTo(null);
        pantallaListarCostosVariables.setVisible(true);
    }

    public void buscarCostoVariableDelProducto() throws ExpertoCostosVariablesException {

        Object productoSeleccionado = pantallaListarCostosVariables.getComboBoxProducto().getSelectedItem();

        if (productoSeleccionado.toString() == "Producto Intermedio Base") {

            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();

            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariablesProdIntermedio(productoIntermedioBase);
            
            if (costosVariablesEncontrados != null) {
                for (CostoVariable costoVariable : costosVariablesEncontrados) {
                    
                    costosVariablesAEditar.add(costoVariable);
                    
                    if (costoVariable.isEsCentro()) {//cargo tabla centro trabajo
                        DTOCentro dtocentrosACargar = new DTOCentro();

                        dtocentrosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                        dtocentrosACargar.setCosto(String.valueOf(costoVariable.getCosto()));
                        dtocentrosACargar.setNombreCentro(costoVariable.getNombre());
                        modeloTablaCentroTrabajoEditar.addRow(dtocentrosACargar);
                        modeloTablaCentroTrabajoEditarEnMemoria.addRow(dtocentrosACargar);

                    } else {//cargo tabla materias primas
                        DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

                        dtoCostosACargar.setNombreProducto(costoVariable.getNombre());
                        dtoCostosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                        dtoCostosACargar.setCostoTotal(String.valueOf(costoVariable.getCosto()));
                        modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);
                        modeloTablaMateriasPrimasEditarEnMemoria.addRow(dtoCostosACargar);
                    }

                }

                pantallaListarCostosVariables.getBotonAgregarCentroTrabajo().setEnabled(true);
                pantallaListarCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                pantallaListarCostosVariables.getBotonEliminarCentroTrabajo().setEnabled(true);
                pantallaListarCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(pantallaABMCostosVariables, "No se han ingresado Costos Variables. No se puede Editar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (productoSeleccionado.toString() == "Desodorante para pisos lavanda") {

            productoFinal = expertoABMCostosVariables.buscarProductoFinal();

            for (MaestroDeArticulo maestroDeArticulo : productoFinal) {

                if (maestroDeArticulo.getNombre().equals("Desodorante para pisos lavanda")) {
                    List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(maestroDeArticulo);
                    if (costosVariablesEncontrados != null) {
                        for (CostoVariable costoVariable : costosVariablesEncontrados) {
                            costosVariablesAEditar.add(costoVariable);
                            if (costoVariable.isEsCentro()) {//cargo tabla centro trabajo
                                DTOCentro dtocentrosACargar = new DTOCentro();

                                dtocentrosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtocentrosACargar.setCosto(String.valueOf(costoVariable.getCosto()));
                                dtocentrosACargar.setNombreCentro(costoVariable.getNombre());
                                modeloTablaCentroTrabajoEditar.addRow(dtocentrosACargar);
                                modeloTablaCentroTrabajoEditarEnMemoria.addRow(dtocentrosACargar);

                            } else {//cargo tabla materias primas
                                DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

                                dtoCostosACargar.setNombreProducto(costoVariable.getNombre());
                                dtoCostosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtoCostosACargar.setCostoTotal(String.valueOf(costoVariable.getCosto()));
                                modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);
                                modeloTablaMateriasPrimasEditarEnMemoria.addRow(dtoCostosACargar);

                            }

                        }

                        pantallaListarCostosVariables.getBotonAgregarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(pantallaABMCostosVariables, "No se han ingresado Costos Variables. No se puede Editar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }


        } else if (productoSeleccionado.toString() == "Detergente de limon") {
            productoFinal = expertoABMCostosVariables.buscarProductoFinal();

            for (MaestroDeArticulo maestroDeArticulo : productoFinal) {

                if (maestroDeArticulo.getNombre().equals("Detergente de limon")) {
                    List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(maestroDeArticulo);
                    if (costosVariablesEncontrados != null) {
                        for (CostoVariable costoVariable : costosVariablesEncontrados) {
                            costosVariablesAEditar.add(costoVariable);
                            if (costoVariable.isEsCentro()) {//cargo tabla centro trabajo
                                DTOCentro dtocentrosACargar = new DTOCentro();

                                dtocentrosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtocentrosACargar.setCosto(String.valueOf(costoVariable.getCosto()));
                                dtocentrosACargar.setNombreCentro(costoVariable.getNombre());
                                modeloTablaCentroTrabajoEditar.addRow(dtocentrosACargar);
                                modeloTablaCentroTrabajoEditarEnMemoria.addRow(dtocentrosACargar);

                            } else {//cargo tabla materias primas
                                DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

                                dtoCostosACargar.setNombreProducto(costoVariable.getNombre());
                                dtoCostosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtoCostosACargar.setCostoTotal(String.valueOf(costoVariable.getCosto()));
                                modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);
                                modeloTablaMateriasPrimasEditarEnMemoria.addRow(dtoCostosACargar);

                            }

                        }

                        pantallaListarCostosVariables.getBotonAgregarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(pantallaABMCostosVariables, "No se han ingresado Costos Variables. No se puede Editar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }


        } else if (productoSeleccionado.toString() == "Enjuage para ropa imitacion vivere") {

            productoFinal = expertoABMCostosVariables.buscarProductoFinal();

            for (MaestroDeArticulo maestroDeArticulo : productoFinal) {

                if (maestroDeArticulo.getNombre().equals("Enjuage para ropa imitacion vivere")) {
                    List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(maestroDeArticulo);
                    if (costosVariablesEncontrados != null) {
                        for (CostoVariable costoVariable : costosVariablesEncontrados) {
                            costosVariablesAEditar.add(costoVariable);
                            if (costoVariable.isEsCentro()) {//cargo tabla centro trabajo
                                DTOCentro dtocentrosACargar = new DTOCentro();

                                dtocentrosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtocentrosACargar.setCosto(String.valueOf(costoVariable.getCosto()));
                                dtocentrosACargar.setNombreCentro(costoVariable.getNombre());
                                modeloTablaCentroTrabajoEditar.addRow(dtocentrosACargar);
                                modeloTablaCentroTrabajoEditarEnMemoria.addRow(dtocentrosACargar);

                            } else {//cargo tabla materias primas
                                DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

                                dtoCostosACargar.setNombreProducto(costoVariable.getNombre());
                                dtoCostosACargar.setCantidad(String.valueOf(costoVariable.getCantidad()));
                                dtoCostosACargar.setCostoTotal(String.valueOf(costoVariable.getCosto()));
                                modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);
                                modeloTablaMateriasPrimasEditarEnMemoria.addRow(dtoCostosACargar);

                            }

                        }

                        pantallaListarCostosVariables.getBotonAgregarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonAgregarMateriaPrima().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarCentroTrabajo().setEnabled(true);
                        pantallaListarCostosVariables.getBotonEliminarMateriaPrima().setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(pantallaABMCostosVariables, "No se han ingresado Costos Variables. No se puede Editar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        }

    }

    public void eliminarMateriaPrimaDeTabla() {

        int r = pantallaABMCostosVariables.getTablaMateriaPrima().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Debe seleccionar un Artículo a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            modeloTablaMateriasPrimas.removeRow(r);
            modeloTablaMateriasPrimas.fireTableDataChanged();

        }
    }

    public void eliminarCentroTrabajoDeTabla() {


        int r = pantallaABMCostosVariables.getTablaCentroTrabajo().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Debe seleccionar un Centro de Trabajo", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            modeloTablaCentroTrabajo.removeRow(r);
            modeloTablaCentroTrabajo.fireTableDataChanged();

        }
    }

    public void limpiarPantalla() {
        pantallaABMCostosVariables.getCampoCAntidad().setText("");
        pantallaABMCostosVariables.getCampoCostoCentro().setText("");
        modeloTablaMateriasPrimas.clear();
        modeloTablaMateriasPrimas.fireTableDataChanged();
        modeloTablaCentroTrabajo.clear();
        modeloTablaCentroTrabajo.fireTableDataChanged();
        pantallaABMCostosVariables.getBotonAgregarCentro().setEnabled(false);
        pantallaABMCostosVariables.getBotonAgregarMateriaPrima().setEnabled(false);
        pantallaABMCostosVariables.getBotonEliminarCentro().setEnabled(false);
        pantallaABMCostosVariables.getBotonEliminarMateriaPrima().setEnabled(false);
        pantallaABMCostosVariables.getComboBoxCentroTrabajo().setEnabled(false);
        pantallaABMCostosVariables.getComboBoxMateriaPrima().setEnabled(false);
    }
    
    
    
    
    
    ////////////////////////

    public void guardarCostosVariablesEditados() {
        //se elimina todo lo que tenia y se carga lo que salga en la tabla
        
         List<MaestroDeArticulo> productoFinal = expertoABMCostosVariables.buscarProductoFinal();

        Object Item = pantallaListarCostosVariables.getComboBoxProducto().getSelectedItem();

        
        //////
        for (int i = 0; i < costosVariablesAEditar.size(); i++) {
            costosVariablesAEditar.get(i).setEliminado(true);
            expertoABMCostosVariables.guardarCostoVariable(costosVariablesAEditar.get(i));
            
        }
        
        
        
        //////
        for (int i = 0; i < modeloTablaMateriasPrimasEditar.getListaElementos().size(); i++) {
            if (nuevoCosto == null) {
                nuevoCosto = new CostoVariable();
            }

            Object nombreComponente = modeloTablaMateriasPrimasEditar.getValueAt(i, 0);
            Object cantidad = modeloTablaMateriasPrimasEditar.getValueAt(i, 1);
            Object costoCantidad = modeloTablaMateriasPrimasEditar.getValueAt(i, 2);
            nuevoCosto.setNombre(nombreComponente.toString());
            nuevoCosto.setCantidad(Double.valueOf(cantidad.toString()));
            nuevoCosto.setCosto(Double.valueOf(costoCantidad.toString()));
            nuevoCosto.setEsCentro(false);

            if (Item.toString() == "Producto Intermedio Base") {
                nuevoCosto.setArticulo(productoIntermedioBase);
            } else {
                for (MaestroDeArticulo productoFinal1 : productoFinal) {

                    if (productoFinal1.getNombre().equals(Item.toString())) {
                        nuevoCosto.setArticulo(productoFinal1);
                    }

                }
            }

            expertoABMCostosVariables.guardarCostoVariable(nuevoCosto);
            nuevoCosto = null;

        }

        for (int i = 0; i < modeloTablaCentroTrabajoEditar.getListaElementos().size(); i++) {
            if (nuevoCosto == null) {
                nuevoCosto = new CostoVariable();
            }

            Object nombreCentro = modeloTablaCentroTrabajoEditar.getValueAt(i, 0);
            Object cantidad = modeloTablaCentroTrabajoEditar.getValueAt(i, 1);
            Object costoCantidad = modeloTablaCentroTrabajoEditar.getValueAt(i, 2);
            nuevoCosto.setNombre(nombreCentro.toString());
            nuevoCosto.setCantidad(Double.valueOf(cantidad.toString()));
            nuevoCosto.setCosto(Double.valueOf(costoCantidad.toString()));
            nuevoCosto.setEsCentro(true);

            if (Item.toString() == "Producto Intermedio Base") {
                nuevoCosto.setArticulo(productoIntermedioBase);
            } else {
                for (MaestroDeArticulo productoFinal1 : productoFinal) {

                    if (productoFinal1.getNombre().equals(Item.toString())) {
                        nuevoCosto.setArticulo(productoFinal1);
                    }

                }
            }

            expertoABMCostosVariables.guardarCostoVariable(nuevoCosto);
            nuevoCosto = null;

        }
        limpiarPantalla();
        JOptionPane.showMessageDialog(pantallaABMCostosVariables, "Costos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        
    }

    
  ///////////////////////////  
    
    
    
    public void cargarTablaMateriasPrimasEditar() throws ExpertoCostosVariablesException {
        Object Item = pantallaListarCostosVariables.getComboBoxProducto().getSelectedItem();
        DTOCostosVariables dtoCostosACargar = new DTOCostosVariables();

        String nombreArticulo = "Producto Intermedio Base";
        if (Item.toString() == "Producto Intermedio Base") {
        }
        MaestroDeArticulo maestroArtSelecc = modeloComboBoxMateriaPrimaEditar.getMaestroArtSeleccionado();
        if (maestroArtSelecc.getNombre().equals(nombreArticulo)) {

            productoIntermedioBase = expertoABMCostosVariables.buscarProductoIntermedioBase();
            List<CostoVariable> costosVariablesEncontrados = expertoABMCostosVariables.buscarCostosVariables(productoIntermedioBase);
            double costo = 0.0;
            for (CostoVariable costoVariable : costosVariablesEncontrados) {
                costo = costo + costoVariable.getCosto();

            }
            double cantidadpr = Double.valueOf(pantallaListarCostosVariables.getCampoCAntidad().getText());
            double cossssto = (cantidadpr * costo) / 500;
            dtoCostosACargar.setNombreProducto("Producto Intermedio Base");
            dtoCostosACargar.setCantidad(pantallaListarCostosVariables.getCampoCAntidad().getText());
            dtoCostosACargar.setCostoUnitario(String.valueOf(cossssto));
            dtoCostosACargar.setCostoTotal(String.valueOf(cossssto));
            modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);

        } else {
            dtoCostosACargar.setNombreProducto(maestroArtSelecc.getNombre());
            dtoCostosACargar.setCantidad(pantallaListarCostosVariables.getCampoCAntidad().getText());
            dtoCostosACargar.setCostoUnitario(String.valueOf(maestroArtSelecc.getCostoUnitarioPorOmision()));
            dtoCostosACargar.setCostoTotal(String.valueOf((maestroArtSelecc.getCostoUnitarioPorOmision() * Double.valueOf(pantallaListarCostosVariables.getCampoCAntidad().getText()))));
            modeloTablaMateriasPrimasEditar.addRow(dtoCostosACargar);
        }

    }

    public void eliminarMateriaPrimaDeTablaEditar() {
        int r = pantallaListarCostosVariables.getTablaMateriaPrima().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaListarCostosVariables, "Debe seleccionar un Artículo a Eliminar", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            modeloTablaMateriasPrimasEditar.removeRow(r);
            modeloTablaMateriasPrimasEditar.fireTableDataChanged();

        }
    }

    public void cargarTablaCentroDeTrabajoEditar() {
        DTOCentro dtocentrosACargar = new DTOCentro();

        MaestroDeCentroDeTrabajo centrosACargar = modeloComboBoxCentroTrabajoEditar.getCentroTrabajoSeleccionado();
        dtocentrosACargar.setCantidad("1");
        dtocentrosACargar.setCosto(String.valueOf(centrosACargar.getCostoCentroDeTrabajo()));
        dtocentrosACargar.setNombreCentro(centrosACargar.getNombreCentro());

        modeloTablaCentroTrabajoEditar.addRow(dtocentrosACargar);
    }

    public void eliminarCentroTrabajoDeTablaEditar() {
        int r = pantallaListarCostosVariables.getTablaCentroTrabajo().getSelectedRow();
        if (r == -1) {

            JOptionPane.showMessageDialog(pantallaListarCostosVariables, "Debe seleccionar un Centro de Trabajo", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);


        } else {
            modeloTablaCentroTrabajoEditar.removeRow(r);
            modeloTablaCentroTrabajoEditar.fireTableDataChanged();

        }
    }
}
