package br.com.furb.trabalho_compiladores.compiler;

final class Semantico implements Constants {

	private SemanticRunner semanticRunner = new SemanticRunner();

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);
		switch (action) {
		case 5:
			this.semanticRunner.run5(token.getLexeme());
			break;
		case 6:
			this.semanticRunner.run6(token.getLexeme());
			break;
		case 7:
			this.semanticRunner.run7();
			break;
		case 15:
			this.semanticRunner.run15("nome do arquivo");// TODO nome do arquivo
			break;
		case 16:
			this.semanticRunner.run16();
			break;
		case 17:
			this.semanticRunner.run17();
			break;

		default:
			break;
		}
	}
}
