/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import Entidades.Demanda;

/**
 *
 * @author edu
 */
public class DTOPromedioPonderadoMovil {

    private double  demandaPronosticada;
    private Demanda demanda;
    private double error;

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getDemandaPronosticada() {
        return demandaPronosticada;
    }

    public void setDemandaPronosticada(double demandaPronosticada) {
        this.demandaPronosticada = demandaPronosticada;
    }

    public DTOPromedioPonderadoMovil() {
    }

    public DTOPromedioPonderadoMovil(double demandaPronosticada) {
        this.demandaPronosticada = demandaPronosticada;
    }

    public Demanda getDemanda() {
        return demanda;
    }

    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }
    
    
}