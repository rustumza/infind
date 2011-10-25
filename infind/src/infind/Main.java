/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package infind;

import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import interfacesGraficas.Controladores.ControladorPantallaMadre;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
        try {

            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Conexion.getInstancia().getSession();
        ControladorPantallaMadre cpm = new ControladorPantallaMadre();
        cpm.iniciar();
    }

}
