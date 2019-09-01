package br.com.batalha.util;

import br.com.batalha.exception.PersonagemException;
import br.com.batalha.model.PersonagemModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BancoMemoriaUtil {

    public static List<PersonagemModel> personagemDtos;
    public static List<String> atributos;

    public static BancoMemoriaUtil getInstance() {
        return new BancoMemoriaUtil();
    }

    public List<PersonagemModel> carregarPersonagens() throws IOException {
        if (personagemDtos == null) {
            personagemDtos = new PersonagemUtil().carregaCSV();
        }

        return personagemDtos;
    }

    public void gravarPersonagem(PersonagemModel p) throws PersonagemException {
        try {
            personagemDtos.add(p);
        } catch (Exception e) {
            throw new PersonagemException("gravarHeroi falhou");
        }
    }

    public List<String> carregarAtributos() throws PersonagemException {
        if (atributos == null) {
            atributos = new ArrayList<>();
        }
        try {
            atributos.add("inteligencia");
            atributos.add("forca");
            atributos.add("destreza");
            atributos.add("defesa");
            atributos.add("poder");
            atributos.add("combate");
            return atributos;
        } catch (Exception e) {
            throw new PersonagemException("carregarAtributos falhou");
        }
    }

}
