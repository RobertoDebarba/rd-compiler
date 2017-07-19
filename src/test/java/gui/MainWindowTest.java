package gui;

import br.com.furb.trabalho_compiladores.view.CompilerWindow;
import br.com.furb.trabalho_compiladores.view.CompilerWindowFactory;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainWindowTest {

    private FrameFixture window;

    @Before
    public void setUp() {
        CompilerWindow frame = GuiActionRunner.execute(CompilerWindowFactory::createInstance);
        this.window = new FrameFixture(frame);
        this.window.show();
    }

    @After
    public void tearDown() throws Exception {
        this.window.close();
    }

    @Test
    public void testShowTeam() {
        String expectedTeamMessage = "Equipe:\r\n" +
                "\tFrancisco Krautchuk Neto\r\n" +
                "\tRoberto Luiz Debarba";

        this.window.button("teamButton").click();
        this.window.textBox("messageTextArea").requireText(expectedTeamMessage);
    }

    @Test
    public void testCompileSuccess() throws Exception {
        this.window.textBox("codeTextArea").setText("algoritmo \"teste\" início fim");
        this.window.button("compileButton").click();

        this.window.textBox("messageTextArea").requireText("programa compilado com sucesso");
    }

    @Test
    public void testCompileError() throws Exception {
        this.window.textBox("codeTextArea").setText("algoritmo \"teste\" comeco");
        this.window.button("compileButton").click();

        this.window.textBox("messageTextArea").requireText("encontrado %s esperado função início procedimento variáveis");
    }

}
