package br.com.furb.trabalho_compiladores;

import javax.swing.UIManager;

import br.com.furb.trabalho_compiladores.view.MainWindow;

public class TrabalhoCompiladores {

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		new MainWindow().setVisible(true);
	}

}
