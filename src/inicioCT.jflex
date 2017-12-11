import java.io.*;

%%

%byaccj

%{

	// Armazena uma referencia para o parser
	private Parser yyparser;

	// Construtor recebendo o parser como parametro adicional
	public Yylex(Reader r, Parser yyparser){
		this(r);
		this.yyparser = yyparser;
	}	

%}

NL = \n | \r | \r\n

%%

funcao_principal { return Parser.FUNCAO_PRINCIPAL; }
incluir	{ return Parser.INCLUIR; }
inteiro { return Parser.INTEIRO; }
real    { return Parser.REAL; }
caracter { return Parser.CARACTER; }
funcao { return Parser.FUNCAO; }
retornar { return Parser.RETORNAR; }
imprima { return Parser.IMPRIMA; }
\<.*\>	{ yyparser.yylval = new ParserVal(yytext());
		  return Parser.INCLUSAO_ARQUIVO; }
\".*\"  { yyparser.yylval = new ParserVal(yytext());
                return Parser.STRING; }
\'.\'   { yyparser.yylval = new ParserVal(yytext());
                return Parser.CHAR; }
"{"	{ return Parser.ABRE_CHAVES; }
"}" { return Parser.FECHA_CHAVES; }
"("	{ return Parser.ABRE_PARENTESES; }
")" { return Parser.FECHA_PARENTESES; }
"["	{ return Parser.ABRE_COLCHETES; }
"]" { return Parser.FECHA_COLCHETES; }
[a-zA-Z][a-zA-Z0-9]*	{ 
		yyparser.yylval = new ParserVal(yytext());
		return Parser.IDENTIFICADOR;
	}
[0-9]+  { yyparser.yylval = new ParserVal(yytext());
                return Parser.NUMERICO; }
"++" { return Parser.OP_INCREMENTO; }
"--" { return Parser.OP_DECREMENTO; }
"+" { return Parser.OP_SOMA; }
"-" { return Parser.OP_SUB; }
"/" { return Parser.OP_DIV; }
"*" { return Parser.OP_MULT; }
"%" { return Parser.OP_MOD; }
"==" { return Parser.OP_IGUALDADE; }
"!=" { return Parser.OP_DIFERENTE; }
">" { return Parser.OP_MAIOR; }
"<" { return Parser.OP_MENOR; }
">=" { return Parser.OP_MAIOR_IGUAL; }
"<=" { return Parser.OP_MENOR_IGUAL; }
":=" {return Parser.OP_ATRIBUICAO; }
"," {return Parser.VIRGULA; }
{NL}|" "|\t	{  }
