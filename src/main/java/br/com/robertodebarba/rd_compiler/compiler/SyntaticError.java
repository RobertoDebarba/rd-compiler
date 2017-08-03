package br.com.robertodebarba.rd_compiler.compiler;

final class SyntaticError extends AnalysisError {

    private String token;

    public SyntaticError(String msg, int position, String token) {
        super(msg, position);
        this.token = token;
    }

    public SyntaticError(String msg, int position) {
        super(msg, position);
    }

    public SyntaticError(String msg) {
        super(msg);
    }

    public String getToken() {
        return token;
    }

}
