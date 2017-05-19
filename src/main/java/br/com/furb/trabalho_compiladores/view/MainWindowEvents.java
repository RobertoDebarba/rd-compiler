package br.com.furb.trabalho_compiladores.view;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.furb.trabalho_compiladores.lexico.LexicalError;
import br.com.furb.trabalho_compiladores.lexico.Lexico;
import br.com.furb.trabalho_compiladores.lexico.SemanticError;
import br.com.furb.trabalho_compiladores.lexico.Semantico;
import br.com.furb.trabalho_compiladores.lexico.Sintatico;
import br.com.furb.trabalho_compiladores.lexico.SyntaticError;

public class MainWindowEvents {
	private static final String MSG_GERACAO_NAO_IMPLEMENTADA = "Geração de código ainda não foi implementada.";
	private static final String EXTENSAO_TXT = "txt";
	private static final String ARQUIVOS_DE_TEXTO = "Arquivos de Texto";

	static void newCommandOnClick(final JTextArea codeTextArea, final JTextArea messageTextArea,
			final JLabel bottonLabel) {
		codeTextArea.setText("");
		messageTextArea.setText("");
		bottonLabel.setText(MainWindow.NAO_MODIFICADO);
	}

	static File openOnCommandClick(final JTextArea codeTextArea, final JTextArea messageTextArea,
			final JLabel bottonLabel, final Component parent) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(ARQUIVOS_DE_TEXTO, EXTENSAO_TXT);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
				String textoArquivoImpotado = "";
				while (br.ready()) {
					textoArquivoImpotado += br.readLine() + System.lineSeparator();
				}
				codeTextArea.setText(textoArquivoImpotado);
				messageTextArea.setText("");
				bottonLabel.setText(MainWindow.NAO_MODIFICADO + " - " + file.getPath());
				return file;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	static void copyOnCommandClick(final JTextArea codeTextArea) {
		codeTextArea.copy();
	}

	static void cutOnCommandClick(final JTextArea codeTextArea) {
		codeTextArea.cut();
	}

	static void pasteOnCommandClick(final JTextArea codeTextArea) {
		codeTextArea.paste();
	}

	static void compileOnCommandClick(JTextArea codeTextArea, JTextArea messageTextArea) {

		try {
			Lexico lexico = new Lexico();
			Semantico semantico = new Semantico();
			Sintatico sintatico = new Sintatico();
			
			lexico.setInput(codeTextArea.getText());
			sintatico.parse(lexico, semantico);

			messageTextArea.setText(messageTextArea.getText() + "\nprograma compilado com sucesso \n");
		} catch (LexicalError e) {
			if (e.getMessage() == "símbolo inválido") {
				messageTextArea.setText(messageTextArea.getText() + "Erro na linha "
						+ buscarLinhaPorPosicao(codeTextArea, e.getPosition()) + " – "
						+ codeTextArea.getText().charAt(e.getPosition()) + " " + e.getMessage() + "\n");
			} else {
				messageTextArea.setText(messageTextArea.getText() + "Erro na linha "
						+ buscarLinhaPorPosicao(codeTextArea, e.getPosition()) + " – " + e.getMessage() + "\n");
			}
		} catch (SyntaticError e) {
			String token = e.getToken();
			if (token.equals("$")) {
				token = "fim de arquivo";
			}
			
			messageTextArea.setText(messageTextArea.getText() + "Erro na linha "
					+ buscarLinhaPorPosicao(codeTextArea, e.getPosition()) + " – " + String.format(e.getMessage(), token) + "\n");
		} catch (SemanticError e) {
			e.printStackTrace();
		}
	}

	private static int buscarLinhaPorPosicao(JTextArea codeTextArea, int posicao) {
		String s = codeTextArea.getText().substring(0, posicao);

		int counter = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\n') {
				counter++;
			}
		}
		return counter;
	}

	static void buildOnCommandClick(JTextArea messageTextArea) {
		messageTextArea
				.setText(messageTextArea.getText() + (messageTextArea.getText().isEmpty() ? "" : System.lineSeparator())
						+ MSG_GERACAO_NAO_IMPLEMENTADA + "\n");
	}

	static void teamOnCommandClick(JTextArea messageTextArea) {
		messageTextArea
				.setText(messageTextArea.getText() + (messageTextArea.getText().isEmpty() ? "" : System.lineSeparator())
						+ "Equipe:" + System.lineSeparator() + "\tFrancisco Krautchuk Neto" + System.lineSeparator()
						+ "\tRoberto Luiz Debarba\n");
	}

	static File saveOnCommandClick(final JTextArea codeTextArea, final JTextArea messageTextArea,
			final JLabel bottonLabel, File file) {
		if (file == null) {
			final JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(ARQUIVOS_DE_TEXTO, EXTENSAO_TXT);
			fc.setFileFilter(filter);
			int returnVal = fc.showSaveDialog(new JFrame());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				BufferedWriter o = null;
				try {
					String filePath;
					if (!selectedFile.getName().endsWith(".txt"))
						filePath = selectedFile.getPath() + ".txt";
					else
						filePath = selectedFile.getPath();

					o = new BufferedWriter(new FileWriter(filePath));

					codeTextArea.write(o);
					o.close();
					messageTextArea.setText("");
					bottonLabel.setText(selectedFile != null ? MainWindow.NAO_MODIFICADO + " - " + filePath
							: MainWindow.NAO_MODIFICADO);
					return new File(filePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			BufferedWriter o = null;
			try {
				o = new BufferedWriter(new FileWriter(file));
				codeTextArea.write(o);
				o.close();
				messageTextArea.setText("");
				bottonLabel.setText(
						file != null ? MainWindow.NAO_MODIFICADO + " - " + file.getPath() : MainWindow.NAO_MODIFICADO);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return file;
	}
}
