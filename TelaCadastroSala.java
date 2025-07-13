package teste;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import teste.TelaCadastroSala;
import teste.ConexaoDB;

public class TelaCadastroSala extends JFrame {

    private final JTextField txtNome;
    private final JTextField txtCapacidade;
    private final JTextField txtEquipamentos;
    private final JRadioButton ativa, inativa;
    private final JButton btnSalvar;

    public TelaCadastroSala() {
        setTitle("Cadastro de Sala");
        setLayout(new GridLayout(5, 2));
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Nome da Sala:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Capacidade:"));
        txtCapacidade = new JTextField();
        add(txtCapacidade);

        add(new JLabel("Equipamentos:"));
        txtEquipamentos = new JTextField();
        add(txtEquipamentos);

        add(new JLabel("Status:"));
        JPanel statusPanel = new JPanel();
        ativa = new JRadioButton("Ativa", true);
        inativa = new JRadioButton("Inativa");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(ativa);
        grupo.add(inativa);
        statusPanel.add(ativa);
        statusPanel.add(inativa);
        add(statusPanel);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarSala());
        add(btnSalvar);
    }

    private void salvarSala() {
        try (Connection conn = ConexaoDB.obterConexao()) {
            String sql = "INSERT INTO salas (nome_sala, capacidade, equipamentos, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtNome.getText());
            stmt.setInt(2, Integer.parseInt(txtCapacidade.getText()));
            stmt.setString(3, txtEquipamentos.getText());
            stmt.setString(4, ativa.isSelected() ? "Ativa" : "Inativa");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sala cadastrada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar sala: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroSala().setVisible(true));
    }
}