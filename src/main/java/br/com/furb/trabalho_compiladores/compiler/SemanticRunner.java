package br.com.furb.trabalho_compiladores.compiler;

import java.text.Normalizer;
import java.util.*;

class SemanticRunner {

    private String moduleName;

    class Symbol {
        Symbol(String type, String from, List<Parameter> parameters) {
            this.type = type;
            this.from = from;
            this.parameters = parameters;
        }

        String type;
        private String from;
        List<Parameter> parameters;
    }

    class Parameter {
        Parameter(String id, String type) {
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
    private Map<String, Symbol> symbolTableLocal = null;
    private int LabelCounter = 0;
    private final Stack<String> labelIf = new Stack<>();
    private final Stack<String> labelRepeat = new Stack<>();

    /**
     * Operação aritmética binária: <b>adição</b>
     */
    void run1(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isInt(type1) && isInt(type2)) {
            this.types.push(DataType.INT);
        } else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
                || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
            this.types.push(DataType.FLOAT);
        } else {
            throw new SemanticError("tipos incompatíveis em operação aritmética binária", token.getPosition());
        }

        this.appendSourceCode(DotNetCommands.ADD);
    }

    /**
     * Operação aritmética binária: <b>subtração</b>
     */
    void run2(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isInt(type1) && isInt(type2)) {
            this.types.push(DataType.INT);
        } else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
                || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
            this.types.push(DataType.FLOAT);
        } else {
            throw new SemanticError("tipos incompatíveis em operação aritmética binária", token.getPosition());
        }

        this.appendSourceCode(DotNetCommands.SUB);
    }

    /**
     * Operação aritmética binária: <b>multiplicação</b>
     */
    void
    run3(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isInt(type1) && isInt(type2)) {
            this.types.push(DataType.INT);
        } else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
                || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
            this.types.push(DataType.FLOAT);
        } else {
            throw new SemanticError("tipos incompatíveis em operação aritmética binária", token.getPosition());
        }

        this.appendSourceCode(DotNetCommands.MUL);
    }

    /**
     * Operação aritmética binária: <b>divisão</b>
     */
    void run4(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isInt(type1) && isInt(type2)) {
            this.types.push(DataType.INT);
        } else if ((isFloat(type1) && isFloat(type2) || isInt(type2))
                || (isFloat(type2) && isFloat(type1) || isInt(type1))) {
            this.types.push(DataType.FLOAT);
        } else {
            throw new SemanticError("tipos incompatíveis em operação aritmética binária", token.getPosition());
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
    void run8(Token token) throws SemanticError {
        final String type = this.types.pop();

        if (isInt(type) || isFloat(type)) {
            this.types.push(type);
        } else {
            throw new SemanticError("tipo incompatível em operação unária.", token.getPosition());
        }
    }

    /**
     * Valor constante negativo
     */
    void run9(Token token) throws SemanticError {
        final String type = this.types.pop();

        if (isInt(type) || isFloat(type)) {
            this.types.push(type);
        } else {
            throw new SemanticError("tipo incompatível em operação unária.", token.getPosition());
        }

        this.appendSourceCode(DotNetCommands.LDC_I8_1);
        this.appendSourceCode(DotNetCommands.MUL);
    }

    /**
     * Operação relacional
     */
    void run10(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if ((isInt(type1) || isFloat(type1) && isInt(type1) || isFloat(type1))
                || (isString(type1) && isString(type2))) {
            this.types.push(DataType.BOOLEAN);
        } else {
            throw new SemanticError("tipos incompatíveis em operação relacional", token.getPosition());
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
                this.appendSourceCode(DotNetCommands.CEQ);
                this.appendSourceCode(DotNetCommands.LDC_I4_0);
                this.appendSourceCode(DotNetCommands.CEQ);
                break;
            case RelationalOperator.LESS_THAN_OR_EQUAL:
                this.appendSourceCode(DotNetCommands.CLT);
                this.appendSourceCode(DotNetCommands.LDC_I4_0);
                this.appendSourceCode(DotNetCommands.CEQ);
                break;
            case RelationalOperator.GREATER_THAN_OR_EQUAL:
                this.appendSourceCode(DotNetCommands.CGT);
                this.appendSourceCode(DotNetCommands.LDC_I4_0);
                this.appendSourceCode(DotNetCommands.CEQ);
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
    void run14(Token token) throws SemanticError {
        final String type = this.types.pop();

        if (isBoolean(type)) {
            this.types.push(type);
        } else {
            throw new SemanticError("tipo incompatível em operação lógica unária", token.getPosition());
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

        this.symbolTableLocal = null;
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
    void run19(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isBoolean(type1) || isBoolean(type2)) {
            this.types.push(DataType.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível em operação lógica binária", token.getPosition());
        }

        this.appendSourceCode(DotNetCommands.AND);
    }

    /**
     * Operação lógica binária: <b>ou</b>
     */
    void run20(Token token) throws SemanticError {
        final String type1 = this.types.pop();
        final String type2 = this.types.pop();

        if (isBoolean(type1) || isBoolean(type2)) {
            this.types.push(DataType.BOOLEAN);
        } else {
            throw new SemanticError("tipo incompatível em operação lógica binária", token.getPosition());
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
        this.ids.add(this.normalizeString(lexeme));
    }

    void run24(Token token) throws SemanticError {

        convertType();

        for (String id : this.ids) {
            if (this.isFunctionOrProcedure()) {
                if (this.symbolTableLocal.containsKey(id)) {
                    throw new SemanticError(id + " já declarado", token.getPosition());
                }
                this.symbolTableLocal.put(id, new Symbol(this.idType, "V", null));
            } else {
                if (this.symbolTable.containsKey(id)) {
                    throw new SemanticError(id + " já declarado", token.getPosition());
                }
                this.symbolTable.put(id, new Symbol(this.idType, "V", null));
            }


            this.appendSourceCode(".locals(" + this.idType + " " + id + ")");

        }
        this.ids.clear();
    }

    private void convertType() {
        switch (this.idType) {
            case "inteiro":
                this.idType = DataType.INT;
                break;
            case "real":
                this.idType = DataType.FLOAT;
                break;
            case "caracter":
                this.idType = DataType.STRING;
                break;
            case "lógico":
                this.idType = DataType.BOOLEAN;
                break;
        }
    }

    void run25(Token token) throws SemanticError {
        for (String id : this.ids) {
            if (this.isFunctionOrProcedure()) {
                if (!this.symbolTableLocal.containsKey(id) && !this.symbolTable.containsKey(id)) {
                    throw new SemanticError(id + " não declarado", token.getPosition());
                }

                Symbol symbol = this.symbolTableLocal.get(id);
                if (symbol == null) {
                    symbol = this.symbolTable.get(id);
                }

                this.idType = symbol.type;
            } else {
                if (!this.symbolTable.containsKey(id)) {
                    throw new SemanticError(id + " não declarado", token.getPosition());
                }

                this.idType = this.symbolTable.get(id).type;
            }

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

        }
        this.ids.clear();
    }

    void run26(Token token) throws SemanticError {
        final String id = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);

        Symbol symbol;
        if (this.isFunctionOrProcedure()) {
            if (!this.symbolTableLocal.containsKey(id) && !this.symbolTable.containsKey(id)) {
                throw new SemanticError(id + " não declarado.", token.getPosition());
            }

            symbol = this.symbolTableLocal.get(id);
            if (symbol == null) {
                symbol = this.symbolTable.get(id);
            }
        } else {
            if (!this.symbolTable.containsKey(id)) {
                throw new SemanticError(id + " não declarado.", token.getPosition());
            }

            symbol = this.symbolTable.get(id);
        }

        this.idType = symbol.type;
        this.types.push(this.idType);

        if (symbol.from != null && symbol.from.equals("V")) {
            this.appendSourceCode("ldloc " + id);
        } else {
            this.appendSourceCode("ldarg " + id);
        }
    }

    void run27(Token token) throws SemanticError {
        String id = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);

        String idType;
        if (this.isFunctionOrProcedure()) {
            if (!this.symbolTableLocal.containsKey(id) && !this.symbolTable.containsKey(id)) {
                throw new SemanticError(id + " não declarado.", token.getPosition());
            }

            idType = this.symbolTableLocal.get(id).type;
            if (idType == null) {
                idType = this.symbolTable.get(id).type;
            }
        } else {
            if (!this.symbolTable.containsKey(id)) {
                throw new SemanticError(id + " não declarado.", token.getPosition());
            }

            idType = this.symbolTable.get(id).type;
        }

        String expressionType = this.types.pop();

        if (!idType.equalsIgnoreCase(expressionType)) {
            throw new SemanticError("tipos incompatíveis em comando de atribuição.", token.getPosition());
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

    void run33(Token token) throws SemanticError {
        String lexeme = this.normalizeString(token.getLexeme());
        if (this.symbolTable.containsKey(lexeme)) {
            throw new SemanticError(lexeme + " já declarado", token.getPosition());
        }

//        this.symbolTable.put(lexeme, null);
        this.moduleName = lexeme;
    }

    void run34(String lexeme) {
        Symbol symbol = this.symbolTable.get(this.moduleName);
        this.symbolTable.remove(this.moduleName);

        switch (lexeme) {
            case "inteiro":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.INT, "F", symbol.parameters));
                break;
            case "real":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.FLOAT, "F", symbol.parameters));
                break;
            case "caracter":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.STRING, "F", symbol.parameters));
                break;
            case "lógico":
                this.symbolTable.put(this.moduleName, new Symbol(DataType.BOOLEAN, "F", symbol.parameters));
                break;
        }

    }

    void run35() {
        this.symbolTable.put(this.moduleName, new Symbol(DataType.VOID, "P", null));
    }

    void run36(Token token) throws SemanticError {
        convertType();
        List<Parameter> parameters = new ArrayList<>();
        for (String id : this.ids) {
            if (this.symbolTable.containsKey(id)) {
                throw new SemanticError("identificador já declarado", token.getPosition());
            }

            parameters.add(new Parameter(id, this.idType));
        }

        this.symbolTable.put(this.moduleName, new Symbol(this.idType, null, parameters));//TODO id tipo parametro

        this.ids.clear();
    }

    void run37(String lexeme) {
        this.symbolTableLocal = new HashMap<>();

        lexeme = this.normalizeString(lexeme);
        Symbol moduleSymbol = this.symbolTable.get(lexeme);

        StringBuilder moduleDeclaration = new StringBuilder(".method public static " + moduleSymbol.type + " " + lexeme + "(");

        if (moduleSymbol.parameters != null) {
            List<Parameter> parameters = moduleSymbol.parameters;
            for (int i = 0; i < parameters.size(); i++) {
                Parameter parameter = parameters.get(i);

                moduleDeclaration.append(parameter.type).append(" ").append(parameter.id);
                if (i < parameters.size() - 1) {
                    moduleDeclaration.append(",");
                }

                this.symbolTableLocal.put(parameter.id, new Symbol(parameter.type, null, null));
            }
        }
        moduleDeclaration.append(") {");

        this.appendSourceCode(moduleDeclaration.toString());
    }

    void run38(Token token) throws SemanticError {
        String moduleName = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);

        if (!this.symbolTable.containsKey(moduleName)) {
            throw new SemanticError(moduleName + " não declarado", token.getPosition());
        }

        Symbol moduleSymbol = this.symbolTable.get(moduleName);

        StringBuilder callModule = new StringBuilder("call void _Principal::" + moduleName + "(");

        if (moduleSymbol.parameters != null) {
            List<Parameter> parameters = moduleSymbol.parameters;
            for (int i = 0; i < parameters.size(); i++) {
                Parameter parameter = parameters.get(i);

                callModule.append(parameter.type);
                if (i < parameters.size() - 1) {
                    callModule.append(",");
                }
            }
        }
        callModule.append(")");

        this.appendSourceCode(callModule.toString());
    }

    void run39(Token token) throws SemanticError {
        String moduleName = this.ids.get(this.ids.size() - 1);
        this.ids.remove(this.ids.size() - 1);

        if (!this.symbolTable.containsKey(moduleName)) {
            throw new SemanticError(moduleName + " não declarado", token.getPosition());
        }

        Symbol moduleSymbol = this.symbolTable.get(moduleName);

        StringBuilder callModule = new StringBuilder("call " + moduleSymbol.type + " _Principal::" + moduleName + "(");

        if (moduleSymbol.parameters != null) {
            List<Parameter> parameters = moduleSymbol.parameters;
            for (int i = 0; i < parameters.size(); i++) {
                Parameter parameter = parameters.get(i);

                callModule.append(parameter.type);
                if (i < parameters.size() - 1) {
                    callModule.append(",");
                }
            }
        }
        callModule.append(")");

        this.appendSourceCode(callModule.toString());
    }

    private void appendSourceCode(final String sourceCode) {
        this.sourceCode.append(sourceCode).append(LINE_BREAK);
    }

    public String getSourceCode() {
        return sourceCode.toString();
    }

    void addType(String dataType) {
        this.types.add(dataType);
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

    private String normalizeString(String lexeme) {
        if (!Normalizer.isNormalized(lexeme, Normalizer.Form.NFD)) {
            lexeme = "_" + lexeme;
        }

        return Normalizer
                .normalize(lexeme, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    private boolean isFunctionOrProcedure() {
        return this.symbolTableLocal != null;
    }

    public static String getSPACE() {
        return SPACE;
    }

    public static String getLineBreak() {
        return LINE_BREAK;
    }

    public static String getScapedLineBreak() {
        return SCAPED_LINE_BREAK;
    }
}
