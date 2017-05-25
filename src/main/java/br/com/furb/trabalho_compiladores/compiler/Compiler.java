package br.com.furb.trabalho_compiladores.compiler;

public final class Compiler {

	private static final String GENERIC_LINE_ERROR_MESSAGE = "Erro na linha %s – %s";
	private static final String INVALID_TOKEN_LINE_ERROR_MESSAGE = "Erro na linha %s – %s %s";
	private static final String COMPILED_SUCCESSFULLY_MESSAGE = "programa compilado com sucesso";
	private static final String END_OF_FILE_MESSAGE = "fim de arquivo";
	private static final String INVALID_TOKEN_MESSAGE = "símbolo inválido";

	private static final String END_OF_FILE_TOKEN = "$";
	private static final char LINE_BREAK = '\n';

	public String compile(final String sourceCode) {
		try {
			final Lexico lexico = new Lexico();
			final Semantico semantico = new Semantico();
			final Sintatico sintatico = new Sintatico();

			lexico.setInput(sourceCode);
			sintatico.parse(lexico, semantico);

			return COMPILED_SUCCESSFULLY_MESSAGE;
		} catch (final LexicalError e) {
			if (e.getMessage().equals(INVALID_TOKEN_MESSAGE)) {
				return String.format(INVALID_TOKEN_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), sourceCode.charAt(e.getPosition()),
						e.getMessage());
			} else {
				return String.format(GENERIC_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), e.getMessage());
			}
		} catch (final SyntaticError e) {
			String token = e.getToken();
			if (token.equals(END_OF_FILE_TOKEN)) {
				token = END_OF_FILE_MESSAGE;
			}
			return String.format(GENERIC_LINE_ERROR_MESSAGE, findLine(sourceCode, e.getPosition()), String.format(e.getMessage(), token));
		} catch (final SemanticError e) {
			e.printStackTrace();
			return ""; // TODO
		}
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
