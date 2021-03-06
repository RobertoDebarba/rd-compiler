package br.com.robertodebarba.rd_compiler.compiler;

public final class Compiler {

    private static final String GENERIC_LINE_ERROR_MESSAGE = "Erro na linha %s – %s";
    private static final String INVALID_TOKEN_LINE_ERROR_MESSAGE = "Erro na linha %s – %s %s";
    private static final String COMPILED_SUCCESSFULLY_MESSAGE = "programa compilado com sucesso";
    private static final String END_OF_FILE_MESSAGE = "fim de arquivo";
    private static final String INVALID_TOKEN_MESSAGE = "símbolo inválido";

    private static final String UNDERLINE_CHAR = "_";
    private static final String SPACE_CHAR = " ";

    private static final String END_OF_FILE_TOKEN = "$";
    private static final char LINE_BREAK = '\n';

    private boolean isCompilationSuccessed = false;

    private Semantico semantico;
    private final Lexico lexico;
    private final Sintatico sintatico;

    public Compiler(final Lexico lexico, final Sintatico sintatico) {
        this.lexico = lexico;
        this.sintatico = sintatico;
    }

    public String compile(final String sourceCode, String fileName) {
        try {
            semantico = new Semantico(fileName.replace(SPACE_CHAR, UNDERLINE_CHAR));

            lexico.setInput(sourceCode);
            sintatico.parse(lexico, semantico);

            this.isCompilationSuccessed = true;
            return COMPILED_SUCCESSFULLY_MESSAGE;
        } catch (final LexicalError e) {
            this.isCompilationSuccessed = false;
            e.printStackTrace();

            if (e.getMessage().equals(INVALID_TOKEN_MESSAGE)) {
                return String.format(INVALID_TOKEN_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), sourceCode.charAt(e.getPosition()),
                        e.getMessage());
            } else {
                return String.format(GENERIC_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), e.getMessage());
            }
        } catch (final SyntaticError e) {
            this.isCompilationSuccessed = false;
            e.printStackTrace();

            String token = e.getToken();
            if (token.equals(END_OF_FILE_TOKEN)) {
                token = END_OF_FILE_MESSAGE;
            }
            return String.format(GENERIC_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), String.format(e.getMessage(), token));
        } catch (final SemanticError e) {
            this.isCompilationSuccessed = false;
            e.printStackTrace();

            return String.format(GENERIC_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), e.getMessage());
        }
    }

    public boolean isCompilationSuccessed() {
        return this.isCompilationSuccessed;
    }

    public String getSourceCode() {
        return this.semantico.getSourceCode();
    }

    private int findLine(final String sourceCode, final int index) {
        final String fileContentAfterIndex = sourceCode.substring(0, index);

        int lineCounter = 1;
        for (int i = 0; i < fileContentAfterIndex.length(); i++) {
            if (fileContentAfterIndex.charAt(i) == LINE_BREAK) {
                lineCounter++;
            }
        }
        return lineCounter;
    }

}
