package br.com.furb.trabalho_compiladores.compiler;

import java.util.Stack;

class SemanticRunner {

	private static final String SPACE = " ";
	private static final String LINE_BREAK = "\n";
	private static final String SCAPED_LINE_BREAK = "\"\\n\"";

	private String operator;
	private final StringBuilder sourceCode = new StringBuilder();
	private final Stack<String> types = new Stack<>();

	/**
	 * Operação aritmética binária: <b>adição</b>
	 * 
	 * @throws SemanticError
	 */
	public void run1() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2)) || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			// TODO validar e extrair mensagens
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.ADD);
	}

	/**
	 * Operação aritmética binária: <b>subtração</b>
	 * 
	 * @throws SemanticError
	 */
	public void run2() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2)) || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.SUB);
	}

	/**
	 * Operação aritmética binária: <b>multiplicação</b>
	 * 
	 * @throws SemanticError
	 */
	public void run3() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2)) || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.MUL);
	}

	/**
	 * Operação aritmética binária: <b>divisão</b>
	 * 
	 * @throws SemanticError
	 */
	public void run4() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2)) || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.DIV);
	}

	/**
	 * Constante inteira
	 * 
	 * @param lexeme
	 */
	public void run5(final String lexeme) {
		this.types.push(DataType.INT);
		this.appendSourceCode(DotNetCommands.LDC_I8 + lexeme);
	}

	/**
	 * Constante real
	 * 
	 * @param lexeme
	 */
	public void run6(final String lexeme) {
		this.types.push(DataType.FLOAT);
		this.appendSourceCode(DotNetCommands.LDC_R8 + lexeme);
	}

	/**
	 * Escrever elemento
	 */
	public void run7() {
		this.appendSourceCode(String.format(DotNetCommands.PRINT, this.types.pop()));
	}

	/**
	 * Valor constante positivo
	 * 
	 * @throws SemanticError
	 */
	public void run8() throws SemanticError {
		final String type = this.types.pop();

		if (isInt(type) || isFloat(type)) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação unária.");
		}
	}

	/**
	 * Valor constante negativo
	 * 
	 * @throws SemanticError
	 */
	public void run9() throws SemanticError {
		final String type = this.types.pop();

		if (isInt(type) || isFloat(type)) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação unária.");
		}

		this.appendSourceCode(DotNetCommands.LDC_I8_1);
		this.appendSourceCode(DotNetCommands.MUL);
	}

	/**
	 * Operação relacional
	 * 
	 * @throws SemanticError
	 */
	public void run10() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if ((isInt(type1) || isFloat(type1) && isInt(type1) || isFloat(type1)) || (isString(type1) && isString(type2))) {
			this.types.push(DataType.BOOLEAN);
		} else {
			throw new SemanticError("tipo incompatível em operação relacional");
		}

		switch (this.operator) {
		case RelationalOperator.EQUALS:
			this.appendSourceCode(DotNetCommands.CEQ);
			break;
		case RelationalOperator.LESS_THAN:
			this.appendSourceCode(DotNetCommands.CLT);
			break;
		case RelationalOperator.GREATER_THAN:
			this.appendSourceCode(DotNetCommands.CGT);
			break;
		case RelationalOperator.NOT_EQUAL:
			this.appendSourceCode(DotNetCommands.CGT);
			this.appendSourceCode(DotNetCommands.LDC_I4_1);
			this.appendSourceCode(DotNetCommands.XOR);
			break;
		case RelationalOperator.LESS_THAN_OR_EQUAL:
			// TODO demais operadores
			break;
		case RelationalOperator.GREATER_THAN_OR_EQUAL:
			// TODO demais operadores
			break;
		}
	}

	/**
	 * Operador relacional
	 * 
	 * @param lexeme
	 */
	public void run11(final String lexeme) {
		this.operator = lexeme;
	}

	/**
	 * Operador verdadeiro
	 */
	public void run12() {
		this.types.push(DataType.BOOLEAN);
		this.appendSourceCode(DotNetCommands.LDC_I4_1);
	}

	/**
	 * Operador falso
	 */
	public void run13() {
		this.types.push(DataType.BOOLEAN);
		this.appendSourceCode(DotNetCommands.LDC_I4_0);
	}

	/**
	 * Operador negação
	 * 
	 * @throws SemanticError
	 */
	public void run14() throws SemanticError {
		final String type = this.types.pop();

		if (isBoolean(type)) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação not");
		}

		this.appendSourceCode(DotNetCommands.LDC_I4_1);
		this.appendSourceCode(DotNetCommands.XOR);
	}

	/**
	 * Cabeçalho programa
	 * 
	 * @param fileName
	 */
	public void run15(final String fileName) {
		this.appendSourceCode(String.format(DotNetCommands.PROGRAM_HEADER, fileName, fileName));
	}

	/**
	 * Fim bloco
	 */
	public void run16() {
		this.appendSourceCode(DotNetCommands.RET);
		this.appendSourceCode(DotNetCommands.CLOSE_BRACES);
	}

	/**
	 * Fim programa
	 * 
	 * @return
	 */
	public String run17() {
		this.appendSourceCode(DotNetCommands.CLOSE_BRACES);

		return this.sourceCode.toString();
	}

	/**
	 * Quebra
	 */
	public void run18() {
		this.types.push(DataType.STRING);
		this.appendSourceCode(DotNetCommands.LDSTR + SPACE + SCAPED_LINE_BREAK);
	}

	/**
	 * Operação lógica binária: <b>e</b>
	 * 
	 * @throws SemanticError
	 */
	public void run19() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isBoolean(type1) || isBoolean(type2)) {
			this.types.push(DataType.BOOLEAN);
		} else {
			throw new SemanticError("tipo incompatível em operação lógica");
		}

		this.appendSourceCode(DotNetCommands.AND);
	}

	/**
	 * Operação lógica binária: <b>ou</b>
	 * 
	 * @throws SemanticError
	 */
	public void run20() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isBoolean(type1) || isBoolean(type2)) {
			this.types.push(DataType.BOOLEAN);
		} else {
			throw new SemanticError("tipo incompatível em operação lógica");
		}

		this.appendSourceCode(DotNetCommands.OR);
	}

	/**
	 * Constante caracter
	 * 
	 * @param lexeme
	 * @throws SemanticError
	 */
	public void run21(final String lexeme) throws SemanticError {
		this.types.push(DataType.STRING);

		this.appendSourceCode(DotNetCommands.LDSTR + SPACE + lexeme);
	}

	private void appendSourceCode(final String sourceCode) {
		this.sourceCode.append(sourceCode).append(LINE_BREAK);
	}

	private boolean isBoolean(final String type) {
		return type.equals(DataType.BOOLEAN);
	}

	private boolean isInt(final String type) {
		return type.equals(DataType.INT);
	}

	private boolean isFloat(final String type) {
		return type.equals(DataType.FLOAT);
	}

	private boolean isString(final String type) {
		return type.equals(DataType.STRING);
	}

}
