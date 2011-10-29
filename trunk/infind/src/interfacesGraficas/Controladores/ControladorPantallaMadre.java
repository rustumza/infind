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
    
    public void editarCentro(){
        new ControladorCentroDeTrabajo(this).editarCentro();
    }
            

    public void crearArticulo() {
        
    }

    public void crearMateriaPrima() {
        new ControladorMateriaPrima(this).crearMateriaPrima();
    }

    public void crearProductoFinal() {
        
    }
    
    public void crearOperario(){
        new ControladorOperarios(this).crearOperario();
        
    }
    
    public void editarOperario(){
        new ControladorOperarios(this).editarOperario();
    }
    
    public void listarMateriaPrima(){
        new ControladorListarMateriasPrimas(this).editarMateriaPrima();
    }
    
    
    
    
}
