/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.Decoradores;
import Entidades.MaestroDeCentroDeTrabajo;
import excepciones.ExpertoCentroDeTrabajoException;
import expertos.ExpertoCentroDeTrabajo;
import persistencia.Conexion;

/**
 *
 * @author edu
 */
public class DecoradorCentroDeTrabajo extends ExpertoCentroDeTrabajo {

    @Override
    public void guardar(MaestroDeCentroDeTrabajo centro) throws ExpertoCentroDeTrabajoException {
        try {
            Conexion.getInstancia().iniciarTX();
            super.guardar(centro);
            Conexion.getInstancia().confirmarTx();
        } catch (ExpertoCentroDeTrabajoException ex) {
            Conexion.getInstancia().deshacerTx();
            throw ex;
        }
    }

    /* @Override
    public void eliminar(Producto articulo) throws ExpertoCentroDeTrabajoException {
    try {
    Conexion.getInstancia().iniciarTX();
    super.eliminar(articulo);
    Conexion.getInstancia().confirmarTx();
    } catch (ExpertoCentroDeTrabajoException ex) {
    Conexion.getInstancia().deshacerTx();
    throw ex;
    }
    }*/
}
