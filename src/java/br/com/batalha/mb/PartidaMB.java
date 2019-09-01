package br.com.batalha.mb;

import java.io.IOException;
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

    private final Integer n = 10;
    private Integer cont = 1;
    
    // Método que inicia um duelo de 10 rounds
    public void iniciarPartida() throws IOException {
        try {
            for (int i = 0; i < n; i++) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Partida: " + cont, "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                batalhar();
                cont++;
            }
            if (cont >= 10) {
                cont = 1;
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao iniciar partida", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
