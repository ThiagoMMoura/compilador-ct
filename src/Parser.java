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
public final static short IDENTIFICADOR_INVALIDO=258;
public final static short INCLUSAO_ARQUIVO=259;
public final static short ABRE_CHAVES=260;
public final static short FECHA_CHAVES=261;
public final static short ABRE_PARENTESES=262;
public final static short FECHA_PARENTESES=263;
public final static short FUNCAO_PRINCIPAL=264;
public final static short ABRE_COLCHETES=265;
public final static short FECHA_COLCHETES=266;
public final static short INCLUIR=267;
public final static short INTEIRO=268;
public final static short REAL=269;
public final static short CARACTER=270;
public final static short FUNCAO=271;
public final static short RETORNAR=272;
public final static short IMPRIMA=273;
public final static short SE=274;
public final static short SENAO=275;
public final static short CASO=276;
public final static short OPCAO=277;
public final static short FIM_OPCAO=278;
public final static short ENQUANTO=279;
public final static short FACA=280;
public final static short ATE=281;
public final static short PARA=282;
public final static short NUMERICO=283;
public final static short OP_INCREMENTO=284;
public final static short OP_DECREMENTO=285;
public final static short OP_SOMA=286;
public final static short OP_SUB=287;
public final static short OP_DIV=288;
public final static short OP_MULT=289;
public final static short OP_MOD=290;
public final static short OP_DIFERENTE=291;
public final static short OP_MAIOR=292;
public final static short OP_MENOR=293;
public final static short OP_IGUAL=294;
public final static short VIRGULA=295;
public final static short DOIS_PONTOS=296;
public final static short PONTO_VIRGULA=297;
public final static short STRING=298;
public final static short CHAR=299;
public final static short COMENTARIO_LINHA=300;
public final static short COMENTARIO_BLOCO=301;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    3,    3,    3,    3,   11,
   31,   32,    4,    5,    5,    5,    5,    5,    5,    5,
    5,    5,    5,    5,    5,   24,   24,   24,   25,   26,
   26,   26,   26,   33,   34,   27,   27,   28,   29,   29,
    6,    6,   12,   12,   12,   12,   13,   13,   13,    8,
    8,   23,   23,   23,    9,    9,   10,   10,   19,   19,
   19,   20,   20,   21,   21,   14,   14,   15,   15,   15,
   15,   15,   15,   15,   16,   16,   16,   16,   16,   22,
   22,   22,   22,   22,   22,   18,   18,   18,   18,   18,
   18,   30,   30,   30,   30,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,   17,   17,    7,    7,    7,
};
final static short yylen[] = {                            2,
    1,    2,    3,    7,    2,    2,    5,    3,    0,    3,
    1,    1,    2,    2,    2,    3,    2,    2,    2,    2,
    2,    2,    2,    2,    0,    7,    5,    4,    7,    8,
    7,    6,    5,    1,    1,    5,    4,    8,    9,    9,
    2,    1,    1,    2,    1,    2,    2,    3,    3,    2,
    3,    2,    2,    2,    2,    3,    1,    3,    3,    1,
    1,    2,    3,    2,    3,    1,    1,    3,    3,    5,
    5,    3,    2,    2,    3,    3,    5,    5,    3,    1,
    1,    1,    1,    1,    3,    1,    1,    1,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    1,    1,    2,    2,    2,    2,    2,
    2,    2,    2,    1,    2,    2,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,   11,    0,
    0,   13,    0,    0,  128,  129,  130,    0,    0,   42,
    5,    6,    2,    3,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   61,    0,    0,    0,    0,    0,    0,    0,   44,   46,
    0,    0,   41,    0,   52,   54,    0,    0,   89,   88,
    0,    0,   87,   67,    0,   82,   83,    0,    0,   53,
    0,    0,    0,    0,    0,   23,   24,   12,   10,   14,
    0,    0,  127,    0,    0,   15,   17,   18,   19,   20,
   21,   22,    0,   47,    0,    0,   50,    0,    0,   55,
    0,    0,    0,    0,    0,    0,    0,    0,   73,   16,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   74,    0,   93,   95,   92,   94,    0,    0,    0,
    0,    0,   63,   65,  126,   59,    0,    0,   49,   48,
   51,    0,   56,    0,   91,   90,    0,    0,   85,  113,
  122,  120,  116,  121,  123,  117,  125,  119,  118,  112,
   75,    0,  107,  104,  105,  106,  111,  108,  109,  110,
  101,  102,  103,   68,    0,    0,    0,    0,    0,    0,
    0,   58,    0,    0,    0,    0,    0,   36,    0,    0,
    4,   71,    0,   78,    0,    0,    0,    0,    0,    0,
    0,   26,    0,   29,    0,    0,    0,    0,    0,    0,
   38,    0,    0,    0,    0,   39,   40,    0,   35,    0,
    0,   32,    0,   30,
};
final static short yydgoto[] = {                          6,
  115,  121,    7,    8,   36,   37,   19,   52,   55,  101,
   10,   38,   49,  102,   63,   64,   85,   65,   39,   40,
   41,   68,   42,   43,   44,  199,   45,   46,   47,  128,
  186,   79,  210,  220,
};
final static short yysindex[] = {                      -157,
 -230, -220, -149, -157, -157,    0,    0, -157,    0, -157,
  878,    0, -221, -221,    0,    0,    0, -212, -145,    0,
    0,    0,    0,    0, -259, -259, -135, -200, -194, -180,
 -175, -230, -172,  878,  878, -165,  878, -116,  878,    0,
    0,  878,  878,  878,  878,  878,  878,  298,    0,    0,
 -222, -230,    0, -158,    0,    0, -127, -181,    0,    0,
 -148,  878,    0,    0,  -37,    0,    0,  303, -167,    0,
 -135,  -54, -135,  878, -145,    0,    0,    0,    0,    0,
 -138, -136,    0, -176, -135,    0,    0,    0,    0,    0,
    0,    0,  -22,    0, -112,  344,    0, -108,  878,    0,
 -105, -129, -103,  -96,  -93,  -90,  -37, -137,    0,    0,
 -117, -111,  -55,  258, -135,  384, -261,  303,  303,  303,
  -22,    0,  -77,    0,    0,    0,    0,  -75,  -71, -165,
 -116, -102,    0,    0,    0,    0,  -93, -137,    0,    0,
    0, -165,    0, -135,    0,    0,  303,  -37,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -37,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  303, -230, -230, -230,  -87, -135,
 -157,    0,  -22, -135,  -76,  878,  -72,    0,  -50,  -80,
    0,    0,  303,    0,  -37, -230, -165,  -54, -165, -135,
 -145,    0,  -73,    0,  -36, -148,  -30,  -21, -230,  878,
    0, -230, -230,  878,  -35,    0,    0,  -32,    0,  -72,
 -165,    0,  -72,    0,
};
final static short yyrindex[] = {                       241,
    0,    0,    0,  241,  241,    0,    0,  241,    0,  241,
    2,    0,  468,  509,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,   42,    0,    0,    0,    0,
    0,    0,    0,    4,    4,  248,    4,  711,    4,    0,
    0,    4,    4,    4,    4,    4,    4,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   83,    0,    0,
  124,    4,    0,    0,  543,    0,    0,  427,  165,    0,
    0,    0,    0,  -12,    0,    0,    0,    0,    0,    0,
  206,  247,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    2,    0,
    0,  -13,  400,  421,  441,    0,    0,  463,    0,    0,
    0,  -83,  737,  876,    0,  296,  501,  503,  569,  743,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  260,    0,    0,    0,    0,  304,  588,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  629,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  345,  745,    0,  780,    0,    0,
  241,    0,    0,    0,  814,  -12,    0,    0,    0,    0,
    0,    0,  386,    0,  670,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -48,  -10,
    0,    0,    0,  -10,    0,    0,    0,    0,    0,    5,
    0,    0,   11,    0,
};
final static short yygindex[] = {                         0,
  128,  -66,    3,    0,  -25,   20,    0,    0,   32,  134,
 -119,   13,   29,    7,  -24,  -52,    0,  -42,  209,   18,
 1047,    8, 1052,    0,    0, -144,    0,    0,    0,  108,
   -1, -104,    0,    0,
};
final static int YYTABLESIZE=1252;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   43,   25,   54,   25,  106,   48,   21,   22,   76,   77,
   23,   80,   24,   86,  107,   20,   87,   88,   89,   90,
   91,   92,   18,   95,  167,  179,  168,  169,  170,    9,
   74,   53,  105,   62,   13,   14,  110,  181,   12,   61,
   97,   45,   50,   48,   66,   15,   16,   17,  130,   51,
   99,  171,  172,  173,   50,   96,  185,   56,  188,   70,
   61,   54,  161,   20,  108,   66,   61,   71,  137,   61,
   98,   66,  162,  142,   66,  222,  202,  123,  224,  129,
  183,   72,   80,   61,  127,   61,   73,  131,   66,   75,
   66,  136,  216,  217,  204,   78,  174,   61,   25,   26,
  138,  109,   66,   57,  100,   61,    1,   13,   14,    2,
   66,   13,   14,    3,   28,  122,  223,  135,   15,   16,
   17,   25,   26,   81,   58,  149,   57,   61,  175,   25,
   26,  194,   66,   61,   57,   81,   82,   28,   66,   59,
   60,  195,    4,    5,  133,   28,  134,   58,  116,  117,
  118,  119,  120,  139,  141,   58,   61,  143,  192,  145,
  197,   66,   59,   60,   84,  144,  146,   81,   82,  147,
  103,  104,  148,  114,  114,  187,  150,   83,  114,   84,
  151,  152,  153,  191,  215,  176,  190,  177,  218,  114,
  193,  178,   61,  189,  180,   61,   61,   66,  196,  114,
   66,   66,   13,   14,  198,   62,  205,  214,   34,   34,
  127,  200,   61,  206,  114,  114,  201,   66,  207,   34,
   34,   34,  209,   34,   34,   34,  211,   34,  124,   34,
   34,   34,  212,   34,   25,   26,  154,  155,  156,   93,
    9,  213,  219,  125,  126,  221,   64,    8,   25,   57,
   28,   34,   34,  111,  112,  113,  114,   43,   43,    7,
   58,   43,   25,   43,   25,   33,   43,   25,   43,   43,
   43,   31,   43,   43,   43,  184,   43,  182,   43,   43,
   43,   25,   43,  132,   43,   43,   43,   43,   43,   43,
   43,   43,   43,   43,   43,   43,   43,   43,   45,   45,
   43,   43,   45,   72,   45,  203,    0,   45,    0,   45,
   45,   45,    0,   45,   45,   45,    0,   45,    0,   45,
   45,   45,    0,   45,    0,   45,   45,   45,   45,   45,
   45,   45,   45,   45,   45,   45,   45,   45,   45,   80,
   80,   45,   45,   80,   69,   80,    0,    0,   80,    0,
   80,   80,   80,    0,   80,   80,   80,    0,   80,    0,
   80,   80,   80,    0,   80,    0,    0,    0,   80,   80,
   80,   80,   80,   80,   80,   80,   80,   80,    0,   80,
   81,   81,   80,   80,   81,   70,   81,    0,    0,   81,
    0,   81,   81,   81,    0,   81,   81,   81,    0,   81,
    0,   81,   81,   81,    0,   81,    0,    0,    0,   81,
   81,   81,   81,   81,   81,   81,   81,   81,   81,    0,
   81,   84,   84,   81,   81,   84,   86,   84,    0,    0,
   84,    0,   84,   84,   84,    0,   84,   84,   84,    0,
   84,    0,   84,   84,   84,    0,   84,    0,    0,    0,
   84,   84,   84,   84,   84,   84,   84,   84,   84,   84,
    0,   84,   62,   62,   84,   84,   62,   43,   62,    0,
    0,   62,    0,   62,   62,   62,    0,   62,   62,   62,
    0,   62,    0,   62,   62,   62,    0,   62,    0,    0,
    0,   62,   62,   62,   62,   62,   62,   62,   62,   62,
   62,    0,   62,   64,   64,   62,   62,   64,   45,   64,
    0,    0,   64,    0,   64,   64,   64,    0,   64,   64,
   64,    0,   64,    0,   64,   64,   64,    0,   64,    0,
    0,    0,   64,   64,   64,   64,   64,   64,   64,   64,
   64,   64,   66,   64,    0,    0,   64,   64,  157,  158,
  159,  160,   96,   96,   25,   26,    0,   96,    0,   93,
   72,   72,    0,   94,   72,    0,   72,    0,   96,   72,
   28,   72,   72,   72,    0,   72,   72,   72,   96,   72,
   58,   72,   72,   72,    0,   72,    0,   79,  116,  117,
  118,  119,  120,    0,   72,   72,   72,   72,   72,    0,
   72,   69,   69,   72,   72,   69,    0,   69,    0,  140,
   69,    0,   69,   69,   69,    0,   69,   69,   69,    0,
   69,    0,   69,   69,   69,    0,   69,    0,   76,  116,
  117,  118,  119,  120,    0,   69,   69,   69,   69,   69,
    0,   69,   70,   70,   69,   69,   70,    0,   70,    0,
    0,   70,    0,   70,   70,   70,    0,   70,   70,   70,
    0,   70,    0,   70,   70,   70,    0,   70,    0,   77,
  163,  164,  165,  166,    0,    0,   70,   70,   70,   70,
   70,    0,   70,   86,   86,   70,   70,   86,    0,   86,
   89,   89,   89,   89,   86,   86,   86,    0,   86,   86,
   86,    0,   86,    0,   86,   86,   86,    0,   86,    0,
   42,   88,   88,   88,   88,    0,    0,   86,   86,   86,
   86,   86,    0,   86,   43,   43,   86,   86,   43,   43,
   43,   87,   87,   87,   87,   43,   43,   43,    0,   43,
   43,   43,    0,   43,   28,   43,   43,   43,    0,   43,
    0,   43,   43,   86,   86,   86,   86,   97,   97,   98,
   98,   43,   97,   43,   98,   45,   45,   43,   43,   45,
   45,   45,    0,   97,    0,   98,   45,   45,   45,   37,
   45,   45,   45,   97,   45,   98,   45,   45,   45,    0,
   45,    0,   45,   45,    0,    0,    0,    0,    0,   66,
   66,    0,   45,   66,   45,   66,    0,    0,   45,   45,
   66,   66,   66,   27,   66,   66,   66,    0,   66,    0,
   66,   66,   66,    0,   66,   99,   99,    0,    0,    0,
   99,    0,    0,    0,    0,    0,    0,   66,    0,   66,
    0,   99,   66,   66,   79,   79,    0,    0,   79,    0,
   79,   99,    0,    0,    0,   79,   79,   79,    0,   79,
   79,   79,    0,   79,    0,   79,   79,   79,    0,   79,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   79,    0,   79,   76,   76,   79,   79,   76,
    0,   76,    0,    0,    0,    0,   76,   76,   76,    0,
   76,   76,   76,    0,   76,    0,   76,   76,   76,    0,
   76,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   76,    0,   76,   77,   77,   76,   76,
   77,    0,   77,    0,    0,    0,    0,   77,   77,   77,
    0,   77,   77,   77,    0,   77,    0,   77,   77,   77,
    0,   77,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   77,    0,   77,   42,   42,   77,
   77,   42,    0,    0,    0,    0,    0,    0,   42,   42,
   42,    0,   42,   42,   42,    0,   42,    0,   42,   42,
   42,    0,   42,  115,  115,    0,    0,    0,  115,  100,
  100,   28,   28,    0,  100,   28,    0,    0,    0,  115,
   42,   42,   28,   28,   28,  100,   28,   28,   28,  115,
   28,    0,   28,   28,   28,  100,   28,    0,    0,    0,
    0,    0,    0,    0,  115,  115,   37,   37,    0,    0,
   37,    0,    0,    0,   28,   28,    0,   37,   37,   37,
    0,   37,   37,   37,    0,   37,    0,   37,   37,   37,
    0,   37,    0,    0,    0,    0,    0,    0,    0,    0,
   27,   27,    0,   67,   27,    0,    0,    0,   69,   37,
   37,   27,   27,   27,    0,   27,   27,   27,    0,   27,
    0,   27,   27,   27,   67,   27,    0,    0,    0,   69,
   67,    0,    0,   67,    0,   69,    0,    0,   69,    0,
    0,    0,    0,   27,   27,    0,    0,   67,    0,   67,
    0,    0,   69,    0,   69,    0,    0,    0,    0,    0,
    0,   67,  124,  124,   25,   26,   69,  124,    0,   67,
    0,    0,    0,    0,   69,   15,   16,   17,  124,   27,
   28,   29,    0,   30,    0,    0,   31,   32,  124,   33,
    0,   67,    0,    0,    0,    0,   69,   67,    0,    0,
    0,    0,   69,  124,  124,    0,    0,   34,   35,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   67,    0,    0,    0,    0,   69,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   67,    0,    0,   67,
   67,   69,    0,    0,   69,   69,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   67,  208,    0,    0,
    0,   69,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          1,
    0,    0,  262,    0,   57,  265,    4,    5,   34,   35,
    8,   37,   10,   39,   57,    3,   42,   43,   44,   45,
   46,   47,    3,   48,  286,  130,  288,  289,  290,  260,
   32,   19,   57,   27,  257,  258,   62,  142,  259,   27,
  263,    0,   14,  265,   27,  268,  269,  270,   74,  262,
   52,  118,  119,  120,   26,   48,  176,   26,  178,   28,
   48,  262,  115,   51,   57,   48,   54,  262,   93,   57,
   51,   54,  115,   99,   57,  220,  196,   71,  223,   73,
  147,  262,    0,   71,   72,   73,  262,   75,   71,  262,
   73,   85,  212,  213,  199,  261,  121,   85,  257,  258,
   93,  283,   85,  262,  263,   93,  264,  257,  258,  267,
   93,  257,  258,  271,  273,  283,  221,  294,  268,  269,
  270,  257,  258,    0,  283,  263,  262,  115,  121,  257,
  258,  184,  115,  121,  262,  284,  285,  273,  121,  298,
  299,  184,  300,  301,  283,  273,  283,  283,  286,  287,
  288,  289,  290,  266,  263,  283,  144,  263,  183,  263,
  186,  144,  298,  299,    0,  295,  263,  284,  285,  263,
  298,  299,  263,  257,  258,  177,  294,  294,  262,  296,
  292,  293,  294,  181,  210,  263,  180,  263,  214,  273,
  183,  263,  180,  281,  297,  183,  184,  180,  275,  283,
  183,  184,  257,  258,  277,    0,  200,  209,  257,  258,
  198,  262,  200,  201,  298,  299,  297,  200,  201,  268,
  269,  270,  296,  272,  273,  274,  263,  276,  283,  278,
  279,  280,  263,  282,  257,  258,  292,  293,  294,  262,
    0,  263,  278,  298,  299,  278,    0,    0,  261,  263,
  273,  300,  301,  291,  292,  293,  294,  257,  258,    0,
  283,  261,  261,  263,  261,  261,  266,  278,  268,  269,
  270,  261,  272,  273,  274,  148,  276,  144,  278,  279,
  280,  278,  282,   75,  284,  285,  286,  287,  288,  289,
  290,  291,  292,  293,  294,  295,  296,  297,  257,  258,
  300,  301,  261,    0,  263,  198,   -1,  266,   -1,  268,
  269,  270,   -1,  272,  273,  274,   -1,  276,   -1,  278,
  279,  280,   -1,  282,   -1,  284,  285,  286,  287,  288,
  289,  290,  291,  292,  293,  294,  295,  296,  297,  257,
  258,  300,  301,  261,    0,  263,   -1,   -1,  266,   -1,
  268,  269,  270,   -1,  272,  273,  274,   -1,  276,   -1,
  278,  279,  280,   -1,  282,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,  292,  293,  294,  295,   -1,  297,
  257,  258,  300,  301,  261,    0,  263,   -1,   -1,  266,
   -1,  268,  269,  270,   -1,  272,  273,  274,   -1,  276,
   -1,  278,  279,  280,   -1,  282,   -1,   -1,   -1,  286,
  287,  288,  289,  290,  291,  292,  293,  294,  295,   -1,
  297,  257,  258,  300,  301,  261,    0,  263,   -1,   -1,
  266,   -1,  268,  269,  270,   -1,  272,  273,  274,   -1,
  276,   -1,  278,  279,  280,   -1,  282,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,  292,  293,  294,  295,
   -1,  297,  257,  258,  300,  301,  261,    0,  263,   -1,
   -1,  266,   -1,  268,  269,  270,   -1,  272,  273,  274,
   -1,  276,   -1,  278,  279,  280,   -1,  282,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,  292,  293,  294,
  295,   -1,  297,  257,  258,  300,  301,  261,    0,  263,
   -1,   -1,  266,   -1,  268,  269,  270,   -1,  272,  273,
  274,   -1,  276,   -1,  278,  279,  280,   -1,  282,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,  292,  293,
  294,  295,    0,  297,   -1,   -1,  300,  301,  291,  292,
  293,  294,  257,  258,  257,  258,   -1,  262,   -1,  262,
  257,  258,   -1,  266,  261,   -1,  263,   -1,  273,  266,
  273,  268,  269,  270,   -1,  272,  273,  274,  283,  276,
  283,  278,  279,  280,   -1,  282,   -1,    0,  286,  287,
  288,  289,  290,   -1,  291,  292,  293,  294,  295,   -1,
  297,  257,  258,  300,  301,  261,   -1,  263,   -1,  266,
  266,   -1,  268,  269,  270,   -1,  272,  273,  274,   -1,
  276,   -1,  278,  279,  280,   -1,  282,   -1,    0,  286,
  287,  288,  289,  290,   -1,  291,  292,  293,  294,  295,
   -1,  297,  257,  258,  300,  301,  261,   -1,  263,   -1,
   -1,  266,   -1,  268,  269,  270,   -1,  272,  273,  274,
   -1,  276,   -1,  278,  279,  280,   -1,  282,   -1,    0,
  287,  288,  289,  290,   -1,   -1,  291,  292,  293,  294,
  295,   -1,  297,  257,  258,  300,  301,  261,   -1,  263,
  291,  292,  293,  294,  268,  269,  270,   -1,  272,  273,
  274,   -1,  276,   -1,  278,  279,  280,   -1,  282,   -1,
    0,  291,  292,  293,  294,   -1,   -1,  291,  292,  293,
  294,  295,   -1,  297,  257,  258,  300,  301,  261,  262,
  263,  291,  292,  293,  294,  268,  269,  270,   -1,  272,
  273,  274,   -1,  276,    0,  278,  279,  280,   -1,  282,
   -1,  284,  285,  291,  292,  293,  294,  257,  258,  257,
  258,  294,  262,  296,  262,  257,  258,  300,  301,  261,
  262,  263,   -1,  273,   -1,  273,  268,  269,  270,    0,
  272,  273,  274,  283,  276,  283,  278,  279,  280,   -1,
  282,   -1,  284,  285,   -1,   -1,   -1,   -1,   -1,  257,
  258,   -1,  294,  261,  296,  263,   -1,   -1,  300,  301,
  268,  269,  270,    0,  272,  273,  274,   -1,  276,   -1,
  278,  279,  280,   -1,  282,  257,  258,   -1,   -1,   -1,
  262,   -1,   -1,   -1,   -1,   -1,   -1,  295,   -1,  297,
   -1,  273,  300,  301,  257,  258,   -1,   -1,  261,   -1,
  263,  283,   -1,   -1,   -1,  268,  269,  270,   -1,  272,
  273,  274,   -1,  276,   -1,  278,  279,  280,   -1,  282,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  295,   -1,  297,  257,  258,  300,  301,  261,
   -1,  263,   -1,   -1,   -1,   -1,  268,  269,  270,   -1,
  272,  273,  274,   -1,  276,   -1,  278,  279,  280,   -1,
  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  295,   -1,  297,  257,  258,  300,  301,
  261,   -1,  263,   -1,   -1,   -1,   -1,  268,  269,  270,
   -1,  272,  273,  274,   -1,  276,   -1,  278,  279,  280,
   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  295,   -1,  297,  257,  258,  300,
  301,  261,   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,
  270,   -1,  272,  273,  274,   -1,  276,   -1,  278,  279,
  280,   -1,  282,  257,  258,   -1,   -1,   -1,  262,  257,
  258,  257,  258,   -1,  262,  261,   -1,   -1,   -1,  273,
  300,  301,  268,  269,  270,  273,  272,  273,  274,  283,
  276,   -1,  278,  279,  280,  283,  282,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  298,  299,  257,  258,   -1,   -1,
  261,   -1,   -1,   -1,  300,  301,   -1,  268,  269,  270,
   -1,  272,  273,  274,   -1,  276,   -1,  278,  279,  280,
   -1,  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,   -1,   27,  261,   -1,   -1,   -1,   27,  300,
  301,  268,  269,  270,   -1,  272,  273,  274,   -1,  276,
   -1,  278,  279,  280,   48,  282,   -1,   -1,   -1,   48,
   54,   -1,   -1,   57,   -1,   54,   -1,   -1,   57,   -1,
   -1,   -1,   -1,  300,  301,   -1,   -1,   71,   -1,   73,
   -1,   -1,   71,   -1,   73,   -1,   -1,   -1,   -1,   -1,
   -1,   85,  257,  258,  257,  258,   85,  262,   -1,   93,
   -1,   -1,   -1,   -1,   93,  268,  269,  270,  273,  272,
  273,  274,   -1,  276,   -1,   -1,  279,  280,  283,  282,
   -1,  115,   -1,   -1,   -1,   -1,  115,  121,   -1,   -1,
   -1,   -1,  121,  298,  299,   -1,   -1,  300,  301,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  144,   -1,   -1,   -1,   -1,  144,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  180,   -1,   -1,  183,
  184,  180,   -1,   -1,  183,  184,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  200,  201,   -1,   -1,
   -1,  200,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=301;
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
null,null,null,"IDENTIFICADOR","IDENTIFICADOR_INVALIDO","INCLUSAO_ARQUIVO",
"ABRE_CHAVES","FECHA_CHAVES","ABRE_PARENTESES","FECHA_PARENTESES",
"FUNCAO_PRINCIPAL","ABRE_COLCHETES","FECHA_COLCHETES","INCLUIR","INTEIRO",
"REAL","CARACTER","FUNCAO","RETORNAR","IMPRIMA","SE","SENAO","CASO","OPCAO",
"FIM_OPCAO","ENQUANTO","FACA","ATE","PARA","NUMERICO","OP_INCREMENTO",
"OP_DECREMENTO","OP_SOMA","OP_SUB","OP_DIV","OP_MULT","OP_MOD","OP_DIFERENTE",
"OP_MAIOR","OP_MENOR","OP_IGUAL","VIRGULA","DOIS_PONTOS","PONTO_VIRGULA",
"STRING","CHAR","COMENTARIO_LINHA","COMENTARIO_BLOCO",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : FUNCAO_PRINCIPAL bloco programa",
"programa : FUNCAO declaracao atributos inicio_bloco comandos fim_bloco programa",
"programa : COMENTARIO_LINHA programa",
"programa : COMENTARIO_BLOCO programa",
"programa : FUNCAO declaracao atributos inicio_bloco comandos",
"programa : FUNCAO_PRINCIPAL inicio_bloco comandos",
"programa :",
"bloco : inicio_bloco comandos fim_bloco",
"inicio_bloco : ABRE_CHAVES",
"fim_bloco : FECHA_CHAVES",
"inclusao : INCLUIR INCLUSAO_ARQUIVO",
"comandos : declaracao comandos",
"comandos : atribuicao comandos",
"comandos : RETORNAR expressoes comandos",
"comandos : chamada_funcao comandos",
"comandos : cmd_if comandos",
"comandos : cmd_switch comandos",
"comandos : cmd_while comandos",
"comandos : cmd_do_while comandos",
"comandos : cmd_for comandos",
"comandos : COMENTARIO_LINHA comandos",
"comandos : COMENTARIO_BLOCO comandos",
"comandos :",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco SENAO bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES bloco",
"cmd_if : SE ABRE_PARENTESES expressoes FECHA_PARENTESES",
"cmd_switch : CASO ABRE_PARENTESES operandos_switch FECHA_PARENTESES inicio_bloco cmd_case fim_bloco",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS inicio_bloco comandos FIM_OPCAO fim_bloco cmd_case",
"cmd_case : OPCAO operandos_switch DOIS_PONTOS inicio_bloco comandos FIM_OPCAO fim_bloco",
"cmd_case : OPCAO operandos_switch inicio_case comandos fim_case cmd_case",
"cmd_case : OPCAO operandos_switch inicio_case comandos fim_case",
"inicio_case : DOIS_PONTOS",
"fim_case : FIM_OPCAO",
"cmd_while : ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES bloco",
"cmd_while : ENQUANTO ABRE_PARENTESES expressoes FECHA_PARENTESES",
"cmd_do_while : FACA inicio_bloco comandos fim_bloco ATE ABRE_PARENTESES expressoes FECHA_PARENTESES",
"cmd_for : PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA incremento FECHA_PARENTESES bloco",
"cmd_for : PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA decremento FECHA_PARENTESES bloco",
"declaracao : tipo variavel",
"declaracao : variavel",
"variavel : IDENTIFICADOR",
"variavel : IDENTIFICADOR vetor",
"variavel : IDENTIFICADOR_INVALIDO",
"variavel : IDENTIFICADOR_INVALIDO vetor",
"vetor : ABRE_COLCHETES FECHA_COLCHETES",
"vetor : ABRE_COLCHETES operandos_aritmeticos FECHA_COLCHETES",
"vetor : ABRE_COLCHETES expres_aritmeticas FECHA_COLCHETES",
"atributos : ABRE_PARENTESES FECHA_PARENTESES",
"atributos : ABRE_PARENTESES declaracao FECHA_PARENTESES",
"chamada_funcao : IDENTIFICADOR parametros",
"chamada_funcao : IMPRIMA parametros",
"chamada_funcao : IDENTIFICADOR_INVALIDO parametros",
"parametros : ABRE_PARENTESES FECHA_PARENTESES",
"parametros : ABRE_PARENTESES mult_parametros FECHA_PARENTESES",
"mult_parametros : expressoes",
"mult_parametros : expressoes VIRGULA mult_parametros",
"atribuicao : variavel op_atribuicao expressoes",
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
"expres_aritmeticas : NUMERICO NUMERICO",
"expres_aritmeticas : chamada_funcao NUMERICO",
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
"operandos_switch : STRING",
"operador_aritmetico : OP_SOMA",
"operador_aritmetico : OP_SUB",
"operador_aritmetico : OP_DIV",
"operador_aritmetico : OP_MULT",
"operador_aritmetico : OP_MOD",
"operador_aritmetico : OP_DIV operador_aritmetico",
"operador_aritmetico : OP_MULT operador_aritmetico",
"operador_aritmetico : OP_MOD operador_aritmetico",
"operador_aritmetico : OP_SOMA OP_DIV",
"operador_aritmetico : OP_SOMA OP_MULT",
"operador_aritmetico : OP_SOMA OP_MOD",
"operador_aritmetico : OP_SOMA OP_SUB",
"operador_aritmetico : OP_SUB OP_DIV",
"operador_aritmetico : OP_SUB OP_MULT",
"operador_aritmetico : OP_SUB OP_MOD",
"operador_aritmetico : OP_SUB OP_SOMA",
"operador_logico : OP_IGUAL OP_IGUAL",
"operador_logico : OP_DIFERENTE OP_IGUAL",
"operador_logico : OP_MAIOR",
"operador_logico : OP_MENOR",
"operador_logico : OP_MAIOR OP_IGUAL",
"operador_logico : OP_MENOR OP_IGUAL",
"operador_logico : OP_IGUAL OP_MENOR",
"operador_logico : OP_IGUAL OP_MAIOR",
"operador_logico : OP_MAIOR OP_MENOR",
"operador_logico : OP_MENOR OP_MAIOR",
"operador_logico : OP_MAIOR OP_MAIOR",
"operador_logico : OP_MENOR OP_MENOR",
"operador_logico : OP_IGUAL",
"operador_logico : OP_IGUAL OP_DIFERENTE",
"op_atribuicao : DOIS_PONTOS OP_IGUAL",
"op_atribuicao : OP_IGUAL",
"tipo : INTEIRO",
"tipo : REAL",
"tipo : CARACTER",
};

//#line 254 "inicioCT.y"

	// Referencia ao JFlex
	private Yylex lexer;
        private int dist = 0;
        private int linha = 1;

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
            System.err.println("Error linha ["+linha+"]: " + error);
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

        public String indenta(int qtd){
            String str = "";
            for(int i=0;i<qtd;i++){
                str += "  ";
            }
            return str;
        }
        
        public void contaLinha(){
            linha++;
        }

        public void geraArquivo(String nomeArquivo,String coteudo) {

		File arquivo = new File(nomeArquivo);
		
		try {
			FileWriter fw = new FileWriter(arquivo);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(coteudo);
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
//#line 746 "Parser.java"
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
//#line 88 "inicioCT.y"
{ System.out.println(val_peek(0).sval); geraArquivo("programa.c",val_peek(0).sval); }
break;
case 2:
//#line 90 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 91 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(5).sval + val_peek(4).sval + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval +"\n" + val_peek(0).sval; }
break;
case 5:
//#line 93 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 7:
//#line 95 "inicioCT.y"
{ yyerror("Bloco não foi fechado, era esperado: }."); yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval +"}"; }
break;
case 8:
//#line 96 "inicioCT.y"
{ yyerror("Bloco não foi fechado, era esperado: }."); yyval.sval = "int main() " + val_peek(1).sval + val_peek(0).sval + "}"; }
break;
case 9:
//#line 97 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 10:
//#line 99 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval;}
break;
case 11:
//#line 101 "inicioCT.y"
{ dist++; yyval.sval = "{\n"; }
break;
case 12:
//#line 103 "inicioCT.y"
{ dist--; yyval.sval = indenta(dist) + "}"; }
break;
case 13:
//#line 105 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 14:
//#line 107 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 15:
//#line 108 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 16:
//#line 109 "inicioCT.y"
{ yyval.sval = indenta(dist) + "return " + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 17:
//#line 110 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 18:
//#line 111 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 19:
//#line 112 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 20:
//#line 113 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 21:
//#line 114 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 22:
//#line 115 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval; }
break;
case 23:
//#line 116 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + "\n" + val_peek(0).sval ; }
break;
case 24:
//#line 117 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + "\n" + val_peek(0).sval ; }
break;
case 25:
//#line 118 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 26:
//#line 120 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n"; }
break;
case 27:
//#line 121 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 28:
//#line 122 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n" + indenta(1); }
break;
case 29:
//#line 124 "inicioCT.y"
{ yyval.sval = "switch(" + val_peek(4).sval + ")" + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval + "\n"; }
break;
case 30:
//#line 126 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(6).sval + ":" + val_peek(4).sval + val_peek(3).sval + indenta(dist) + "break;\n" + val_peek(1).sval + val_peek(0).sval; }
break;
case 31:
//#line 127 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(5).sval + ":" + val_peek(3).sval + val_peek(2).sval + indenta(dist) + "break;\n" + val_peek(0).sval; }
break;
case 32:
//#line 128 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(4).sval + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 33:
//#line 129 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 34:
//#line 131 "inicioCT.y"
{ dist++; yyval.sval = ":\n"; }
break;
case 35:
//#line 133 "inicioCT.y"
{ yyval.sval = indenta(dist) + "break;"; dist--; }
break;
case 36:
//#line 135 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 37:
//#line 136 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n"; }
break;
case 38:
//#line 138 "inicioCT.y"
{ yyval.sval = "do" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + "while(" + val_peek(1).sval + ");\n"; }
break;
case 39:
//#line 140 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 40:
//#line 141 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 41:
//#line 143 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 42:
//#line 144 "inicioCT.y"
{ yyerror("Declaração inválida."); yyval.sval = "int" + val_peek(0).sval; }
break;
case 43:
//#line 147 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 44:
//#line 148 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 45:
//#line 149 "inicioCT.y"
{ yyerror("Identificador inválido"); yyval.sval = "xxxx"; }
break;
case 46:
//#line 150 "inicioCT.y"
{ yyerror("Identificador inválido"); yyval.sval = "xxxxx" + val_peek(0).sval; }
break;
case 47:
//#line 152 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 48:
//#line 153 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 49:
//#line 154 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 50:
//#line 156 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 51:
//#line 157 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 52:
//#line 159 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 53:
//#line 160 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 54:
//#line 161 "inicioCT.y"
{ yyerror("Nome de função inválido."); yyval.sval = "xyyyyy" + val_peek(0).sval; }
break;
case 55:
//#line 163 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 56:
//#line 164 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 57:
//#line 166 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 167 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 59:
//#line 169 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 60:
//#line 170 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 61:
//#line 171 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 173 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 63:
//#line 174 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 64:
//#line 176 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 65:
//#line 177 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 66:
//#line 179 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 67:
//#line 180 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 68:
//#line 182 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 69:
//#line 183 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 70:
//#line 184 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 71:
//#line 185 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 72:
//#line 186 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 73:
//#line 187 "inicioCT.y"
{ yyerror("Falta de operador."); yyval.sval = val_peek(1).sval  + "+" + val_peek(0).sval; }
break;
case 74:
//#line 188 "inicioCT.y"
{ yyerror("Falta de operador."); yyval.sval = val_peek(1).sval  + "+" + val_peek(0).sval; }
break;
case 75:
//#line 190 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 76:
//#line 191 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 77:
//#line 192 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 78:
//#line 193 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 79:
//#line 194 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 80:
//#line 196 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 81:
//#line 197 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 82:
//#line 198 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 83:
//#line 199 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 84:
//#line 200 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 85:
//#line 201 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 86:
//#line 203 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 87:
//#line 204 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 88:
//#line 205 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 89:
//#line 206 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 90:
//#line 207 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 91:
//#line 208 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 92:
//#line 210 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 93:
//#line 211 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 94:
//#line 212 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 95:
//#line 213 "inicioCT.y"
{ yyerror("Tipo de valor inválido, era esperado: variavel,char,int,float"); yyval.sval = "1"; }
break;
case 96:
//#line 215 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 97:
//#line 216 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 98:
//#line 217 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 99:
//#line 218 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 100:
//#line 219 "inicioCT.y"
{ yyval.sval = " % "; }
break;
case 101:
//#line 220 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "/"; }
break;
case 102:
//#line 221 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "*"; }
break;
case 103:
//#line 222 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "%"; }
break;
case 104:
//#line 223 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "+"; }
break;
case 105:
//#line 224 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "+"; }
break;
case 106:
//#line 225 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "+"; }
break;
case 107:
//#line 226 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "+"; }
break;
case 108:
//#line 227 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "-"; }
break;
case 109:
//#line 228 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "-"; }
break;
case 110:
//#line 229 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "-"; }
break;
case 111:
//#line 230 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: +,-,*,/,%"); yyval.sval = "-"; }
break;
case 112:
//#line 232 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 113:
//#line 233 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 114:
//#line 234 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 115:
//#line 235 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 116:
//#line 236 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 117:
//#line 237 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 118:
//#line 238 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " <= "; }
break;
case 119:
//#line 239 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " >= "; }
break;
case 120:
//#line 240 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " > "; }
break;
case 121:
//#line 241 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " != "; }
break;
case 122:
//#line 242 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " ? "; }
break;
case 123:
//#line 243 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " < "; }
break;
case 124:
//#line 244 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " == "; }
break;
case 125:
//#line 245 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: ==,!=,>,<,>=,<=."); yyval.sval = " != "; }
break;
case 126:
//#line 247 "inicioCT.y"
{ yyval.sval = " = "; }
break;
case 127:
//#line 248 "inicioCT.y"
{ yyerror("Operador inválido, era esperado: :=."); yyval.sval = " = "; }
break;
case 128:
//#line 250 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 129:
//#line 251 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 130:
//#line 252 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 1415 "Parser.java"
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
