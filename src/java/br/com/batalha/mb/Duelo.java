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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class Duelo implements Serializable {

    private List<HeroiDto> listaDuelo = new ArrayList<>();
    private HeroiDto p1 = new HeroiDto();
    private HeroiDto p2 = new HeroiDto();
    private String atributo;

    public Duelo() {

    }

    public void randomHerois() {
        Random r = new Random();
        HeroisController hc = new HeroisController();
        try {
            while (listaDuelo.size() < 2) {
                List<HeroiDto> auxLista = hc.carregarHerois();
                int n1 = r.nextInt(auxLista.size());
                int n2 = r.nextInt(auxLista.size());

                if (n1 == n2) {
                    n2 = r.nextInt(auxLista.size());
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
        } catch (HeroisException e) {
            System.out.println("Erro: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao randomizar Heróis.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void removerLista() {
        if (!listaDuelo.isEmpty()) {
            for (HeroiDto p : listaDuelo) {
                listaDuelo.remove(p);
            }
            listaDuelo = new ArrayList<>();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Heróis removidos.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void randomAtributo() {
        Random r = new Random();
        HeroisController hc = new HeroisController();
        try {
            List<String> auxLista = hc.carregarAtributos();
            int i = r.nextInt(auxLista.size());
            atributo = auxLista.get(i);
        } catch (HeroisException e) {
            System.out.println("Erro: " + e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao randomizar atributos.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void batalhar() {
        if (atributo.equals("inteligencia")) {
            if (p1.getInteligencia().equals(p2.getInteligencia())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getInteligencia() < p2.getInteligencia()) {
                p1.setInteligencia(p1.getInteligencia() - 2);
                p2.setInteligencia(p2.getInteligencia() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setInteligencia(p1.getInteligencia() + 2);
                p2.setInteligencia(p2.getInteligencia() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else if (atributo.equals("forca")) {
            if (p1.getForca().equals(p2.getForca())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getForca() < p2.getForca()) {
                p1.setForca(p1.getForca() - 2);
                p2.setForca(p2.getForca() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setForca(p1.getForca() + 2);
                p2.setForca(p2.getForca() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else if (atributo.equals("destreza")) {
            if (p1.getDestreza().equals(p2.getDestreza())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getDestreza() < p2.getDestreza()) {
                p1.setDestreza(p1.getDestreza() - 2);
                p2.setDestreza(p2.getDestreza() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setDestreza(p1.getDestreza() + 2);
                p2.setDestreza(p2.getDestreza() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else if (atributo.equals("defesa")) {
            if (p1.getDefesa().equals(p2.getDefesa())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getDefesa() < p2.getDefesa()) {
                p1.setDefesa(p1.getDefesa() - 2);
                p2.setDefesa(p2.getDefesa() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setDefesa(p1.getDefesa() + 2);
                p2.setDefesa(p2.getDefesa() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else if (atributo.equals("poder")) {
            if (p1.getPoder().equals(p2.getPoder())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getPoder() < p2.getPoder()) {
                p1.setPoder(p1.getPoder() - 2);
                p2.setPoder(p2.getPoder() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setPoder(p1.getPoder() + 2);
                p2.setPoder(p2.getPoder() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            if (p1.getCombate().equals(p2.getCombate())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empate!", "Randomizando um novo atributo");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                randomAtributo();
            } else if (p1.getCombate() < p2.getCombate()) {
                p1.setCombate(p1.getCombate() - 2);
                p2.setCombate(p2.getCombate() + 2);
                listaDuelo.remove(p1);
                p1 = randomNovoHeroi();
                listaDuelo.add(p1);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p2.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                p1.setCombate(p1.getCombate() + 2);
                p2.setCombate(p2.getCombate() - 2);
                listaDuelo.remove(p2);
                p2 = randomNovoHeroi();
                listaDuelo.add(p2);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,  p1.getNome() + " vs " + p2.getNome(), "O vencedor é " + p1.getNome());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public HeroiDto randomNovoHeroi() {
        Random r = new Random();
        HeroisController hc = new HeroisController();
        HeroiDto novoHeroi = new HeroiDto();
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
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O herói " + novoHeroi.getNome() + " entrou no duelo!", "");;;;
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (HeroisException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao iniciar um novo duelo.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
        return novoHeroi;
    }
    

    public List<HeroiDto> getListaDuelo() {
        return listaDuelo;
    }

    /**
     * @param listaDuelo the listaDuelo to set
     */
    public void setListaDuelo(List<HeroiDto> listaDuelo) {
        this.listaDuelo = listaDuelo;
    }

    /**
     * @return the p1
     */
    public HeroiDto getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public HeroiDto getP2() {
        return p2;
    }

    /**
     * @return the atributo
     */
    public String getAtributo() {
        return atributo;
    }

}
