package br.com.batalha.mb;

import br.com.batalha.controller.HeroisController;
import br.com.batalha.exception.PersonagemException;
import br.com.batalha.model.PersonagemModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
    private PersonagemModel novoHeroi;
    
    @PostConstruct
    public void init() {
        novoHeroi = new PersonagemModel(); // instancia um novo heroi ao carregar a página
    }
    
    // Limpa atributos dos herois
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
    
    // Método que salva o heroi temporariamente na lista carregada com todos os herois
    public void salvar() {
        HeroisController hc = new HeroisController();
        try {
            if (hc.validarHeroi(novoHeroi)) {
                hc.salvarHeroi(novoHeroi);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Herói inserido!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);                
            }
        } catch (PersonagemException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir herói", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public PersonagemModel getNovoHeroi() {
        return novoHeroi;
    }
    
    public void setNovoHeroi(PersonagemModel novoHeroi) {
        this.novoHeroi = novoHeroi;
    }
}
