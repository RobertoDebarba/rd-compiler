package br.com.robertodebarba.rd_compiler.compiler;

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
        steps.givenFileName(fileName);

        steps.whenRunStep15();

        steps.thenSourceCodeShouldStartsWithProgramHeader();
    }

    @Test
    public void testRun1() throws Exception {
        steps.givenStackTypesContains(DataType.INT);
        steps.givenStackTypesContains(DataType.INT);
        steps.givenTokenIsValid();

        steps.whenRunStep1();

        steps.thenSourceCodeShouldContainsAddOperation();
    }

    @Test
    public void testRun17() throws Exception {
        steps.whenRunStep17();
        steps.thenSourceCodeShouldEndsWithCloseBraces();
    }

    @Test
    public void testRun18() throws Exception {
        steps.whenRunStep18();
        steps.thenSourceCodeShouldContainsLineBreak();
    }

    @Test
    public void testRun19() throws Exception {
        steps.givenStackTypesContains(DataType.BOOLEAN);
        steps.givenStackTypesContains(DataType.BOOLEAN);
        steps.whenRunStep19(steps.getLogicalToken());
        steps.thenSourceCodeShouldContainsAndExpression();
    }

    @Test
    public void testRun10() throws Exception {
        steps.givenStackTypesContains(DataType.STRING);
        steps.givenStackTypesContains(DataType.STRING);

        steps.whenRun10(steps.getLogicalToken());

        steps.thenSourceCodeShouldThrowIncompatibleTypesException();
    }
}