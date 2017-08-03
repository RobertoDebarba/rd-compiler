package br.com.robertodebarba.rd_compiler;

import br.com.robertodebarba.rd_compiler.view.CompilerWindowFactory;

import javax.swing.*;

public class RDCompiler {

    public static void main(final String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        CompilerWindowFactory.createInstance().setVisible(true);
    }

}
