package br.com.furb.trabalho_compiladores.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.furb.trabalho_compiladores.compiler.Compiler;

final class CompilerWindowHandler {

	private static final String TEAM_MESSAGE = "Equipe:\r\n\tFrancisco Krautchuk Neto\r\n\tRoberto Luiz Debarba";
	private static final String NOT_IMPLEMENTED_YET_MESSAGE = "Geração de código ainda não foi implementada.";
	private static final String TEXT_FILES_MESSAGE = "Arquivos de Texto";

	private static final String PROJECT_FILE_EXTENSION = "txt";

	private static final String CHANGED_FILE = "modificado - %s";
	private static final String CHANGED = "modificado";
	private static final String NON_CHANGED_FILE = "não modificado - %s";
	private static final String NON_CHANGED = "não modificado";

	private final Compiler compiler = new Compiler();
	private File file;
	private final JTextArea codeTextArea;
	private final JTextArea messageTextArea;
	private final JLabel bottomLabel;

	public CompilerWindowHandler(final JTextArea codeTextArea, final JTextArea messageTextArea, final JLabel bottomLabel) {
		this.codeTextArea = codeTextArea;
		this.messageTextArea = messageTextArea;
		this.bottomLabel = bottomLabel;
	}

	public void newProject() {
		clean(codeTextArea);
		clean(messageTextArea);
		bottomLabel.setText(NON_CHANGED);
	}

	public void openProject() {
		final JFileChooser fileChooser = new JFileChooser();
		final FileNameExtensionFilter textFileFilter = new FileNameExtensionFilter(TEXT_FILES_MESSAGE, PROJECT_FILE_EXTENSION);
		fileChooser.setFileFilter(textFileFilter);

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			final File selectedFile = fileChooser.getSelectedFile();

			try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile.getPath()))) {
				final StringBuilder fileContent = new StringBuilder();
				while (reader.ready()) {
					fileContent.append(reader.readLine()).append(System.lineSeparator());
				}
				codeTextArea.setText(fileContent.toString());

				clean(messageTextArea);
				file = selectedFile;
				setFileNotChanged();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveProject() {
		if (isFileOpen()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
				codeTextArea.write(writer);

				clean(messageTextArea);
				setFileNotChanged();
			} catch (final IOException e) {
				e.printStackTrace();
			}

		} else {
			final JFileChooser fileChooser = new JFileChooser();
			final FileNameExtensionFilter textFileFilter = new FileNameExtensionFilter(TEXT_FILES_MESSAGE, PROJECT_FILE_EXTENSION);
			fileChooser.setFileFilter(textFileFilter);

			if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
				try {
					final File selectedFile = fileChooser.getSelectedFile();

					String filePath;
					if (isProjectFileExtension(selectedFile)) {
						filePath = selectedFile.getPath();
					} else {
						filePath = selectedFile.getPath() + "." + PROJECT_FILE_EXTENSION;
					}

					try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
						codeTextArea.write(writer);
					}

					clean(messageTextArea);
					this.file = selectedFile;
					setFileNotChanged();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void copy() {
		codeTextArea.copy();
	}

	public void cut() {
		codeTextArea.cut();
	}

	public void paste() {
		codeTextArea.paste();
	}

	public void compile() {
		log(compiler.compile(codeTextArea.getText()));
	}

	public void build() {
		log(NOT_IMPLEMENTED_YET_MESSAGE);
	}

	public void showTeam() {
		log(TEAM_MESSAGE);
	}

	public void setFileChanged() {
		bottomLabel.setText(isFileOpen() ? String.format(CHANGED_FILE, file.getPath()) : CHANGED);
	}

	public void setFileNotChanged() {
		bottomLabel.setText(isFileOpen() ? String.format(NON_CHANGED_FILE, file.getPath()) : NON_CHANGED);
	}

	private void clean(final JTextArea textArea) {
		textArea.setText("");
	}

	private boolean isProjectFileExtension(final File selectedFile) {
		return selectedFile.getName().endsWith(PROJECT_FILE_EXTENSION);
	}

	private boolean isFileOpen() {
		return this.file != null;
	}

	private void log(final String text) {
		messageTextArea.setText(text);
	}

}
