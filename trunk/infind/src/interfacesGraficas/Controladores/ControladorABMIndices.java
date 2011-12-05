/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.Controladores;

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
    GradoDependenciaEmpresa nuevoGradoDependenciaEmpresa;
    IndiceLiquides nuevoIndiceLiquidez;
    NivelDeCredito nuevoNivelCredito;
    QueSeDebe nuevoQueSeDebe;
    DesarrolloCarteraCLiente nuevoDesarrolloCarteraCliente;
    VolumenDeCompra nuevoVolumenDeComra;
    RendimientoDeLasVentas nuevoRendimiento;
    Rentabiliadda nuevoRentabilidad;
    RotacionDeLasInversiones nuevoRotacionInversiones;
    RotacionDeLosCapitalesPropios nuevoRotacionCapitalesPropios;
    RotacionDeStock nuevoRotacionStock;
    VentasEnCuotas nuevoVentasCuotas;
    EficienciaVendedor nuevoEficienciaVendedor;
    
    
    

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
        JOptionPane.showMessageDialog(pantallaCalidad, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaCalidad.getCampoNivelDesperdicio().setText("");
        pantallaCalidad.getCampoTotaUnidadesProducidad().setText("");
        pantallaCalidad.getCampoTotalHP().setText("");
        pantallaCalidad.getCampoUnidadesREchazadas().setText("");
        pantallaCalidad.setVisible(false);


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
        JOptionPane.showMessageDialog(pantallaCLimaSocial, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaCLimaSocial.getCampoBajasVoluntarias().setText("");
        pantallaCLimaSocial.getCampoHorasAusentismo().setText("");
        pantallaCLimaSocial.getCampoHorasNormales().setText("");
        pantallaCLimaSocial.getCampoNroEmpleadosRotaron().setText("");
        pantallaCLimaSocial.getCampoSugerenciasREcibidads().setText("");
        pantallaCLimaSocial.getCampoTotalPersonal().setText("");
        pantallaCLimaSocial.setVisible(false);


    }

    public void guardarEstructuraFinanciera() {
        Date fecha = new Date();

        nuevoNivelCredito = new NivelDeCredito();
        nuevoQueSeDebe = new QueSeDebe();
        nuevoGradoDependenciaEmpresa = new GradoDependenciaEmpresa();
        nuevoIndiceLiquidez = new IndiceLiquides();


        nuevoNivelCredito.setFecha(fecha);
        nuevoNivelCredito.setCuentasPorCobrar(Double.valueOf(pantallaEstructuraFinanciera.getCampoDeudasxcobrar().getText()));
        nuevoNivelCredito.setPromocionCobro(Double.valueOf(pantallaEstructuraFinanciera.getCampoProCobros().getText()));
        expertoABMIndice.guardarNivelCredito(nuevoNivelCredito);

        nuevoQueSeDebe.setFecha(fecha);
        nuevoQueSeDebe.setDeudasQueContraigo(Double.valueOf(pantallaEstructuraFinanciera.getCampoDeudasContraigo().getText()));
        nuevoQueSeDebe.setPasivoaCortoPlazo(Double.valueOf(pantallaEstructuraFinanciera.getCampoPasivoCortoPlazo().getText()));

        expertoABMIndice.guardarQueSeDebe(nuevoQueSeDebe);

        nuevoGradoDependenciaEmpresa.setFecha(fecha);
        nuevoGradoDependenciaEmpresa.setPasivoPropio(Integer.valueOf(pantallaEstructuraFinanciera.getCampoPasivoPropio().getText()));
        nuevoGradoDependenciaEmpresa.setPasivoTotal(Integer.valueOf(pantallaEstructuraFinanciera.getCampoPasivoTotal().getText()));

        expertoABMIndice.guardarGradoDependenciaEmpresa(nuevoGradoDependenciaEmpresa);


        nuevoIndiceLiquidez.setFecha(fecha);
        nuevoIndiceLiquidez.setDisponibilidad(Double.valueOf(pantallaEstructuraFinanciera.getCampoDisponibilidad().getText()));
        nuevoIndiceLiquidez.setPasivoCorriente(Double.valueOf(pantallaEstructuraFinanciera.getCampoPasivoCorriente().getText()));
        nuevoIndiceLiquidez.setRealizableACortoPlazo(Double.valueOf(pantallaEstructuraFinanciera.getCampoRealizCortoPlazo().getText()));

        expertoABMIndice.guardarIndiceLiquides(nuevoIndiceLiquidez);

        JOptionPane.showMessageDialog(pantallaCLimaSocial, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaEstructuraFinanciera.getCampoDeudasContraigo().setText("");
        pantallaEstructuraFinanciera.getCampoDeudasxcobrar().setText("");
        pantallaEstructuraFinanciera.getCampoDisponibilidad().setText("");
        pantallaEstructuraFinanciera.getCampoPasivoCorriente().setText("");
        pantallaEstructuraFinanciera.getCampoPasivoCortoPlazo().setText("");
        pantallaEstructuraFinanciera.getCampoPasivoPropio().setText("");
        pantallaEstructuraFinanciera.getCampoPasivoTotal().setText("");
        pantallaEstructuraFinanciera.getCampoProCobros().setText("");
        pantallaEstructuraFinanciera.getCampoRealizCortoPlazo().setText("");
        pantallaEstructuraFinanciera.setVisible(false);
        


    }

    public void guardarRelacionCliente() {
         Date fecha = new Date();
         nuevoDesarrolloCarteraCliente = new DesarrolloCarteraCLiente();
         nuevoVolumenDeComra = new VolumenDeCompra();
         
         nuevoDesarrolloCarteraCliente.setFecha(fecha);
         nuevoDesarrolloCarteraCliente.setClientesNuevos(Integer.valueOf(pantallaRelacionClientes.getCampoNroClientesNuevos().getText()));
         nuevoDesarrolloCarteraCliente.setTotalCLientesM(Integer.valueOf(pantallaRelacionClientes.getCampoTotalClientes().getText()));
         
        expertoABMIndice.guardarDesarrolloCarteraCliente(nuevoDesarrolloCarteraCliente); 
        
        JOptionPane.showMessageDialog(pantallaRelacionClientes, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaRelacionClientes.getCampoClientesPrincipales().setText("");
        pantallaRelacionClientes.getCampoNroClientesNuevos().setText("");
        pantallaRelacionClientes.getCampoTotalClientes().setText("");
        
        pantallaRelacionClientes.setVisible(false);
         
    }

    public void guardarRendimiento() {
        Date fecha = new Date();
        nuevoRendimiento = new RendimientoDeLasVentas();
        
        nuevoRendimiento.setFecha(fecha);
        nuevoRendimiento.setUtilidades(Double.valueOf(pantallaRendimiento.getCampoUtilidades().getText()));
        nuevoRendimiento.setVenta(Double.valueOf(pantallaRendimiento.getCampoVentas().getText()));
        expertoABMIndice.guardarrendimiento(nuevoRendimiento); 
        
        JOptionPane.showMessageDialog(pantallaRendimiento, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaRendimiento.getCampoUtilidades().setText("");
        pantallaRendimiento.getCampoVentas().setText("");
        pantallaRendimiento.setVisible(false);
        
    }

    public void guardarRentabilidad() {
        Date fecha = new Date();
        nuevoRentabilidad = new Rentabiliadda();
        
        nuevoRentabilidad.setFecha(fecha);
        nuevoRentabilidad.setActivoTotal(Double.valueOf(pantallaRentabilidad.getCampoActivoTotal().getText()));
        nuevoRentabilidad.setUtilidades(Double.valueOf(pantallaRentabilidad.getCampoUtilidad().getText()));
        expertoABMIndice.guardarrentabilidad(nuevoRentabilidad); 
        
        JOptionPane.showMessageDialog(pantallaRentabilidad, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaRentabilidad.getCampoUtilidad().setText("");
        pantallaRentabilidad.getCampoActivoTotal().setText("");
        pantallaRentabilidad.setVisible(false);
        
    }

    public void guardarRotacioin() {
        Date fecha = new Date();
        
        nuevoRotacionCapitalesPropios = new RotacionDeLosCapitalesPropios();
        nuevoRotacionInversiones = new RotacionDeLasInversiones();
        nuevoRotacionStock = new RotacionDeStock();
        
        nuevoRotacionCapitalesPropios.setFecha(fecha);
        nuevoRotacionCapitalesPropios.setActivoNeto(Double.valueOf(pantallaRotacion.getCampoActivoNeto().getText()));
        nuevoRotacionCapitalesPropios.setVentas(Double.valueOf(pantallaRotacion.getCampoVentas().getText()));
         expertoABMIndice.guardarRotacionCapitalesPropios(nuevoRotacionCapitalesPropios); 
         
         nuevoRotacionCapitalesPropios.setFecha(fecha);
         nuevoRotacionInversiones.setActivoTotal(Double.valueOf(pantallaRotacion.getCampoActivoTotal().getText()));
         nuevoRotacionInversiones.setVentas(Double.valueOf(pantallaRotacion.getCampoVentas().getText()));
         expertoABMIndice.guardarRotacionInversiones(nuevoRotacionInversiones); 
         
         nuevoRotacionStock.setFecha(fecha);
         nuevoRotacionStock.setNivelStock(Double.valueOf(pantallaRotacion.getCampoNivelStock().getText()));
         nuevoRotacionStock.setVentas(Double.valueOf(pantallaRotacion.getCampoVentas().getText()));
         expertoABMIndice.guardarRotacionStock(nuevoRotacionStock); 
        
        JOptionPane.showMessageDialog(pantallaRotacion, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaRotacion.getCampoActivoNeto().setText("");
        pantallaRotacion.getCampoActivoTotal().setText("");
        pantallaRotacion.getCampoNivelStock().setText("");
        pantallaRotacion.getCampoVentas().setText("");
        pantallaRotacion.setVisible(false);
        
                
        
    }

    public void guardarVentas() {
        Date fecha = new Date();
        nuevoEficienciaVendedor = new EficienciaVendedor();
        nuevoVentasCuotas = new VentasEnCuotas();
        
        nuevoEficienciaVendedor.setFecha(fecha);
        nuevoEficienciaVendedor.setNroVendedores(Integer.valueOf(pantallaVentas.getCampoNroVendedores().getText()));
        nuevoEficienciaVendedor.setVentasDelegacion(Double.valueOf(pantallaVentas.getCampoVentasDelegacion().getText()));
         expertoABMIndice.guardarEficienciaVendedor(nuevoEficienciaVendedor); 
         
         nuevoVentasCuotas.setFecha(fecha);
         nuevoVentasCuotas.setVentasEnCuotas(Double.valueOf(pantallaVentas.getCampoVentasCotas().getText()));
         nuevoVentasCuotas.setVentasTotales(Double.valueOf(pantallaVentas.getCampoVentasTotales().getText()));
         expertoABMIndice.guardarVentasCuotas(nuevoVentasCuotas); 
          
        JOptionPane.showMessageDialog(pantallaVentas, "Datos Guardados Correctamente", "ATENCIÓN", JOptionPane.INFORMATION_MESSAGE);
        pantallaVentas.getCampoNroVendedores().setText("");
        pantallaVentas.getCampoVentasCotas().setText("");
        pantallaVentas.getCampoVentasDelegacion().setText("");
        pantallaVentas.getCampoVentasTotales().setText("");
        pantallaVentas.setVisible(false);
    }
}
