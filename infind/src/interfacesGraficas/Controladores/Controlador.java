/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfacesGraficas.Controladores;
import javax.swing.JInternalFrame;

/**
 *
 * @author edu
 */
public abstract class Controlador extends Thread{

    public abstract void iniciar();
    public abstract void finalizar();
    public abstract JInternalFrame getPantalla();
    public abstract void continuar();

}