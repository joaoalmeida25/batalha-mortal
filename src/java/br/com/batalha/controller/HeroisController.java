package br.com.batalha.controller;

import br.com.batalha.exception.PersonagemException;
import java.util.List;

import br.com.batalha.model.PersonagemModel;
import br.com.batalha.util.BancoMemoriaUtil;
import java.io.IOException;
import java.util.Objects;

public class HeroisController {

    public List<PersonagemModel> carregarHerois() throws IOException {
        return BancoMemoriaUtil.getInstance().carregarPersonagens();
    }

    public void salvarHeroi(PersonagemModel p) throws PersonagemException {
        BancoMemoriaUtil.getInstance().gravarPersonagem(p);
    }

    public List<String> carregarAtributos() throws PersonagemException {
        return BancoMemoriaUtil.getInstance().carregarAtributos();
    }

    public Boolean validarHeroi(PersonagemModel p) throws PersonagemException {
        if (Objects.nonNull(p.getNome()) || "".equals(p.getNome())) {
            throw new PersonagemException("Nome é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getAlinhamento()) || "".equals(p.getAlinhamento())) {
            throw new PersonagemException("Alinhamento é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getInteligencia()) || 0 == p.getInteligencia()) {
            throw new PersonagemException("Inteligência é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getForca()) || 0 == p.getForca()) {
            throw new PersonagemException("Força é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getDestreza()) || 0 == p.getDestreza()) {
            throw new PersonagemException("Destreza é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getPoder()) || 0 == p.getPoder()) {
            throw new PersonagemException("Poder é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getCombate()) || 0 == p.getCombate()) {
            throw new PersonagemException("Combate é um campo obrigatório! Insira os dados para continuar.");
        }
        if (Objects.nonNull(p.getDefesa()) || 0 == p.getDefesa()) {
            throw new PersonagemException("Defesa é um campo obrigatório! Insira os dados para continuar.");
        }
        return true;
    }
}
