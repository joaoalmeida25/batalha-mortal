package br.com.batalha.controller;

import br.com.batalha.exception.HeroisException;
import java.util.List;

import br.com.batalha.model.dto.HeroiDto;
import br.com.batalha.model.util.BancoMemoriaUtil;

/**
 * Classe adicionar face
 *
 * @author c1297467
 *
 */
public class HeroisController {

    public List<HeroiDto> carregarHerois() throws HeroisException {
        return BancoMemoriaUtil.getInstance().carregarHerois();
    }

    public void salvarHeroi(HeroiDto h) throws HeroisException {
        BancoMemoriaUtil.getInstance().gravarHeroi(h);
    }

    public List<String> carregarAtributos() throws HeroisException {
        return BancoMemoriaUtil.getInstance().carregarAtributos();
    }

    public Boolean validarHeroi(HeroiDto heroi) throws HeroisException {
        if (null == heroi.getNome() || "".equals(heroi.getNome())) {
            throw new HeroisException("Nome é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getAlinhamento() || "".equals(heroi.getAlinhamento())) {
            throw new HeroisException("Alinhamento é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getInteligencia() || 0 == heroi.getInteligencia()) {
            throw new HeroisException("Inteligência é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getForca() ||  0 == heroi.getForca()) {
            throw new HeroisException("Força é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getDestreza() ||  0 == heroi.getDestreza()) {
            throw new HeroisException("Destreza é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getPoder() ||  0 == heroi.getPoder()) {
            throw new HeroisException("Poder é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getCombate() ||  0 == heroi.getCombate()) {
            throw new HeroisException("Combate é um campo obrigatório! Insira os dados para continuar.");
        }
        if (null == heroi.getDefesa() ||  0 == heroi.getDefesa()) {
            throw new HeroisException("Defesa é um campo obrigatório! Insira os dados para continuar.");
        }
        return true;
    }
}
