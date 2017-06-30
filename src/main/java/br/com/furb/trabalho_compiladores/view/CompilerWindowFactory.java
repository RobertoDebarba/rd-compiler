package br.com.furb.trabalho_compiladores.view;

import javax.swing.*;

public final class CompilerWindowFactory {

    private CompilerWindowFactory() {
        // Factory
    }

    public static final CompilerWindow createInstance() {
        final JTextArea codeTextArea = new JTextArea();
        final JTextArea messageTextArea = new JTextArea();
        final JLabel bottomLabel = new JLabel();

        return new CompilerWindow(codeTextArea, messageTextArea, bottomLabel, new CompilerWindowHandler(codeTextArea, messageTextArea, bottomLabel));
    }

}
