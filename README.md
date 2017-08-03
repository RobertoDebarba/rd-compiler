# RD Compiler

[![Build Status](https://travis-ci.org/RobertoDebarba/rd-compiler.svg?branch=master)](https://travis-ci.org/RobertoDebarba/rd-compiler)

A simple compiler for my own language writen on Java and [Gals](http://gals.sourceforge.net/) generated code.

*Um compilador simples para minha própria linguagem escrito em Java e código gerado pelo [Gals](http://gals.sourceforge.net/).*

![main](https://github.com/RobertoDebarba/rd-compiler/blob/master/screenshot/main.png)

## Sample programs

### Hello world

```
algoritmo "Hello world"
início
    escreva ("Hello world!");
fim
```

### Average calculator with functions

```
algoritmo "Average calculator"
função média (n1, n2: real): real;
procedimento cargaHorária ();
variáveis
	i: inteiro;
	nota1, nota2, soma: real;
início
	i <- 0;
	soma <- 0.0;
	repita
	i <- i + 1;
	cargaHorária ();
	escreva ("digite uma nota (enter) após outra: ",
	quebra);
	leia (nota1, nota2);
	soma <- soma  +
	média (nota1 , nota2 ) ;
	até i = 3;
	escreva (soma / 3);
fim

função média ;
início
	retorne (n1 + n2) / 2;
fim 

procedimento cargaHorária ;
variáveis
	CH: inteiro;
	disciplina: caracter;
início
	escreva ("qual a disciplina? ");
	leia (disciplina);
	repita
	escreva ("qual a CH da disciplina? ");
	leia (CH);
	até CH > 17;
	escreva ("total de créditos: ", CH / 18, quebra);
fim 
```

**You can check all samples on folder [samples](https://github.com/RobertoDebarba/rd-compiler/tree/master/samples).**

## How to build

### Prerequisites

* JDK 8
* Maven

### Build and package

1. `mvn package`

### Test

1. `mvn test`

## Language specification

### Regular definitions

```
acentuada: [áÁâÂãÃàÀéÉêÊíÍîÎóÓôÔõÕúÚûÛçÇ]
letra: [a-z A-Z]
digito: [0-9]
simbolo: [^\\\n\"]
```

### Tokens

```
identificador: ({letra}|{acentuada})(_)?((({letra}|{digito}|{acentuada})+_)*|({letra}|{acentuada}|{digito}))*
constante_inteira: 0|[1-9][0-9]*
constante_real: (0|[1-9][0-9]*)\.[0-9]+
constante_caracter: \"({simbolo}|\\\"|\\\\)*\"
```

### Keywords

```
algoritmo
até
caracter
e
então
escreva
falso
fim
função
início
inteiro
interrompa
leia
lógico
não
ou
procedimento
quebra
real
repita
retorne
se
senão
variáveis
verdadeiro
```

### Special Characters

```
+
-
*
/
,
:
;
<-
=
<>
<
<=
>
>=
(
)
```

### Grammar

```
<programa>::= algoritmo constante_caracter <cabecalho_modulos> <variaveis_opcional> início <lista_comandos> fim <lista_modulos>;
<cabecalho_modulos>::=<cabecalho><cabecalho_modulos>|î;
<variaveis_opcional>::=<variaveis>|î;
<lista_comandos>::=<comando>";"<lista_comandos>|î;
<comando>::=identificador <comando_1>|<entrada_dados>|<saida_dados>|<selecao>|<repeticao>|<interrompa>|<retorne>;
<comando_1>::="<-" <expressao> |"(" <parametros_reais> ")";
<lista_modulos>::=<modulo><lista_modulos>|î;
<modulo>::=<funcao>|<procedimento>;
<cabecalho>::=<funcao_cabecalho>|<procedimento_cabecalho>;
<funcao_cabecalho>::=funcao identificador "(" <lista_parametros> ")" ":" <tipo>  ";";
<procedimento_cabecalho>::=procedimento identificador "(" <lista_parametros> ")" ";";
<lista_parametros>::=<parametro><lista_parametros_adicionais>|î;
<lista_parametros_adicionais>::=";" <parametro><lista_parametros_adicionais>|î;
<parametro>::=<lista_identificadores> ":" <tipo> ;
<lista_identificadores>::=identificador <lista_identificadores_adicionais>;
<lista_identificadores_adicionais>::="," identificador <lista_identificadores_adicionais>|î;
<tipo>::=caracter|logico|inteiro|real;
<variaveis>::=variaveis <lista_variaveis>;
<lista_variaveis>::=<variavel><lista_variaveis_1>;
<lista_variaveis_1>::=î|<lista_variaveis>;
<variavel>::=<lista_identificadores> ":" <tipo>  ";";
<entrada_dados>::=leia "(" <lista_identificadores> ")";
<saida_dados>::=escreva "(" <lista_valores> ")";
<lista_valores>::=<valor> <lista_valores_adicionais>;
<lista_valores_adicionais>::="," <valor> <lista_valores_adicionais>|î;
<valor>::=<expressao>|quebra;
<selecao>::=se <expressao> entao <lista_comandos> <senao_opcional> fim;
<senao_opcional>::=senao <lista_comandos>|î;
<repeticao>::=repita <lista_comandos> ate <expressao>;
<interrompa>::=interrompa;
<retorne>::=retorne <expressao>;
<parametros_reais>::=<lista_expressoes>|î;
<lista_expressoes>::=<expressao><lista_expressoes_adicionais>;
<lista_expressoes_adicionais>::="," <expressao><lista_expressoes_adicionais>|î;
<funcao>::=funcao identificador ";" <variaveis_opcional> início <lista_comandos> fim;
<procedimento>::=procedimento identificador ";" <variaveis_opcional> início <lista_comandos> fim;
<expressao> ::= <elemento><expressao_1>;
<expressao_1> ::= î|e <elemento> <expressao_1>|ou <elemento> <expressao_1>;
<elemento> ::= <relacional> | verdadeiro | falso | nao <elemento>;
<relacional> ::=<aritmetica><relacional_1>;
<relacional_1>::=<operador_relacional> <aritmetica> |î;
<operador_relacional> ::= "=" | "<>" | "<" | "<=" | ">" | ">=";
<aritmetica> ::= <termo><aritmetica_1>;
<aritmetica_1> ::=î| "+" <termo> <aritmetica_1>|"-" <termo> <aritmetica_1>;
<termo> ::=<fator><termo_1>;
<termo_1>::=î|"*" <fator> <termo_1>|"/" <fator> <termo_1>;
<fator> ::= identificador <fator_1> | constante_inteira | constante_real | constante_caracter | "(" <expressao> ")" | "+" <fator> | "-" <fator>;
<fator_1>::= î|"(" <parametros_reais> ")";
```

**You can check all specification on file [gramatica.gals](https://github.com/RobertoDebarba/rd-compiler/tree/master/gramatica.gals).**

## Authors

* [Roberto Luiz Debarba](https://github.com/RobertoDebarba)
* [Francisco Krautchuk Neto](https://github.com/Fkneto)

## Contributors

* [Matheus Hoeltgebaum Pereira](https://github.com/matheushoeltgebaum)

## License

The codebase is licensed under [GPL v3.0](http://www.gnu.org/licenses/gpl-3.0.html).