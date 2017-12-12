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
public final static short IMPRIMA=272;
public final static short SE=273;
public final static short SENAO=274;
public final static short OP_ATRIBUICAO=275;
public final static short NUMERICO=276;
public final static short OP_INCREMENTO=277;
public final static short OP_DECREMENTO=278;
public final static short OP_SOMA=279;
public final static short OP_SUB=280;
public final static short OP_DIV=281;
public final static short OP_MULT=282;
public final static short OP_MOD=283;
public final static short OP_IGUALDADE=284;
public final static short OP_DIFERENTE=285;
public final static short OP_MAIOR=286;
public final static short OP_MENOR=287;
public final static short OP_MAIOR_IGUAL=288;
public final static short OP_MENOR_IGUAL=289;
public final static short VIRGULA=290;
public final static short STRING=291;
public final static short CHAR=292;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    4,   12,    5,    6,    6,
    6,    6,    6,    6,    6,    7,   13,   13,   14,   14,
   14,    9,    9,   23,   23,   10,   10,   11,   11,   19,
   19,   19,   20,   20,   21,   21,   15,   15,   16,   16,
   16,   16,   16,   17,   17,   17,   17,   17,   22,   22,
   22,   22,   22,   22,   18,   18,   18,   18,   18,   18,
    2,    2,    2,    2,    1,    1,    1,    1,    1,    1,
    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    0,    2,    3,    2,    2,    2,
    3,    2,    6,    8,    0,    2,    1,    2,    2,    3,
    3,    2,    3,    2,    2,    2,    3,    1,    3,    3,
    1,    1,    2,    3,    2,    3,    1,    1,    3,    3,
    5,    5,    3,    3,    3,    5,    5,    3,    1,    1,
    1,    1,    1,    3,    1,    1,    1,    1,    3,    3,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    6,    8,
   71,   72,   73,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,   31,   32,    0,    0,    0,
    0,   16,    0,    0,   24,   18,    0,   49,   58,   57,
    0,    0,   56,   38,    0,   51,   52,    0,   53,   25,
    0,    7,    9,    0,    0,    0,   10,   12,   22,    0,
    0,   26,    0,    0,    0,   19,    0,    0,    0,    0,
    0,    0,    0,    0,   11,   65,   66,   67,   68,   69,
   70,    0,   61,   62,   63,   64,    0,    0,   30,   34,
   36,   23,    4,   27,    0,    0,    0,   21,   20,   60,
   59,    0,    0,   54,   44,    0,   39,    0,    0,   29,
    0,    0,    0,   42,    0,   47,    0,    0,   13,    0,
   14,
};
final static short yydgoto[] = {                          4,
   82,   87,    5,    6,    7,   22,   23,   15,   30,   35,
   63,    9,   41,   36,   64,   43,   44,   45,   25,   46,
   47,   48,   49,
};
final static short yysindex[] = {                        14,
 -255, -252, -189,    0,    0,   14,   14,   78,    0,    0,
    0,    0,    0, -242, -229,    0,    0, -160, -209, -228,
 -216, -213,   78, -217,   78,    0,    0,   78, -159, -255,
 -211,    0, -221,  148,    0,    0, -207,    0,    0,    0,
 -240,   78,    0,    0,   95,    0,    0, -104,    0,    0,
 -209,    0,    0, -209, -214, -210,    0,    0,    0, -190,
   14,    0, -173, -195, -165,    0, -168, -163, -142, -137,
 -136, -134,   95, -206,    0,    0,    0,    0,    0,    0,
    0, -209,    0,    0,    0,    0, -165, -132,    0,    0,
    0,    0,    0,    0, -209, -136, -206,    0,    0,    0,
    0, -104,   95,    0,    0,   95,    0, -104, -255,    0,
 -165, -209,   44,    0, -104,    0,   95, -255,    0,   78,
    0,
};
final static short yyrindex[] = {                       131,
    0,    0,    0,    0,    0,  131,  131, -127,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -133,    0,    0,
    0,    0, -127,    0, -127,    0,    0, -127,    0,    0,
  219,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -99, -127,    0,    0,  139,    0,    0,  105,    0,    0,
    0,    0,    0,    0,  -65,  -31,    0,    0,    0,    0,
  131,    0,    0, -125,    0,    0,    0,    0,  209,  215,
  221,    0,    0,  227,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    3,  159,    0,    0,  179,    0,   37,    0,    0,
    0,    0, -127,    0,   71,    0,  199,    0,    0, -127,
    0,
};
final static short yygindex[] = {                         0,
   38,   41,   25,    0,    0,  -20,   10,    0,    0,  130,
   64,  -28,   -8,    0,   40,  -23,  -25,  -14,    0,   -7,
    1,   12,    2,
};
final static int YYTABLESIZE=516;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         24,
   26,   61,   53,    8,   57,   10,   32,   58,   27,   28,
   67,   72,   14,   71,   24,   26,   24,   26,   29,   24,
   26,   75,   73,   27,   28,   27,   28,   31,   27,   28,
   16,   17,   33,   24,   26,   18,   55,   56,   60,   37,
   62,   96,   27,   28,   51,   68,   52,   18,   74,   18,
   20,   37,   34,   37,   38,  104,  105,   54,   42,   55,
   56,   90,   20,  107,   20,   91,   38,  106,   38,   39,
   40,   92,   83,   84,   85,   86,   97,   11,   12,   13,
  113,   39,   40,   69,   70,   93,  116,  114,   94,  120,
   88,   18,  119,   89,   95,   65,   98,  117,  108,  121,
   33,   99,   59,   34,   24,   26,   20,   11,   12,   13,
   38,   24,   26,   27,   28,   83,   84,   85,   86,  100,
   27,   28,  115,   17,  101,  102,   17,  103,   17,  109,
    5,   17,   15,   17,   17,   17,   28,   17,   17,   17,
  112,   17,  111,   17,   17,   17,   17,   17,   17,   50,
   17,   17,   17,   17,   17,   17,   17,   50,  110,    0,
   50,    0,   50,    0,    0,   50,    0,   50,   50,   50,
    0,   50,   50,   50,   83,   84,   85,   86,    0,   50,
   50,   50,   50,    0,   50,   50,   50,   50,   50,   50,
   50,   33,    0,    0,   33,    0,   33,    0,    0,   33,
    0,   33,   33,   33,    0,   33,   33,   33,    0,    0,
    0,    0,    0,   33,   33,   33,   33,    0,   33,   33,
   33,   33,   33,   33,   33,   35,    0,    0,   35,    0,
   35,    0,    0,   35,    0,   35,   35,   35,    0,   35,
   35,   35,    0,    0,    0,    0,    0,   35,   35,   35,
   35,    0,   35,   35,   35,   35,   35,   35,   35,   43,
    0,    0,   43,    0,   43,    0,    0,   43,    0,   43,
   43,   43,    0,   43,   43,   43,    1,    0,    0,    2,
    0,    0,    0,    3,    0,    0,   43,   43,   43,   43,
   43,   43,   43,   40,    0,    0,   40,    0,   40,    0,
   18,   40,    0,   40,   40,   40,    0,   40,   40,   40,
   11,   12,   13,    0,   19,   20,   21,  118,    0,    0,
   40,   40,   40,   40,   40,   40,   40,   41,    0,    0,
   41,    0,   41,    0,   18,   41,    0,   41,   41,   41,
    0,   41,   41,   41,   11,   12,   13,    0,   19,   20,
   21,    0,    0,    0,   41,   41,   41,   41,   41,   41,
   41,   55,    0,    0,   55,    0,   55,    0,    0,    0,
    0,   55,   55,   55,    0,   55,   55,   55,   76,   77,
   78,   79,   80,   81,    0,    0,    0,    0,   55,   55,
   55,   55,   55,   55,   55,   37,    0,    0,   37,    0,
   37,    0,    0,    0,   18,   37,   37,   37,   65,   37,
   37,   37,   66,    0,    0,   48,    0,    0,   48,   20,
   48,    0,    0,   38,    0,   48,   48,   48,   37,   48,
   48,   48,    0,    0,    0,   45,    0,    0,   45,    0,
   45,    0,    0,    0,    0,   45,   45,   45,   48,   45,
   45,   45,    0,    0,    0,   46,    0,    0,   46,    0,
   46,    0,    0,    0,    0,   46,   46,   46,   45,   46,
   46,   46,    0,    0,    0,   17,    0,    0,   17,   17,
   17,    0,    0,    0,    0,   17,   17,   17,   46,   17,
   17,   17,   58,   58,   58,   58,   58,   58,   57,   57,
   57,   57,   57,   57,   56,   56,   56,   56,   56,   56,
   55,   55,   55,   55,   55,   55,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          8,
    8,   30,   23,  259,   25,  258,   15,   28,    8,    8,
   34,   37,    3,   37,   23,   23,   25,   25,  261,   28,
   28,   42,   37,   23,   23,   25,   25,  257,   28,   28,
    6,    7,  261,   42,   42,  257,  277,  278,   29,  261,
  262,   65,   42,   42,  261,   34,  260,  257,   37,  257,
  272,  261,  264,  261,  276,  262,   82,  275,   19,  277,
  278,  276,  272,   87,  272,  276,  276,   82,  276,  291,
  292,  262,  279,  280,  281,  282,   65,  267,  268,  269,
  109,  291,  292,  291,  292,   61,  112,  111,  262,  118,
   51,  257,  113,   54,  290,  261,  265,  112,   87,  120,
  261,  265,  262,  264,  113,  113,  272,  267,  268,  269,
  276,  120,  120,  113,  113,  279,  280,  281,  282,  262,
  120,  120,  111,  257,  262,  262,  260,  262,  262,  262,
    0,  265,  260,  267,  268,  269,  262,  271,  272,  273,
  103,  275,  102,  277,  278,  279,  280,  281,  282,   20,
  284,  285,  286,  287,  288,  289,  290,  257,   95,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,  269,
   -1,  271,  272,  273,  279,  280,  281,  282,   -1,  279,
  280,  281,  282,   -1,  284,  285,  286,  287,  288,  289,
  290,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,   -1,
   -1,   -1,   -1,  279,  280,  281,  282,   -1,  284,  285,
  286,  287,  288,  289,  290,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,  279,  280,  281,
  282,   -1,  284,  285,  286,  287,  288,  289,  290,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
  268,  269,   -1,  271,  272,  273,  263,   -1,   -1,  266,
   -1,   -1,   -1,  270,   -1,   -1,  284,  285,  286,  287,
  288,  289,  290,  257,   -1,   -1,  260,   -1,  262,   -1,
  257,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
  267,  268,  269,   -1,  271,  272,  273,  274,   -1,   -1,
  284,  285,  286,  287,  288,  289,  290,  257,   -1,   -1,
  260,   -1,  262,   -1,  257,  265,   -1,  267,  268,  269,
   -1,  271,  272,  273,  267,  268,  269,   -1,  271,  272,
  273,   -1,   -1,   -1,  284,  285,  286,  287,  288,  289,
  290,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,
   -1,  267,  268,  269,   -1,  271,  272,  273,  284,  285,
  286,  287,  288,  289,   -1,   -1,   -1,   -1,  284,  285,
  286,  287,  288,  289,  290,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,   -1,  257,  267,  268,  269,  261,  271,
  272,  273,  265,   -1,   -1,  257,   -1,   -1,  260,  272,
  262,   -1,   -1,  276,   -1,  267,  268,  269,  290,  271,
  272,  273,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,  290,  271,
  272,  273,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,  290,  271,
  272,  273,   -1,   -1,   -1,  257,   -1,   -1,  260,  261,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,  290,  271,
  272,  273,  284,  285,  286,  287,  288,  289,  284,  285,
  286,  287,  288,  289,  284,  285,  286,  287,  288,  289,
  284,  285,  286,  287,  288,  289,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=292;
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
"IMPRIMA","SE","SENAO","OP_ATRIBUICAO","NUMERICO","OP_INCREMENTO",
"OP_DECREMENTO","OP_SOMA","OP_SUB","OP_DIV","OP_MULT","OP_MOD","OP_IGUALDADE",
"OP_DIFERENTE","OP_MAIOR","OP_MENOR","OP_MAIOR_IGUAL","OP_MENOR_IGUAL",
"VIRGULA","STRING","CHAR",
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
"comandos : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco comandos",
"comandos : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco SENAO bloco comandos",
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
"chamada_funcao : IMPRIMA parametros",
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
"expressoes : expres_logicas",
"expres_aritmeticas : operandos_aritmeticos operador_aritmetico expres_aritmeticas",
"expres_aritmeticas : operandos_aritmeticos operador_aritmetico operandos_aritmeticos",
"expres_aritmeticas : ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES operador_aritmetico operandos_aritmeticos",
"expres_aritmeticas : ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES operador_aritmetico expres_aritmeticas",
"expres_aritmeticas : ABRE_PARENTESES expres_aritmeticas FECHA_PARENTESES",
"expres_logicas : operandos_logicos operador_logico expres_logicas",
"expres_logicas : operandos_logicos operador_logico operandos_logicos",
"expres_logicas : ABRE_PARENTESES expres_logicas FECHA_PARENTESES operador_logico operandos_logicos",
"expres_logicas : ABRE_PARENTESES expres_logicas FECHA_PARENTESES operador_logico expres_logicas",
"expres_logicas : ABRE_PARENTESES expres_logicas FECHA_PARENTESES",
"operandos_aritmeticos : NUMERICO",
"operandos_aritmeticos : variavel",
"operandos_aritmeticos : incremento",
"operandos_aritmeticos : decremento",
"operandos_aritmeticos : chamada_funcao",
"operandos_aritmeticos : ABRE_PARENTESES operandos_aritmeticos FECHA_PARENTESES",
"operandos_logicos : operandos_aritmeticos",
"operandos_logicos : expres_aritmeticas",
"operandos_logicos : CHAR",
"operandos_logicos : STRING",
"operandos_logicos : ABRE_PARENTESES CHAR FECHA_PARENTESES",
"operandos_logicos : ABRE_PARENTESES STRING FECHA_PARENTESES",
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

//#line 165 "inicioCT.y"

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
//#line 457 "Parser.java"
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
//#line 68 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 70 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 71 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 72 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 5:
//#line 73 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 75 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval; }
break;
case 7:
//#line 77 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}\n"; }
break;
case 8:
//#line 79 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 10:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 11:
//#line 83 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 85 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 86 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(5).sval + ")" + val_peek(3).sval + "else" + val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 87 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 16:
//#line 89 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 17:
//#line 91 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 18:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 19:
//#line 94 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 20:
//#line 95 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 21:
//#line 96 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 22:
//#line 98 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 23:
//#line 99 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 24:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 25:
//#line 102 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 26:
//#line 104 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 27:
//#line 105 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 28:
//#line 107 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 29:
//#line 108 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 30:
//#line 110 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 31:
//#line 111 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 32:
//#line 112 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 33:
//#line 114 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 34:
//#line 115 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 35:
//#line 117 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 36:
//#line 118 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 37:
//#line 120 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 38:
//#line 121 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 39:
//#line 123 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 40:
//#line 124 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 41:
//#line 125 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 42:
//#line 126 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 43:
//#line 127 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 44:
//#line 129 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 45:
//#line 130 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 46:
//#line 131 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 47:
//#line 132 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 48:
//#line 133 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 49:
//#line 135 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 136 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 137 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 52:
//#line 138 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 53:
//#line 139 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 54:
//#line 140 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 55:
//#line 142 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 56:
//#line 143 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 57:
//#line 144 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 145 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 59:
//#line 146 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 60:
//#line 147 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 61:
//#line 149 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 62:
//#line 150 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 63:
//#line 151 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 64:
//#line 152 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 65:
//#line 154 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 66:
//#line 155 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 67:
//#line 156 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 68:
//#line 157 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 69:
//#line 158 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 70:
//#line 159 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 71:
//#line 161 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 72:
//#line 162 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 73:
//#line 163 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 898 "Parser.java"
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
