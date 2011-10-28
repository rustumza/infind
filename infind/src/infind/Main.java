/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infind;

import Entidades.MaestroDeArticulo;
import Entidades.MaestroDeCentroDeTrabajo;
import Entidades.MateriaPrima;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import excepciones.ExpertoCentroDeTrabajoException;
import interfacesGraficas.Controladores.ControladorPantallaMadre;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistencia.Conexion;
import persistencia.Fachada;

/**
 *
 * @author rustu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Conexion.getInstancia().getSession();
        //ControladorPantallaMadre cpm = new ControladorPantallaMadre();
        //cpm.iniciar();
        Date fechaSistema = new Date();
        List<MateriaPrima> articulosDisponibles = null;
        Criteria criterioArticulo = Fachada.getInstancia().crearCriterioSinEliminado(MateriaPrima.class);
        criterioArticulo.add(Restrictions.eq("eliminado", true));
        articulosDisponibles = Fachada.getInstancia().buscar(MateriaPrima.class, criterioArticulo);
        for (MateriaPrima materiaPrima : articulosDisponibles) {
            if (materiaPrima.getFechaEntrarEnActividad().before(fechaSistema) || materiaPrima.getFechaEntrarEnActividad().equals(fechaSistema)) {
                System.out.println("Eliminado?: " + materiaPrima.getEliminado() + "  Nombre: " + materiaPrima.getNombre());
                materiaPrima.setFechaEntrarEnActividad(null);
                materiaPrima.setEliminado(Boolean.FALSE);
                Conexion.getInstancia().iniciarTX();
                Fachada.getInstancia().guardar(materiaPrima);
                Conexion.getInstancia().confirmarTx();
            }
        }

        new ControladorPantallaMadre().iniciar();
    }
}
