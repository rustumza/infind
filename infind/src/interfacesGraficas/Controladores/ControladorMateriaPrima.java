/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaCrearMateriaPrima;

/**
 *
 * @author rustu
 */
public class ControladorMateriaPrima {

    ControladorPantallaMadre controladorPantallaMadre;
    
    ControladorMateriaPrima(ControladorPantallaMadre contPantMad) {
        controladorPantallaMadre = contPantMad;
    }

    public void crearMateriaPrima() {
        PantallaCrearMateriaPrima pantalla = new PantallaCrearMateriaPrima(controladorPantallaMadre.getPantalla(), false);
        pantalla.setVisible(true);
    }
    
}
