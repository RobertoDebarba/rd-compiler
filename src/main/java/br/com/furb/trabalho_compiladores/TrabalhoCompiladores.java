package br.com.furb.trabalho_compiladores;

import br.com.furb.trabalho_compiladores.view.CompilerWindowFactory;

import javax.swing.*;

public class TrabalhoCompiladores {

    public static void main(final String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        CompilerWindowFactory.createInstance().setVisible(true);
    }

}
