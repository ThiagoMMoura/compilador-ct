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
public final static short CASO=275;
public final static short OPCAO=276;
public final static short FIM_OPCAO=277;
public final static short ENQUANTO=278;
public final static short OP_ATRIBUICAO=279;
public final static short NUMERICO=280;
public final static short OP_INCREMENTO=281;
public final static short OP_DECREMENTO=282;
public final static short OP_SOMA=283;
public final static short OP_SUB=284;
public final static short OP_DIV=285;
public final static short OP_MULT=286;
public final static short OP_MOD=287;
public final static short OP_IGUALDADE=288;
public final static short OP_DIFERENTE=289;
public final static short OP_MAIOR=290;
public final static short OP_MENOR=291;
public final static short OP_MAIOR_IGUAL=292;
public final static short OP_MENOR_IGUAL=293;
public final static short VIRGULA=294;
public final static short DOIS_PONTOS=295;
public final static short STRING=296;
public final static short CHAR=297;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    4,   12,    5,    6,    6,
    6,    6,    6,    6,    6,    6,   24,   24,   24,   25,
   26,   26,   26,   26,   27,   27,    7,   13,   13,   14,
   14,   14,    9,    9,   23,   23,   10,   10,   11,   11,
   19,   19,   19,   20,   20,   21,   21,   15,   15,   16,
   16,   16,   16,   16,   17,   17,   17,   17,   17,   22,
   22,   22,   22,   22,   22,   18,   18,   18,   18,   18,
   18,   28,   28,   28,    2,    2,    2,    2,    1,    1,
    1,    1,    1,    1,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    0,    2,    3,    2,    2,    2,
    3,    2,    2,    2,    2,    0,    7,    5,    4,    7,
    8,    7,    6,    5,    5,    4,    2,    1,    2,    2,
    3,    3,    2,    3,    2,    2,    2,    3,    1,    3,
    3,    1,    1,    2,    3,    2,    3,    1,    1,    3,
    3,    5,    5,    3,    3,    3,    5,    5,    3,    1,
    1,    1,    1,    1,    3,    1,    1,    1,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    6,    8,
   85,   86,   87,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   42,   43,    0,
    0,    0,    0,    0,    0,    0,   27,    0,    0,   35,
   29,    0,   60,   69,   68,    0,    0,   67,   49,    0,
   62,   63,    0,   64,   36,    0,    0,    0,    7,    9,
    0,    0,    0,   10,   12,   13,   14,   15,   33,    0,
    0,   37,    0,    0,    0,   30,    0,    0,    0,    0,
    0,    0,    0,    0,   11,   79,   80,   81,   82,   83,
   84,    0,   75,   76,   77,   78,    0,    0,   73,   72,
   74,    0,    0,   41,   45,   47,   34,    4,   38,    0,
    0,    0,   32,   31,   71,   70,    0,    0,   65,   55,
    0,   50,    0,    0,    0,    0,   40,    0,    0,    0,
    0,   25,   53,    0,   58,    0,    0,    0,    0,   17,
    0,   20,    0,    0,    0,    0,    0,    0,   23,    0,
   21,
};
final static short yydgoto[] = {                          4,
   92,   97,    5,    6,    7,   24,   25,   15,   35,   40,
   73,    9,   46,   41,   74,   48,   49,   50,   27,   51,
   52,   53,   54,   31,   32,  139,   33,  102,
};
final static short yysindex[] = {                      -196,
 -254, -247, -150,    0,    0, -196, -196,  387,    0,    0,
    0,    0,    0, -223, -211,    0,    0, -199, -175, -189,
 -182, -176, -154, -210,  387, -198,  387,    0,    0,  387,
  387,  387,  387,   45, -254, -201,    0, -184, -166,    0,
    0, -141,    0,    0,    0, -261,  387,    0,    0,   62,
    0,    0,  165,    0,    0, -175, -186, -175,    0,    0,
 -175, -172, -170,    0,    0,    0,    0,    0,    0, -138,
 -196,    0, -137, -165, -157,    0, -133,   -9, -134, -128,
 -125, -124,   62,  -47,    0,    0,    0,    0,    0,    0,
    0, -175,    0,    0,    0,    0, -157, -121,    0,    0,
    0, -120, -119,    0,    0,    0,    0,    0,    0, -175,
 -125,  -47,    0,    0,    0,    0,  165,   62,    0,    0,
   62,    0,  165, -254, -113, -254,    0, -157, -175, -126,
 -127,    0,    0,  165,    0,   62, -254, -186, -100,    0,
 -131,    0,  374,  387, -111, -108, -127,  -90,    0, -127,
    0,
};
final static short yyrindex[] = {                       173,
    0,    0,    0,    0,    0,  173,  173,  -86,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -81,    0,    0,
    0,    0,    0,    0, -248,    0, -248,    0,    0, -248,
 -248, -248, -248,    0,    0, -110,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -43, -248,    0,    0,  223,
    0,    0,  185,    0,    0,    0,    0,    0,    0,    0,
    0,   -5,   33,    0,    0,    0,    0,    0,    0,    0,
  173,    0,    0,  -87,    0,    0,    0,    0,  100,  138,
  176,    0,    0,  292,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   71,  247,    0,    0,
  271,    0,  109,  319,    0,  338,    0,    0,    0,  357,
    0,    0,    0,  147,    0,  295,    0,    0,    0,    0,
    0,    0,  -99,  -99,    0,    0,  -83,    0,    0,  -80,
    0,
};
final static short yygindex[] = {                         0,
   64,   66,   -3,    0,    0,  -17,   42,    0,    0,  169,
   83,  -34,   -8,    0,    8,    5,  -40,  -36,    0,    1,
   10,   12,   28,    0,    0,  -49,    0,   57,
};
final static int YYTABLESIZE=665;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         26,
   71,   82,   16,   17,    8,   83,   37,   60,   28,   64,
   10,   16,   65,   66,   67,   68,   26,   29,   26,   62,
   63,   26,   26,   26,   26,   28,   47,   28,   16,   85,
   28,   28,   28,   28,   29,   30,   29,   34,   26,   29,
   29,   29,   29,   77,   14,   36,   81,   28,  101,   59,
   78,  120,   30,   84,   30,  121,   29,   30,   30,   30,
   30,   38,   39,   98,   39,  103,    1,  108,  104,    2,
   36,   38,   18,    3,   30,   70,   42,   72,   56,  111,
   61,   18,   62,   63,   57,   42,  112,   20,  135,  130,
   18,  132,  136,   99,   75,   43,   20,  149,   76,   18,
  151,  122,  140,   75,   43,   20,   58,  105,  123,  106,
  100,   44,   45,   43,   20,   18,   11,   12,   13,   42,
   44,   45,   43,  107,  109,  145,  146,  115,  110,  101,
   20,  113,  133,  116,   26,   26,  117,  118,   43,  134,
  124,  125,  126,   28,   28,  131,   28,  137,  138,   28,
   28,   28,   29,   29,   79,   80,   28,   28,   28,  142,
   28,   28,   28,  143,   28,  147,   28,   28,  148,  150,
   30,   30,    5,   16,   39,   28,   24,   16,   28,   22,
   28,  129,  128,   28,   28,   28,   28,   28,   55,   28,
   28,   28,  127,   28,  141,   28,   28,   28,    0,   28,
   28,   28,   28,   28,   28,    0,   28,   28,   28,   28,
   28,   28,   28,   61,  119,    0,   61,    0,   61,    0,
    0,   61,    0,   61,   61,   61,    0,   61,   61,   61,
    0,   61,    0,   61,   61,   93,   94,   95,   96,   61,
   61,   61,   61,    0,   61,   61,   61,   61,   61,   61,
   61,   44,    0,    0,   44,  114,   44,    0,    0,   44,
    0,   44,   44,   44,    0,   44,   44,   44,    0,   44,
    0,   44,   44,   93,   94,   95,   96,   44,   44,   44,
   44,    0,   44,   44,   44,   44,   44,   44,   44,   46,
    0,    0,   46,    0,   46,    0,    0,   46,    0,   46,
   46,   46,    0,   46,   46,   46,   69,   46,    0,   46,
   46,   11,   12,   13,    0,   46,   46,   46,   46,    0,
   46,   46,   46,   46,   46,   46,   46,   54,    0,    0,
   54,    0,   54,    0,    0,   54,    0,   54,   54,   54,
    0,   54,   54,   54,    0,   54,    0,   54,   54,   86,
   87,   88,   89,   90,   91,    0,    0,    0,   54,   54,
   54,   54,   54,   54,   54,   51,    0,    0,   51,    0,
   51,    0,    0,   51,    0,   51,   51,   51,    0,   51,
   51,   51,    0,   51,    0,   51,   51,   69,   69,   69,
   69,   69,   69,    0,    0,    0,   51,   51,   51,   51,
   51,   51,   51,   52,    0,    0,   52,    0,   52,    0,
    0,   52,    0,   52,   52,   52,    0,   52,   52,   52,
    0,   52,    0,   52,   52,   68,   68,   68,   68,   68,
   68,    0,    0,    0,   52,   52,   52,   52,   52,   52,
   52,   66,    0,    0,   66,    0,   66,   93,   94,   95,
   96,   66,   66,   66,    0,   66,   66,   66,    0,   66,
    0,   66,   66,   67,   67,   67,   67,   67,   67,    0,
    0,    0,   66,   66,   66,   66,   66,   66,   66,   48,
    0,    0,   48,    0,   48,    0,    0,    0,    0,   48,
   48,   48,    0,   48,   48,   48,    0,   48,    0,   48,
   48,    0,    0,   59,    0,    0,   59,    0,   59,    0,
    0,    0,    0,   59,   59,   59,   48,   59,   59,   59,
    0,   59,    0,   59,   59,    0,    0,   56,    0,    0,
   56,    0,   56,    0,    0,    0,    0,   56,   56,   56,
   59,   56,   56,   56,    0,   56,    0,   56,   56,    0,
    0,   57,    0,    0,   57,    0,   57,    0,    0,    0,
    0,   57,   57,   57,   56,   57,   57,   57,    0,   57,
    0,   57,   57,    0,    0,   19,    0,    0,   19,   66,
   66,   66,   66,   66,   66,   19,   19,   19,   57,   19,
   19,   19,    0,   19,   26,   19,   19,   26,    0,    0,
    0,    0,    0,    0,   26,   26,   26,    0,   26,   26,
   26,    0,   26,   18,   26,   26,   18,    0,    0,    0,
    0,    0,    0,   18,   18,   18,    0,   18,   18,   18,
   18,   18,  144,   18,   18,    0,    0,    0,    0,    0,
   11,   12,   13,   18,   19,   20,   21,    0,   22,    0,
    0,   23,    0,   11,   12,   13,    0,   19,   20,   21,
    0,   22,    0,    0,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          8,
   35,   42,    6,    7,  259,   42,   15,   25,    8,   27,
  258,  260,   30,   31,   32,   33,   25,    8,   27,  281,
  282,   30,   31,   32,   33,   25,   19,   27,  277,   47,
   30,   31,   32,   33,   25,    8,   27,  261,   47,   30,
   31,   32,   33,   39,    3,  257,   42,   47,   57,  260,
   39,   92,   25,   42,   27,   92,   47,   30,   31,   32,
   33,  261,  264,   56,  264,   58,  263,   71,   61,  266,
  257,  261,  257,  270,   47,   34,  261,  262,  261,   75,
  279,  257,  281,  282,  261,  261,   75,  272,  129,  124,
  257,  126,  129,  280,  261,  280,  272,  147,  265,  257,
  150,   97,  137,  261,  280,  272,  261,  280,   97,  280,
  297,  296,  297,  280,  272,  257,  267,  268,  269,  261,
  296,  297,  280,  262,  262,  143,  144,  262,  294,  138,
  272,  265,  128,  262,  143,  144,  262,  262,  280,  128,
  262,  262,  262,  143,  144,  259,  257,  274,  276,  260,
  261,  262,  143,  144,  296,  297,  267,  268,  269,  260,
  271,  272,  273,  295,  275,  277,  277,  278,  277,  260,
  143,  144,    0,  260,  262,  257,  260,  277,  260,  260,
  262,  118,  117,  265,  295,  267,  268,  269,   20,  271,
  272,  273,  110,  275,  138,  277,  278,  279,   -1,  281,
  282,  283,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,  293,  294,  257,  262,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  283,  284,  285,  286,  283,
  284,  285,  286,   -1,  288,  289,  290,  291,  292,  293,
  294,  257,   -1,   -1,  260,  265,  262,   -1,   -1,  265,
   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,  275,
   -1,  277,  278,  283,  284,  285,  286,  283,  284,  285,
  286,   -1,  288,  289,  290,  291,  292,  293,  294,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
  268,  269,   -1,  271,  272,  273,  262,  275,   -1,  277,
  278,  267,  268,  269,   -1,  283,  284,  285,  286,   -1,
  288,  289,  290,  291,  292,  293,  294,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,  269,
   -1,  271,  272,  273,   -1,  275,   -1,  277,  278,  288,
  289,  290,  291,  292,  293,   -1,   -1,   -1,  288,  289,
  290,  291,  292,  293,  294,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  277,  278,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,  288,  289,  290,  291,
  292,  293,  294,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  288,  289,  290,  291,  292,
  293,   -1,   -1,   -1,  288,  289,  290,  291,  292,  293,
  294,  257,   -1,   -1,  260,   -1,  262,  283,  284,  285,
  286,  267,  268,  269,   -1,  271,  272,  273,   -1,  275,
   -1,  277,  278,  288,  289,  290,  291,  292,  293,   -1,
   -1,   -1,  288,  289,  290,  291,  292,  293,  294,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,  277,
  278,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,  294,  271,  272,  273,
   -1,  275,   -1,  277,  278,   -1,   -1,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,   -1,   -1,  267,  268,  269,
  294,  271,  272,  273,   -1,  275,   -1,  277,  278,   -1,
   -1,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,
   -1,  267,  268,  269,  294,  271,  272,  273,   -1,  275,
   -1,  277,  278,   -1,   -1,  257,   -1,   -1,  260,  288,
  289,  290,  291,  292,  293,  267,  268,  269,  294,  271,
  272,  273,   -1,  275,  257,  277,  278,  260,   -1,   -1,
   -1,   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,
  273,   -1,  275,  257,  277,  278,  260,   -1,   -1,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,  273,
  257,  275,  259,  277,  278,   -1,   -1,   -1,   -1,   -1,
  267,  268,  269,  257,  271,  272,  273,   -1,  275,   -1,
   -1,  278,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,   -1,  278,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=297;
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
"IMPRIMA","SE","SENAO","CASO","OPCAO","FIM_OPCAO","ENQUANTO","OP_ATRIBUICAO",
"NUMERICO","OP_INCREMENTO","OP_DECREMENTO","OP_SOMA","OP_SUB","OP_DIV",
"OP_MULT","OP_MOD","OP_IGUALDADE","OP_DIFERENTE","OP_MAIOR","OP_MENOR",
"OP_MAIOR_IGUAL","OP_MENOR_IGUAL","VIRGULA","DOIS_PONTOS","STRING","CHAR",
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
"comandos : cmd_switch comandos",
"comandos : cmd_while comandos",
"comandos :",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco SENAO bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES",
"cmd_switch : CASO ABRE_PARENTESES operandos_switch FECHA_PARENTESES ABRE_CHAVES cmd_case FECHA_CHAVES",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS ABRE_CHAVES comandos FIM_OPCAO FECHA_CHAVES cmd_case",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS ABRE_CHAVES comandos FIM_OPCAO FECHA_CHAVES",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS comandos FIM_OPCAO cmd_case",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS comandos FIM_OPCAO",
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
"operandos_switch : CHAR",
"operandos_switch : NUMERICO",
"operandos_switch : variavel",
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

//#line 194 "inicioCT.y"

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
//#line 517 "Parser.java"
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
//#line 78 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 80 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 81 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 82 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 83 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 85 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval + "\n"; }
break;
case 7:
//#line 87 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}"; }
break;
case 8:
//#line 89 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 91 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 10:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 11:
//#line 93 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 97 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 16:
//#line 98 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 17:
//#line 100 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n "; }
break;
case 18:
//#line 101 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 19:
//#line 102 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n  "; }
break;
case 20:
//#line 104 "inicioCT.y"
{ yyval.sval = "switch(" + val_peek(4).sval + "){\n  " + val_peek(1).sval + "\n }\n "; }
break;
case 21:
//#line 106 "inicioCT.y"
{ yyval.sval = "case " + val_peek(6).sval + ":{\n   " + val_peek(3).sval + "  break;\n}" + val_peek(0).sval; }
break;
case 22:
//#line 107 "inicioCT.y"
{ yyval.sval = "case " + val_peek(5).sval + ":{\n   " + val_peek(2).sval + "  break;\n}"; }
break;
case 23:
//#line 108 "inicioCT.y"
{ yyval.sval = "case " + val_peek(4).sval + ":\n   " + val_peek(2).sval + "  break;\n  " + val_peek(0).sval; }
break;
case 24:
//#line 109 "inicioCT.y"
{ yyval.sval = "case " + val_peek(3).sval + ":\n   " + val_peek(1).sval + "  break;"; }
break;
case 25:
//#line 111 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 26:
//#line 112 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n  "; }
break;
case 27:
//#line 114 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 28:
//#line 116 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 29:
//#line 117 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 30:
//#line 119 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 31:
//#line 120 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 32:
//#line 121 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 33:
//#line 123 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 34:
//#line 124 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 35:
//#line 126 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 36:
//#line 127 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 37:
//#line 129 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 38:
//#line 130 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 39:
//#line 132 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 133 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 41:
//#line 135 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 42:
//#line 136 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 43:
//#line 137 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 44:
//#line 139 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 45:
//#line 140 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 46:
//#line 142 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 47:
//#line 143 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 48:
//#line 145 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 146 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 148 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 51:
//#line 149 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 52:
//#line 150 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 53:
//#line 151 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 54:
//#line 152 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 55:
//#line 154 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 56:
//#line 155 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 57:
//#line 156 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 58:
//#line 157 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 59:
//#line 158 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 60:
//#line 160 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 61:
//#line 161 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 162 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 163 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 164 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 65:
//#line 165 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 66:
//#line 167 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 67:
//#line 168 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 68:
//#line 169 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 69:
//#line 170 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 70:
//#line 171 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 71:
//#line 172 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 72:
//#line 174 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 73:
//#line 175 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 74:
//#line 176 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 178 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 76:
//#line 179 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 77:
//#line 180 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 78:
//#line 181 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 79:
//#line 183 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 80:
//#line 184 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 81:
//#line 185 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 82:
//#line 186 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 83:
//#line 187 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 84:
//#line 188 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 85:
//#line 190 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 86:
//#line 191 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 87:
//#line 192 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 1014 "Parser.java"
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
