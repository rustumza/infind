/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import excepciones.ExpertoABMDemandaExcepcion;
import excepciones.ExpertoCalcularDemandaException;
import excepciones.ExpertoCostosFijosException;
import excepciones.ExpertoCostosVariablesException;
import excepciones.ExpertoPuntoEquilibrioException;
import interfacesGraficas.PantallaMadre;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author rustu
 */
public class ControladorPantallaMadre {

    private PantallaMadre pantalla;

    public PantallaMadre getPantalla() {
        return pantalla;
    }

    public ControladorPantallaMadre() {
        pantalla = new PantallaMadre(this);

    }

    public void iniciar() {
        pantalla.setVisible(true);
        pantalla.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*List<MaestroDeArticulo> lista = Fachada.getInstancia().buscar(MaestroDeArticulo.class, null);
        for (MaestroDeArticulo maestroDeArticulo : lista) {
        Stock stock = new Stock();
        stock.setCantidadFisicaReal((float)0);
        stock.setCantidadPorEntrar((float)0);
        stock.setCantidadReservada((float)0);
        stock.setEliminado(Boolean.TRUE);
        maestroDeArticulo.setStock(stock);
        Fachada.getInstancia().guardar(stock);
        Fachada.getInstancia().guardar(maestroDeArticulo);
        }
         * 
         */
    }

    public void crearCentro() {
        new ControladorCentroDeTrabajo(this).crearCentro();
    }

    public void editarCentro() {
        new ControladorCentroDeTrabajo(this).editarCentro();
    }

    public void crearMateriaPrima() {
        new ControladorMateriaPrima(this).crearMateriaPrima();
    }

    public void crearProductoIntermedio() {
        new ControladorProductoIntermedio(this).crearProductoIntermedio();
    }

    public void crearProductoComponente() {
        new ControladorProductoComponente(this).crearProductoComponente();
    }

    public void crearProductoFinal() {
        new ControladorProductoFinal(this).crearProductoFinal();
    }

    public void crearOperario() {
        new ControladorOperarios(this).crearOperario();

    }

    public void editarOperario() {
        new ControladorOperarios(this).editarOperario();
    }

    public void listarMateriaPrima() {
        new ControladorListarMateriasPrimas(this).iniciar();
    }

    public void listarProductoComponente() {
        new ControladorListarProductosComponentes(this).iniciar();
    }

    public void listarProductosIntermedios() {
        new ControladorListarProductosIntermedios(this).iniciar();
    }

    public void listarProductosFinales() {
        new ControladorListarProductosFinales(this).iniciar();
    }

    public void listarProveedores() {
        new ControladorListarProveedores(this).iniciar();
    }

    public void crearRutaFabricacion() {
        new ControladorCrearRutaDeFabricacion(this).crearRutaFabricacion();
    }

    public void crearProveedor() {
        new ControladorProveedores(this).crearProveedor();
    }

    public void crearEstructuraDeProducto() {
        new ControladorEstructuraDeProducto(this).crearEstructuraDeProducto();
    }

    public void crearProductoIQE() {
        new ControladorProductoIQE(this).crearProductoIQE();
    }

    public void listarProductosIQE() {
        new ControladorListarProductosIQE(this).iniciar();
    }

    public void EditarRutaFabricacion() {
        new ControladorEditarRutaDeFabricacion(this).editarRuta();
    }

    public void crearPedidoAProveedor() {
        new ControladorPedidoAProveedor(this).iniciar();
    }

    public void listarPedidosAProveedores() {
        new ControladorEditarPedidoAProveedor(this).iniciar();
    }

    public void crearOrdeDeProduccion() {
        new ControladorCrearOrdenDeFabricacion(this).iniciar();
    }

    public void abmCostosFijos() {
        try {
            new ControladorABMCostosFijos(this).iniciar();
        } catch (ExpertoCostosFijosException ex) {
            Logger.getLogger(ControladorPantallaMadre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abmCostosVAriables() {
        try {
            new ControladorABMCostosVariables(this).iniciar();
        } catch (ExpertoCostosVariablesException ex) {
            Logger.getLogger(ControladorPantallaMadre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void puntoEquilibrio() throws ExpertoPuntoEquilibrioException {
        new ControladorPuntoEquilibrio(this).iniciar();
    }

    public void abmDemandas() throws ExpertoABMDemandaExcepcion {
        new ControladorABMDemanda(this).iniciar();
    }

    public void listarOrdenesDeFabricacion() {
        new ControladorListarOrdenesDeFabricacion(this).iniciar();
    }

    public void prediccion() {
        new ControladorPrediccionDemanda(this).iniciar();
    }


    public void crearIndices() {
        new ControladorABMIndices(this).iniciar();
    }

    public void cgiParaUnProducto() {
        new ControladorGestionDeInventarioParaUnProducto(this).iniciar();
    }
}