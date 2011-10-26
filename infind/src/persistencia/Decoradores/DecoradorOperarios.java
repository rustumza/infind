/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Decoradores;

import Entidades.Operario;
import excepciones.ExpertoOperarioException;
import expertos.ExpertoOperarios;
import persistencia.Conexion;

/**
 *
 * @author eduardo
 */
public class DecoradorOperarios extends ExpertoOperarios {

    @Override
    public void guardar(Operario opera) throws ExpertoOperarioException {
        try {
            Conexion.getInstancia().iniciarTX();
            super.guardar(opera);
            Conexion.getInstancia().confirmarTx();
        } catch (ExpertoOperarioException ex) {
            Conexion.getInstancia().deshacerTx();
            throw ex;
        }
    }

    @Override
    public void eliminar(Operario opera) throws ExpertoOperarioException {
        try {
            Conexion.getInstancia().iniciarTX();
            super.eliminar(opera);
            Conexion.getInstancia().confirmarTx();
        } catch (ExpertoOperarioException ex) {
            Conexion.getInstancia().deshacerTx();
            throw ex;
        }
    }
}
