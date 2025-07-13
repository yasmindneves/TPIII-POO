package teste;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaReservaSala extends JFrame {
    private JTextField txtUsuario, txtSala, txtData, txtHora;
    private JButton btnReservar;

    public TelaReservaSala() {
        setTitle("Reserva de Sala");
        setLayout(new GridLayout(5, 2));
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Sala:"));
        txtSala = new JTextField();
        add(txtSala);

        add(new JLabel("Data (YYYY-MM-DD):"));
        txtData = new JTextField();
        add(txtData);

        add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField();
        add(txtHora);

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> reservarSala());
        add(btnReservar);
    }

    private void reservarSala() {
        try (Connection conn = ConexaoDB.obterConexao()) {
            String sql = "INSERT INTO reservas (usuario, sala, data, hora) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtUsuario.getText());
            stmt.setString(2, txtSala.getText());
            stmt.setString(3, txtData.getText());
            stmt.setString(4, txtHora.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sala reservada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao reservar sala: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaReservaSala().setVisible(true));
    }
}
