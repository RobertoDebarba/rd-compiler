package br.com.furb.trabalho_compiladores.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SemanticRunnerTest {

    private static SemanticRunnerTestSteps steps = new SemanticRunnerTestSteps();

    @Before
    public void setUp() {
        steps.clean();
    }

    @Test
    public void testRun15() throws Exception {
        String fileName = "Teste00.txt";
        steps.giveFileName(fileName);

        steps.whenRunStep15();

        steps.thenSourceCodeShouldEquals("Cabeçalho inválido", String.format(DotNetCommands.PROGRAM_HEADER, fileName, fileName));
    }

    @Test
    public void testRun1() throws Exception {
        steps.giveStackTypesContains(DataType.INT);
        steps.giveStackTypesContains(DataType.INT);
        steps.givenTokenIsValid();

        steps.whenRunStep1();

        steps.thenSourceCodeShouldEquals("Soma binária inválida", DotNetCommands.ADD);
    }

    @Test
    public void testRun17() throws Exception {
        steps.whenRunStep17();

        steps.thenSourceCodeShouldEquals("Fim de programa inválido", DotNetCommands.CLOSE_BRACES);
    }

    @Test
    public void testRun18() throws Exception {
        steps.whenRunStep18();
        steps.thenSourceCodeShouldEquals("Quebra de linha inválido",
                DotNetCommands.LDSTR + SemanticRunner.getSPACE() + SemanticRunner.getScapedLineBreak());
    }
}