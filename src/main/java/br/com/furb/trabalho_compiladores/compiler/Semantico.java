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
			break;
		case 2:
			this.semanticRunner.run2();
			break;
		case 3:
			this.semanticRunner.run3();
			break;
		case 4:
			this.semanticRunner.run4();
			break;
		case 5:
			this.semanticRunner.run5(token.getLexeme());
			break;
		case 6:
			this.semanticRunner.run6(token.getLexeme());
			break;
		case 7:
			this.semanticRunner.run7();
			break;
		case 8:
			this.semanticRunner.run8();
			break;
		case 9:
			this.semanticRunner.run9();
			break;
		case 10:
			this.semanticRunner.run10();
			break;
		case 11:
			this.semanticRunner.run11(token.getLexeme());
			break;
		case 12:
			this.semanticRunner.run12();
			break;
		case 13:
			this.semanticRunner.run13();
			break;
		case 14:
			this.semanticRunner.run14();
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
		case 18:
			this.semanticRunner.run18();
			break;
		case 19:
			this.semanticRunner.run19();
			break;
		case 20:
			this.semanticRunner.run20();
			break;
		case 21:
			this.semanticRunner.run21(token.getLexeme());
			break;
		case 22:
			this.semanticRunner.run22(token.getLexeme());
			break;
		case 23:
			this.semanticRunner.run23(token.getLexeme());
			break;
		case 24:
			this.semanticRunner.run24();
			break;
		case 25:
			this.semanticRunner.run25();
			break;
		case 26:
			this.semanticRunner.run26(token.getLexeme());
			break;
		case 27:
			this.semanticRunner.run27();
			break;

		default:
			break;
		}
	}

	public String getSourceCode() {
		return this.sourceCode;
	}
}
