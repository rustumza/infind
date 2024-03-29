/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import Entidades.Numerador;
import Entidades.Stock;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class ExpertoMateriaPrima extends Experto{

    MateriaPrima materiaPrimaExperto;
    
    public void guardar(MateriaPrima matPrim) {
        Stock stock = new Stock();
        stock.setCantidadFisicaReal(0);
        stock.setCantidadPorEntrar(0);
        stock.setCantidadReservada(0);
        stock.setEliminado(Boolean.TRUE);
        matPrim.setStock(stock);
        Fachada.getInstancia().guardar(matPrim);
        
        
        //sumarle uno al numerador
        List<Numerador> numeroDisponibles = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(Numerador.class);
        if((matPrim.getTipoMateriaPrima()).equals("Primaria")){
            criterioNumerador.add(Restrictions.eq("codificacion", "1.1."));
        }else if((matPrim.getTipoMateriaPrima()).equals("Secundaria -  Aromatizantes")){
            criterioNumerador.add(Restrictions.eq("codificacion", "1.2.1."));
        }else{
            criterioNumerador.add(Restrictions.eq("codificacion", "1.2.2."));
        
        }
        numeroDisponibles = Fachada.getInstancia().buscar(Numerador.class, criterioNumerador);
        Numerador numerador = numeroDisponibles.get(0);
        String numString = numerador.getUltimaClasificacion();
        int numInt = Integer.parseInt(numString);
        numInt++;
        numerador.setUltimaClasificacion(String.valueOf(numInt));
        Fachada.getInstancia().guardar(numerador);
        
    }

    public MateriaPrima buscarMateriaPrima(String codigoMateriaPrima) {
        
        List<MateriaPrima> listaMateriaPrima = null;
        Criteria criterioNumerador = Fachada.getInstancia().crearCriterioSinEliminado(MateriaPrima.class);
        criterioNumerador.add(Restrictions.eq("codigo", codigoMateriaPrima));
        listaMateriaPrima = Fachada.getInstancia().buscar(MateriaPrima.class, criterioNumerador);
        materiaPrimaExperto = listaMateriaPrima.get(0);
        return materiaPrimaExperto;
        
    }

    public MateriaPrima getMateriaPrima(){
        return materiaPrimaExperto;
    }

    public void editar(MateriaPrima matPrim) {
        Fachada.getInstancia().guardar(matPrim);
    }

}
