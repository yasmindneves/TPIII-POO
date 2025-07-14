/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

/**
 *
 * @author Gustavo
 */
public enum Dict {
    ALUNO("Aluno"),
    PROFESSOR("Professor"),
    TECNICO("Técnico");

    private final String descricao;

    Dict(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}