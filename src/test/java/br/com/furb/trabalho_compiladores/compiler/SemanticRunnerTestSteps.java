package br.com.furb.trabalho_compiladores.compiler;

import org.junit.Assert;

public class SemanticRunnerTestSteps {

    private SemanticRunner semanticRunner = new SemanticRunner();
    private Token validToken;
    private String validFileName;

    void giveStackTypesContains(String type) {
        semanticRunner.addType(type);
    }

    void givenTokenIsValid() {
        validToken = new Token(0, "+", 3);
    }

    void giveFileName(String fileName){
        validFileName = fileName;
    }

    void whenRunStep1() throws SemanticError {
        semanticRunner.run1(validToken);
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

    void thenSourceCodeShouldEquals(String invalidMessage, String expectedSourceCode) {
        Assert.assertEquals(invalidMessage, expectedSourceCode + "\n", semanticRunner.getSourceCode());
    }

    void clean() {
        semanticRunner = new SemanticRunner();
    }

}
