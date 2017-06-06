package br.com.furb.trabalho_compiladores.compiler;

import java.util.Stack;

class SemanticRunner {

	private String operator;
	private final StringBuilder sourceCode = new StringBuilder();
	private final Stack<String> types = new Stack<>();

	public void run1() {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();
		if (type1.equals("float64") || type2.equals("float64")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("add");
	}

	public void run2() {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();
		if (type1.equals("float64") || type2.equals("float64")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("sub");
	}

	public void run3() {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();
		if (type1.equals("float64") || type2.equals("float64")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("mul");
	}

	public void run4() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();
		if (type1.equals(type2)) {
			this.types.push(type1);
		} else {
			throw new SemanticError("tipos incompatíveis em operação de divisão.");
		}
		this.appendSourceCode("div");
	}

	public void run5(final String lexeme) {
		this.types.push("int64");
		this.appendSourceCode("ldc.i8 " + lexeme);
	}

	public void run6(final String lexeme) {
		this.types.push("float64");
		this.appendSourceCode("ldc.r8 " + lexeme);
	}

	public void run7() {
		this.appendSourceCode("call void [mscorlib]System.Console::Write(" + this.types.pop() + ")");
	}

	public void run8() throws SemanticError {
		final String type = this.types.pop();
		if (type.equals("int64") || type.equals("float64")) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação unária.");
		}
	}

	public void run10() throws SemanticError {
		final String type1 = this.types.pop();
		final String type2 = this.types.pop();
		if (type1.equals(type2)) {
			this.types.push("bool");
		} else {
			throw new SemanticError("tipo incompatível em operação relacional");
		}

		switch (this.operator) {
		case "=":
			this.appendSourceCode("ceq");
			break;
		case "<":
			this.appendSourceCode("clt");
			break;
		case ">":
			this.appendSourceCode("cgt");
			break;
		}
	}

	public void run11(final String lexeme) {
		this.operator = lexeme;
	}

	public void run12() {
		this.types.push("bool");
		this.appendSourceCode("ldc.i4.1");
	}

	public void run13() {
		this.types.push("bool");
		this.appendSourceCode("ldc.i4.0");
	}

	public void run14() throws SemanticError {
		final String type = this.types.pop();
		if (type.equals("bool")) {
			this.types.push(type);
		} else {
			throw new SemanticError("tipo incompatível em operação not");
		}

		this.appendSourceCode("ldc.i4.1");
		this.appendSourceCode("xor");
	}

	public void run15(final String fileName) {
		this.appendSourceCode(".assembly extern mscorlib {}");
		this.appendSourceCode(".assembly " + fileName + "{}");
		this.appendSourceCode(".module " + fileName + ".exe");
		this.appendSourceCode(".class public _Principal{");
		this.appendSourceCode(".method static public void _principal()");
		this.appendSourceCode("{ .entrypoint");
	}

	public void run16() {
		this.appendSourceCode(" ret");
		this.appendSourceCode("}");
	}

	public String run17() {
		this.appendSourceCode("}");

		return this.sourceCode.toString();
	}

	private void appendSourceCode(final String sourceCode) {
		this.sourceCode.append(sourceCode).append("\n");
	}

}
