package br.com.furb.trabalho_compiladores.compiler;

final class Semantico implements Constants {

	public String fileName;
	private String sourceCode;
	private final SemanticRunner semanticRunner = new SemanticRunner();

	public Semantico(final String fileName) {
		super();
		this.fileName = fileName;
	}

	public void executeAction(final int action, final Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);
		switch (action) {
		case 1:
			this.semanticRunner.run1();
		case 2:
			this.semanticRunner.run2();
		case 3:
			this.semanticRunner.run3();
		case 4:
			this.semanticRunner.run4();
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
			this.semanticRunner.run15(this.fileName);
			break;
		case 16:
			this.semanticRunner.run16();
			break;
		case 17:
			this.sourceCode = this.semanticRunner.run17();
			break;

		default:
			break;
		}
	}

	public String getSourceCode() {
		return this.sourceCode;
	}
}
