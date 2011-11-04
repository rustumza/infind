/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaMadre;
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
    }

    public void crearCentro() {
        new ControladorCentroDeTrabajo(this).crearCentro();
    }

    public void editarCentro() {
        new ControladorCentroDeTrabajo(this).editarCentro();
    }

    public void crearArticulo() {
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

    public void crearRutaFabricacion() {
        new ControladorCrearRutaDeFabricacion(this).crearRutaFabricacion();
    }

    public void crearProveedor() {
        new ControladorProveedores(this).crearProveedor();
    }

    public void crearEstructuraDeProducto() {
        new ControladorEstructuraDeProducto(this).crearEstructuraDeProducto();
    }
}
