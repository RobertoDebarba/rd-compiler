package br.com.furb.trabalho_compiladores.compiler;

final class DotNetCommands {

	static final String OR = "or";
	static final String AND = "and";
	static final String LDSTR = "ldstr";
	static final String RET = "ret";
	static final String LDC_I4_0 = "ldc.i4.0";
	static final String XOR = "xor";
	static final String LDC_I4_1 = "ldc.i4.1";
	static final String CGT = "cgt";
	static final String CLT = "clt";
	static final String CEQ = "ceq";
	static final String LDC_I8_1 = "ldc.i8 -1";
	static final String LDC_R8 = "ldc.r8 ";
	static final String LDC_I8 = "ldc.i8 ";
	static final String DIV = "div";
	static final String MUL = "mul";
	static final String SUB = "sub";
	static final String ADD = "add";
	static final String CLOSE_BRACES = "}";
	static final String PROGRAM_HEADER = ".assembly extern mscorlib {}\n" + ".assembly %s{}\n" + ".module %s.exe\n" + ".class public _Principal{\n"
			+ ".method static public void _principal()\n" + "{ .entrypoint";
	static final String PRINT = "call void [mscorlib]System.Console::Write(%s)";

	private DotNetCommands() {
	}

}
