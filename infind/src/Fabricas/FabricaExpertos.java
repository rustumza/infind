/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabricas;

import expertos.Experto;
import persistencia.Decoradores.DecoradorCentroDeTrabajo;
import persistencia.Decoradores.DecoradorOperarios;

/**
 *
 * @author diego
 */
public class FabricaExpertos {

    private static FabricaExpertos instancia;

    public static FabricaExpertos getInstancia() {
        if (instancia == null) {
            instancia = new FabricaExpertos();
        }
        return instancia;
    }

    public enum expertos {

        CENTRO_DE_TRABAJO, OPERARIOS
    }

    public Experto getExperto(expertos expertoNombre) {
        switch (expertoNombre) {
            case CENTRO_DE_TRABAJO:
                return new DecoradorCentroDeTrabajo();
            case OPERARIOS:
                return new DecoradorOperarios();
            default:
                return null;

        }
    }
}
