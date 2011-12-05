/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.CostosFijos;
import Entidades.indices.DesarrolloCarteraCLiente;
import Entidades.indices.EficienciaVendedor;
import Entidades.indices.GradoDependenciaEmpresa;
import Entidades.indices.IndiceLiquides;
import Entidades.indices.Indices;
import Entidades.indices.NivelAusentismo;
import Entidades.indices.NivelDeCredito;
import Entidades.indices.NivelDeRechazos;
import Entidades.indices.QueSeDebe;
import Entidades.indices.RendimientoDeLasVentas;
import Entidades.indices.Rentabiliadda;
import Entidades.indices.RotacionDeLasInversiones;
import Entidades.indices.RotacionDeLosCapitalesPropios;
import Entidades.indices.RotacionDeStock;
import Entidades.indices.RotacionExterna;
import Entidades.indices.RotacionInterna;
import Entidades.indices.SeguimientoPlanSugerencias;
import Entidades.indices.UsoDeHP;
import Entidades.indices.VentasEnCuotas;
import excepciones.ExpertoCostosFijosException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author edu
 */
public class ExpertoABMIndices extends Experto {

    public void guardarIndices(Indices indice) {
        Fachada.getInstancia().guardar(indice);
    }

    public Indices buscarIndices() throws ExpertoCostosFijosException {

        List<Indices> indicesEncontrados = null;

        Criteria criterioIndices = Fachada.getInstancia().crearCriterioSinEliminado(Indices.class);
        criterioIndices.add(Restrictions.eq("eliminado", false));
        indicesEncontrados = Fachada.getInstancia().buscar(Indices.class, criterioIndices);

        return indicesEncontrados.get(0);
    }

    public void guardarNivelRechazo(NivelDeRechazos nuevoNivelRechazos) {
        Fachada.getInstancia().guardar(nuevoNivelRechazos);
    }

    public void guardarUsoHP(UsoDeHP nuevoUsoHP) {
        Fachada.getInstancia().guardar(nuevoUsoHP);
    }

    public void guardarRotacionExterna(RotacionExterna nuevaRotacionExterna) {
        Fachada.getInstancia().guardar(nuevaRotacionExterna);
    }

    public void guardarRotacionInterna(RotacionInterna nuevaRotacionInterna) {
        Fachada.getInstancia().guardar(nuevaRotacionInterna);
    }

    public void guardarNivelAusentismo(NivelAusentismo nuevoNivelAusentismo) {
        Fachada.getInstancia().guardar(nuevoNivelAusentismo);
    }

    public void guardarSeguimientPlan(SeguimientoPlanSugerencias nuevoSeguimientoPlan) {
        Fachada.getInstancia().guardar(nuevoSeguimientoPlan);
    }

    public void guardarIndiceLiquides(IndiceLiquides nuevoIndiceLiquidez) {
        Fachada.getInstancia().guardar(nuevoIndiceLiquidez);
    }

    public void guardarGradoDependenciaEmpresa(GradoDependenciaEmpresa nuevoGradoDependenciaEmpresa) {
        Fachada.getInstancia().guardar(nuevoGradoDependenciaEmpresa);
    }

    public void guardarQueSeDebe(QueSeDebe nuevoQueSeDebe) {
        Fachada.getInstancia().guardar(nuevoQueSeDebe);
    }

    public void guardarNivelCredito(NivelDeCredito nuevoNivelCredito) {
        Fachada.getInstancia().guardar(nuevoNivelCredito);
    }

    public void guardarDesarrolloCarteraCliente(DesarrolloCarteraCLiente nuevoDesarrolloCarteraCliente) {
        Fachada.getInstancia().guardar(nuevoDesarrolloCarteraCliente);
    }

    public void guardarrendimiento(RendimientoDeLasVentas nuevoRendimiento) {
        Fachada.getInstancia().guardar(nuevoRendimiento);
    }

    public void guardarrentabilidad(Rentabiliadda nuevoRentabilidad) {
        Fachada.getInstancia().guardar(nuevoRentabilidad);
    }

    public void guardarRotacionCapitalesPropios(RotacionDeLosCapitalesPropios nuevoRotacionCapitalesPropios) {
        Fachada.getInstancia().guardar(nuevoRotacionCapitalesPropios);
    }

    public void guardarRotacionInversiones(RotacionDeLasInversiones nuevoRotacionInversiones) {
        Fachada.getInstancia().guardar(nuevoRotacionInversiones);
    }

    public void guardarRotacionStock(RotacionDeStock nuevoRotacionStock) {
        Fachada.getInstancia().guardar(nuevoRotacionStock);
    }

    public void guardarVentasCuotas(VentasEnCuotas nuevoVentasCuotas) {
        Fachada.getInstancia().guardar(nuevoVentasCuotas);
    }

    public void guardarEficienciaVendedor(EficienciaVendedor nuevoEficienciaVendedor) {
        Fachada.getInstancia().guardar(nuevoEficienciaVendedor);
    }
    
    
}
