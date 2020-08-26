package br.com.batalha.mb;

import br.com.batalha.controller.HeroisController;
import br.com.batalha.exception.PersonagemException;
import br.com.batalha.model.PersonagemModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class DueloMB implements Serializable {

    private List<PersonagemModel> listaDuelo = new ArrayList<>();
    private List<PersonagemModel> listaVencedor = new ArrayList<>();
    private PersonagemModel p1 = new PersonagemModel();
    private PersonagemModel p2 = new PersonagemModel();
    private String atributo;
    private Integer contDuelo = 1;

    @PostConstruct
    public void init() {

    }

    // Método que randomiza os heróis carregados da lista
    public void randomHerois() {
        Random r = new Random();
        HeroisController hc = new HeroisController();
        contDuelo = 1;
        try {
            while (listaDuelo.size() < 2) {
                List<PersonagemModel> auxLista = hc.carregarHerois();
                int n1 = r.nextInt(auxLista.size());
                int n2 = r.nextInt(auxLista.size());

                if (n1 == n2) {
                    randomHerois();
                } else {
                    p1 = auxLista.get(n1);
                    p2 = auxLista.get(n2);
                    if (!p1.getAlinhamento().equals(p2.getAlinhamento())) {
                        listaDuelo.add(p1);
                        listaDuelo.add(p2);
                        randomAtributo();
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dois Heróis foram escolhidos.", "");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } else {
                        randomHerois();
                    }
                }
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao randomizar Heróis.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    // Método para limpar lista de duelo e a lista dos vencedores
    public void removerLista() {
        if (!listaDuelo.isEmpty()) {
            for (PersonagemModel p : listaDuelo) {
                listaDuelo.remove(p);
            }
            listaDuelo = new ArrayList<>();
        }
        contDuelo = 1;
    }

    public void removerListaVencedor() {
        if (!listaVencedor.isEmpty()) {
            for (PersonagemModel p : listaVencedor) {
                listaVencedor.remove(p);
            }
            listaVencedor = new ArrayList<>();
        }
    }

    // Método para randomizar o atributo do combate
    public void randomAtributo() {
        Random r = new Random();
        HeroisController hc = new HeroisController();
        try {
            List<String> auxLista = hc.carregarAtributos();
            int i = r.nextInt(auxLista.size());
            atributo = auxLista.get(i);
        } catch (PersonagemException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao randomizar atributos.", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloInteligencia() {
        try {
            if (p1.getInteligencia().equals(p2.getInteligencia())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getInteligencia() < p2.getInteligencia()) {
                p1.setInteligencia(p1.getInteligencia() - 2);
                p2.setInteligencia(p2.getInteligencia() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setInteligencia(p1.getInteligencia() + 2);
                p2.setInteligencia(p2.getInteligencia() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por inteligência falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloForca() {
        try {
            if (p1.getForca().equals(p2.getForca())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getForca() < p2.getForca()) {
                p1.setForca(p1.getForca() - 2);
                p2.setForca(p2.getForca() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setForca(p1.getForca() + 2);
                p2.setForca(p2.getForca() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por força falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloDestreza() {
        try {
            if (p1.getDestreza().equals(p2.getDestreza())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getDestreza() < p2.getDestreza()) {
                p1.setDestreza(p1.getDestreza() - 2);
                p2.setDestreza(p2.getDestreza() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setDestreza(p1.getDestreza() + 2);
                p2.setDestreza(p2.getDestreza() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por destreza falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloDefesa() {
        try {
            if (p1.getDefesa().equals(p2.getDefesa())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getDefesa() < p2.getDefesa()) {
                p1.setDefesa(p1.getDefesa() - 2);
                p2.setDefesa(p2.getDefesa() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setDefesa(p1.getDefesa() + 2);
                p2.setDefesa(p2.getDefesa() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por defesa falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloPoder() {
        try {
            if (p1.getPoder().equals(p2.getPoder())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getPoder() < p2.getPoder()) {
                p1.setPoder(p1.getPoder() - 2);
                p2.setPoder(p2.getPoder() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setPoder(p1.getPoder() + 2);
                p2.setPoder(p2.getPoder() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por poder falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void dueloCombate() {
        try {
            if (p1.getCombate().equals(p2.getCombate())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getCombate() < p2.getCombate()) {
                p1.setCombate(p1.getCombate() - 2);
                p2.setCombate(p2.getCombate() + 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p2.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p1);
                vencedor(p2);
                p1 = randomNovoHeroi(p2);
                listaDuelo.add(p1);
            } else {
                p1.setCombate(p1.getCombate() + 2);
                p2.setCombate(p2.getCombate() - 2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O vencedor é " + p1.getNome(), "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                listaDuelo.remove(p2);
                vencedor(p1);
                p2 = randomNovoHeroi(p1);
                listaDuelo.add(p2);
            }
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duelo por combate falhou", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    // Método para luta entre os personagens p1 e p2 de acordo com o atributo randomizado
    public void batalhar() throws IOException {
        if (contDuelo >= 11) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fim!", "Número máximo de partidas");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            switch (atributo) {
                case "inteligencia":
                    dueloInteligencia();
                    break;
                case "forca":
                    dueloForca();
                    break;
                case "destreza":
                    dueloDestreza();
                    break;
                case "defesa":
                    dueloDefesa();
                    break;
                case "poder":
                    dueloPoder();
                    break;
                default:
                    dueloCombate();
                    break;
            }
        }
        contDuelo++;
    }

    // Método que randomiza um novo heroi para o próximo duelo
    public PersonagemModel randomNovoHeroi(PersonagemModel p) throws IOException {
        if (contDuelo >= 10) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Heroi vencedor das 10 partidas:", p.getNome());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        } else {
            Random r = new Random();
            HeroisController hc = new HeroisController();
            PersonagemModel novoHeroi = new PersonagemModel();
            try {
                int i = r.nextInt(hc.carregarHerois().size());
                novoHeroi = hc.carregarHerois().get(i);

                while (novoHeroi.getAlinhamento().equals(p1.getAlinhamento()) || novoHeroi.getAlinhamento().equals(p2.getAlinhamento())) {
                    i = r.nextInt(hc.carregarHerois().size());
                    novoHeroi = hc.carregarHerois().get(i);
                }
                while (novoHeroi.getNome().equals(p1.getNome()) || novoHeroi.getNome().equals(p2.getNome())) {
                    i = r.nextInt(hc.carregarHerois().size());
                    novoHeroi = hc.carregarHerois().get(i);
                }
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O herói " + novoHeroi.getNome() + " entrou na luta!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (IOException e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao iniciar uma nova luta.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            return novoHeroi;
        }
    }

    // Método que inseri o vencedor do duelo na lista de vencedores
    public void vencedor(PersonagemModel p) {
        p.setNumeroVitorias(p.getNumeroVitorias() + 1); // Incrementa o número de vitorias
        listaVencedor.add(p);
    }

//    Getters e Setters
    public List<PersonagemModel> getListaDuelo() {
        return listaDuelo;
    }

    public void setListaDuelo(List<PersonagemModel> listaDuelo) {
        this.listaDuelo = listaDuelo;
    }

    public PersonagemModel getP1() {
        return p1;
    }

    public PersonagemModel getP2() {
        return p2;
    }

    public String getAtributo() {
        return atributo;
    }

    public List<PersonagemModel> getListaVencedor() {
        return listaVencedor;
    }

    public void setListaVencedor(List<PersonagemModel> listaVencedor) {
        this.listaVencedor = listaVencedor;
    }
}
