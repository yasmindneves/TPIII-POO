/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

/**
 *
 * @author Gustavo
 */
public class Tecnico extends Usuario {
    public Tecnico(String nomeCompleto, String matricula) {
        super(nomeCompleto, matricula, Dict.TECNICO);
    }
}
