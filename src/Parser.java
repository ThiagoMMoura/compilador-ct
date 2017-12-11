//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "inicioCT.y"
	import java.io.*;
	import java.util.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short INCLUSAO_ARQUIVO=258;
public final static short ABRE_CHAVES=259;
public final static short FECHA_CHAVES=260;
public final static short ABRE_PARENTESES=261;
public final static short FECHA_PARENTESES=262;
public final static short FUNCAO_PRINCIPAL=263;
public final static short ABRE_COLCHETES=264;
public final static short FECHA_COLCHETES=265;
public final static short INCLUIR=266;
public final static short INTEIRO=267;
public final static short REAL=268;
public final static short CARACTER=269;
public final static short FUNCAO=270;
public final static short RETORNAR=271;
public final static short OP_ATRIBUICAO=272;
public final static short NUMERICO=273;
public final static short OP_INCREMENTO=274;
public final static short OP_DECREMENTO=275;
public final static short OP_SOMA=276;
public final static short OP_SUB=277;
public final static short OP_DIV=278;
public final static short OP_MULT=279;
public final static short OP_MOD=280;
public final static short OP_IGUALDADE=281;
public final static short OP_DIFERENTE=282;
public final static short OP_MAIOR=283;
public final static short OP_MENOR=284;
public final static short OP_MAIOR_IGUAL=285;
public final static short OP_MENOR_IGUAL=286;
public final static short VIRGULA=287;
public final static short STRING=288;
public final static short CHAR=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    4,   12,    5,    6,    6,
    6,    6,    6,    7,   13,   13,   14,   14,   14,    9,
    9,   23,   10,   10,   11,   11,   19,   19,   19,   20,
   20,   21,   21,   15,   15,   15,   16,   16,   17,   17,
   17,   17,   17,   17,   22,   22,   22,   22,   22,   18,
   18,   18,    2,    2,    2,    2,    1,    1,    1,    1,
    1,    1,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    0,    2,    3,    2,    2,    2,
    3,    2,    0,    2,    1,    2,    2,    3,    3,    2,
    3,    2,    2,    3,    1,    3,    3,    1,    1,    2,
    3,    2,    3,    1,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    6,    8,
   63,   64,   65,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,   28,   29,    0,    0,    0,    0,   14,
    0,    0,   22,   16,   45,   52,   51,    0,    0,    0,
   36,    0,   47,   48,    0,   49,    7,    9,    0,    0,
    0,   10,   12,   20,    0,    0,   23,    0,    0,   17,
    0,    0,   11,   57,   58,   59,   60,   61,   62,    0,
    0,   53,   54,   55,   56,    0,   27,   31,   33,   21,
    4,   24,    0,   19,   18,    0,   43,    0,    0,   39,
    0,   37,    0,   26,
};
final static short yydgoto[] = {                          4,
   70,   76,    5,    6,    7,   20,   21,   15,   28,   33,
   58,    9,   38,   34,   59,   40,   41,   42,   23,   43,
   44,   45,   46,
};
final static short yysindex[] = {                      -252,
 -242, -237,  -95,    0,    0, -252, -252,  -32,    0,    0,
    0,    0,    0, -228, -211,    0,    0, -232, -251, -192,
  -32, -142,  -32,    0,    0,  -32, -206, -242, -191,    0,
 -253, -249,    0,    0,    0,    0,    0, -215,  -32,   67,
    0,   67,    0,    0, -135,    0,    0,    0, -251, -196,
 -186,    0,    0,    0, -182, -252,    0, -161, -185,    0,
 -157, -166,    0,    0,    0,    0,    0,    0,    0, -251,
 -251,    0,    0,    0,    0, -250,    0,    0,    0,    0,
    0,    0, -251,    0,    0,   67,    0,   67,   67,    0,
   67,    0, -135,    0,
};
final static short yyrindex[] = {                       104,
    0,    0,    0,    0,    0,  104,  104, -133,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -193,    0,    0,
 -133,    0, -133,    0,    0, -133,    0,    0,   75,    0,
    0,    0,    0,    0,    0,    0,    0, -162, -133,   -7,
    0,    6,    0,    0,  -38,    0,    0,    0,    0, -131,
 -100,    0,    0,    0,    0,  104,    0,    0, -144,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   19,    0,   34,   47,    0,
   60,    0,  -69,    0,
};
final static short yygindex[] = {                         0,
  -40,    0,   -6,    0,    0,  169,    7,    0,    0,    0,
   45,  107,    4,    0,   -4,  -29,   -5,   26,    0,    5,
   31,  -27,   32,
};
final static int YYTABLESIZE=353;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         16,
   17,   71,   61,   18,   62,   18,   18,   18,   57,   14,
    1,   22,   24,    2,   39,   60,    8,    3,   30,   35,
   10,   35,   35,   35,   22,   24,   22,   24,   31,   22,
   24,   32,   27,   55,   36,   37,   36,   37,   25,   26,
   86,   89,   22,   24,   77,   29,   92,   71,   93,   81,
   71,   25,   26,   25,   26,   54,   25,   26,   50,   51,
   11,   12,   13,   15,   87,   90,   15,   47,   15,   25,
   26,   15,   32,   15,   15,   15,   78,   15,   15,   80,
   15,   15,   15,   15,   15,   15,   79,   15,   15,   15,
   15,   15,   15,   15,   46,   88,   91,   46,   85,   46,
   82,   83,   46,    5,   46,   46,   46,   84,   46,   72,
   73,   74,   75,   46,   46,   46,   46,   25,   46,   46,
   46,   46,   46,   46,   46,   30,   13,   94,   30,   49,
   30,   50,   51,   30,   56,   30,   30,   30,    0,   30,
   72,   73,   74,   75,   30,   30,   30,   30,    0,   30,
   30,   30,   30,   30,   30,   30,   32,    0,    0,   32,
    0,   32,    0,    0,   32,    0,   32,   32,   32,    0,
   32,   11,   12,   13,    0,   32,   32,   32,   32,    0,
   32,   32,   32,   32,   32,   32,   32,   38,    0,   48,
   38,   52,   38,    0,   53,   38,    0,   38,   38,   38,
    0,   38,    0,    0,    0,    0,    0,   63,    0,    0,
    0,   38,   38,   38,   38,   38,   38,   38,   50,    0,
    0,   50,    0,   50,   18,    0,    0,    0,   50,   50,
   50,    0,   50,    0,   11,   12,   13,    0,   19,    0,
    0,    0,   50,   50,   50,   50,   50,   50,   50,   35,
    0,    0,   35,    0,   35,    0,    0,    0,    0,   35,
   35,   35,   34,   35,    0,   34,    0,   34,    0,    0,
    0,    0,   34,   34,   34,   41,   34,    0,   41,   35,
   41,    0,    0,    0,    0,   41,   41,   41,    0,   41,
   42,    0,   34,   42,    0,   42,    0,    0,    0,    0,
   42,   42,   42,   40,   42,   41,   40,    0,   40,    0,
    0,    0,    0,   40,   40,   40,   44,   40,    0,   44,
   42,   44,    0,    0,    0,    0,   44,   44,   44,    0,
   44,   15,    0,   40,   15,   15,   15,    0,    0,    0,
    0,   15,   15,   15,    0,   15,   44,   64,   65,   66,
   67,   68,   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    7,   42,   32,  257,   32,  257,  257,  257,  262,    3,
  263,    8,    8,  266,   19,  265,  259,  270,   15,  273,
  258,  273,  273,  273,   21,   21,   23,   23,  261,   26,
   26,  264,  261,   27,  288,  289,  288,  289,    8,    8,
   70,   71,   39,   39,   49,  257,   76,   88,   76,   56,
   91,   21,   21,   23,   23,  262,   26,   26,  274,  275,
  267,  268,  269,  257,   70,   71,  260,  260,  262,   39,
   39,  265,  264,  267,  268,  269,  273,  271,  272,  262,
  274,  275,  276,  277,  278,  279,  273,  281,  282,  283,
  284,  285,  286,  287,  257,   70,   71,  260,  265,  262,
  262,  287,  265,    0,  267,  268,  269,  265,  271,  276,
  277,  278,  279,  276,  277,  278,  279,  262,  281,  282,
  283,  284,  285,  286,  287,  257,  260,   83,  260,  272,
  262,  274,  275,  265,   28,  267,  268,  269,   -1,  271,
  276,  277,  278,  279,  276,  277,  278,  279,   -1,  281,
  282,  283,  284,  285,  286,  287,  257,   -1,   -1,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,  268,  269,   -1,
  271,  267,  268,  269,   -1,  276,  277,  278,  279,   -1,
  281,  282,  283,  284,  285,  286,  287,  257,   -1,   21,
  260,   23,  262,   -1,   26,  265,   -1,  267,  268,  269,
   -1,  271,   -1,   -1,   -1,   -1,   -1,   39,   -1,   -1,
   -1,  281,  282,  283,  284,  285,  286,  287,  257,   -1,
   -1,  260,   -1,  262,  257,   -1,   -1,   -1,  267,  268,
  269,   -1,  271,   -1,  267,  268,  269,   -1,  271,   -1,
   -1,   -1,  281,  282,  283,  284,  285,  286,  287,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,  257,  271,   -1,  260,   -1,  262,   -1,   -1,
   -1,   -1,  267,  268,  269,  257,  271,   -1,  260,  287,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,   -1,  271,
  257,   -1,  287,  260,   -1,  262,   -1,   -1,   -1,   -1,
  267,  268,  269,  257,  271,  287,  260,   -1,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,  257,  271,   -1,  260,
  287,  262,   -1,   -1,   -1,   -1,  267,  268,  269,   -1,
  271,  257,   -1,  287,  260,  261,  262,   -1,   -1,   -1,
   -1,  267,  268,  269,   -1,  271,  287,  281,  282,  283,
  284,  285,  286,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=289;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"IDENTIFICADOR","INCLUSAO_ARQUIVO","ABRE_CHAVES","FECHA_CHAVES",
"ABRE_PARENTESES","FECHA_PARENTESES","FUNCAO_PRINCIPAL","ABRE_COLCHETES",
"FECHA_COLCHETES","INCLUIR","INTEIRO","REAL","CARACTER","FUNCAO","RETORNAR",
"OP_ATRIBUICAO","NUMERICO","OP_INCREMENTO","OP_DECREMENTO","OP_SOMA","OP_SUB",
"OP_DIV","OP_MULT","OP_MOD","OP_IGUALDADE","OP_DIFERENTE","OP_MAIOR","OP_MENOR",
"OP_MAIOR_IGUAL","OP_MENOR_IGUAL","VIRGULA","STRING","CHAR",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : funcao_principal programa",
"programa : FUNCAO declaracao atributos bloco programa",
"programa :",
"funcao_principal : FUNCAO_PRINCIPAL bloco",
"bloco : ABRE_CHAVES comandos FECHA_CHAVES",
"inclusao : INCLUIR INCLUSAO_ARQUIVO",
"comandos : declaracao comandos",
"comandos : atribuicao comandos",
"comandos : RETORNAR expressoes comandos",
"comandos : chamada_funcao comandos",
"comandos :",
"declaracao : tipo variavel",
"variavel : IDENTIFICADOR",
"variavel : IDENTIFICADOR vetor",
"vetor : ABRE_COLCHETES FECHA_COLCHETES",
"vetor : ABRE_COLCHETES operandos_aritmeticos FECHA_COLCHETES",
"vetor : ABRE_COLCHETES expres_aritmeticas FECHA_COLCHETES",
"atributos : ABRE_PARENTESES FECHA_PARENTESES",
"atributos : ABRE_PARENTESES declaracao FECHA_PARENTESES",
"chamada_funcao : IDENTIFICADOR parametros",
"parametros : ABRE_PARENTESES FECHA_PARENTESES",
"parametros : ABRE_PARENTESES mult_parametros FECHA_PARENTESES",
"mult_parametros : expressoes",
"mult_parametros : expressoes VIRGULA mult_parametros",
"atribuicao : variavel OP_ATRIBUICAO expressoes",
"atribuicao : incremento",
"atribuicao : decremento",
"incremento : variavel OP_INCREMENTO",
"incremento : variavel OP_INCREMENTO NUMERICO",
"decremento : variavel OP_DECREMENTO",
"decremento : variavel OP_DECREMENTO NUMERICO",
"expressoes : operandos_logicos",
"expressoes : expres_aritmeticas",
"expressoes : expres_logicas",
"expres_aritmeticas : operandos_aritmeticos operador_aritmetico expres_aritmeticas",
"expres_aritmeticas : operandos_aritmeticos operador_aritmetico operandos_aritmeticos",
"expres_logicas : operandos_logicos operador_logico expres_logicas",
"expres_logicas : operandos_logicos operador_logico expres_aritmeticas",
"expres_logicas : expres_aritmeticas operador_logico expres_aritmeticas",
"expres_logicas : expres_aritmeticas operador_logico operandos_logicos",
"expres_logicas : expres_aritmeticas operador_logico expres_logicas",
"expres_logicas : operandos_logicos operador_logico operandos_logicos",
"operandos_aritmeticos : NUMERICO",
"operandos_aritmeticos : variavel",
"operandos_aritmeticos : incremento",
"operandos_aritmeticos : decremento",
"operandos_aritmeticos : chamada_funcao",
"operandos_logicos : operandos_aritmeticos",
"operandos_logicos : CHAR",
"operandos_logicos : STRING",
"operador_aritmetico : OP_SOMA",
"operador_aritmetico : OP_SUB",
"operador_aritmetico : OP_DIV",
"operador_aritmetico : OP_MULT",
"operador_logico : OP_IGUALDADE",
"operador_logico : OP_DIFERENTE",
"operador_logico : OP_MAIOR",
"operador_logico : OP_MENOR",
"operador_logico : OP_MAIOR_IGUAL",
"operador_logico : OP_MENOR_IGUAL",
"tipo : INTEIRO",
"tipo : REAL",
"tipo : CARACTER",
};

//#line 154 "inicioCT.y"

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
//#line 402 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 65 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 67 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 68 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 69 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 5:
//#line 70 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 72 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval; }
break;
case 7:
//#line 74 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 76 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 78 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 10:
//#line 79 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 11:
//#line 80 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 82 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 14:
//#line 84 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 15:
//#line 86 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 16:
//#line 87 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 89 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 18:
//#line 90 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 19:
//#line 91 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 20:
//#line 93 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 21:
//#line 94 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 22:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 23:
//#line 98 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 24:
//#line 99 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 25:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 26:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 27:
//#line 104 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 28:
//#line 105 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 29:
//#line 106 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 30:
//#line 108 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 31:
//#line 109 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 32:
//#line 111 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 33:
//#line 112 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 34:
//#line 114 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 35:
//#line 115 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 36:
//#line 116 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 37:
//#line 118 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 38:
//#line 119 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 39:
//#line 121 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 40:
//#line 122 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 41:
//#line 123 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 42:
//#line 124 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 43:
//#line 125 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 44:
//#line 126 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 45:
//#line 128 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 46:
//#line 129 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 47:
//#line 130 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 48:
//#line 131 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 132 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 134 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 135 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 52:
//#line 136 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 53:
//#line 138 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 54:
//#line 139 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 55:
//#line 140 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 56:
//#line 141 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 57:
//#line 143 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 58:
//#line 144 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 59:
//#line 145 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 60:
//#line 146 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 61:
//#line 147 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 62:
//#line 148 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 63:
//#line 150 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 64:
//#line 151 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 65:
//#line 152 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 811 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
