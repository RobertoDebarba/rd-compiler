package br.com.robertodebarba.rd_compiler.compiler;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CompilerTest {

    @Test
    public void testCompileLexicalInvalidToken() throws Exception {
        Lexico lexicoMock = Mockito.mock(Lexico.class);
        Mockito.when(lexicoMock.nextToken()).thenThrow(new LexicalError("símbolo inválido", 0));

        Compiler compiler = new Compiler(lexicoMock, new Sintatico());
        String compileResult = compiler.compile("algoritmo", "");

        Assert.assertEquals("Mensagem de erro diferente da esperada", "Erro na linha 1 – a símbolo inválido", compileResult);
    }

    @Test
    public void testCompileSyntatic() throws Exception {
        Sintatico sintaticoMock = Mockito.mock(Sintatico.class);
        Mockito.doThrow(new SyntaticError("símbolo inválido", 0, "algoritmo")).when(sintaticoMock).parse(Mockito.any(), Mockito.any());

        Compiler compiler = new Compiler(new Lexico(), sintaticoMock);
        String compileResult = compiler.compile("algoritmo", "");

        Assert.assertEquals("Mensagem de erro diferente da esperada", "Erro na linha 1 – símbolo inválido", compileResult);
    }

}