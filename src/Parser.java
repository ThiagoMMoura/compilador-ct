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
public final static short FACA=279;
public final static short ATE=280;
public final static short OP_ATRIBUICAO=281;
public final static short NUMERICO=282;
public final static short OP_INCREMENTO=283;
public final static short OP_DECREMENTO=284;
public final static short OP_SOMA=285;
public final static short OP_SUB=286;
public final static short OP_DIV=287;
public final static short OP_MULT=288;
public final static short OP_MOD=289;
public final static short OP_IGUALDADE=290;
public final static short OP_DIFERENTE=291;
public final static short OP_MAIOR=292;
public final static short OP_MENOR=293;
public final static short OP_MAIOR_IGUAL=294;
public final static short OP_MENOR_IGUAL=295;
public final static short VIRGULA=296;
public final static short DOIS_PONTOS=297;
public final static short STRING=298;
public final static short CHAR=299;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    4,   12,    5,    6,    6,
    6,    6,    6,    6,    6,    6,    6,   24,   24,   24,
   25,   26,   26,   26,   26,   27,   27,   28,    7,   13,
   13,   14,   14,   14,    9,    9,   23,   23,   10,   10,
   11,   11,   19,   19,   19,   20,   20,   21,   21,   15,
   15,   16,   16,   16,   16,   16,   17,   17,   17,   17,
   17,   22,   22,   22,   22,   22,   22,   18,   18,   18,
   18,   18,   18,   29,   29,   29,    2,    2,    2,    2,
    1,    1,    1,    1,    1,    1,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    0,    2,    3,    2,    2,    2,
    3,    2,    2,    2,    2,    2,    0,    7,    5,    4,
    7,    8,    7,    6,    5,    5,    4,    8,    2,    1,
    2,    2,    3,    3,    2,    3,    2,    2,    2,    3,
    1,    3,    3,    1,    1,    2,    3,    2,    3,    1,
    1,    3,    3,    5,    5,    3,    3,    3,    5,    5,
    3,    1,    1,    1,    1,    1,    3,    1,    1,    1,
    1,    3,    3,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    1,    0,    0,    0,    6,    8,
   87,   88,   89,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   44,   45,
    0,    0,    0,    0,    0,    0,    0,    0,   29,    0,
    0,   37,   31,    0,   62,   71,   70,    0,    0,   69,
   51,    0,   64,   65,    0,   66,   38,    0,    0,    0,
    0,    7,    9,    0,    0,    0,   10,   12,   13,   14,
   15,   16,   35,    0,    0,   39,    0,    0,    0,   32,
    0,    0,    0,    0,    0,    0,    0,    0,   11,   81,
   82,   83,   84,   85,   86,    0,   77,   78,   79,   80,
    0,    0,   75,   74,   76,    0,    0,    0,   43,   47,
   49,   36,    4,   40,    0,    0,    0,   34,   33,   73,
   72,    0,    0,   67,   57,    0,   52,    0,    0,    0,
    0,    0,   42,    0,    0,    0,    0,   26,    0,   55,
    0,   60,    0,    0,    0,    0,    0,   18,    0,   21,
    0,    0,   28,    0,    0,    0,    0,    0,   24,    0,
   22,
};
final static short yydgoto[] = {                          4,
   96,  101,    5,    6,    7,   25,   26,   15,   37,   42,
   77,    9,   48,   43,   78,   50,   51,   52,   28,   53,
   54,   55,   56,   32,   33,  146,   34,   35,  106,
};
final static short yysindex[] = {                      -260,
 -258, -244,  -90,    0,    0, -260, -260,  447,    0,    0,
    0,    0,    0, -233, -227,    0,    0, -252, -158, -222,
 -217, -206, -204, -200, -199,  447, -165,  447,    0,    0,
  447,  447,  447,  447,  447, -147, -258, -196,    0, -149,
 -130,    0,    0, -129,    0,    0,    0, -191,  447,    0,
    0,   97,    0,    0,   22,    0,    0, -158, -197, -158,
  447,    0,    0, -158, -212, -209,    0,    0,    0,    0,
    0,    0,    0, -186, -260,    0, -176, -185,  -77,    0,
 -168,  -17, -162, -137, -133, -132,   97, -124,    0,    0,
    0,    0,    0,    0,    0, -158,    0,    0,    0,    0,
  -77, -128,    0,    0,    0, -126, -123, -113,    0,    0,
    0,    0,    0,    0, -158, -133, -124,    0,    0,    0,
    0,   22,   97,    0,    0,   97,    0,   22, -258, -111,
 -258, -122,    0,  -77, -158, -120, -125,    0, -105,    0,
   22,    0,   97, -258, -197, -100, -158,    0, -109,    0,
  -97,  434,    0,  447, -110, -104, -125,  -88,    0, -125,
    0,
};
final static short yyrindex[] = {                       190,
    0,    0,    0,    0,    0,  190,  190,  -66,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -53,    0,    0,
    0,    0,    0,    0,    0, -256,    0, -256,    0,    0,
 -256, -256, -256, -256, -256,    0,    0,  -86,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -13, -256,    0,
    0,  267,    0,    0,  227,    0,    0,    0,    0,    0,
  -66,    0,    0,    0,   27,   67,    0,    0,    0,    0,
    0,    0,    0,    0,  190,    0,    0,  -65,    0,    0,
    0,    0,  137,  177,  217,    0,    0,  342,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  107,  293,    0,    0,  319,    0,  147,  371,    0,
  394,    0,    0,    0,    0,  417,    0,    0,    0,    0,
  187,    0,  345,    0,    0,    0,    0,    0,    0,    0,
    0,  -81,    0,  -81,    0,    0,  -61,    0,    0,  -59,
    0,
};
final static short yygindex[] = {                         0,
   79,   81,   26,    0,    0,   56,   47,    0,    0,  186,
   98,  -35,   -8,    0,   -2,  -36,  -29,  -25,    0,    3,
   14,   25,   46,    0,    0, -144,    0,    0,   72,
};
final static int YYTABLESIZE=726;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         27,
    8,   75,    1,   17,   81,    2,   39,   85,   40,    3,
   29,   41,  159,   10,   86,  161,   49,   27,   87,   27,
   17,   30,   27,   27,   27,   27,   27,   36,   29,   38,
   29,   16,   17,   29,   29,   29,   29,   29,   40,   30,
   27,   30,  116,   58,   30,   30,   30,   30,   30,   14,
  105,   29,   27,   31,   59,  102,   60,  107,   61,   38,
   62,  109,   30,   29,  127,   82,  125,   41,   88,  110,
  126,   31,  111,   31,   30,  112,   31,   31,   31,   31,
   31,   63,   74,   67,  103,  114,   68,   69,   70,   71,
   72,   65,   66,  136,   31,  138,  118,  140,   18,  120,
  113,  104,   44,  117,   89,  142,   31,   18,  148,  143,
  115,   44,   76,   20,   73,   64,  108,   65,   66,   11,
   12,   13,   20,   45,  121,  128,   18,   18,  122,  123,
   79,   44,   45,  129,   80,  130,  105,  124,  131,   46,
   47,   20,   20,   27,  151,   27,  132,  137,   46,   47,
  145,   45,   45,  144,   29,  147,   29,  139,  141,  150,
   97,   98,   99,  100,  153,   30,  157,   30,   83,   84,
   30,  160,  158,   30,   30,   30,   11,   12,   13,   18,
   30,   30,   30,   79,   30,   30,   30,  152,   30,    5,
   30,   30,   30,   17,   20,   17,   41,   31,   25,   31,
   23,  135,  134,   30,   45,   57,   30,  155,   30,  156,
   30,   30,  133,   30,   30,   30,  149,   30,   30,   30,
    0,   30,    0,   30,   30,   30,    0,   30,    0,   30,
   30,   30,   30,   30,   30,    0,   30,   30,   30,   30,
   30,   30,   30,   63,    0,    0,   63,  119,   63,    0,
    0,   63,    0,   63,   63,   63,    0,   63,   63,   63,
    0,   63,    0,   63,   63,   63,    0,   97,   98,   99,
  100,   63,   63,   63,   63,    0,   63,   63,   63,   63,
   63,   63,   63,   46,    0,    0,   46,    0,   46,    0,
    0,   46,    0,   46,   46,   46,    0,   46,   46,   46,
    0,   46,    0,   46,   46,   46,   97,   98,   99,  100,
    0,   46,   46,   46,   46,    0,   46,   46,   46,   46,
   46,   46,   46,   48,    0,    0,   48,    0,   48,    0,
    0,   48,    0,   48,   48,   48,    0,   48,   48,   48,
    0,   48,    0,   48,   48,   48,    0,    0,    0,    0,
    0,   48,   48,   48,   48,    0,   48,   48,   48,   48,
   48,   48,   48,   56,    0,    0,   56,    0,   56,    0,
    0,   56,    0,   56,   56,   56,    0,   56,   56,   56,
    0,   56,    0,   56,   56,   56,   90,   91,   92,   93,
   94,   95,    0,    0,    0,    0,   56,   56,   56,   56,
   56,   56,   56,   53,    0,    0,   53,    0,   53,    0,
    0,   53,    0,   53,   53,   53,    0,   53,   53,   53,
    0,   53,    0,   53,   53,   53,   71,   71,   71,   71,
   71,   71,    0,    0,    0,    0,   53,   53,   53,   53,
   53,   53,   53,   54,    0,    0,   54,    0,   54,    0,
    0,   54,    0,   54,   54,   54,    0,   54,   54,   54,
    0,   54,    0,   54,   54,   54,   70,   70,   70,   70,
   70,   70,    0,    0,    0,    0,   54,   54,   54,   54,
   54,   54,   54,   68,    0,    0,   68,    0,   68,    0,
    0,    0,    0,   68,   68,   68,    0,   68,   68,   68,
    0,   68,    0,   68,   68,   68,   69,   69,   69,   69,
   69,   69,    0,    0,    0,    0,   68,   68,   68,   68,
   68,   68,   68,   50,    0,    0,   50,    0,   50,    0,
    0,    0,    0,   50,   50,   50,    0,   50,   50,   50,
    0,   50,    0,   50,   50,   50,    0,    0,    0,   61,
    0,    0,   61,    0,   61,    0,    0,    0,    0,   61,
   61,   61,   50,   61,   61,   61,    0,   61,    0,   61,
   61,   61,    0,    0,    0,   58,    0,    0,   58,    0,
   58,    0,    0,    0,    0,   58,   58,   58,   61,   58,
   58,   58,    0,   58,    0,   58,   58,   58,    0,    0,
    0,   59,    0,    0,   59,    0,   59,    0,    0,    0,
    0,   59,   59,   59,   58,   59,   59,   59,    0,   59,
    0,   59,   59,   59,    0,    0,    0,   20,    0,    0,
   20,   68,   68,   68,   68,   68,   68,   20,   20,   20,
   59,   20,   20,   20,    0,   20,    0,   20,   20,   20,
   27,    0,    0,   27,    0,    0,    0,    0,    0,    0,
   27,   27,   27,    0,   27,   27,   27,    0,   27,    0,
   27,   27,   27,   19,    0,    0,   19,    0,    0,    0,
    0,    0,    0,   19,   19,   19,    0,   19,   19,   19,
   18,   19,  154,   19,   19,   19,    0,    0,    0,    0,
   11,   12,   13,   18,   19,   20,   21,    0,   22,    0,
    0,   23,   24,   11,   12,   13,    0,   19,   20,   21,
    0,   22,    0,    0,   23,   24,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          8,
  259,   37,  263,  260,   41,  266,   15,   44,  261,  270,
    8,  264,  157,  258,   44,  160,   19,   26,   44,   28,
  277,    8,   31,   32,   33,   34,   35,  261,   26,  257,
   28,    6,    7,   31,   32,   33,   34,   35,  261,   26,
   49,   28,   79,  261,   31,   32,   33,   34,   35,    3,
   59,   49,   61,    8,  261,   58,  261,   60,  259,  257,
  260,   64,   49,   61,  101,   41,   96,  264,   44,  282,
   96,   26,  282,   28,   61,  262,   31,   32,   33,   34,
   35,   26,   36,   28,  282,  262,   31,   32,   33,   34,
   35,  283,  284,  129,   49,  131,  265,  134,  257,  262,
   75,  299,  261,   79,   49,  135,   61,  257,  144,  135,
  296,  261,  262,  272,  262,  281,   61,  283,  284,  267,
  268,  269,  272,  282,  262,  101,  257,  257,  262,  262,
  261,  261,  282,  262,  265,  262,  145,  262,  262,  298,
  299,  272,  272,  152,  147,  154,  260,  259,  298,  299,
  276,  282,  282,  274,  152,  261,  154,  280,  134,  260,
  285,  286,  287,  288,  262,  152,  277,  154,  298,  299,
  257,  260,  277,  260,  261,  262,  267,  268,  269,  257,
  267,  268,  269,  261,  271,  272,  273,  297,  275,    0,
  277,  278,  279,  260,  272,  277,  262,  152,  260,  154,
  260,  123,  122,  257,  282,   20,  260,  152,  262,  154,
  297,  265,  115,  267,  268,  269,  145,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,   -1,  281,   -1,  283,
  284,  285,  286,  287,  288,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,  265,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,   -1,  285,  286,  287,
  288,  285,  286,  287,  288,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,  285,  286,  287,  288,
   -1,  285,  286,  287,  288,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,   -1,   -1,   -1,   -1,
   -1,  285,  286,  287,  288,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,  290,  291,  292,  293,
  294,  295,   -1,   -1,   -1,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,  290,  291,  292,  293,
  294,  295,   -1,   -1,   -1,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,  290,  291,  292,  293,
  294,  295,   -1,   -1,   -1,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,  290,  291,  292,  293,
  294,  295,   -1,   -1,   -1,   -1,  290,  291,  292,  293,
  294,  295,  296,  257,   -1,   -1,  260,   -1,  262,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,   -1,   -1,   -1,  257,
   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,  296,  271,  272,  273,   -1,  275,   -1,  277,
  278,  279,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,
  262,   -1,   -1,   -1,   -1,  267,  268,  269,  296,  271,
  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,   -1,
   -1,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,   -1,
   -1,  267,  268,  269,  296,  271,  272,  273,   -1,  275,
   -1,  277,  278,  279,   -1,   -1,   -1,  257,   -1,   -1,
  260,  290,  291,  292,  293,  294,  295,  267,  268,  269,
  296,  271,  272,  273,   -1,  275,   -1,  277,  278,  279,
  257,   -1,   -1,  260,   -1,   -1,   -1,   -1,   -1,   -1,
  267,  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,
  277,  278,  279,  257,   -1,   -1,  260,   -1,   -1,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,  271,  272,  273,
  257,  275,  259,  277,  278,  279,   -1,   -1,   -1,   -1,
  267,  268,  269,  257,  271,  272,  273,   -1,  275,   -1,
   -1,  278,  279,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,   -1,  278,  279,
};
}
final static short YYFINAL=4;
final static short YYMAXTOKEN=299;
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
"IMPRIMA","SE","SENAO","CASO","OPCAO","FIM_OPCAO","ENQUANTO","FACA","ATE",
"OP_ATRIBUICAO","NUMERICO","OP_INCREMENTO","OP_DECREMENTO","OP_SOMA","OP_SUB",
"OP_DIV","OP_MULT","OP_MOD","OP_IGUALDADE","OP_DIFERENTE","OP_MAIOR","OP_MENOR",
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
"comandos : cmd_do_while comandos",
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
"cmd_do_while : FACA ABRE_CHAVES comandos FECHA_CHAVES ATE ABRE_PARENTESES expressoes FECHA_PARENTESES",
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

//#line 200 "inicioCT.y"

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
//#line 536 "Parser.java"
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
//#line 81 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 83 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 84 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 85 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 86 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 6:
//#line 88 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval + "\n"; }
break;
case 7:
//#line 90 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}"; }
break;
case 8:
//#line 92 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 9:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 10:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 11:
//#line 96 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 97 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 98 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 14:
//#line 99 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 15:
//#line 100 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 16:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 102 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 18:
//#line 104 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n "; }
break;
case 19:
//#line 105 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 20:
//#line 106 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n  "; }
break;
case 21:
//#line 108 "inicioCT.y"
{ yyval.sval = "switch(" + val_peek(4).sval + "){\n  " + val_peek(1).sval + "\n }\n "; }
break;
case 22:
//#line 110 "inicioCT.y"
{ yyval.sval = "case " + val_peek(6).sval + ":{\n   " + val_peek(3).sval + "  break;\n}" + val_peek(0).sval; }
break;
case 23:
//#line 111 "inicioCT.y"
{ yyval.sval = "case " + val_peek(5).sval + ":{\n   " + val_peek(2).sval + "  break;\n}"; }
break;
case 24:
//#line 112 "inicioCT.y"
{ yyval.sval = "case " + val_peek(4).sval + ":\n   " + val_peek(2).sval + "  break;\n  " + val_peek(0).sval; }
break;
case 25:
//#line 113 "inicioCT.y"
{ yyval.sval = "case " + val_peek(3).sval + ":\n   " + val_peek(1).sval + "  break;"; }
break;
case 26:
//#line 115 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 27:
//#line 116 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n  "; }
break;
case 28:
//#line 118 "inicioCT.y"
{ yyval.sval = "do{\n  " + val_peek(5).sval + "}while(" + val_peek(1).sval + ");"; }
break;
case 29:
//#line 120 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 30:
//#line 122 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 31:
//#line 123 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 32:
//#line 125 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 33:
//#line 126 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 34:
//#line 127 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 35:
//#line 129 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 36:
//#line 130 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 37:
//#line 132 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 38:
//#line 133 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 39:
//#line 135 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 40:
//#line 136 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 41:
//#line 138 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 42:
//#line 139 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 43:
//#line 141 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 44:
//#line 142 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 45:
//#line 143 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 46:
//#line 145 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 47:
//#line 146 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 48:
//#line 148 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 49:
//#line 149 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 50:
//#line 151 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 152 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 52:
//#line 154 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 53:
//#line 155 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 54:
//#line 156 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 55:
//#line 157 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 56:
//#line 158 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 57:
//#line 160 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 58:
//#line 161 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 59:
//#line 162 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 60:
//#line 163 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 61:
//#line 164 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 62:
//#line 166 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 167 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 168 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 65:
//#line 169 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 66:
//#line 170 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 67:
//#line 171 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 68:
//#line 173 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 69:
//#line 174 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 70:
//#line 175 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 71:
//#line 176 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 72:
//#line 177 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 73:
//#line 178 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 74:
//#line 180 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 181 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 76:
//#line 182 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 184 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 78:
//#line 185 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 79:
//#line 186 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 80:
//#line 187 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 81:
//#line 189 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 82:
//#line 190 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 83:
//#line 191 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 84:
//#line 192 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 85:
//#line 193 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 86:
//#line 194 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 87:
//#line 196 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 88:
//#line 197 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 89:
//#line 198 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 1041 "Parser.java"
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
