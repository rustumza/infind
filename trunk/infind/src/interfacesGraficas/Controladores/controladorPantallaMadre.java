/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaMadre;

/**
 *
 * @author rustu
 */
public class controladorPantallaMadre {

    PantallaMadre pantalla;
    
    public void iniciar() {
        pantalla = new PantallaMadre();
        pantalla.setVisible(true);
    }

    public void crearCentro() {
       new ControladorCentroDeTrabajo(this).crearCentro();
    }
    
    
    
}
