/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

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
import javax.swing.DefaultComboBoxModel;
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


        modeloTablaMateriasPrimas = new ModeloTablaMateriasPrimas();
        pantallaABMCostosVariables.getComboBoxProducto().setModel(new DefaultComboBoxModel(expertoABMCostosVariables.buscarProducto().toArray()));
        pantallaABMCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimas);
        pantallaABMCostosVariables.setLocationRelativeTo(null);
        pantallaABMCostosVariables.setVisible(true);
    }

    public void listarCostosVariables() throws ExpertoCostosVariablesException {

        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar();
        modeloComboBoxMateriaPrimaEditar = new ModeloComboBoxMateriaPrimaEditar(expertoABMCostosVariables.buscarMaestroArticulo());
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar();
        modeloComboBoxCentroTrabajoEditar = new ModeloComboBoxCentroDeTrabajoEditar(expertoABMCostosVariables.buscarCentroDeTrabajo());
    }

    public void buscarCostoVariableDelProducto() throws ExpertoCostosVariablesException {

        limpiarPantalla();
        Object productoSeleccionado = pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem();
        List<DetalleEstructuraDeProducto> detalleEstructuraProductoMateriasPrimas = new ArrayList<DetalleEstructuraDeProducto>();
        ProductoFinal prod = expertoABMCostosVariables.buscarProductoFinal((ProductoFinal) pantallaABMCostosVariables.getComboBoxProducto().getSelectedItem());


        double costoTotalMAteriasPrimas = 0.0;
        double costoTOtalManoDeObra = 0.0;
        double costoTotalGastosFAbricacion = 0.0;

        //busco los costos de materia prima para un lote optimo
        detalleEstructuraProductoMateriasPrimas = prod.getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList();

        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : detalleEstructuraProductoMateriasPrimas) {

            costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
        }
        for (DetalleEstructuraDeProducto detalleEstructuraDeProducto : prod.getProductoTipoIQE().getMaestroEstructuraDeProducto().getDetalleEstructuraProductoList()) {

            costoTotalMAteriasPrimas = costoTotalMAteriasPrimas + (detalleEstructuraDeProducto.getCantidad() * detalleEstructuraDeProducto.getMaestroArticulo().getCostoUnitarioPorOmision());
        }
        //busco los costos de mano de obra
        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion = prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion) {
            for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
            }

        }
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : prod.getProductoTipoIQE().getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {
            if (etapaDeRutaDeFabricacion.getMaestroCentroTrabajo() != null) {

                for (Operario operario : etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getOperario()) {
                    costoTOtalManoDeObra = costoTOtalManoDeObra + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeOperarios() * operario.getTipoOperario().getCostoXHora() / 60); //es dividido 60 para pasar el costo por hora a minutos
                }
            }


        }
        //busco los gastos de fabricacion
        List<EtapaDeRutaDeFabricacion> etapaRutaFabricacion1 = prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion();

        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : etapaRutaFabricacion1) {

            costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
        }
        for (EtapaDeRutaDeFabricacion etapaDeRutaDeFabricacion : prod.getMaestroEstructuraDeProducto().getMaestroRutaFabricacion().getEtapaRutaFabricacion()) {

            costoTotalGastosFAbricacion = costoTotalGastosFAbricacion + (etapaDeRutaDeFabricacion.getTiempoDeTrabajoDeMaquinas() * etapaDeRutaDeFabricacion.getMaestroCentroTrabajo().getCostoCentroDeTrabajo());
        }
        DTOCostosVariables dtoCostos = new DTOCostosVariables();
        dtoCostos.setNombreProducto(productoSeleccionado.toString());
        dtoCostos.setCostoMateriaPrima(String.valueOf(costoTotalMAteriasPrimas));
        dtoCostos.setCostosManoObra(String.valueOf(costoTOtalManoDeObra));
        dtoCostos.setGastoFabricacion(String.valueOf(costoTotalGastosFAbricacion));
        modeloTablaMateriasPrimas.addRow(dtoCostos);
        pantallaABMCostosVariables.getTablaMateriaPrima().setModel(modeloTablaMateriasPrimas);
        detalleEstructuraProductoMateriasPrimas = null;
    }

    public void limpiarPantalla() {

        modeloTablaMateriasPrimas.clear();
        modeloTablaMateriasPrimas.fireTableDataChanged();



    }
}
