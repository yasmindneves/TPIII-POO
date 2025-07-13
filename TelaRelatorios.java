package teste;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaRelatorios extends JFrame {
    private final JTextArea relatorio;

    public TelaRelatorios() {
        setTitle("Relatório de Reservas");
        setSize(500, 400);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        relatorio = new JTextArea();
        add(new JScrollPane(relatorio), BorderLayout.CENTER);
        gerarRelatorio();
    }

    private void gerarRelatorio() {
        try (Connection conn = ConexaoDB.obterConexao()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservas");
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id_reserva"))
                  .append(" | Usuário: ").append(rs.getString("usuario"))
                  .append(" | Sala: ").append(rs.getString("sala"))
                  .append(" | Data: ").append(rs.getString("data"))
                  .append(" | Hora: ").append(rs.getString("hora")).append("");
            }
            relatorio.setText(sb.toString());
        } catch (Exception ex) {
            relatorio.setText("Erro ao gerar relatório: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaRelatorios().setVisible(true));
    }
}
