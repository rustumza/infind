/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.Parametros;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoParametros extends Experto{
    
     public void guardarParametros(Parametros pm) {
        Fachada.getInstancia().guardar(pm);
    }
    
}
