/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rustu
 */
public class ControladorPantallaMadre {

    PantallaMadre pantalla;

    public ControladorPantallaMadre() {
        pantalla.getCrearCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                crearCentro();
            }
        });
    }
    
    
    public void iniciar() {
        pantalla = new PantallaMadre();
        pantalla.setVisible(true);
    }

    public void crearCentro() {
       new ControladorCentroDeTrabajo(this).crearCentro();
    }
    
    
    
}
