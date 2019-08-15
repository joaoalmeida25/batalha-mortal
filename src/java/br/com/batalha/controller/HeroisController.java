package br.com.batalha.controller;

import br.com.batalha.exception.HeroisException;
import java.io.IOException;
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

    public List<String> carregarAtributos() throws HeroisException {
        return BancoMemoriaUtil.getInstance().carregarAtributos();
    }
}
