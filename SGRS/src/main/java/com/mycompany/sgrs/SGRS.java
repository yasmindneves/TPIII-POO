package com.mycompany.sgrs;

import teste.TelaInicial;

public class SGRS {

    public static void main(String[] args) {
        // Inicia a interface gráfica principal do sistema
        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}
