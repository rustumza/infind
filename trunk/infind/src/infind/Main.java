/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infind;

import Entidades.Herramientas;
import Entidades.MaestroDeArticulo;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
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
import utilidades.ArmarPDF;

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
        List<MaestroDeArticulo> articulosDisponibles = null;
        Criteria criterioArticulo = Fachada.getInstancia().crearCriterioSinEliminado(MaestroDeArticulo.class);
        criterioArticulo.add(Restrictions.eq("eliminado", true));
        articulosDisponibles = Fachada.getInstancia().buscar(MaestroDeArticulo.class, criterioArticulo);
        for (MaestroDeArticulo maestroDeArticulos : articulosDisponibles) {
            if(maestroDeArticulos.getFechaEntrarEnActividad()!=null){
                if (maestroDeArticulos.getFechaEntrarEnActividad().before(fechaSistema)) {
                    maestroDeArticulos.setFechaEntrarEnActividad(null);
                    maestroDeArticulos.setEliminado(Boolean.FALSE);
                    Fachada.getInstancia().guardar(maestroDeArticulos);
                }
            }
        }
        //TODO esto es para probar como genera un PDF de forma muy facil y funciona como trompada
        //en utilidades hay una clase ArmarPDF para que lo vean
        List<Herramientas> herramientas = null;
        Criteria criterioHerramientas = Fachada.getInstancia().crearCriterio(Herramientas.class);
        herramientas = Fachada.getInstancia().buscar(Herramientas.class, criterioHerramientas);
        ArmarPDF pdf = new ArmarPDF();
       // pdf.armar(herramientas);
        new ControladorPantallaMadre().iniciar();
    }
}
