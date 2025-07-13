package teste;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaCancelamentoReserva extends JFrame {
    private JTextField txtIdReserva;
    private JButton btnCancelar;

    public TelaCancelamentoReserva() {
        setTitle("Cancelar Reserva");
        setLayout(new GridLayout(2, 2));
        setSize(300, 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        add(new JLabel("ID da Reserva:"));
        txtIdReserva = new JTextField();
        add(txtIdReserva);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> cancelarReserva());
        add(btnCancelar);
    }

    private void cancelarReserva() {
        try (Connection conn = ConexaoDB.obterConexao()) {
            String sql = "DELETE FROM reservas WHERE id_reserva = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtIdReserva.getText()));
            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                JOptionPane.showMessageDialog(this, "Reserva cancelada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "ID de reserva não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cancelar reserva: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCancelamentoReserva().setVisible(true));
    }
}
