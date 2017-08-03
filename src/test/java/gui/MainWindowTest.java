package gui;

import br.com.robertodebarba.rd_compiler.view.CompilerWindow;
import br.com.robertodebarba.rd_compiler.view.CompilerWindowFactory;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class MainWindowTest {

    private static FrameFixture window;

    @BeforeClass
    public static void setUpClass() {
        CompilerWindow frame = GuiActionRunner.execute(CompilerWindowFactory::createInstance);
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    public void testShowTeam() {
        String expectedTeamMessage = "Equipe:\r\n" +
                "\tFrancisco Krautchuk Neto\r\n" +
                "\tRoberto Luiz Debarba";

        window.button("teamButton").click();
        window.textBox("messageTextArea").requireText(expectedTeamMessage);
    }

    @Test
    public void testCompileSuccess() throws Exception {
        window.textBox("codeTextArea").setText("algoritmo \"teste\" início fim");
        window.button("compileButton").click();

        window.textBox("messageTextArea").requireText("programa compilado com sucesso");
    }

    @Test
    public void testCompileError() throws Exception {
        window.textBox("codeTextArea").setText("algoritmo \"teste\" comeco");
        window.button("compileButton").click();

        window.textBox("messageTextArea").requireText("Erro na linha 1 – encontrado comeco esperado função início procedimento variáveis");
    }

    @Test
    public void testCopyCode() throws Exception {
        window.textBox("codeTextArea").setText("codigo");
        window.textBox("codeTextArea").selectAll();
        window.button("copyButton").click();

        String clipboardContent = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);

        Assert.assertEquals("CopyButton doesnt work", "codigo", clipboardContent);
    }

    @Test
    public void testPasteCode() throws Exception {
        StringSelection clipboardContent = new StringSelection("codigoColado");
        Toolkit.getDefaultToolkit()
                .getSystemClipboard().setContents(clipboardContent, clipboardContent);

        window.textBox("codeTextArea").setText("");
        window.button("pasteButton").click();

        window.textBox("codeTextArea").requireText("codigoColado");
    }

    @Test
    public void testCutCode() throws Exception {
        window.textBox("codeTextArea").setText("codigoRecortado");
        window.textBox("codeTextArea").selectAll();
        window.button("cutButton").click();

        window.textBox("codeTextArea").requireText("");

        String clipboardContent = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);

        Assert.assertEquals("CopyButton doesnt work", "codigoRecortado", clipboardContent);
    }

}
