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
public final static short ENQUANTO=275;
public final static short OP_ATRIBUICAO=276;
public final static short NUMERICO=277;
public final static short OP_INCREMENTO=278;
public final static short OP_DECREMENTO=279;
public final static short OP_SOMA=280;
public final static short OP_SUB=281;
public final static short OP_DIV=282;
public final static short OP_MULT=283;
public final static short OP_MOD=284;
public final static short OP_IGUALDADE=285;
public final static short OP_DIFERENTE=286;
public final static short OP_MAIOR=287;
public final static short OP_MENOR=288;
public final static short OP_MAIOR_IGUAL=289;
public final static short OP_MENOR_IGUAL=290;
public final static short VIRGULA=291;
public final static short STRING=292;
public final static short CHAR=293;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    4,   12,    5,    6,    6,
    6,    6,    6,    6,    6,   24,   24,   24,   25,   25,
    7,   13,   13,   14,   14,   14,    9,    9,   23,   23,
   10,   10,   11,   11,   19,   19,   19,   20,   20,   21,
   21,   15,   15,   16,   16,   16,   16,   16,   17,   17,
   17,   17,   17,   22,   22,   22,   22,   22,   22,   18,
   18,   18,   18,   18,   18,    2,    2,    2,    2,    1,
    1,    1,    1,    1,    1,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    0,    2,    3,    2,    2,    2,
    3,    2,    2,    2,    0,    7,    5,    4,    5,    4,
    2,    1,    2,    2,    3,    3,    2,    3,    2,    2,
    2,    3,    1,    3,    3,    1,    1,    2,    3,    2,
    3,    1,    1,    3,    3,    5,    5,    3,    3,    3,
    5,    5,    3,    1,    1,    1,    1,    1,    3,    1,
    1,    1,    1,    3,    3,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    6,    8,
   76,   77,   78,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   36,   37,    0,    0,
    0,    0,    0,    0,   21,    0,    0,   29,   23,    0,
   54,   63,   62,    0,    0,   61,   43,    0,   56,   57,
    0,   58,   30,    0,    0,    7,    9,    0,    0,    0,
   10,   12,   13,   14,   27,    0,    0,   31,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,    0,
   11,   70,   71,   72,   73,   74,   75,    0,   66,   67,
   68,   69,    0,    0,    0,   35,   39,   41,   28,    4,
   32,    0,    0,    0,   26,   25,   65,   64,    0,    0,
   59,   49,    0,   44,    0,    0,    0,   34,    0,    0,
    0,   19,   47,    0,   52,    0,    0,   16,
};
final static short yydgoto[] = {                          4,
   88,   93,    5,    6,    7,   23,   24,   15,   33,   38,
   69,    9,   44,   39,   70,   46,   47,   48,   26,   49,
   50,   51,   52,   30,   31,
};
final static short yysindex[] = {                      -247,
 -235, -246,  -19,    0,    0, -247, -247,   16,    0,    0,
    0,    0,    0, -223, -209,    0,    0, -136, -225, -221,
 -207, -201, -198,   16,  -78,   16,    0,    0,   16,   16,
   16, -151, -235, -195,    0, -162, -244,    0,    0, -171,
    0,    0,    0, -248,   16,    0,    0,   33,    0,    0,
  -67,    0,    0, -225, -225,    0,    0, -225, -203, -197,
    0,    0,    0,    0,    0, -183, -247,    0, -174, -202,
 -243,    0, -160, -102, -159, -149, -148, -143,   33, -217,
    0,    0,    0,    0,    0,    0,    0, -225,    0,    0,
    0,    0, -243, -142, -129,    0,    0,    0,    0,    0,
    0, -225, -148, -217,    0,    0,    0,    0,  -67,   33,
    0,    0,   33,    0,  -67, -235, -235,    0, -243, -225,
 -137,    0,    0,  -67,    0,   33, -235,    0,
};
final static short yyrindex[] = {                       126,
    0,    0,    0,    0,    0,  126,  126, -119,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -133,    0,    0,
    0,    0,    0, -119,    0, -119,    0,    0, -119, -119,
 -119,    0,    0,  223,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -98, -119,    0,    0,  147,    0,    0,
  112,    0,    0,    0,    0,    0,    0,    0,  -63,  -28,
    0,    0,    0,    0,    0,    0,  126,    0,    0, -118,
    0,    0,    0,    0,   68,  103,  216,    0,    0,  233,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    7,  164,
    0,    0,  185,    0,   42,  240,  257,    0,    0,    0,
  274,    0,    0,   77,    0,  202,    0,    0,
};
final static short yygindex[] = {                         0,
   41,   51,    3,    0,    0,   78,   12,    0,    0,  141,
   63,  -33,   -4,    0,  -12,  -34,  -39,  -38,    0,   27,
   47,  -32,   67,    0,    0,
};
final static int YYTABLESIZE=549;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         67,
   78,   79,   73,   25,   74,   77,   45,   80,   16,   17,
   35,   10,   18,   18,   14,    1,   71,   71,    2,   25,
   72,   25,    3,    8,   25,   25,   25,   20,   20,   59,
   60,   18,   41,   41,   27,   40,  103,   32,  104,   36,
   25,   94,   95,   66,  111,   96,   20,   34,  112,  113,
   27,   41,   27,   54,   28,   27,   27,   27,  114,   55,
  115,   56,   89,   90,   91,   92,   42,   43,   37,  100,
   28,   27,   28,   97,   29,   28,   28,   28,   99,   98,
  125,  126,  121,  122,  123,   18,  124,  101,  102,   40,
   29,   28,   29,  128,   18,   29,   29,   29,   40,   68,
   20,   57,  107,   61,  105,   41,   62,   63,   64,   20,
   65,   29,  108,  109,   41,   11,   12,   13,  110,  116,
   75,   76,   81,   22,   36,    5,   22,   37,   22,   42,
   43,   22,  117,   22,   22,   22,  127,   22,   22,   22,
   15,   22,   22,   33,   22,   22,   22,   22,   22,   22,
  120,   22,   22,   22,   22,   22,   22,   22,   55,  119,
   53,   55,  106,   55,  118,    0,   55,    0,   55,   55,
   55,    0,   55,   55,   55,    0,   55,   89,   90,   91,
   92,   55,   55,   55,   55,    0,   55,   55,   55,   55,
   55,   55,   55,   38,    0,    0,   38,   58,   38,   59,
   60,   38,    0,   38,   38,   38,    0,   38,   38,   38,
    0,   38,   89,   90,   91,   92,   38,   38,   38,   38,
    0,   38,   38,   38,   38,   38,   38,   38,   40,    0,
    0,   40,    0,   40,    0,    0,   40,    0,   40,   40,
   40,    0,   40,   40,   40,    0,   40,   11,   12,   13,
    0,   40,   40,   40,   40,    0,   40,   40,   40,   40,
   40,   40,   40,   48,    0,    0,   48,    0,   48,    0,
    0,   48,   18,   48,   48,   48,    0,   48,   48,   48,
    0,   48,   11,   12,   13,    0,   19,   20,   21,    0,
   22,   48,   48,   48,   48,   48,   48,   48,   45,    0,
    0,   45,    0,   45,    0,    0,   45,    0,   45,   45,
   45,    0,   45,   45,   45,    0,   45,   82,   83,   84,
   85,   86,   87,    0,    0,    0,   45,   45,   45,   45,
   45,   45,   45,   46,    0,    0,   46,    0,   46,    0,
    0,   46,    0,   46,   46,   46,    0,   46,   46,   46,
    0,   46,   63,   63,   63,   63,   63,   63,    0,    0,
    0,   46,   46,   46,   46,   46,   46,   46,   60,    0,
    0,   60,    0,   60,    0,    0,    0,    0,   60,   60,
   60,    0,   60,   60,   60,    0,   60,   62,   62,   62,
   62,   62,   62,    0,    0,    0,   60,   60,   60,   60,
   60,   60,   60,   42,    0,    0,   42,    0,   42,    0,
    0,    0,    0,   42,   42,   42,    0,   42,   42,   42,
   53,   42,    0,   53,    0,   53,    0,    0,    0,    0,
   53,   53,   53,    0,   53,   53,   53,   42,   53,    0,
    0,   50,    0,    0,   50,    0,   50,    0,    0,    0,
    0,   50,   50,   50,   53,   50,   50,   50,   51,   50,
    0,   51,    0,   51,    0,    0,    0,    0,   51,   51,
   51,    0,   51,   51,   51,   50,   51,    0,    0,   22,
    0,    0,   22,   22,   22,    0,    0,    0,    0,   22,
   22,   22,   51,   22,   22,   22,   18,   22,    0,   18,
   61,   61,   61,   61,   61,   61,   18,   18,   18,    0,
   18,   18,   18,   20,   18,    0,   20,   60,   60,   60,
   60,   60,   60,   20,   20,   20,    0,   20,   20,   20,
   17,   20,    0,   17,    0,    0,    0,    0,    0,    0,
   17,   17,   17,    0,   17,   17,   17,    0,   17,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   40,   40,   37,    8,   37,   40,   19,   40,    6,    7,
   15,  258,  257,  257,    3,  263,  261,  261,  266,   24,
  265,   26,  270,  259,   29,   30,   31,  272,  272,  278,
  279,  257,  277,  277,    8,  261,   71,  261,   71,  261,
   45,   54,   55,   32,  262,   58,  272,  257,   88,   88,
   24,  277,   26,  261,    8,   29,   30,   31,   93,  261,
   93,  260,  280,  281,  282,  283,  292,  293,  264,   67,
   24,   45,   26,  277,    8,   29,   30,   31,  262,  277,
  120,  120,  116,  117,  119,  257,  119,  262,  291,  261,
   24,   45,   26,  127,  257,   29,   30,   31,  261,  262,
  272,   24,  262,   26,  265,  277,   29,   30,   31,  272,
  262,   45,  262,  262,  277,  267,  268,  269,  262,  262,
  292,  293,   45,  257,  261,    0,  260,  264,  262,  292,
  293,  265,  262,  267,  268,  269,  274,  271,  272,  273,
  260,  275,  276,  262,  278,  279,  280,  281,  282,  283,
  110,  285,  286,  287,  288,  289,  290,  291,  257,  109,
   20,  260,  265,  262,  102,   -1,  265,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,  280,  281,  282,
  283,  280,  281,  282,  283,   -1,  285,  286,  287,  288,
  289,  290,  291,  257,   -1,   -1,  260,  276,  262,  278,
  279,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,  280,  281,  282,  283,  280,  281,  282,  283,
   -1,  285,  286,  287,  288,  289,  290,  291,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,  267,  268,  269,
   -1,  280,  281,  282,  283,   -1,  285,  286,  287,  288,
  289,  290,  291,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,  257,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,  267,  268,  269,   -1,  271,  272,  273,   -1,
  275,  285,  286,  287,  288,  289,  290,  291,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,  285,  286,  287,
  288,  289,  290,   -1,   -1,   -1,  285,  286,  287,  288,
  289,  290,  291,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,  285,  286,  287,  288,  289,  290,   -1,   -1,
   -1,  285,  286,  287,  288,  289,  290,  291,  257,   -1,
   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,  285,  286,  287,
  288,  289,  290,   -1,   -1,   -1,  285,  286,  287,  288,
  289,  290,  291,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,  273,
  257,  275,   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,
  267,  268,  269,   -1,  271,  272,  273,  291,  275,   -1,
   -1,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,
   -1,  267,  268,  269,  291,  271,  272,  273,  257,  275,
   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,  267,  268,
  269,   -1,  271,  272,  273,  291,  275,   -1,   -1,  257,
   -1,   -1,  260,  261,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,  291,  271,  272,  273,  257,  275,   -1,  260,
  285,  286,  287,  288,  289,  290,  267,  268,  269,   -1,
  271,  272,  273,  257,  275,   -1,  260,  285,  286,  287,
  288,  289,  290,  267,  268,  269,   -1,  271,  272,  273,
  257,  275,   -1,  260,   -1,   -1,   -1,   -1,   -1,   -1,
  267,  268,  269,   -1,  271,  272,  273,   -1,  275,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=293;
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
"IMPRIMA","SE","SENAO","ENQUANTO","OP_ATRIBUICAO","NUMERICO","OP_INCREMENTO",
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
"comandos : cmd_if comandos",
"comandos : cmd_while comandos",
"comandos :",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco SENAO bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES",
"cmd_while : ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES bloco",
"cmd_while : ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES",
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

//#line 175 "inicioCT.y"

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
//#line 469 "Parser.java"
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
//#line 71 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 73 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 74 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 75 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 76 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 78 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval + "\n"; }
break;
case 7:
//#line 80 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}"; }
break;
case 8:
//#line 82 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 10:
//#line 85 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 11:
//#line 86 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 87 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 88 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 89 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 90 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 16:
//#line 92 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n "; }
break;
case 17:
//#line 93 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 18:
//#line 94 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n  "; }
break;
case 19:
//#line 96 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 20:
//#line 97 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n  "; }
break;
case 21:
//#line 99 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 22:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 23:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 24:
//#line 104 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 25:
//#line 105 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 26:
//#line 106 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 27:
//#line 108 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 28:
//#line 109 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 29:
//#line 111 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 30:
//#line 112 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 31:
//#line 114 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 32:
//#line 115 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 33:
//#line 117 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 34:
//#line 118 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 35:
//#line 120 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 36:
//#line 121 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 37:
//#line 122 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 38:
//#line 124 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 39:
//#line 125 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 40:
//#line 127 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 41:
//#line 128 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 42:
//#line 130 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 43:
//#line 131 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 44:
//#line 133 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 45:
//#line 134 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 46:
//#line 135 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 47:
//#line 136 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 48:
//#line 137 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 49:
//#line 139 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 50:
//#line 140 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 51:
//#line 141 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 52:
//#line 142 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 53:
//#line 143 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 54:
//#line 145 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 55:
//#line 146 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 56:
//#line 147 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 57:
//#line 148 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 149 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 59:
//#line 150 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 60:
//#line 152 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 61:
//#line 153 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 154 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 155 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 156 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 65:
//#line 157 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 66:
//#line 159 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 67:
//#line 160 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 68:
//#line 161 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 69:
//#line 162 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 70:
//#line 164 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 71:
//#line 165 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 72:
//#line 166 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 73:
//#line 167 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 74:
//#line 168 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 75:
//#line 169 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 76:
//#line 171 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 77:
//#line 172 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 78:
//#line 173 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 930 "Parser.java"
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
