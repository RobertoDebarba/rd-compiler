package br.com.furb.trabalho_compiladores.compiler;

final class Semantico implements Constants {
	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);
	}
}
