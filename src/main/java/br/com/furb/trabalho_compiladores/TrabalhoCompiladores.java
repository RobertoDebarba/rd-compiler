package br.com.furb.trabalho_compiladores;

import javax.swing.UIManager;

import br.com.furb.trabalho_compiladores.view.CompilerWindowFactory;

public class TrabalhoCompiladores {

	public static void main(final String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		CompilerWindowFactory.createInstance().setVisible(true);
	}

}
