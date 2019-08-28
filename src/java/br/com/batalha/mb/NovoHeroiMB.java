/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.batalha.mb;

import br.com.batalha.controller.HeroisController;
import br.com.batalha.exception.HeroisException;
import br.com.batalha.model.dto.HeroiDto;
import java.io.Serializable;
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
public class NovoHeroiMB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private HeroiDto novoHeroi = new HeroiDto();

    ;
    
    public NovoHeroiMB() {
    }
    
    public void limpar() {
        novoHeroi.setAlinhamento(null);
        novoHeroi.setCombate(null);
        novoHeroi.setInteligencia(null);
        novoHeroi.setDefesa(null);
        novoHeroi.setNome(null);
        novoHeroi.setPoder(null);
        novoHeroi.setDestreza(null);
        novoHeroi.setPoder(null);
    }
    
    public void salvar() {
        HeroisController hc = new HeroisController();
        try {
            if (hc.validarHeroi(novoHeroi)) {
                hc.salvarHeroi(novoHeroi);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Herói inserido!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);                
            }
        } catch (HeroisException ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public HeroiDto getNovoHeroi() {
        return novoHeroi;
    }
    
    public void setNovoHeroi(HeroiDto novoHeroi) {
        this.novoHeroi = novoHeroi;
    }
}
