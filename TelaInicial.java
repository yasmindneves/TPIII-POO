/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yasmi
 */
package teste;


import teste.TelaCadastroSala;
import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Sistema de Gerenciamento de Reservas de Salas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SGRS - Tela Inicial", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(6, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnUsuario = new JButton("Cadastro de Usuário");
        btnUsuario.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));

        JButton btnSala = new JButton("Cadastro de Sala");
        btnSala.addActionListener(e -> new TelaCadastroSala().setVisible(true));

        JButton btnReserva = new JButton("Reserva de Sala");
        btnReserva.addActionListener(e -> new TelaReservaSala().setVisible(true));

        JButton btnCancelar = new JButton("Cancelamento de Reserva");
        btnCancelar.addActionListener(e -> new TelaCancelamentoReserva().setVisible(true));

        JButton btnRelatorio = new JButton("Relatórios");
        btnRelatorio.addActionListener(e -> new TelaRelatorios().setVisible(true));

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));

        painelBotoes.add(btnUsuario);
        painelBotoes.add(btnSala);
        painelBotoes.add(btnReserva);
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnRelatorio);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.CENTER);
    }

    // Para testar isoladamente:
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}
