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

    public ControladorPantallaMadre() {
        pantalla = new PantallaMadre();
        pantalla.setTitle("Informática Industrial - EON Argentina");

//boton crear centro de trabajo        
        pantalla.getCrearCentro().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                crearCentro();
            }
        });
        
//boton salir        
        pantalla.getBotonSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    
    public void iniciar() {
        
        pantalla.setVisible(true);
        pantalla.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void crearCentro() {
       new ControladorCentroDeTrabajo(this).crearCentro();
    }

    public void crearArticulo() {
        
    }

    public void crearMateriaPrima() {
        
    }

    public void crearProductoFinal() {
        
    }
    
    
    
}
