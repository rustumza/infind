/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import interfacesGraficas.PantallaCrearCentro;
import interfacesGraficas.PantallaMadre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author diego
 */
public class ControladorCentroDeTrabajo {
    private PantallaCrearCentro pantallacrearcentro;
    PantallaMadre pantallaMadre;
    
    

    
    public ControladorCentroDeTrabajo(ControladorPantallaMadre contrPantMadre) {
        pantallacrearcentro = new PantallaCrearCentro(pantallaMadre, false);
        
//boton salir        
        pantallacrearcentro.getBotonSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
        
//boton guardar
        pantallacrearcentro.getBotonGuardar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardarCentroDeTrabajo();
            }
        });
        
        
        
    }

    public ControladorCentroDeTrabajo() {
        
        
    
    }
    
    public void guardarCentroDeTrabajo(){
        
    }

    public void crearCentro() {
        
        iniciar();
    }
    
    public void iniciar(){
        pantallacrearcentro.setVisible(true);
        pantallacrearcentro.setLocationRelativeTo(null);
    }
    
    public void finalizar(){
        pantallacrearcentro.setVisible(false);
    }
    
}
