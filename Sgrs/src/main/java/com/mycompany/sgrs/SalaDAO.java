/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sgrs;

/**
 *
 * @author Gustavo
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    
    public void salvar(Sala sala) throws SQLException {
        String sql = "INSERT INTO salas (nome_sala, capacidade, equipamentos, status) VALUES (?, ?, ?, ?)";
        try (Connection conexao = Conexao.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, sala.getNome());
            pstmt.setInt(2, sala.getCapacidade());
            pstmt.setString(3, sala.getEquipamentosComoString());
            pstmt.setString(4, sala.isAtiva() ? "Ativa" : "Inativa");
            pstmt.executeUpdate();
        }
    }
    public List<Sala> listarSalasAtivas() throws SQLException {
    List<Sala> salas = new ArrayList<>();
    String sql = "SELECT * FROM salas WHERE status = 'Ativa'";

    try (Connection conexao = Conexao.getConexao();
         PreparedStatement pstmt = conexao.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) { // CORRIGIDO

        while (rs.next()) {
            Sala sala = new Sala();
            sala.setId(rs.getInt("id"));
            sala.setNome(rs.getString("nome_sala"));
            sala.setCapacidade(rs.getInt("capacidade"));
            sala.setEquipamentosPorString(rs.getString("equipamentos"));
            sala.setAtiva(true);
            salas.add(sala);
        }
    }
    return salas;
}
public Sala buscarPorNome(String nomeSala) throws SQLException {
    String sql = "SELECT * FROM salas WHERE nome_sala = ?";
    try (Connection conexao = Conexao.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, nomeSala);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Sala sala = new Sala();
            sala.setId(rs.getInt("id"));
            sala.setNome(rs.getString("nome_sala"));
            sala.setCapacidade(rs.getInt("capacidade"));
            sala.setEquipamentosPorString(rs.getString("equipamentos"));
            sala.setAtiva(rs.getString("status").equalsIgnoreCase("Ativa"));
            return sala;
        }
    }
    return null;
}

    public boolean inserirSala(Sala sala) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public List<String> listarNomesDasSalas() {
    List<String> nomes = new ArrayList<>();
    String sql = "SELECT nome_sala FROM salas";

    try (Connection conn = Conexao.getConexao(); 
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            nomes.add(rs.getString("nome_sala"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return nomes;
}   
}