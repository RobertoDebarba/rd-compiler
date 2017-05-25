package br.com.furb.trabalho_compiladores.compiler;

interface Constants extends ScannerConstants, ParserConstants {
	int EPSILON = 0;
	int DOLLAR = 1;

	int t_identificador = 2;
	int t_constante_inteira = 3;
	int t_constante_real = 4;
	int t_constante_caracter = 5;
	int t_algoritmo = 6;
	int t_ate = 7;
	int t_caracter = 8;
	int t_e = 9;
	int t_entao = 10;
	int t_escreva = 11;
	int t_falso = 12;
	int t_fim = 13;
	int t_funcao = 14;
	int t_inicio = 15;
	int t_inteiro = 16;
	int t_interrompa = 17;
	int t_leia = 18;
	int t_logico = 19;
	int t_nao = 20;
	int t_ou = 21;
	int t_procedimento = 22;
	int t_quebra = 23;
	int t_real = 24;
	int t_repita = 25;
	int t_retorne = 26;
	int t_se = 27;
	int t_senao = 28;
	int t_variaveis = 29;
	int t_verdadeiro = 30;
	int t_TOKEN_31 = 31; // "+"
	int t_TOKEN_32 = 32; // "-"
	int t_TOKEN_33 = 33; // "*"
	int t_TOKEN_34 = 34; // "/"
	int t_TOKEN_35 = 35; // ","
	int t_TOKEN_36 = 36; // ":"
	int t_TOKEN_37 = 37; // ";"
	int t_TOKEN_38 = 38; // "<-"
	int t_TOKEN_39 = 39; // "="
	int t_TOKEN_40 = 40; // "<>"
	int t_TOKEN_41 = 41; // "<"
	int t_TOKEN_42 = 42; // "<="
	int t_TOKEN_43 = 43; // ">"
	int t_TOKEN_44 = 44; // ">="
	int t_TOKEN_45 = 45; // "("
	int t_TOKEN_46 = 46; // ")"

}
