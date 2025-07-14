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

public class ReservaDAO {

    public void salvar(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reservas (id_usuario, id_sala, data_reserva, hora_inicio, hora_fim, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getIdSala());
            stmt.setString(3, reserva.getDataReserva());   // formato yyyy-MM-dd
            stmt.setString(4, reserva.getHoraInicio());    // formato HH:mm
            stmt.setString(5, reserva.getHoraFim());       // formato HH:mm
            stmt.setString(6, reserva.getStatus());        // Confirmada ou Cancelada
            stmt.executeUpdate();
        }
    }

    // VERIFICAR CONFLITO DE HORÁRIO NA MESMA SALA E DATA
    public boolean verificarConflito(int idSala, String data, String horaInicio, String horaFim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas " +
                     "WHERE id_sala = ? AND data_reserva = ? AND status = 'Confirmada' " +
                     "AND ( (hora_inicio < ? AND hora_fim > ?) OR (hora_inicio < ? AND hora_fim > ?) OR " +
                     "(hora_inicio >= ? AND hora_fim <= ?) )";

        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idSala);
            stmt.setString(2, data);

            stmt.setString(3, horaFim);  
            stmt.setString(4, horaInicio); 
            stmt.setString(5, horaFim);
            stmt.setString(6, horaInicio);
            stmt.setString(7, horaInicio);
            stmt.setString(8, horaFim);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        }
    }
    public List<Reserva> listarReservasFuturas(int idUsuario) throws SQLException {
        String sql = "SELECT r.id, r.id_sala, s.nome_sala, r.data_reserva, r.hora_inicio, r.hora_fim " +
                     "FROM reservas r " +
                     "JOIN salas s ON s.id = r.id_sala " +
                     "WHERE r.id_usuario = ? AND r.status = 'Confirmada' AND r.data_reserva >= CURDATE()";

        List<Reserva> lista = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reserva r = new Reserva();
                    r.setId(rs.getInt("id"));
                    r.setIdSala(rs.getInt("id_sala"));
                    r.setDataReserva(rs.getString("data_reserva"));
                    r.setHoraInicio(rs.getString("hora_inicio"));
                    r.setHoraFim(rs.getString("hora_fim"));
                    // use a descrição da sala em campo temporário ou crie DTO
                    // aqui vamos reaproveitar o campo status para guardar nome_sala temporariamente
                    r.setStatus(rs.getString("nome_sala"));
                    lista.add(r);
                }
            }
        }
        return lista;
    }

    /**
     * Cancela uma reserva, atualizando o status para 'Cancelada'.
     */
        public void cancelarReserva(int idReserva) throws SQLException {
            String sql = "UPDATE reservas SET status = 'Cancelada' WHERE id = ?";
            try (Connection conexao = Conexao.getConexao();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idReserva);
                stmt.executeUpdate();
            }
        }
        public List<Object[]> buscarSalasMaisReservadas() throws SQLException {
        String sql = "SELECT s.nome_sala, COUNT(*) AS total FROM reservas r " +
                     "JOIN salas s ON r.id_sala = s.id " +
                     "GROUP BY r.id_sala ORDER BY total DESC";

        List<Object[]> resultado = new ArrayList<>();
        try (Connection conexao = Conexao.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(new Object[]{ rs.getString("nome_sala"), rs.getInt("total") });
            }
        }
        return resultado;
    }

}
