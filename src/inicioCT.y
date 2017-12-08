%{
	import java.io.*;
	import java.util.*;
%}

/* BYACC Declarations */
%token <sval> IDENTIFICADOR
%token <sval> INCLUSAO_ARQUIVO
%token ABRE_CHAVES
%token FECHA_CHAVES
%token ABRE_PARENTESES
%token FECHA_PARENTESES
%token FUNCAO_PRINCIPAL
%token ABRE_COLCHETES
%token FECHA_COLCHETES
%token INCLUIR
%token INTEIRO
%token REAL
%token CARACTER
%token FUNCAO
%token RETORNAR
%type <sval> programa
%type <sval> funcao_principal
%type <sval> inclusao
%type <sval> comandos
%type <sval> declaracao
%type <sval> tipo
%type <sval> atributos
%type <sval> bloco
%type <sval> declaracao_vetor
%type <sval> vetor

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		 | funcao_principal programa { $$ = $1 + "\n" + $2; }
                 | FUNCAO declaracao atributos bloco programa { $$ = $2 + $3 + $4 + $5; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL bloco { $$ = "int main() " + $2; }

bloco : ABRE_CHAVES comandos FECHA_CHAVES { $$ = "{\n " + $2 + "}\n"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao comandos	{ $$ = $1 + ";\n" + $2; }
            | declaracao_vetor comandos { $$ = $1 + ";\n" + $2; }
            | RETORNAR IDENTIFICADOR comandos { $$ = "return " + $2 + ";\n" + $3; }
		 |					{ $$ = ""; }

declaracao_vetor : declaracao vetor { $$ = $1 + $2; }

declaracao : tipo IDENTIFICADOR	{  $$ = $1 + $2;  }

vetor : ABRE_COLCHETES FECHA_COLCHETES { $$ = "[]"; }
        | ABRE_COLCHETES IDENTIFICADOR FECHA_COLCHETES { $$ = "[" + $2 + "]"; }

atributos : ABRE_PARENTESES FECHA_PARENTESES { $$ = "()"; }
            | ABRE_PARENTESES declaracao FECHA_PARENTESES { $$ = "(" + $2 + ")"; }
            | ABRE_PARENTESES declaracao_vetor FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

tipo : INTEIRO { $$ = "int "; }
        | REAL { $$ = "float "; }
        | CARACTER { $$ = "char "; }
%%

	// Referencia ao JFlex
	private Yylex lexer;

	/* Interface com o JFlex */
	private int yylex(){
		int yyl_return = -1;
		try {
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("Erro de IO: " + e);
		}
		return yyl_return;
	}

	/* Reporte de erro */
	public void yyerror(String error){
		System.err.println("Error: " + error);
	}

	// Interface com o JFlex eh criado no construtor
	public Parser(Reader r){
		lexer = new Yylex(r, this);
	}

	// Main
	public static void main(String[] args){
		try{ 
			Parser yyparser = new Parser(new FileReader(args[0]));
			yyparser.yyparse();
			} catch (IOException ex) {
				System.err.println("Error: " + ex);
			}
	}
