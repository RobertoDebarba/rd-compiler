package br.com.furb.trabalho_compiladores.compiler;

final class SemanticError extends AnalysisError {
    public SemanticError(String msg, int position) {
        super(msg, position);
    }

    public SemanticError(String msg) {
        super(msg);
    }
}
