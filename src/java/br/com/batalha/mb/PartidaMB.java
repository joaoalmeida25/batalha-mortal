/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batalha.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joao
 */
@ManagedBean
@ViewScoped
public class PartidaMB extends DueloMB {

    private Integer n = 10;
    private Integer cont = 1;

    public PartidaMB() {

    }

    public void iniciarPartida() {
        for (int i = 0; i < n; i++) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Partida: " + cont, "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            batalhar();
            cont++;
        }
        if (cont >= 10) {
            cont = 1;
        }
    }
}
