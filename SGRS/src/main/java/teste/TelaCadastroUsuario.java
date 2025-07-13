package teste;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TelaCadastroUsuario extends JFrame {
    private JTextField txtNome, txtMatricula;
    private JComboBox<String> tipoUsuario;
    private JButton btnCadastrar;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setLayout(new GridLayout(4, 2));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("Tipo de Usuário:"));
        tipoUsuario = new JComboBox<>(new String[]{"Aluno", "Professor", "Técnico"});
        add(tipoUsuario);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrar());
        add(btnCadastrar);
    }

    private void cadastrar() {
        try (Connection conn = ConexaoDB.obterConexao()) {
            String sql = "INSERT INTO usuarios (nome, matricula, tipo_usuario) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtNome.getText());
            stmt.setString(2, txtMatricula.getText());
            stmt.setString(3, tipoUsuario.getSelectedItem().toString());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastroUsuario().setVisible(true));
    }
}
