/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import DTOs.DTOPromedioPonderadoMovil;
import Entidades.Demanda;
import Entidades.MaestroDeArticulo;
import Entidades.Parametros;
import Metodo.Calculo;
import Metodo.Estacionalidad;
import Metodo.Simple;
import Metodo.Tendencia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author eduardo
 */
public class ExpertoPrediccionDemanda extends Experto{
    
    public List<MaestroDeArticulo> buscarProductos() {
        List<MaestroDeArticulo> productosEncontrados = null;
        Criteria criterioProducto = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioProducto.add(Restrictions.eq("eliminado", false));
        productosEncontrados = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioProducto);
        return productosEncontrados;
    }
    
    
     public void guardarParametros(Parametros pm) {
        Fachada.getInstancia().guardar(pm);
    }
    
  
    public List<DTOPromedioPonderadoMovil> calcularPPMovil(int periodos, MaestroDeArticulo articulo, int periodoDesde){
        
        List<Demanda> demanda = articulo.getDemanda();        
        Collections.sort(demanda, new OrdenarDemandas());
        float[] factor = new float[3];
        factor[0]=(float)0.5;
        factor[1]=(float)0.3;
        factor[2]=(float)0.2;
        
        
        int periodoInicial = periodoDesde;
        int periodoFinal = periodoDesde + periodos;
        List<DTOPromedioPonderadoMovil> dtoPPMovil = new ArrayList<DTOPromedioPonderadoMovil>();
        for (int i = periodoInicial; i < periodoFinal; i++) {
            DTOPromedioPonderadoMovil dto = new DTOPromedioPonderadoMovil();
            dto.setDemandaPronosticada((float)0);
            for (int j = 0; j < 3; j++) {
                if(i-(j+1)<demanda.size()){
                    dto.setDemandaPronosticada(dto.getDemandaPronosticada() + factor[j] *demanda.get(i-(j+1)).getDemandaHistorica());
                }else{
                    dto.setDemandaPronosticada(dto.getDemandaPronosticada() + factor[j] * dtoPPMovil.get(dtoPPMovil.size()-(j+1)).getDemandaPronosticada());                        
            
                }
            }
            if(i<=demanda.size()){
                dto.setDemanda(demanda.get(i-1));
                dto.setError(Math.abs(dto.getDemandaPronosticada() - dto.getDemanda().getDemandaHistorica()));
            }else{
                Demanda dem = new Demanda();
                dem.setPeriodo(dtoPPMovil.get(dtoPPMovil.size()-1).getDemanda().getPeriodo()+1);
                dto.setDemanda(dem);
                dto.setError(0);
            }
            
            
            dtoPPMovil.add(dto);

        }
        return dtoPPMovil;

    }

    public List<DTOPromedioPonderadoMovil> calcularPromExpSimple(int cantidadDePeriodos, int periodoDesde, float alfa, MaestroDeArticulo articulo) {
        Simple simp = new Simple();
        List<Demanda> demanda = articulo.getDemanda();        
        Collections.sort(demanda, new OrdenarDemandas());
        
        //List<double> lista = new ArrayList<double>();
        double[] lista = new double[cantidadDePeriodos];
        for (int i = periodoDesde; i < periodoDesde+cantidadDePeriodos; i++) {
            if(i <= demanda.size()){
                lista[i-periodoDesde] = demanda.get(i-1).getDemandaHistorica();
            }
            /*else{
                //Demanda dem = new Demanda();
                //dem.setPeriodo(i);
                lista.add((Float)0);
            }*/
        }
        
        
        double[] nuevaLista = simp.calcularSimple(lista, alfa, 0);
        
        List<DTOPromedioPonderadoMovil> listaFinal = new ArrayList<DTOPromedioPonderadoMovil>();
        for (int i = 0; i < nuevaLista.length; i++) {
                DTOPromedioPonderadoMovil dto = new DTOPromedioPonderadoMovil();
                Demanda dem = new Demanda();
                dto.setDemandaPronosticada(nuevaLista[i]);
                dem.setPeriodo(i+periodoDesde);
                dto.setDemanda(dem);
                if(i+periodoDesde-1<demanda.size()){
                    dto.getDemanda().setDemandaHistorica(demanda.get(i+periodoDesde-1).getDemandaHistorica());
                    dto.setError(Math.abs(dto.getDemandaPronosticada() - demanda.get(i+periodoDesde-1).getDemandaHistorica()));
                }else{
                    dto.setError((double)0);
                }
                
                listaFinal.add(dto);
        }
        return listaFinal;
        
    }

    public List<DTOPromedioPonderadoMovil> calcularTendencia(int cantidadDePeriodos, int periodoDesde, float alfa, float gama, MaestroDeArticulo articulo) {
        Tendencia tend = new Tendencia();
        List<Demanda> demanda = articulo.getDemanda();        
        Collections.sort(demanda, new OrdenarDemandas());
        
        double[] lista = new double[cantidadDePeriodos];
        for (int i = periodoDesde; i < periodoDesde+cantidadDePeriodos; i++) {
            if(i <= demanda.size()){
                lista[i-periodoDesde] = demanda.get(i-1).getDemandaHistorica();
            }
        }
        double[] nuevaLista = tend.calcularTendencia(lista, alfa, gama, demanda.size(), 0);
    
        List<DTOPromedioPonderadoMovil> listaFinal = new ArrayList<DTOPromedioPonderadoMovil>();
            for (int i = 0; i < nuevaLista.length; i++) {
                    DTOPromedioPonderadoMovil dto = new DTOPromedioPonderadoMovil();
                    Demanda dem = new Demanda();
                    dto.setDemandaPronosticada(nuevaLista[i]);
                    dem.setPeriodo(i+periodoDesde);
                    dto.setDemanda(dem);
                    if(i+periodoDesde-1<demanda.size()){
                        dto.getDemanda().setDemandaHistorica(demanda.get(i+periodoDesde-1).getDemandaHistorica());
                        dto.setError(Math.abs(dto.getDemandaPronosticada() - demanda.get(i+periodoDesde-1).getDemandaHistorica()));
                    }else{
                        dto.setError((double)0);
                    }
                    listaFinal.add(dto);
            }
        return listaFinal;
    
    }
    
    
    private static class OrdenarDemandas implements Comparator<Demanda> {

        public int compare(Demanda t, Demanda t1) {
            return new Integer(t.getPeriodo()).compareTo(t1.getPeriodo());
        }
    }

}
