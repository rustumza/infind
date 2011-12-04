/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author edu
 */
public class DTOPE {
    
    private String pePRodEstandar;
    private String totalDemandaPeriodo;
    private String peEnjuague;
    
    public DTOPE() {
    }


    public DTOPE(String estanda, String deso, String enjua) {
        this.totalDemandaPeriodo = deso;
        this.pePRodEstandar = estanda;
        this.peEnjuague = enjua;
    }

    public String getPeEnjuague() {
        return peEnjuague;
    }

    public void setPeEnjuague(String peEnjuague) {
        this.peEnjuague = peEnjuague;
    }

    public String getPePRodEstandar() {
        return pePRodEstandar;
    }

    public void setPePRodEstandar(String pePRodEstandar) {
        this.pePRodEstandar = pePRodEstandar;
    }

    public String getTotalDemandaPeriodo() {
        return totalDemandaPeriodo;
    }

    public void setTotalDemandaPeriodo(String totalDemandaPeriodo) {
        this.totalDemandaPeriodo = totalDemandaPeriodo;
    }

    
    
    
}
