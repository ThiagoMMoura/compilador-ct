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
%token IMPRIMA
%token SE
%token SENAO
%token CASO
%token OPCAO
%token FIM_OPCAO
%token ENQUANTO
%token FACA
%token ATE
%token PARA
%token OP_ATRIBUICAO
%token <sval> NUMERICO
%token OP_INCREMENTO
%token OP_DECREMENTO
%token OP_SOMA
%token OP_SUB
%token OP_DIV
%token OP_MULT
%token OP_MOD
%token OP_IGUALDADE
%token OP_DIFERENTE
%token OP_MAIOR
%token OP_MENOR
%token OP_MAIOR_IGUAL
%token OP_MENOR_IGUAL
%token VIRGULA
%token DOIS_PONTOS
%token PONTO_VIRGULA
%token <sval> STRING
%token <sval> CHAR
%token <sval> COMENTARIO_LINHA
%token <sval> COMENTARIO_BLOCO
%type <sval> operador_logico
%type <sval> operador_aritmetico
%type <sval> programa
%type <sval> funcao_principal
%type <sval> inclusao
%type <sval> comandos
%type <sval> declaracao
%type <sval> tipo
%type <sval> atributos
%type <sval> parametros
%type <sval> mult_parametros
%type <sval> bloco
%type <sval> variavel
%type <sval> vetor
%type <sval> expressoes
%type <sval> expres_aritmeticas
%type <sval> expres_logicas
%type <sval> operandos_logicos
%type <sval> atribuicao
%type <sval> incremento
%type <sval> decremento
%type <sval> operandos_aritmeticos
%type <sval> chamada_funcao
%type <sval> cmd_if
%type <sval> cmd_switch
%type <sval> cmd_case
%type <sval> cmd_while
%type <sval> cmd_do_while
%type <sval> cmd_for
%type <sval> operandos_switch

%%
inicio : programa	 { System.out.println($1); }

programa : inclusao programa	{ $$ = $1 + "\n" + $2; }
		| funcao_principal programa { $$ = $1 + "\n" + $2; }
                | FUNCAO declaracao atributos bloco programa { $$ = $2 + $3 + $4 + "\n" + $5; }
                | COMENTARIO_LINHA programa { $$ = $1 + "\n" + $2; }
                | COMENTARIO_BLOCO programa { $$ = $1 + "\n" + $2; }
	     |					{ $$ = ""; }

funcao_principal : FUNCAO_PRINCIPAL bloco { $$ = "int main() " + $2 + "\n"; }

bloco : ABRE_CHAVES comandos FECHA_CHAVES { $$ = "{\n " + $2 + "}"; }

inclusao : INCLUIR INCLUSAO_ARQUIVO	{ $$ = "#include " + $2; }

comandos : declaracao comandos	{ $$ = $1 + ";\n " + $2; }
            | atribuicao comandos { $$ = $1 + ";\n " + $2; }
            | RETORNAR expressoes comandos { $$ = "return " + $2 + ";\n " + $3; }
            | chamada_funcao comandos { $$ = $1 + ";\n " + $2; }
            | cmd_if comandos { $$ = $1 + $2; }
            | cmd_switch comandos { $$ = $1 + $2; }
            | cmd_while comandos { $$ = $1 + $2; }
            | cmd_do_while comandos { $$ = $1 + $2; }
            | cmd_for comandos { $$ = $1 + $2; }
            | COMENTARIO_LINHA comandos { $$ = $1 + "\n" + $2; }
            | COMENTARIO_BLOCO comandos { $$ = $1 + "\n" + $2; }
            |					{ $$ = ""; }

cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco SENAO bloco { $$ = "if(" + $3 + ")" + $5 + "else" + $7 + "\n "; }
        | SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco { $$ = "if(" + $3 + ")" + $5 + "\n "; }
        | SE ABRE_PARENTESES expressoes FECHA_PARENTESES { $$ = "if(" + $3 + ")\n  "; }

cmd_switch : CASO ABRE_PARENTESES operandos_switch FECHA_PARENTESES ABRE_CHAVES cmd_case FECHA_CHAVES { $$ = "switch(" + $3 + "){\n  " + $6 + "\n }\n "; }

cmd_case: OPCAO operandos_switch DOIS_PONTOS ABRE_CHAVES comandos FIM_OPCAO FECHA_CHAVES cmd_case { $$ = "case " + $2 + ":{\n   " + $5 + "  break;\n}" + $8; }
        | OPCAO operandos_switch DOIS_PONTOS ABRE_CHAVES comandos FIM_OPCAO FECHA_CHAVES { $$ = "case " + $2 + ":{\n   " + $5 + "  break;\n}"; }
        | OPCAO operandos_switch DOIS_PONTOS comandos FIM_OPCAO cmd_case { $$ = "case " + $2 + ":\n   " + $4 + "  break;\n  " + $6; }
        | OPCAO operandos_switch DOIS_PONTOS comandos FIM_OPCAO { $$ = "case " + $2 + ":\n   " + $4 + "  break;"; }

cmd_while : ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES bloco { $$ = "while(" + $3 + ")" + $5 + "\n "; }
        | ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES { $$ = "while(" + $3 + ")\n  "; }

cmd_do_while : FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES expressoes FECHA_PARENTESES { $$ = "do{\n  " + $3 + "}while(" + $7 + ");\n "; }

cmd_for : PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA incremento FECHA_PARENTESES bloco { $$ = "for(" + $3 + ";" + $5 + ";" + $7 + ")" + $9 + "\n "; }
        | PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA decremento FECHA_PARENTESES bloco { $$ = "for(" + $3 + ";" + $5 + ";" + $7 + ")" + $9 + "\n "; }

declaracao : tipo variavel	{  $$ = $1 + $2;  }

variavel :  IDENTIFICADOR { $$ = $1; }
        | IDENTIFICADOR vetor { $$ = $1 + $2; }

vetor : ABRE_COLCHETES FECHA_COLCHETES { $$ = "[]"; }
        | ABRE_COLCHETES operandos_aritmeticos FECHA_COLCHETES { $$ = "[" + $2 + "]"; }
        | ABRE_COLCHETES expres_aritmeticas FECHA_COLCHETES { $$ = "[" + $2 + "]"; }

atributos : ABRE_PARENTESES FECHA_PARENTESES { $$ = "()"; }
        | ABRE_PARENTESES declaracao FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

chamada_funcao : IDENTIFICADOR parametros { $$ = $1 + $2; }
        | IMPRIMA parametros { $$ = "printf" + $2; }

parametros : ABRE_PARENTESES FECHA_PARENTESES { $$ = "()"; }
        | ABRE_PARENTESES mult_parametros FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

mult_parametros : expressoes { $$ = $1; }
        | expressoes VIRGULA mult_parametros { $$ = $1 + "," + $3; }

atribuicao : variavel OP_ATRIBUICAO expressoes { $$ = $1 + " = " + $3; }
        | incremento { $$ = $1; }
        | decremento { $$ = $1; }

incremento : variavel OP_INCREMENTO { $$ = $1 + "++"; }
        | variavel OP_INCREMENTO NUMERICO { $$ = $1 + "++" + $3; }

decremento : variavel OP_DECREMENTO { $$ = $1 + "--"; }
        | variavel OP_DECREMENTO NUMERICO { $$ = $1 + "++" + $3; }

expressoes : operandos_logicos { $$ = $1; }
        | expres_logicas { $$ = $1; }

expres_aritmeticas : operandos_aritmeticos operador_aritmetico expres_aritmeticas { $$ = $1 + $2 + $3; }
        | operandos_aritmeticos operador_aritmetico operandos_aritmeticos { $$ = $1 + $2 + $3; }
        | ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES operador_aritmetico operandos_aritmeticos { $$ = "(" + $2 + ")" + $4 + $5; }
        | ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES operador_aritmetico expres_aritmeticas { $$ = "(" + $2 + ")" + $4 + $5; }
        | ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

expres_logicas : operandos_logicos operador_logico expres_logicas { $$ = $1 + $2 + $3; }
        | operandos_logicos operador_logico operandos_logicos { $$ = $1 + $2 + $3; }
        | ABRE_PARENTESES expres_logicas FECHA_PARENTESES operador_logico operandos_logicos { $$ = "(" + $2 + ")" + $4 + $5; }
        | ABRE_PARENTESES expres_logicas FECHA_PARENTESES operador_logico expres_logicas { $$ = "(" + $2 + ")" + $4 + $5; }
        | ABRE_PARENTESES expres_logicas FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

operandos_aritmeticos : NUMERICO { $$ = $1; }
        | variavel { $$ = $1; }
        | incremento { $$ = $1; }
        | decremento { $$ = $1; }
        | chamada_funcao { $$ = $1; }
        | ABRE_PARENTESES operandos_aritmeticos FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

operandos_logicos : operandos_aritmeticos { $$ = $1; }
        | expres_aritmeticas { $$ = $1; }
        | CHAR { $$ = $1; }
        | STRING { $$ = $1; }
        | ABRE_PARENTESES CHAR FECHA_PARENTESES { $$ = "(" + $2 + ")"; }
        | ABRE_PARENTESES STRING FECHA_PARENTESES { $$ = "(" + $2 + ")"; }

operandos_switch : CHAR { $$ = $1; }
        | NUMERICO { $$ = $1; }
        | variavel { $$ = $1; }

operador_aritmetico : OP_SOMA { $$ = " + "; }
        | OP_SUB { $$ = " - "; }
        | OP_DIV { $$ = " / "; }
        | OP_MULT { $$ = " * "; }
        | OP_MOD { $$ = " % "; }

operador_logico : OP_IGUALDADE { $$ = " == "; }
        | OP_DIFERENTE { $$ = " != "; }
        | OP_MAIOR { $$ = " > "; }
        | OP_MENOR { $$ = " < "; }
        | OP_MAIOR_IGUAL { $$ = " >= "; }
        | OP_MENOR_IGUAL { $$ = " <= "; }

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
