package br.com.robertodebarba.rd_compiler.compiler;

import org.junit.Assert;

class SemanticRunnerTestSteps {

    private SemanticRunner semanticRunner = new SemanticRunner();
    private Token validToken;
    private String validFileName;
    private String exceptionMessage;

    void givenStackTypesContains(String type) {
        semanticRunner.addType(type);
    }

    void givenTokenIsValid() {
        validToken = new Token(0, "+", 3);
    }

    Token getLogicalToken() {
        return new Token(0, ">", 0);
    }

    void givenFileName(String fileName) {
        validFileName = fileName;
    }

    void whenRunStep1() throws SemanticError {
        semanticRunner.run1(validToken);
    }

    void whenRun10(Token token) throws SemanticError {
        try {
            semanticRunner.setOperator(">=");
            semanticRunner.run10(token);
        } catch (Exception e) {
            exceptionMessage = e.getMessage();
        }
    }

    void whenRunStep15() throws SemanticError {
        semanticRunner.run15(validFileName);
    }

    void whenRunStep17() throws SemanticError {
        semanticRunner.run17();
    }

    void whenRunStep18() {
        semanticRunner.run18();
    }

    void whenRunStep19(Token token) throws SemanticError {
        semanticRunner.run19(token);
    }

    void clean() {
        semanticRunner = new SemanticRunner();
    }

    void thenSourceCodeShouldContainsAndExpression() {
        Assert.assertEquals("tipo incompatível em operação lógica binária", DotNetCommands.AND + "\n", semanticRunner.getSourceCode());
    }

    void thenSourceCodeShouldContainsLineBreak() {
        Assert.assertEquals("Quebra de linha inválido", DotNetCommands.LDSTR + SemanticRunner.getSPACE() + SemanticRunner.getScapedLineBreak() + "\n", semanticRunner.getSourceCode());
    }

    void thenSourceCodeShouldEndsWithCloseBraces() {
        Assert.assertEquals("Fim de programa inválido", DotNetCommands.CLOSE_BRACES + "\n", semanticRunner.getSourceCode());
    }

    void thenSourceCodeShouldContainsAddOperation() {
        Assert.assertEquals("Soma binária inválida", DotNetCommands.ADD + "\n", semanticRunner.getSourceCode());
    }

    void thenSourceCodeShouldStartsWithProgramHeader() {
        Assert.assertEquals("Cabeçalho inválido", String.format(DotNetCommands.PROGRAM_HEADER, validFileName, validFileName) + "\n", semanticRunner.getSourceCode());
    }

    void thenSourceCodeShouldThrowIncompatibleTypesException() {
        Assert.assertEquals("Operação lógica inválida", "tipos incompatíveis em operação relacional", exceptionMessage);
    }
}
