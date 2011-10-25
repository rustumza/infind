/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    
    
    
}
