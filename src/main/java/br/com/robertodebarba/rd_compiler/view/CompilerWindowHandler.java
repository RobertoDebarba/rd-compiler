package br.com.robertodebarba.rd_compiler.view;

import br.com.robertodebarba.rd_compiler.compiler.Compiler;
import br.com.robertodebarba.rd_compiler.compiler.Lexico;
import br.com.robertodebarba.rd_compiler.compiler.Sintatico;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

final class CompilerWindowHandler {

    private static final String TEAM_MESSAGE = "Equipe:\r\n\tFrancisco Krautchuk Neto\r\n\tRoberto Luiz Debarba";
    private static final String NOT_IMPLEMENTED_YET_MESSAGE = "Geração de código ainda não foi implementada.";
    private static final String TEXT_FILES_MESSAGE = "Arquivos de Texto";
    private static final String READ_ERROR_MESSAGE = "Erro ao ler arquivo";
    private static final String SAVE_ERROR_MESSAGE = "Erro ao salvar arquivo";

    private static final String PROJECT_EXTENSION = "txt";
    private static final String PROJECT_FILE_EXTENSION = ".txt";
    private static final String OUTPUT_FILE_EXTENSION = ".il";

    private static final String CHANGED_FILE = "modificado - %s";
    private static final String CHANGED = "modificado";
    private static final String NON_CHANGED_FILE = "não modificado - %s";
    private static final String NON_CHANGED = "não modificado";

    private final Compiler compiler = new Compiler(new Lexico(), new Sintatico());
    private Path filePath;
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
        final FileNameExtensionFilter textFileFilter = new FileNameExtensionFilter(TEXT_FILES_MESSAGE, PROJECT_EXTENSION);
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
                this.filePath = selectedFile.toPath();
                setFileNotChanged();
            } catch (final IOException e) {
                log(READ_ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public void saveProject() {
        final String text = codeTextArea.getText();

        if (isFileOpen()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile()))) {
                writer.write(text);
            } catch (final IOException e) {
                e.printStackTrace();
            }

        } else {
            final JFileChooser fileChooser = new JFileChooser();
            final FileNameExtensionFilter textFileFilter = new FileNameExtensionFilter(TEXT_FILES_MESSAGE, PROJECT_EXTENSION);
            fileChooser.setFileFilter(textFileFilter);

            if (fileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
                try {
                    final File selectedFile = fileChooser.getSelectedFile();

                    if (isProjectFileExtension(selectedFile)) {
                        this.filePath = selectedFile.toPath();
                    } else {
                        this.filePath = Paths.get(selectedFile.getPath().toString() + PROJECT_FILE_EXTENSION);
                    }

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile()))) {
                        writer.write(text);
                    }
                } catch (final IOException e) {
                    log(SAVE_ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }

        setFileNotChanged();
        clean(messageTextArea);
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
        log(compiler.compile(codeTextArea.getText(), ""));
    }

    public void build() {
        this.saveProject();

        final String projectFileName = this.filePath.toFile().getName().replace(PROJECT_FILE_EXTENSION, "");
        final String compilationMessage = compiler.compile(codeTextArea.getText(), projectFileName);

        if (this.compiler.isCompilationSuccessed()) {
            try {
                final String projectFilePath = this.filePath.toString().replace(PROJECT_FILE_EXTENSION, OUTPUT_FILE_EXTENSION);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(projectFilePath))) {
                    writer.write(this.compiler.getSourceCode());
                }
            } catch (final IOException e) {
                log(SAVE_ERROR_MESSAGE);
            }
        }

        log(compilationMessage);
    }

    public void showTeam() {
        log(TEAM_MESSAGE);
    }

    public void setFileChanged() {
        bottomLabel.setText(isFileOpen() ? String.format(CHANGED_FILE, this.filePath) : CHANGED);
    }

    public void setFileNotChanged() {
        bottomLabel.setText(isFileOpen() ? String.format(NON_CHANGED_FILE, this.filePath) : NON_CHANGED);
    }

    private void clean(final JTextArea textArea) {
        textArea.setText("");
    }

    private boolean isProjectFileExtension(final File selectedFile) {
        return selectedFile.getName().endsWith(PROJECT_EXTENSION);
    }

    private boolean isFileOpen() {
        return this.filePath != null;
    }

    private void log(final String text) {
        messageTextArea.setText(text);
    }

}
