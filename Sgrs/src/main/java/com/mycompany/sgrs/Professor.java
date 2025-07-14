/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

/**
 *
 * @author Gustavo
 */
public class Professor extends Usuario {
    public Professor(String nomeCompleto, String matricula) {
        super(nomeCompleto, matricula, Dict.PROFESSOR);
    }
}