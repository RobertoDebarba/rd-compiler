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
                this.semanticRunner.run1(token);
                break;
            case 2:
                this.semanticRunner.run2(token);
                break;
            case 3:
                this.semanticRunner.run3(token);
                break;
            case 4:
                this.semanticRunner.run4(token);
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
                this.semanticRunner.run8(token);
                break;
            case 9:
                this.semanticRunner.run9(token);
                break;
            case 10:
                this.semanticRunner.run10(token);
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
                this.semanticRunner.run14(token);
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
                this.semanticRunner.run19(token);
                break;
            case 20:
                this.semanticRunner.run20(token);
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
                this.semanticRunner.run24(token);
                break;
            case 25:
                this.semanticRunner.run25(token);
                break;
            case 26:
                this.semanticRunner.run26(token);
                break;
            case 27:
                this.semanticRunner.run27(token);
                break;
            case 28:
                this.semanticRunner.run28();
                break;
            case 29:
                this.semanticRunner.run29();
                break;
            case 30:
                this.semanticRunner.run30();
                break;
            case 31:
                this.semanticRunner.run31();
                break;
            case 32:
                this.semanticRunner.run32();
                break;
            case 33:
                this.semanticRunner.run33(token);
                break;
            case 34:
                this.semanticRunner.run34(token.getLexeme());
                break;
            case 35:
                this.semanticRunner.run35();
                break;
            case 36:
                this.semanticRunner.run36(token);
                break;
            case 37:
                this.semanticRunner.run37(token.getLexeme());
                break;
            case 38:
                this.semanticRunner.run38(token);
                break;
            case 39:
                this.semanticRunner.run39(token);
                break;


            default:
                break;
        }
    }

    public String getSourceCode() {
        return this.sourceCode;
    }
}
