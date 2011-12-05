/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

import Entidades.indices.Indices;
import Entidades.indices.NivelAusentismo;
import Entidades.indices.NivelDeRechazos;
import Entidades.indices.RotacionExterna;
import Entidades.indices.RotacionInterna;
import Entidades.indices.SeguimientoPlanSugerencias;
import Entidades.indices.UsoDeHP;
import Fabricas.FabricaExpertos;
import excepciones.ExpertoCostosFijosException;
import expertos.ExpertoABMIndices;
import interfacesGraficas.PantallaABMIndices;
import interfacesGraficas.indices.PantallaCalidad;
import interfacesGraficas.indices.PantallaClimaSocial;
import interfacesGraficas.indices.PantallaEStructuraFinanciera;
import interfacesGraficas.indices.PantallaRelacionConClients;
import interfacesGraficas.indices.PantallaRendimiento;
import interfacesGraficas.indices.PantallaRentabilidad;
import interfacesGraficas.indices.PantallaRotacion;
import interfacesGraficas.indices.PantallaStock;
import interfacesGraficas.indices.PantallaVentas;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author edu
 */
public class ControladorABMIndices {

    PantallaABMIndices pantallaABMIndices;
    PantallaEStructuraFinanciera pantallaEstructuraFinanciera;
    PantallaRentabilidad pantallaRentabilidad;
    PantallaStock pantallaStock;
    PantallaClimaSocial pantallaCLimaSocial;
    PantallaVentas pantallaVentas;
    PantallaRelacionConClients pantallaRelacionClientes;
    PantallaCalidad pantallaCalidad;
    PantallaRotacion pantallaRotacion;
    PantallaRendimiento pantallaRendimiento;
    ControladorPantallaMadre controladorPantMadre;
    ExpertoABMIndices expertoABMIndice;
    Indices indice;
    Indices indices;
    NivelDeRechazos nuevoNivelRechazos;
    UsoDeHP nuevoUsoHP;
    RotacionExterna nuevaRotacionExterna;
    SeguimientoPlanSugerencias nuevoSeguimientoPlan;
    RotacionInterna nuevaRotacionInterna;
    NivelAusentismo nuevoNivelAusentismo;

    public ControladorABMIndices(ControladorPantallaMadre controlador) {

        controladorPantMadre = controlador;
        expertoABMIndice = (ExpertoABMIndices) FabricaExpertos.getInstancia().getExperto(FabricaExpertos.expertos.ABM_INDICE);

    }

    void iniciar() {
        pantallaABMIndices = new PantallaABMIndices(controladorPantMadre.getPantalla(), true, this);

        pantallaABMIndices.setLocationRelativeTo(null);
        pantallaABMIndices.setVisible(true);

    }

    public void crearEstructuraFinanciera() {
        pantallaEstructuraFinanciera = new PantallaEStructuraFinanciera(controladorPantMadre.getPantalla(), true, this);
        pantallaEstructuraFinanciera.setLocationRelativeTo(null);
        pantallaEstructuraFinanciera.setVisible(true);
    }

    public void crearRentabilidad() {
        pantallaRentabilidad = new PantallaRentabilidad(controladorPantMadre.getPantalla(), true, this);
        pantallaRentabilidad.setLocationRelativeTo(null);
        pantallaRentabilidad.setVisible(true);
    }

    public void crearStock() {
        pantallaStock = new PantallaStock(controladorPantMadre.getPantalla(), true, this);
        pantallaStock.setLocationRelativeTo(null);
        pantallaStock.setVisible(true);
    }

    public void crearClimaSocial() {
        pantallaCLimaSocial = new PantallaClimaSocial(controladorPantMadre.getPantalla(), true, this);
        pantallaCLimaSocial.setLocationRelativeTo(null);
        pantallaCLimaSocial.setVisible(true);
    }

    public void crearVentas() {
        pantallaVentas = new PantallaVentas(controladorPantMadre.getPantalla(), true, this);
        pantallaVentas.setLocationRelativeTo(null);
        pantallaVentas.setVisible(true);
    }

    public void crearRElacionCliente() {
        pantallaRelacionClientes = new PantallaRelacionConClients(controladorPantMadre.getPantalla(), true, this);
        pantallaRelacionClientes.setLocationRelativeTo(null);
        pantallaRelacionClientes.setVisible(true);
    }

    public void crearCalidad() {
        pantallaCalidad = new PantallaCalidad(controladorPantMadre.getPantalla(), true, this);
        pantallaCalidad.setLocationRelativeTo(null);
        pantallaCalidad.setVisible(true);

    }

    public void crearRotacion() {
        pantallaRotacion = new PantallaRotacion(controladorPantMadre.getPantalla(), true, this);
        pantallaRotacion.setLocationRelativeTo(null);
        pantallaRotacion.setVisible(true);
    }

    public void crearRendimiento() {
        pantallaRendimiento = new PantallaRendimiento(controladorPantMadre.getPantalla(), true, this);
        pantallaRendimiento.setLocationRelativeTo(null);
        pantallaRendimiento.setVisible(true);

    }

    public void guardarCalidad() {
        Date fecha = new Date();
        nuevoNivelRechazos = new NivelDeRechazos();
        nuevoUsoHP = new UsoDeHP();
        
        nuevoNivelRechazos.setFecha(fecha);
        nuevoNivelRechazos.setUnidadesProducidas(Integer.valueOf(pantallaCalidad.getCampoTotaUnidadesProducidad().getText()));
        nuevoNivelRechazos.setUnidadesRechazadas(Integer.valueOf(pantallaCalidad.getCampoUnidadesREchazadas().getText()));
        
        expertoABMIndice.guardarNivelRechazo(nuevoNivelRechazos);
        
        nuevoUsoHP.setFecha(fecha);
        nuevoUsoHP.setNivelDesperdicioHP(Double.valueOf(pantallaCalidad.getCampoNivelDesperdicio().getText()));
        nuevoUsoHP.setTotalHPUsada(Double.valueOf(pantallaCalidad.getCampoTotalHP().getText()));
        
        expertoABMIndice.guardarUsoHP(nuevoUsoHP);
        
    }

    public void guardarClimaSocial() {
        Date fecha = new Date();
        nuevaRotacionExterna = new RotacionExterna();
        nuevaRotacionInterna = new RotacionInterna();
        nuevoNivelAusentismo = new NivelAusentismo();
        nuevoSeguimientoPlan = new SeguimientoPlanSugerencias();
        
        nuevaRotacionExterna.setFecha(fecha);
        nuevaRotacionExterna.setCantidadPErsonal(Integer.valueOf(pantallaCLimaSocial.getCampoTotalPersonal().getText()));
        nuevaRotacionExterna.setBajasVoluntarias(Integer.valueOf(pantallaCLimaSocial.getCampoBajasVoluntarias().getText()));
        
        expertoABMIndice.guardarRotacionExterna(nuevaRotacionExterna);
        
        nuevaRotacionInterna.setFecha(fecha);
        nuevaRotacionInterna.setNroEmpleadosRotaron(Integer.valueOf(pantallaCLimaSocial.getCampoNroEmpleadosRotaron().getText()));
        nuevaRotacionInterna.setTotalEmpleados(Integer.valueOf(pantallaCLimaSocial.getCampoTotalPersonal().getText()));
        
        expertoABMIndice.guardarRotacionInterna(nuevaRotacionInterna);
        
        nuevoNivelAusentismo.setFecha(fecha);
        nuevoNivelAusentismo.setHorasATrabajas(Double.valueOf(pantallaCLimaSocial.getCampoHorasNormales().getText()));
        nuevoNivelAusentismo.setHorasAusentismo(Double.valueOf(pantallaCLimaSocial.getCampoHorasAusentismo().getText()));
        
        expertoABMIndice.guardarNivelAusentismo(nuevoNivelAusentismo);
        
        nuevoSeguimientoPlan.setFecha(fecha);
        nuevoSeguimientoPlan.setSugerenciasRecibidas(Integer.valueOf(pantallaCLimaSocial.getCampoSugerenciasREcibidads().getText()));
        nuevoSeguimientoPlan.setTotalEmpleados(Integer.valueOf(pantallaCLimaSocial.getCampoTotalPersonal().getText()));
        expertoABMIndice.guardarSeguimientPlan(nuevoSeguimientoPlan);
        
    }
}
