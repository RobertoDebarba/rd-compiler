package br.com.furb.trabalho_compiladores.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
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

public class MainWindow extends JFrame {

	private static final String SALVAR_CTRL_S = "salvar [ctrl-s]";
	private static final String MODIFICADO = "modificado";
	public static final String EQUIPE_F1 = "equipe [F1]";
	public static final String GERAR_CODIGO_F9 = "gerar código [F9]";
	public static final String COMPILAR_F8 = "compilar [F8]";
	public static final String COLAR_CTRL_V = "colar [ctrl-v]";
	public static final String RECORTAR_CTRL_X = "recortar [ctrl-x]";
	public static final String COPIAR_CTRL_C = "copiar [ctrl-c]";
	public static final String ABRIR_CTRL_O = "abrir [ctrl-o]";
	public static final String NOVO_CTRL_N = "novo [ctrl-n]";
	static final String NAO_MODIFICADO = "não modificado";
	private static final long serialVersionUID = 8845541166781805405L;
	private JPanel contentPane;
	private File file;

	public MainWindow() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 620);
		setMinimumSize(new Dimension(900, 620));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel buttonsPanel = new JPanel();
		JPanel bottonPanel = new JPanel();

		final JScrollPane codePanel = new JScrollPane();
		codePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		codePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JScrollPane messagePanel = new JScrollPane();
		messagePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		messagePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(messagePanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
								.addComponent(codePanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)))
				.addComponent(bottonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(codePanel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(messagePanel,
												GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
								.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 544,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(bottonPanel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)));

		final JTextArea codeTextArea = new JTextArea();
		codeTextArea.setBorder(new NumberedBorder());
		codePanel.setViewportView(codeTextArea);

		final JTextArea messageTextArea = new JTextArea();
		messageTextArea.setEditable(false);
		messagePanel.setViewportView(messageTextArea);

		JButton newButton = new JButton(NOVO_CTRL_N);
		newButton.setIcon(new ImageIcon(getClass().getResource("filenew.png")));
		newButton.setHorizontalTextPosition(JButton.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);

		JButton openButton = new JButton(ABRIR_CTRL_O);
		openButton.setIcon(new ImageIcon(getClass().getResource("fileopen.png")));
		openButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		openButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton saveButton = new JButton(SALVAR_CTRL_S);
		saveButton.setIcon(new ImageIcon(getClass().getResource("filesave.png")));
		saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton pasteButton = new JButton(COLAR_CTRL_V);
		pasteButton.setIcon(new ImageIcon(getClass().getResource("editpaste.png")));
		pasteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		pasteButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton cutButton = new JButton(RECORTAR_CTRL_X);
		cutButton.setIcon(new ImageIcon(getClass().getResource("editcut.png")));
		cutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cutButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton compileButton = new JButton(COMPILAR_F8);
		compileButton.setIcon(new ImageIcon(getClass().getResource("player_play.png")));
		compileButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		compileButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton buildButton = new JButton(GERAR_CODIGO_F9);
		buildButton.setIcon(new ImageIcon(getClass().getResource("gear.png")));
		buildButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		buildButton.setHorizontalTextPosition(SwingConstants.CENTER);

		JButton teamButton = new JButton(EQUIPE_F1);
		teamButton.setIcon(new ImageIcon(getClass().getResource("kontact_contacts.png")));
		teamButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		teamButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		buttonsPanel.add(newButton);
		buttonsPanel.add(openButton);
		buttonsPanel.add(saveButton);

		JButton copyButton = new JButton(COPIAR_CTRL_C);
		copyButton.setIcon(new ImageIcon(getClass().getResource("editcopy.png")));
		copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonsPanel.add(copyButton);

		copyButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), COPIAR_CTRL_C);
		copyButton.getActionMap().put(COPIAR_CTRL_C, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.copyOnCommandClick(codeTextArea);
			}
		});
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.copyOnCommandClick(codeTextArea);
			}
		});
		buttonsPanel.add(pasteButton);
		buttonsPanel.add(cutButton);
		buttonsPanel.add(compileButton);
		buttonsPanel.add(buildButton);
		buttonsPanel.add(teamButton);
		bottonPanel.setLayout(null);

		final JLabel bottonLabel = new JLabel(NAO_MODIFICADO);
		bottonLabel.setBounds(0, 3, 865, 14);
		bottonPanel.add(bottonLabel);
		contentPane.setLayout(gl_contentPane);

		// Definicao dos atalhos e funcionalidades.
		newButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), NOVO_CTRL_N);
		newButton.getActionMap().put(NOVO_CTRL_N, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.newCommandOnClick(codeTextArea, messageTextArea, bottonLabel);
				setFile(null);
			}
		});
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.newCommandOnClick(codeTextArea, messageTextArea, bottonLabel);
				setFile(null);
			}
		});

		openButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), ABRIR_CTRL_O);
		openButton.getActionMap().put(ABRIR_CTRL_O, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				setFile(MainWindowEvents.openOnCommandClick(codeTextArea, messageTextArea, bottonLabel, getParent()));
			}
		});
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFile(MainWindowEvents.openOnCommandClick(codeTextArea, messageTextArea, bottonLabel, getParent()));
			}
		});

		cutButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), RECORTAR_CTRL_X);
		cutButton.getActionMap().put(RECORTAR_CTRL_X, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.cutOnCommandClick(codeTextArea);
			}
		});
		cutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.cutOnCommandClick(codeTextArea);
			}
		});

		pasteButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), COLAR_CTRL_V);
		pasteButton.getActionMap().put(COLAR_CTRL_V, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.pasteOnCommandClick(codeTextArea);
			}
		});
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.pasteOnCommandClick(codeTextArea);
			}
		});

		compileButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), COMPILAR_F8);
		compileButton.getActionMap().put(COMPILAR_F8, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.compileOnCommandClick(codeTextArea, messageTextArea);
			}
		});
		compileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.compileOnCommandClick(codeTextArea, messageTextArea);
			}
		});

		buildButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), GERAR_CODIGO_F9);
		buildButton.getActionMap().put(GERAR_CODIGO_F9, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.buildOnCommandClick(messageTextArea);
			}
		});
		buildButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.buildOnCommandClick(messageTextArea);
			}
		});

		teamButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), EQUIPE_F1);
		teamButton.getActionMap().put(EQUIPE_F1, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.teamOnCommandClick(messageTextArea);
			}
		});
		teamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowEvents.teamOnCommandClick(messageTextArea);
			}
		});

		codeTextArea.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				bottonLabel.setText(getFile() != null ? MODIFICADO + " - " + getFile().getPath() : MODIFICADO);
			}

			public void insertUpdate(DocumentEvent e) {
				bottonLabel.setText(getFile() != null ? MODIFICADO + " - " + getFile().getPath() : MODIFICADO);
			}

			public void changedUpdate(DocumentEvent arg0) {
				bottonLabel.setText(getFile() != null ? MODIFICADO + " - " + getFile().getPath() : MODIFICADO);
			}
		});

		saveButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), SALVAR_CTRL_S);
		saveButton.getActionMap().put(SALVAR_CTRL_S, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				setFile(MainWindowEvents.saveOnCommandClick(codeTextArea, messageTextArea, bottonLabel, getFile()));
			}
		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFile(MainWindowEvents.saveOnCommandClick(codeTextArea, messageTextArea, bottonLabel, getFile()));
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				super.windowOpened(e);
				codeTextArea.requestFocus();
			}

		});
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
