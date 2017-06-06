package br.com.furb.trabalho_compiladores.compiler;

import java.util.Stack;

class SemanticRunner {

	private String operator;
	private StringBuilder sourceCode = new StringBuilder();
	private Stack<String> types = new Stack<>();

	public void run1() {
		String tipo1 = this.types.pop();
		String tipo2 = this.types.pop();
		if (tipo1.contains(".") || tipo2.contains(".")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("add");
	}

	public void run2() {
		String tipo1 = this.types.pop();
		String tipo2 = this.types.pop();
		if (tipo1.contains(".") || tipo2.contains(".")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("sub");
	}

	public void run3() {
		String tipo1 = this.types.pop();
		String tipo2 = this.types.pop();
		if (tipo1.contains(".") || tipo2.contains(".")) {
			this.types.push("float64");
		} else {
			this.types.push("int64");
		}
		this.appendSourceCode("mul");
	}
	public void run4() {
		String tipo1 = this.types.pop();
		String tipo2 = this.types.pop();
		if (tipo1.contains(".") && tipo2.contains(".")) {
			this.types.push("float64");
		} else  if  (!tipo1.contains(".") && !tipo2.contains(".")) {
			this.types.push("int64");
		}
		else 
		{
			//throw new SemanticError("tipos incompatíveis em operação de divisão.");			
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

	private void appendSourceCode(String sourceCode) {
		this.sourceCode.append(sourceCode).append("\n");
	}

}
