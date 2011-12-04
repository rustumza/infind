/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOPromedioPonderadoMovil {

    private Double demandaPronosticada;

    public Double getDemandaPronosticada() {
        return demandaPronosticada;
    }

    public void setDemandaPronosticada(Double demandaPronosticada) {
        this.demandaPronosticada = demandaPronosticada;
    }

    public DTOPromedioPonderadoMovil() {
    }

    public DTOPromedioPonderadoMovil(Double demandaPronosticada) {
        this.demandaPronosticada = demandaPronosticada;
    }
}