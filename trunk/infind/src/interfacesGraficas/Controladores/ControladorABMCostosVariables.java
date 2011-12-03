/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;
//TODO cargar los tipos de operarios jefe de produccion y operario, y asignarle a los centros de trabajo esos tipos de operarios
import DTOs.DTOCentro;
import DTOs.DTOCostosVariables;
import Entidades.CostoVariable;
import Entidades.DetalleEstructuraDeProducto;
import Entidades.EtapaDeRutaDeFabricacion;
import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.Operario;
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
    private List<ProductoFinal> productoFinal;

    public ControladorABMCostosVariables(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMCostosVariables = (ExpertoABMCostosVariables) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_COSTOS_VARIABLES);

    }

    public void iniciar() throws ExpertoCostosVariablesException {
        pantallaABMCostosVariables = new PantallaABMCostosVariables(controladorPantMadre.getPantalla(), true, this);

        modeloTablaCentroTrabajo = new ModeloTablaCentroDeTrabajo();
        modeloTablaMateriasPrimas = new ModeloTablaMateriasPrimas();
        modeloComboBoxMateriaPrima = new ModeloComboBoxMateriaPrima(expertoABMCostosVariables.buscarProducto());
        pantallaABMCostosVariables.getComboBoxProducto().setModel(modeloComboBoxMateriaPrima);
        pantallaABMCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimas);
        pantallaABMCostosVariables.setLocationRelativeTo(null);
        pantallaABMCostosVariables.setVisible(true);
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
//            double cantidadpr = Double.valueOf(pantallaABMCostosVariables.getCampoCAntidad().getText());
//            double cossssto = (cantidadpr * costo) / 500;
//            dtoCostosACargar.setNombreProducto("Producto Intermedio Base");
//            dtoCostosACargar.setCantidad(pantallaABMCostosVariables.getCampoCAntidad().getText());
//            dtoCostosACargar.setCostoUnitario(String.valueOf(cossssto));
//            dtoCostosACargar.setCostoTotal(String.valueOf(cossssto));
//            modeloTablaMateriasPrimas.addRow(dtoCostosACargar);

        } else {
            dtoCostosACargar.setNombreProducto(maestroArtSelecc.getNombre());
            //     dtoCostosACargar.setCantidad(pantallaABMCostosVariables.getCampoCAntidad().getText());
            //   dtoCostosACargar.setCostoUnitario(String.valueOf(maestroArtSelecc.getCostoUnitarioPorOmision()));
            //  dtoCostosACargar.setCostoTotal(String.valueOf((maestroArtSelecc.getCostoUnitarioPorOmision() * Double.valueOf(pantallaABMCostosVariables.getCampoCAntidad().getText()))));
            modeloTablaMateriasPrimas.addRow(dtoCostosACargar);
        }




    }

    public void listarCostosVariables() throws ExpertoCostosVariablesException {
      
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar();
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar(expertoABMCostosVariables.buscarMaestroArticulo());
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar();
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar(expertoABMCostosVariables.buscarCentroDeTrabajo());
        modeloTablaCentroTrabajoEditar = new ModeloTablaCentroDeTrabajoEditar();
        modeloTablaMateriasPrimasEditar = new ModeloTablaMateriasPrimasEditar();
        modeloTablaCentroTrabajoEditarEnMemoria = new ModeloTablaCentroDeTrabajoEditar();
        modeloTablaMateriasPrimasEditarEnMemoria = new ModeloTablaMateriasPrimasEditar();
      }

    public void buscarCostoVariableDelProducto() throws ExpertoCostosVariablesException {

        Object productoSeleccionado = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();


        //if (productoSeleccionado.toString().equals("Desodorante para pisos lavanda")) {

        productoFinal = expertoABMCostosVariables.buscarProductoFinal();
        for (ProductoFinal prodFinal : productoFinal) {
            if (prodFinal.getNombre().equals("Desodorante para pisos lavanda")) {
                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;

                //busco los costos de materia prima para un lote optimo
                List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = prodFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }

            } else if (prodFinal.getNombre().equals("Detergente de limon")) {
                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;

                //busco los costos de materia prima para un lote optimo
                List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = prodFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }

            } else if (prodFinal.getNombre().equals("Enjuague para ropa imitacion vivere")) {
                double costoTotalMAteriasPrimas = 0.0;
                double costoTOtalManoDeObra = 0.0;
                double costoTotalGastosFAbricacion = 0.0;

                //busco los costos de materia prima para un lote optimo
                List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = prodFinal.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

                for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

                    costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
                }
                //busco los costos de mano de obra
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
                    for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                        costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                    }

                }
                //busco los gastos de fabricacion
                List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = prodFinal.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

                for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

                    costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
                }

            }
        }


    }

    public void limpiarPantalla() {

        modeloTablaMateriasPrimas.clear();
        modeloTablaMateriasPrimas.fireTableDataChanged();
        modeloTablaCentroTrabajo.clear();
        modeloTablaCentroTrabajo.fireTableDataChanged();

    }
}
