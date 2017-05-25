package br.com.furb.trabalho_compiladores.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class CompilerWindow extends JFrame {

	private static final long serialVersionUID = 8845541166781805405L;

	private static final String KEY_F1 = "F1";
	private static final String KEY_F8 = "F8";
	private static final String KEY_F9 = "F9";

	private static final String COPY_ICON = "editcopy.png";
	private static final String TEAM_ICON = "kontact_contacts.png";
	private static final String BUILD_ICON = "gear.png";
	private static final String PLAY_ICON = "player_play.png";
	private static final String EDIT_ICON = "editcut.png";
	private static final String PASTE_ICON = "editpaste.png";
	private static final String SAVE_ICON = "filesave.png";
	private static final String OPEN_ICON = "fileopen.png";
	private static final String NEW_ICON = "filenew.png";

	private static final String SAVE_CTRL_S = "salvar [ctrl-s]";
	private static final String TEAM_F1 = "equipe [F1]";
	private static final String BUILD_F9 = "gerar código [F9]";
	private static final String COMPILE_F8 = "compilar [F8]";
	private static final String PASTE_CTRL_V = "colar [ctrl-v]";
	private static final String CUT_CTRL_X = "recortar [ctrl-x]";
	private static final String COPY_CTRL_C = "copiar [ctrl-c]";
	private static final String OPEN_CTRL_O = "abrir [ctrl-o]";
	private static final String NEW_CTRL_N = "novo [ctrl-n]";

	private final JPanel contentPane;

	public CompilerWindow(final JTextArea codeTextArea, final JTextArea messageTextArea, final JLabel bottomLabel, final CompilerWindowHandler windowHandler) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 620);
		setMinimumSize(new Dimension(900, 620));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final JPanel buttonsPanel = new JPanel();
		final JPanel bottomPanel = new JPanel();

		final JScrollPane codePanel = new JScrollPane();
		codePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		codePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		final JScrollPane messagePanel = new JScrollPane();
		messagePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		messagePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		final GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
				.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(messagePanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
						.addComponent(codePanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)))
				.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(codePanel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(messagePanel, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)));

		codeTextArea.setBorder(new NumberedBorder());
		codePanel.setViewportView(codeTextArea);

		messageTextArea.setEditable(false);
		messagePanel.setViewportView(messageTextArea);

		final JButton newButton = new JButton(NEW_CTRL_N);
		newButton.setIcon(new ImageIcon(getClass().getResource(NEW_ICON)));
		newButton.setHorizontalTextPosition(JButton.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonsPanel.add(newButton);

		final JButton openButton = new JButton(OPEN_CTRL_O);
		openButton.setIcon(new ImageIcon(getClass().getResource(OPEN_ICON)));
		openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		openButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(openButton);

		final JButton saveButton = new JButton(SAVE_CTRL_S);
		saveButton.setIcon(new ImageIcon(getClass().getResource(SAVE_ICON)));
		saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(saveButton);

		final JButton copyButton = new JButton(COPY_CTRL_C);
		copyButton.setIcon(new ImageIcon(getClass().getResource(COPY_ICON)));
		copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(copyButton);

		final JButton pasteButton = new JButton(PASTE_CTRL_V);
		pasteButton.setIcon(new ImageIcon(getClass().getResource(PASTE_ICON)));
		pasteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		pasteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(pasteButton);

		final JButton cutButton = new JButton(CUT_CTRL_X);
		cutButton.setIcon(new ImageIcon(getClass().getResource(EDIT_ICON)));
		cutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cutButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(cutButton);

		final JButton compileButton = new JButton(COMPILE_F8);
		compileButton.setIcon(new ImageIcon(getClass().getResource(PLAY_ICON)));
		compileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		compileButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(compileButton);

		final JButton buildButton = new JButton(BUILD_F9);
		buildButton.setIcon(new ImageIcon(getClass().getResource(BUILD_ICON)));
		buildButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		buildButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(buildButton);

		final JButton teamButton = new JButton(TEAM_F1);
		teamButton.setIcon(new ImageIcon(getClass().getResource(TEAM_ICON)));
		teamButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		teamButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		buttonsPanel.add(teamButton);

		bottomPanel.setLayout(null);

		bottomLabel.setBounds(0, 3, 865, 14);
		bottomPanel.add(bottomLabel);
		contentPane.setLayout(gl_contentPane);

		windowHandler.setFileNotChanged();

		this.setAction(copyButton, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), COPY_CTRL_C, (e) -> windowHandler.copy());

		this.setAction(newButton, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), NEW_CTRL_N, (e) -> windowHandler.newProject());

		this.setAction(openButton, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), OPEN_CTRL_O, (e) -> windowHandler.openProject());

		this.setAction(cutButton, KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), CUT_CTRL_X, (e) -> windowHandler.cut());

		this.setAction(pasteButton, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), PASTE_CTRL_V, (e) -> windowHandler.paste());

		this.setAction(saveButton, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), SAVE_CTRL_S, (e) -> windowHandler.saveProject());

		this.setAction(compileButton, KeyStroke.getKeyStroke(KEY_F8), COMPILE_F8, (e) -> windowHandler.compile());

		this.setAction(buildButton, KeyStroke.getKeyStroke(KEY_F9), BUILD_F9, (e) -> windowHandler.build());

		this.setAction(teamButton, KeyStroke.getKeyStroke(KEY_F1), TEAM_F1, (e) -> windowHandler.showTeam());

		codeTextArea.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(final DocumentEvent e) {
				windowHandler.setFileChanged();
			}

			public void insertUpdate(final DocumentEvent e) {
				windowHandler.setFileChanged();
			}

			public void changedUpdate(final DocumentEvent arg0) {
				windowHandler.setFileChanged();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(final WindowEvent e) {
				super.windowOpened(e);
				codeTextArea.requestFocus();
			}
		});
	}

	private void setAction(final AbstractButton button, final KeyStroke keyStroke, final Object actionMapKey, final ActionListener action) {
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionMapKey);
		button.getActionMap().put(actionMapKey, new AbstractAction() {
			private static final long serialVersionUID = -6000880758361767786L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				action.actionPerformed(e);
			}
		});
		button.addActionListener(action);
	}

}
