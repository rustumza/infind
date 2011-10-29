/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOMateriaPrima;
import Entidades.MateriaPrima;
import Entidades.Numerador;
import excepciones.ExpertoMateriaPrimaException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoListarMateriaPrima extends Experto{

        public List<MateriaPrima> buscarMateriaPrima(DTOMateriaPrima dtoMateriaPrima) throws ExpertoMateriaPrimaException {

        List<MateriaPrima> materiaPrimaEncontrada = null;
        MateriaPrima materiaPrimaDevuelta = null;


        if (dtoMateriaPrima == null) {

            materiaPrimaEncontrada = Fachada.getInstancia().buscar(MateriaPrima.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MateriaPrima.class);
            if (dtoMateriaPrima.getCodigoMateriaPrima() != null) {
                criterioCentro.add(Restrictions.like("codigo", dtoMateriaPrima.getCodigoMateriaPrima()));
            }

            if (dtoMateriaPrima.getNombreMateriaPrima() != null) {
                criterioCentro.add(Restrictions.like("nombre", dtoMateriaPrima.getNombreMateriaPrima()));
            }

            materiaPrimaEncontrada = Fachada.getInstancia().buscar(MateriaPrima.class, criterioCentro);
        }
        if (materiaPrimaEncontrada.isEmpty()) {
            throw new ExpertoMateriaPrimaException("No se encontró Materia Prima para los datos ingresados");

        }
        return materiaPrimaEncontrada;
    }

    public void guardarMateriaPrima(MateriaPrima materiaPrima) throws ExpertoMateriaPrimaException {
        if (materiaPrimaInvalida(materiaPrima)) {
            throw new ExpertoMateriaPrimaException("Faltan completar Campos");
        } else {

            try {
                Fachada.getInstancia().guardar(materiaPrima);
            } catch (Exception ex) {
                throw new ExpertoMateriaPrimaException("Error al guardar la Materia Prima");
            }
        }
    }

    public void eliminarMateriaPrima(MateriaPrima materiaPrima) throws ExpertoMateriaPrimaException {

        materiaPrima.setEliminado(Boolean.TRUE);

        try {
            Fachada.getInstancia().guardar(materiaPrima);

        } catch (Exception ex) {
            throw new ExpertoMateriaPrimaException("Error al eliminar la Materia Prima");
        }

    }

    private boolean materiaPrimaInvalida(MateriaPrima materiaPrima) {
        if (materiaPrima.getDescripcion().length() == 0) {
            return true;
        } else if (materiaPrima.getCodigo().length() == 0) {
            return true;
        } else if (materiaPrima.getTamanioLoteEstandar() == 0) {
            return true;
        } else if (materiaPrima.getUbicacionEnAlmacen().length() == 0) {
            return true;
        } else if (materiaPrima.getPrecioBase() == 0) {
            return true;
        } else if (materiaPrima.getNombre().length() == 0) {
            return true;
        } else if (materiaPrima.getCostoEstandar() == 0) {
            return true;
        } else if (materiaPrima.getCategoria() == 0) {
            return true;

        } else {
            return false;
        }
    }
}
