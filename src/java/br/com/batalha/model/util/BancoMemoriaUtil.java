package br.com.batalha.model.util;

import br.com.batalha.exception.HeroisException;
import java.util.ArrayList;
import java.util.List;
import br.com.batalha.model.dto.HeroiDto;

public class BancoMemoriaUtil {

    public static List<HeroiDto> heroiDtos;
    public static List<String> atributos;

    HeroiDto p1 = new HeroiDto("3-D Man", "bom", 50, 31, 43, 32, 25, 52);
    HeroiDto p2 = new HeroiDto("A-Bomb", "bom", 38, 100, 17, 80, 17, 64);
    HeroiDto p3 = new HeroiDto("Abomination", "mal", 63, 80, 53, 90, 55, 95);
    HeroiDto p4 = new HeroiDto("Blackwulf", "neutro", 50, 28, 8, 30, 59, 25);
    HeroiDto p5 = new HeroiDto("Blackout", "mal", 63, 32, 45, 80, 38, 65);

    public static BancoMemoriaUtil getInstance() {
        return new BancoMemoriaUtil();
    }

    public List<HeroiDto> carregarHerois() throws HeroisException {
        try {
            if (heroiDtos == null) {
                heroiDtos = new ArrayList<>();
                heroiDtos.add(p1);
                heroiDtos.add(p2);
                heroiDtos.add(p3);
                heroiDtos.add(p4);
                heroiDtos.add(p5);
            }
            return heroiDtos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HeroisException("carregarHerois falhou");
        }
    }

    public void gravarHeroi(HeroiDto h) throws HeroisException {
        try {
            heroiDtos.add(h);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HeroisException("gravarHeroi falhou");
        }
    }

    public List<String> carregarAtributos() throws HeroisException {
        try {
            atributos = new ArrayList<>();
            atributos.add("inteligencia");
            atributos.add("forca");
            atributos.add("destreza");
            atributos.add("defesa");
            atributos.add("poder");
            atributos.add("combate");
            return atributos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HeroisException("carregarAtributos falhou");
        }
    }
}
