/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author rustu
 */
public class DTOGestionDeInventario {
    private float costoDeAlmacenamiento;
    private float costoDePedido;
    private float loteOptimo;
    private String tipoInventario;

    public float getCostoDeAlmacenamiento() {
        return costoDeAlmacenamiento;
    }

    public void setCostoDeAlmacenamiento(float costoDeAlmacenamiento) {
        this.costoDeAlmacenamiento = costoDeAlmacenamiento;
    }

    public float getCostoDePedido() {
        return costoDePedido;
    }

    public void setCostoDePedido(float costoDePedido) {
        this.costoDePedido = costoDePedido;
    }

    public float getLoteOptimo() {
        return loteOptimo;
    }

    public void setLoteOptimo(float loteOptimo) {
        this.loteOptimo = loteOptimo;
    }

    public String getTipoInventario() {
        return tipoInventario;
    }

    public void setTipoInventario(String tipoInventario) {
        this.tipoInventario = tipoInventario;
    }
    
    
    
    
}
