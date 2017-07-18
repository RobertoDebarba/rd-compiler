package br.com.furb.trabalho_compiladores.compiler;

import org.junit.Assert;
import org.junit.Test;

public class SemanticRunnerTest {

    @Test
    public void testRun15() throws Exception {
        SemanticRunner semanticRunner = new SemanticRunner();
        String fileName = "Teste00.txt";
        String expectedSourceCode = String.format(DotNetCommands.PROGRAM_HEADER, fileName, fileName) + "\n";

        semanticRunner.run15(fileName);
        StringBuilder sourceCode = semanticRunner.getSourceCode();

        Assert.assertEquals("Cabeçalho inválido", expectedSourceCode, sourceCode.toString());
    }

}