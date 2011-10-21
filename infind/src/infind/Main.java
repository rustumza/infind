/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package infind;

import interfacesGraficas.Controladores.ControladorPantallaMadre;
import persistencia.Conexion;

/**
 *
 * @author rustu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexion.getInstancia().getSession();
        new ControladorPantallaMadre().iniciar();
    }

}
