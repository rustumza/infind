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
public class ExpertoMateriaPrima extends Experto{

    public void guardar(MateriaPrima matPrim) {
        Fachada.getInstancia().guardar(matPrim);
        
        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        if((matPrim.getTipoMateriaPrima()).equals("Primaria")){
            criterioNumerador.add(Restrictions.eq("codificacion", "1.1."));
        }else{
            criterioNumerador.add(Restrictions.eq("codificacion", "1.2."));
        }
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);
        
    }
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
        } else if (materiaPrima.getCorreoElectronico().length() == 0) {
            return true;
        } else if (materiaPrima.getDireccion().length() == 0) {
            return true;
        } else if (materiaPrima.getDni() == 0) {
            return true;
        } else if (materiaPrima.getNombre().length() == 0) {
            return true;
        } else if (materiaPrima.getTelefono() == 0) {
            return true;
            /*} else if (operario.getTipoOperario() == null) {
            return true;*/
        } else {
            return false;
        }


    }

    public List<TipoOperario> buscarTipoOperario() {
        List<TipoOperario> tipoOperarioEncontrada = null;

        tipoOperarioEncontrada = Fachada.getInstancia().buscar(TipoOperario.class, null);

        return tipoOperarioEncontrada;

    }
}
