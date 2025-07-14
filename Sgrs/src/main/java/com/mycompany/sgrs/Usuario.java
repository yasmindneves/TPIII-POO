/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

/**
 *
 * @author Gustavo
 */
public abstract class Usuario {
    private int id;
    private String nomeCompleto;
    private String matricula;
    private Dict tipo;

    public Usuario(String nomeCompleto, String matricula, Dict tipo) {
        this.nomeCompleto = nomeCompleto;
        this.matricula = matricula;
        this.tipo = tipo;
    }

    public int getId() { 
        return id; }
    public String getNomeCompleto() { 
        return nomeCompleto; }
    public String getMatricula() { 
        return matricula; }
    public Dict getTipo() { 
        return tipo; }

    public void setId(int id) {
    this.id = id;
    }

}