/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author Gustavo
 */

public class UsuarioDAO {

    // MÉTODO 1 - Você já tem este
    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, matricula, tipo_usuario) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getMatricula());
            pstmt.setString(3, usuario.getTipo().getDescricao());

            pstmt.executeUpdate();
        }
    }

    // MÉTODO 2 - Adicione este logo abaixo
    public Optional<Usuario> buscarPorMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE matricula = ?";
        try (Connection conexao = Conexao.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String tipoStr = rs.getString("tipo_usuario");
                    
                    Usuario usuario = null;
                    // Converte a String do banco para o nosso Enum
                    Dict tipo = Dict.valueOf(tipoStr.toUpperCase().replace(" ", "_"));

                    switch(tipo) {
                        case ALUNO:
                            usuario = new Aluno(nome, matricula);
                            break;
                        case PROFESSOR:
                            usuario = new Professor(nome, matricula);
                            break;
                        case TECNICO:
                            usuario = new Tecnico(nome, matricula);
                            break;
                    }
                    usuario.setId(id);
                    
                    
                    return Optional.ofNullable(usuario);
                }
            }
        }
        return Optional.empty();
    }
}