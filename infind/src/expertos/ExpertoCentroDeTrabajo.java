/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOCentro;
import DTOs.DTOHerramienta;
import DTOs.DTOMaquina;
import DTOs.DTOOperario;
import Entidades.Herramientas;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.Maquina;
import Entidades.Numerador;
import Entidades.Operario;
import excepciones.ExpertoCentroDeTrabajoException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoCentroDeTrabajo extends Experto {

    public Numerador buscarNumerador(Numerador numerador) {
        
        List<Numerador> numeroEncontrado = null;

        Criteria criterioCentro = Fachada.getInstancia().crearCriterio(Numerador.class);
        if (numerador != null) {
            
            
            criterioCentro.add(Restrictions.like("codificacion", numerador.getCodificacion()));
        }


        numeroEncontrado = Fachada.getInstancia().buscar(Numerador.class, criterioCentro);
        

        return numeroEncontrado.get(0);
    }

    public List<MaestroDeCentroDeTrabajo> buscarCentrosDeTrabajo(MaestroDeCentroDeTrabajo centro) throws ExpertoCentroDeTrabajoException {

        List<MaestroDeCentroDeTrabajo> centrosEncontrados = null;

        if (centro == null) {

            centrosEncontrados = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (centro.getCodigo() != null) {
                criterioCentro.add(Restrictions.like("codigo", centro.getCodigo()));
            }

            if (centro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", centro.getNombreCentro()));
            }

            centrosEncontrados = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);
        }
        /*if (centrosEncontrados.isEmpty()) {
        throw new ExpertoCentroDeTrabajoException("No se encontraron Productos para los datos ingresados");
        
        }*/

        return centrosEncontrados;
    }

    //busca el centro desde la pantalla editar centros
    public MaestroDeCentroDeTrabajo buscarCentros(DTOCentro dtoCentro) throws ExpertoCentroDeTrabajoException {

        List<MaestroDeCentroDeTrabajo> centroEncontrado = null;
        MaestroDeCentroDeTrabajo centroDevuelto = null;

        if (dtoCentro == null) {

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, null);
        } else {
            Criteria criterioCentro = Fachada.getInstancia().crearCriterio(MaestroDeCentroDeTrabajo.class);
            if (dtoCentro.getCodigoCentro() != null) {
                criterioCentro.add(Restrictions.like("codigo", dtoCentro.getCodigoCentro()));
            }

            if (dtoCentro.getNombreCentro() != null) {
                criterioCentro.add(Restrictions.like("nombreCentro", dtoCentro.getNombreCentro()));
            }

            centroEncontrado = Fachada.getInstancia().buscar(MaestroDeCentroDeTrabajo.class, criterioCentro);
        }
        if (centroEncontrado.isEmpty()) {
            throw new ExpertoCentroDeTrabajoException("No se encontró Centro de Trabajo para los datos ingresados");

        }

        centroDevuelto = centroEncontrado.get(0);
        return centroDevuelto;
    }
    
    
    public List<Operario> buscarOperarios(DTOOperario operario) throws ExpertoCentroDeTrabajoException {

        List<Operario> operarioEncontrados = null;

        if (operario == null) {

            operarioEncontrados = Fachada.getInstancia().buscar(Operario.class, null);
        } else {
            Criteria criterioOperario = Fachada.getInstancia().crearCriterio(Operario.class);
            if (operario.getCodigoOperario() != null) {
                criterioOperario.add(Restrictions.like("codigoOperario", operario.getCodigoOperario()));
            }

            if (operario.getApellidoOperario() != null) {
                criterioOperario.add(Restrictions.like("apellido", operario.getApellidoOperario()).ignoreCase());
            }

            operarioEncontrados = Fachada.getInstancia().buscar(Operario.class, criterioOperario);
        }
       if (operarioEncontrados.isEmpty()) {
        throw new ExpertoCentroDeTrabajoException("No se encontraron Operarios para los datos ingresados");
        
        }

        return operarioEncontrados;
    }
    
    
    public List<Maquina> buscarMaquina(DTOMaquina maquina) throws ExpertoCentroDeTrabajoException {

        List<Maquina> maquinaEncontrados = null;

        if (maquina == null) {

            maquinaEncontrados = Fachada.getInstancia().buscar(Maquina.class, null);
        } else {
            Criteria criterioMaquina = Fachada.getInstancia().crearCriterio(Maquina .class);
            if (maquina.getCodigoMaquina() != null) {
                criterioMaquina.add(Restrictions.like("codigo", maquina.getCodigoMaquina()));
            }

            if (maquina.getNombreMaquina() != null) {
                criterioMaquina.add(Restrictions.like("nombreMaquina", maquina.getNombreMaquina()).ignoreCase());
            }

            maquinaEncontrados = Fachada.getInstancia().buscar(Maquina.class, criterioMaquina);
        }
       if (maquinaEncontrados.isEmpty()) {
        throw new ExpertoCentroDeTrabajoException("No se encontraron Máquinas para los datos ingresados");
        
        }

        return maquinaEncontrados;
    }
    
    
    
    public List<Herramientas> buscarHerramienta(DTOHerramienta herramienta) throws ExpertoCentroDeTrabajoException {

        List<Herramientas> herramientaEncontrados = null;

        if (herramienta == null) {

            herramientaEncontrados = Fachada.getInstancia().buscar(Herramientas.class, null);
        } else {
            Criteria criterioHerramientas = Fachada.getInstancia().crearCriterio(Herramientas .class);
            if (herramienta.getCodigoHerramienta() != null) {
                criterioHerramientas.add(Restrictions.like("codigo", herramienta.getCodigoHerramienta()));
            }

            if (herramienta.getNombreHerramienta() != null) {
                criterioHerramientas.add(Restrictions.ilike("nombreHerramientas", herramienta.getNombreHerramienta()));
            }

            herramientaEncontrados = Fachada.getInstancia().buscar(Maquina.class, criterioHerramientas);
        }
       if (herramientaEncontrados.isEmpty()) {
        throw new ExpertoCentroDeTrabajoException("No se encontraron Herramientas para los datos ingresados");
        
        }

        return herramientaEncontrados;
    }
    
    
    
    

    public void guardar(MaestroDeCentroDeTrabajo centroDeTrabajo) throws ExpertoCentroDeTrabajoException {
        if (centroInvalido(centroDeTrabajo)) {
            throw new ExpertoCentroDeTrabajoException("Faltan completar Campos");
        } else {

            try {
                Fachada.getInstancia().guardar(centroDeTrabajo);
            } catch (Exception ex) {
                throw new ExpertoCentroDeTrabajoException("Error al guardar el Centro de Trabajo");
            }
        }
    }

    public void eliminar(MaestroDeCentroDeTrabajo centro) throws ExpertoCentroDeTrabajoException {

        centro.setEliminado(Boolean.TRUE);

        try {
            Fachada.getInstancia().guardar(centro);

        } catch (Exception ex) {
            throw new ExpertoCentroDeTrabajoException("Error al eliminar el Centro de Trabajo");
        }


    }

    private boolean centroInvalido(MaestroDeCentroDeTrabajo centro) {
        if (centro.getCodigo().length() == 0) {
            return true;
        } else if (centro.getDescripcion().length() == 0) {
            return true;
        } else {
            return false;
        }

    }
}
