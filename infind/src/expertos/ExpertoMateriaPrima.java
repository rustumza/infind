/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expertos;

import Entidades.MateriaPrima;
import Entidades.Numerador;
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
}
