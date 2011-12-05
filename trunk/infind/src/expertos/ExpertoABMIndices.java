/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOIndices;
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
import Entidades.indices.VolumenDeCompra;
import excepciones.ExpertoCostosFijosException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Conexion;
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

    public List<DTOIndices> buscarIndicesParaPantalla() {
        List<DTOIndices> listaDto = new ArrayList<DTOIndices>();
        List<Indices> listaInd = null;
        Criteria criterioInd = Fachada.getInstancia().crearCriterio(Indices.class);
        listaInd = Fachada.getInstancia().buscarSinTx(Indices.class, criterioInd);
        for (Indices indices : listaInd) {
            DTOIndices dto = new DTOIndices();
            if(indices.getNombre().equals("Desarrollo Cartera Cliente")){ //
                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(DesarrolloCarteraCLiente.class);
                List<DesarrolloCarteraCLiente> lista1 = Fachada.getInstancia().buscarSinTx(DesarrolloCarteraCLiente.class, criterio1);
                if(!lista1.isEmpty()){
                DesarrolloCarteraCLiente desarrolloCarteraCLiente = lista1.get(0);
                for (DesarrolloCarteraCLiente desarrolloCarteraCLiente1 : lista1) {
                    if(desarrolloCarteraCLiente1.getId() > desarrolloCarteraCLiente.getId()){
                        desarrolloCarteraCLiente = desarrolloCarteraCLiente1;
                    }
                }
                dto.setNormalidad(valoresEntre(desarrolloCarteraCLiente.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(desarrolloCarteraCLiente.getTotal());
                }
                listaDto.add(dto);
                

            }else if(indices.getNombre().equals("Grado Dependencia Empresa")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(GradoDependenciaEmpresa.class);
                List<GradoDependenciaEmpresa> lista1 = Fachada.getInstancia().buscarSinTx(GradoDependenciaEmpresa.class, criterio1);
                if(!lista1.isEmpty()){
                GradoDependenciaEmpresa objAux = lista1.get(0);
                for (GradoDependenciaEmpresa aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
                
            }else
            if(indices.getNombre().equals("Indice Liquides")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(IndiceLiquides.class);
                List<IndiceLiquides> lista1 = Fachada.getInstancia().buscarSinTx(IndiceLiquides.class, criterio1);
                if(!lista1.isEmpty()){
                IndiceLiquides objAux = lista1.get(0);
                for (IndiceLiquides aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
                    
            }else
            if(indices.getNombre().equals("Nivel Ausentismo")){//

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(NivelAusentismo.class);
                List<NivelAusentismo> lista1 = Fachada.getInstancia().buscarSinTx(NivelAusentismo.class, criterio1);
                if(!lista1.isEmpty()){
                NivelAusentismo objAux = lista1.get(0);
                for (NivelAusentismo aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
            }else
            if(indices.getNombre().equals("Nivel De Credito")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(NivelDeCredito.class);
                List<NivelDeCredito> lista1 = Fachada.getInstancia().buscarSinTx(NivelDeCredito.class, criterio1);
                if(!lista1.isEmpty()){
                NivelDeCredito objAux = lista1.get(0);
                for (NivelDeCredito aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
            }else
            if(indices.getNombre().equals("Que Se Debe")){ //
            
                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(QueSeDebe.class);
                List<QueSeDebe> lista1 = Fachada.getInstancia().buscarSinTx(QueSeDebe.class, criterio1);
                if(!lista1.isEmpty()){
                QueSeDebe objAux = lista1.get(0);
                for (QueSeDebe aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
                
            }else
            if(indices.getNombre().equals("Rotacion Externa")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(RotacionExterna.class);
                List<RotacionExterna> lista1 = Fachada.getInstancia().buscarSinTx(RotacionExterna.class, criterio1);
                if(!lista1.isEmpty()){
                RotacionExterna objAux = lista1.get(0);
                for (RotacionExterna aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                
                listaDto.add(dto);
            }else
            if(indices.getNombre().equals("Rotacion Interna")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(RotacionInterna.class);
                List<RotacionInterna> lista1 = Fachada.getInstancia().buscarSinTx(RotacionInterna.class, criterio1);
                if(!lista1.isEmpty()){
                RotacionInterna objAux = lista1.get(0);
                for (RotacionInterna aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
            }else
            if(indices.getNombre().equals("Seguimiento Plan Sugerencias")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(SeguimientoPlanSugerencias.class);
                List<SeguimientoPlanSugerencias> lista1 = Fachada.getInstancia().buscarSinTx(SeguimientoPlanSugerencias.class, criterio1);
                if(!lista1.isEmpty()){
                SeguimientoPlanSugerencias objAux = lista1.get(0);
                for (SeguimientoPlanSugerencias aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
            }else
            if(indices.getNombre().equals("Volumen De Compra")){ //

                dto.setInd(indices);
                Criteria criterio1 = Fachada.getInstancia().crearCriterio(VolumenDeCompra.class);
                List<VolumenDeCompra> lista1 = Fachada.getInstancia().buscarSinTx(VolumenDeCompra.class, criterio1);
                if(!lista1.isEmpty()){
                VolumenDeCompra objAux = lista1.get(0);
                for (VolumenDeCompra aux : lista1) {
                    if(aux.getId() > objAux.getId()){
                        objAux = aux;
                    }
                }
                dto.setNormalidad(valoresEntre(objAux.getTotal(), indices.getMinimo(), indices.getMaximo()));
                dto.setValor(objAux.getTotal());
                }
                listaDto.add(dto);
            }
        
        }
        
        
        return listaDto;
    }
    
 
    public int valoresEntre(double valor, double menor, double mayor){
        if(valor< menor)
            return -1;
        else if(valor> mayor)
            return 1;
        else return 0;
    
    }
    
    public void guardarValoresNormales(List<Indices> lista) {
    
        for (Indices indices : lista) {
            Conexion.getInstancia().iniciarTX();
            Fachada.getInstancia().guardarSinTranasaccion(indices);
            Conexion.getInstancia().confirmarTx();
        }
        
    }
    
}
