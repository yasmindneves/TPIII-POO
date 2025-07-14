package com.mycompany.sgrs;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public class Sala {
    private int id;
    private String nome;
    private int capacidade;
    private List<String> equipamentos;
    private boolean ativa;

    /**
     * Construtor completo (sem id).
     */
    public Sala(String nome, int capacidade, List<String> equipamentos, boolean ativa) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.equipamentos = equipamentos;
        this.ativa = ativa;
    }

    /**
     * Construtor padrão para uso do DAO.
     */
    public Sala() {
        // Construtor vazio: permite instanciar e popular via setters.
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<String> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<String> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    /**
     * Constrói a lista de equipamentos a partir de uma string CSV.
     */
    public void setEquipamentosPorString(String equipamentosStr) {
        this.equipamentos = Arrays.asList(equipamentosStr.split("\\s*,\\s*"));
    }

    /**
     * Retorna a lista de equipamentos como string CSV.
     */
    public String getEquipamentosComoString() {
        return String.join(", ", this.equipamentos);
    }
}
