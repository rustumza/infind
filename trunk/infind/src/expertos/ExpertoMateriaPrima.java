/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoMateriaPrima extends Experto{

    public void guardar(MateriaPrima matPrim) {
        Fachada.getInstancia().guardar(matPrim);
    }
    
}
