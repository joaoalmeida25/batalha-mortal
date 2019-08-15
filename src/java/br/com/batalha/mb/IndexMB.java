package br.com.batalha.mb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.batalha.controller.HeroisController;
import br.com.batalha.exception.HeroisException;
import br.com.batalha.model.dto.HeroiDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class IndexMB implements Serializable {

    private List<HeroiDto> listaHerois;

    public IndexMB() {
        listaHerois = new ArrayList<>();
        try {
            HeroisController hc = new HeroisController();
            setListaHerois(hc.carregarHerois());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista carregada!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (HeroisException e) {
            System.out.println("Erro: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar lista...", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the listaHerois
     */
    public List<HeroiDto> getListaHerois() {
        return listaHerois;
    }

    /**
     * @param listaHerois the listaHerois to set
     */
    public void setListaHerois(List<HeroiDto> listaHerois) {
        this.listaHerois = listaHerois;
    }

}
