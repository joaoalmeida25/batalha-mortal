package br.com.batalha.model.dto;

import java.io.Serializable;

public class HeroiDto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String alinhamento;
    private Integer inteligencia;
    private Integer forca;
    private Integer destreza;
    private Integer poder;
    private Integer combate;
    private Integer defesa;
    
    public HeroiDto(){
        
    }

    public HeroiDto(String nome, String alinhamento, Integer inteligencia, Integer forca, Integer destreza,
            Integer poder, Integer combate, Integer defesa) {
        this.nome = nome;
        this.alinhamento = alinhamento;
        this.inteligencia = inteligencia;
        this.forca = forca;
        this.destreza = destreza;
        this.poder = poder;
        this.combate = combate;
        this.defesa = defesa;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDestreza() {
        return destreza;
    }

    public void setDestreza(Integer destreza) {
        this.destreza = destreza;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public Integer getCombate() {
        return combate;
    }

    public void setCombate(Integer combate) {
        this.combate = combate;
    }

}
