package br.com.batalha.mb;

import br.com.batalha.controller.HeroisController;
import br.com.batalha.model.PersonagemModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class IndexMB implements Serializable {

    private List<PersonagemModel> listaHerois;
    
    @PostConstruct
    public void init() {
        carregarLista(); // Carrega a lista assim que a página for chamada
    }
    
    public Integer qntdHerois(){
        return listaHerois.size();
    }
    
    // Método que carrega a lista de hérois
    public final void carregarLista() {
        listaHerois = new ArrayList<>();
        HeroisController hc = new HeroisController();
        try {
            listaHerois = hc.carregarHerois();
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao carregar lista", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the listaHerois
     */
    public List<PersonagemModel> getListaHerois() {
        return listaHerois;
    }

    /**
     * @param listaHerois the listaHerois to set
     */
    public void setListaHerois(List<PersonagemModel> listaHerois) {
        this.listaHerois = listaHerois;
    }

}
