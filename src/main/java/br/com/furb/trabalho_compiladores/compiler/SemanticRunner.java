package br.com.furb.trabalho_compiladores.compiler;

import java.util.*;

class SemanticRunner {

    private String moduleName;

    class Symbol {
        public Symbol(String type, List<Parameter> parameters) {
            this.type = type;
            this.parameters = parameters;
        }

        String type;
        List<Parameter> parameters;
    }

    class Parameter {
        public Parameter(String id, String type) {
            this.id = id;
            this.type = type;
        }

        String id;
        String type;
    }

	private static final String SPACE = " ";
	private static final String LINE_BREAK = "\n";
	private static final String SCAPED_LINE_BREAK = "\"\\n\"";

	private String operator;
	private String idType;
	private final StringBuilder sourceCode = new StringBuilder();
	private final Stack<String> types = new Stack<>();
	private final List<String> ids = new ArrayList<>();
	private final Map<String, Symbol> symbolTable = new HashMap<>();
	private int LabelCounter = 0;
	private final Stack<String> labelIf = new Stack<>();
	private final Stack<String> labelRepeat = new Stack<>();

	/**
	 * Operação aritmética binária: <b>adição</b>
	 */
	void run1() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
				|| (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			// TODO validar e extrair mensagens
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.ADD);
	}

	/**
	 * Operação aritmética binária: <b>subtração</b>
	 */
	void run2() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
				|| (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.SUB);
	}

	/**
	 * Operação aritmética binária: <b>multiplicação</b>
	 */
	void run3() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
				|| (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.MUL);
	}

	/**
	 * Operação aritmética binária: <b>divisão</b>
	 */
	void run4() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if (isInt(type1) && isInt(type2)) {
			this.types.push(DataType.INT);
		} else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
				|| (isFloat(type2) && isFloat(type1) || isInt(type1))) {
			this.types.push(DataType.FLOAT);
		} else {
			throw new SemanticError("Tipos incompatíveis em operação aritmética binária");
		}

		this.appendSourceCode(DotNetCommands.DIV);
	}

	/**
	 * Constante inteira
	 */
	void run5(final String lexeme) {
		this.types.push(DataType.INT);
		this.appendSourceCode(DotNetCommands.LDC_I8 + lexeme);
	}

	/**
	 * Constante real
	 */
	void run6(final String lexeme) {
		this.types.push(DataType.FLOAT);
		this.appendSourceCode(DotNetCommands.LDC_R8 + lexeme);
	}

	/**
	 * Escrever elemento
	 */
	void run7() {
		this.appendSourceCode(String.format(DotNetCommands.PRINT, this.types.pop()));
	}

	/**
	 * Valor constante positivo
	 */
	void run8() throws SemanticError {
		final String type = this.types.pop();

		if (isInt(type) || isFloat(type)) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação unária.");
		}
	}

	/**
	 * Valor constante negativo
	 */
	void run9() throws SemanticError {
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
	 */
	void run10() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();

		if ((isInt(type1) || isFloat(type1) && isInt(type1) || isFloat(type1))
				|| (isString(type1) && isString(type2))) {
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
	 */
	void run11(final String lexeme) {
		this.operator = lexeme;
	}

	/**
	 * Operador verdadeiro
	 */
	void run12() {
		this.types.push(DataType.BOOLEAN);
		this.appendSourceCode(DotNetCommands.LDC_I4_1);
	}

	/**
	 * Operador falso
	 */
	void run13() {
		this.types.push(DataType.BOOLEAN);
		this.appendSourceCode(DotNetCommands.LDC_I4_0);
	}

	/**
	 * Operador negação
	 */
	void run14() throws SemanticError {
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
	 */
	void run15(final String fileName) {
		this.appendSourceCode(String.format(DotNetCommands.PROGRAM_HEADER, fileName, fileName));
	}

	/**
	 * Fim bloco
	 */
	void run16() {
		this.appendSourceCode(DotNetCommands.RET);
		this.appendSourceCode(DotNetCommands.CLOSE_BRACES);
	}

	/**
	 * Fim programa
	 */
	String run17() {
		this.appendSourceCode(DotNetCommands.CLOSE_BRACES);

		return this.sourceCode.toString();
	}

	/**
	 * Quebra
	 */
	void run18() {
		this.types.push(DataType.STRING);
		this.appendSourceCode(DotNetCommands.LDSTR + SPACE + SCAPED_LINE_BREAK);
	}

	/**
	 * Operação lógica binária: <b>e</b>
	 */
	void run19() throws SemanticError {
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
	 */
	void run20() throws SemanticError {
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
	 */
	void run21(final String lexeme) throws SemanticError {
		this.types.push(DataType.STRING);

		this.appendSourceCode(DotNetCommands.LDSTR + SPACE + lexeme);
	}

	void run22(final String lexeme) {
		this.idType = lexeme;
	}

	void run23(final String lexeme) {
		this.ids.add(lexeme);
	}

	void run24() throws SemanticError {

		switch (this.idType) {
		case "int":
			this.idType = "int64";
			break;
		case "real":
			this.idType = "float64";
			break;
		}

		for (String id : this.ids) {
			if (this.symbolTable.containsKey(id)) {
				throw new SemanticError("identificador já declarado");
			}

			this.symbolTable.put(id, new Symbol(this.idType, null));
			this.appendSourceCode(".locals(" + this.idType + " " + id + ")");

			this.ids.clear();
		}
	}

	void run25() throws SemanticError {
		for (String id : this.ids) {
			if (!this.symbolTable.containsKey(id)) {
				throw new SemanticError("Identificador não declarado");
			}

			this.idType = this.symbolTable.get(id).type;
			this.appendSourceCode("call string [mscorlib]System.Console::ReadLine()");

			switch (this.idType) {
			case "int64":
				this.appendSourceCode("call int64 [mscorlib]System.Int64::Parse(string)");
				break;
			case "float64":
				this.appendSourceCode("call float64 [mscorlib]System.Double::Parse(string)");
				break;
			}

			this.appendSourceCode("stloc " + id);

			this.ids.clear();
		}
	}

	void run26() throws SemanticError {
		final String id = this.ids.get(this.ids.size() - 1);
		this.ids.remove(this.ids.size() - 1);

		if (!this.symbolTable.containsKey(id)) {
			throw new SemanticError("Identificador não declarado.");
		}

		this.idType = this.symbolTable.get(id).type;
		this.types.push(this.idType);
		// TODO verificar se id é variavel ou parametro formal.
		this.appendSourceCode("ldloc " + id);
	}
//	void run26(String lexeme) throws SemanticError {
//		final String id = lexeme;
//
//		if (!this.symbolTable.containsKey(id)) {
//			throw new SemanticError("Identificador não declarado.");
//		}
//
//		this.idType = this.symbolTable.get(id).type;
//		this.types.push(this.idType);
//		// TODO verificar se id é variavel ou parametro formal.
//		this.appendSourceCode("ldloc " + id);
//	}

	void run27() throws SemanticError {
		String id = this.ids.get(this.ids.size() - 1);
		this.ids.remove(this.ids.size() - 1);

		if (!this.symbolTable.containsKey(id)) {
			throw new SemanticError("Identificador não declarado.");
		}

		String idType = this.symbolTable.get(id).type;
		String expressionType = this.types.pop();

		if (!idType.equalsIgnoreCase(expressionType)) {
			throw new SemanticError("Tipos incompatíveis em comando de atribuição.");
		}

		this.appendSourceCode("stloc " + id);
	}

	void run28() {
		this.LabelCounter++;
		this.appendSourceCode("brfalse label" + this.LabelCounter);
		this.labelIf.push("label" + this.LabelCounter);
	}

	void run29() {
		this.appendSourceCode(this.labelIf.pop() + ":");
	}

	void run30() {
		this.LabelCounter++;
		this.appendSourceCode("br label" + this.LabelCounter);
		this.appendSourceCode(this.labelIf.pop() + ":");
		this.labelIf.push("label" + this.LabelCounter);
	}

	void run31() {
		this.LabelCounter++;
		this.appendSourceCode("label" + this.LabelCounter + ":");
		this.labelRepeat.push("label" + this.LabelCounter);

	}

	void run32() {
		this.appendSourceCode("brfalse " + this.labelRepeat.pop());
	}

	void run33(String lexeme) {
		this.symbolTable.put(lexeme, null);
		this.moduleName = lexeme;
//		this.ids.add(lexeme);
	}

	void run34(String lexeme) {
//		String id = this.ids.get(this.ids.size() - 1);

//		this.ids.remove(id);
		this.symbolTable.remove(this.moduleName);

        List<Parameter> parameters = new ArrayList<>();
        for (String id: this.ids) {
            parameters.add(new Parameter(id, this.idType));
        }
        switch (lexeme) {
            case "inteiro":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.INT, parameters));
                break;
            case "real":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.FLOAT, parameters));
                break;
            case "caracter":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.STRING, parameters));
                break;
            case "lógico":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.BOOLEAN, parameters));
                break;
        }

        this.ids.clear();
	}

	void run35() {
		String id = this.ids.get(this.ids.size() - 1);
		this.ids.remove(this.ids.size() - 1);
		//this.symbolTable.get(id);
		this.symbolTable.put(id, new Symbol(DataType.VOID, null));
	}

	void run36() throws SemanticError {
        switch (this.idType) {
            case "int":
                this.idType = "int64";
                break;
            case "real":
                this.idType = "float64";
                break;
        }

        for (String id : this.ids) {
            if (this.symbolTable.containsKey(id)) {
                throw new SemanticError("identificador já declarado");
            }

            this.symbolTable.put(id, new Symbol(this.idType, null));

//            this.ids.clear();
        }
    }

    void run37(String lexeme) {
        Symbol moduleSymbol = this.symbolTable.get(lexeme);

        StringBuilder moduleDeclaration = new StringBuilder(".method public static " + moduleSymbol.type + " _" + lexeme + "(");

        List<Parameter> parameters = moduleSymbol.parameters;
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);

            moduleDeclaration.append(parameter.type).append(" ").append(parameter.id);
            if (i < parameters.size() - 1) {
                moduleDeclaration.append(",");
            }
        }
        moduleDeclaration.append(") {");


        this.appendSourceCode(moduleDeclaration.toString());
    }

    void run38() {
        String moduleName = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);
        Symbol moduleSymbol = this.symbolTable.get(moduleName);

        StringBuilder callModule = new StringBuilder("call void _Principal::_" + moduleName + "(");

        List<Parameter> parameters = moduleSymbol.parameters;
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);

            callModule.append(parameter.type);
            if (i < parameters.size() - 1) {
                callModule.append(",");
            }
        }
        callModule.append(")");

        this.appendSourceCode(callModule.toString());
    }

    void run39() {
        String moduleName = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);

        Symbol moduleSymbol = this.symbolTable.get(moduleName);

        StringBuilder callModule = new StringBuilder("call " + moduleSymbol.type + " _Principal::_" + moduleName + "(");

        List<Parameter> parameters = moduleSymbol.parameters;
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);

            callModule.append(parameter.type);
            if (i < parameters.size() - 1) {
                callModule.append(",");
            }
        }
        callModule.append(")");

        this.appendSourceCode(callModule.toString());
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
