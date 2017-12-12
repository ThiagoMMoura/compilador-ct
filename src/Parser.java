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
public final static short PARA=281;
public final static short OP_ATRIBUICAO=282;
public final static short NUMERICO=283;
public final static short OP_INCREMENTO=284;
public final static short OP_DECREMENTO=285;
public final static short OP_SOMA=286;
public final static short OP_SUB=287;
public final static short OP_DIV=288;
public final static short OP_MULT=289;
public final static short OP_MOD=290;
public final static short OP_IGUALDADE=291;
public final static short OP_DIFERENTE=292;
public final static short OP_MAIOR=293;
public final static short OP_MENOR=294;
public final static short OP_MAIOR_IGUAL=295;
public final static short OP_MENOR_IGUAL=296;
public final static short VIRGULA=297;
public final static short DOIS_PONTOS=298;
public final static short PONTO_VIRGULA=299;
public final static short STRING=300;
public final static short CHAR=301;
public final static short COMENTARIO_LINHA=302;
public final static short COMENTARIO_BLOCO=303;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    3,    3,    3,    3,    4,   12,    5,
    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,   24,   24,   24,   25,   26,   26,   26,   26,
   27,   27,   28,   29,   29,    7,   13,   13,   14,   14,
   14,    9,    9,   23,   23,   10,   10,   11,   11,   19,
   19,   19,   20,   20,   21,   21,   15,   15,   16,   16,
   16,   16,   16,   17,   17,   17,   17,   17,   22,   22,
   22,   22,   22,   22,   18,   18,   18,   18,   18,   18,
   30,   30,   30,    2,    2,    2,    2,    1,    1,    1,
    1,    1,    1,    8,    8,    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    2,    2,    0,    2,    3,    2,
    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,
    2,    0,    7,    5,    4,    7,    8,    7,    6,    5,
    5,    4,    8,    9,    9,    2,    1,    2,    2,    3,
    3,    2,    3,    2,    2,    2,    3,    1,    3,    3,
    1,    1,    2,    3,    2,    3,    1,    1,    3,    3,
    5,    5,    3,    3,    3,    5,    5,    3,    1,    1,
    1,    1,    1,    3,    1,    1,    1,    1,    3,    3,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,
    8,   10,   94,   95,   96,    0,    0,    5,    6,    3,
    2,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   51,   52,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   36,    0,    0,   44,
   38,    0,   69,   78,   77,    0,    0,   76,   58,    0,
   71,   72,    0,   73,   45,    0,    0,    0,    0,    0,
   20,   21,    9,   11,    0,    0,    0,   12,   14,   15,
   16,   17,   18,   19,   42,    0,    0,   46,    0,    0,
    0,   39,    0,    0,    0,    0,    0,    0,    0,    0,
   13,   88,   89,   90,   91,   92,   93,    0,   84,   85,
   86,   87,    0,    0,   82,   81,   83,    0,    0,    0,
    0,   50,   54,   56,   43,    4,   47,    0,    0,    0,
   41,   40,   80,   79,    0,    0,   74,   64,    0,   59,
    0,    0,    0,    0,    0,    0,   49,    0,    0,    0,
    0,   31,    0,    0,   62,    0,   67,    0,    0,    0,
    0,    0,    0,   23,    0,   26,    0,    0,    0,    0,
    0,   33,    0,    0,    0,    0,   34,   35,    0,    0,
    0,   29,    0,   27,
};
final static short yydgoto[] = {                          6,
  108,  113,    7,    8,    9,   32,   33,   17,   45,   50,
   89,   11,   34,   51,   90,   58,   59,   60,   35,   36,
   37,   63,   38,   39,   40,  161,   41,   42,   43,  118,
};
final static short yysindex[] = {                      -103,
 -250, -255, -133, -103, -103,    0,    0, -103, -103,  580,
    0,    0,    0,    0,    0, -248, -234,    0,    0,    0,
    0, -228, -231, -223, -213, -196, -188, -179, -168,  580,
  580, -165,  580, -195,  580,    0,    0,  580,  580,  580,
  580,  580,  580,  -90, -250, -167,    0, -178,  -64,    0,
    0, -145,    0,    0,    0, -256,  580,    0,    0,  -69,
    0,    0, -211,    0,    0, -231, -246, -231,  580, -234,
    0,    0,    0,    0, -231, -185, -183,    0,    0,    0,
    0,    0,    0,    0,    0, -154, -103,    0, -153, -184,
 -169,    0, -150,  -73, -144, -134, -132, -120,  -69, -105,
    0,    0,    0,    0,    0,    0,    0, -231,    0,    0,
    0,    0, -169, -115,    0,    0,    0, -111, -104, -143,
 -137,    0,    0,    0,    0,    0,    0, -231, -132, -105,
    0,    0,    0,    0, -211,  -69,    0,    0,  -69,    0,
 -211, -250,  -95, -250, -112, -231,    0, -169, -231, -109,
 -102,    0,  -86, -119,    0, -211,    0,  -69, -250, -246,
  -75, -231, -234,    0, -110,    0,  -68, -256,  -67,  -60,
  543,    0, -250, -250,  580,  -79,    0,    0,  -74, -102,
  -55,    0, -102,    0,
};
final static short yyrindex[] = {                       209,
    0,    0,    0,  209,  209,    0,    0,  209,  209,  -50,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,    0,    0,    0,    0,    0,    0,    0, -233,
 -233,    0, -233,    0, -233,    0,    0, -233, -233, -233,
 -233, -233, -233,    0,    0,  -25,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   66, -233,    0,    0,  374,
    0,    0,  330,    0,    0,    0,    0,    0,  -50,    0,
    0,    0,    0,    0,    0,  110,  154,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  209,    0,    0,  -51,
    0,    0,    0,    0,  -30,  189,  233,    0,    0,  277,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  198,  412,    0,    0,  450,    0,
  242,  526,    0,  563,    0,    0,    0,    0,    0,  600,
    0,    0,    0,    0,    0,  286,    0,  488,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -59,    0,    0,    0,  -59,    0,    0,    0,    0,  -43,
    0,    0,  -32,    0,
};
final static short yygindex[] = {                         0,
   94,   96,   63,    0,    0,  -23,   -2,    0,    0,  214,
  105,   17,  -17,    0,  -21,  -28,  -48,  -47,  169,   -9,
   58,  -27,  121,    0,    0, -127,    0,    0,    0,   80,
};
final static int YYTABLESIZE=903;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         47,
   16,   57,   12,   98,   99,   56,   71,   72,   10,   74,
   46,   78,   44,   61,   79,   80,   81,   82,   83,   84,
   93,   94,   46,   97,  100,   22,   22,   76,   77,   52,
   56,   56,   48,  101,   56,   49,  115,   48,   61,   61,
   24,   86,   61,   22,  114,  120,  119,   66,   56,  117,
   56,   53,  182,  122,  116,  184,   61,   56,   61,  138,
  139,   87,  129,  130,   67,   61,   18,   19,   54,   55,
   20,   21,   68,   56,  109,  110,  111,  112,   22,   69,
   62,   61,   52,   88,  140,  141,   75,   22,   76,   77,
   56,   91,   70,   24,   73,   56,   49,  123,   61,  124,
  157,  158,   24,   61,   53,   62,   62,  125,  127,   62,
   56,   22,  128,   53,  131,   52,  145,  133,   61,  155,
  156,   54,   55,   62,  154,   62,   24,  134,   56,  135,
   56,   56,   62,   13,   14,   15,   61,   53,   61,   61,
  167,  136,  117,   64,   56,  168,  142,  176,   62,  126,
  143,  179,   61,  169,   95,   96,  137,  144,  150,    1,
  152,  146,    2,  151,  159,   62,    3,  153,   64,   64,
   62,   85,   64,  160,  162,  164,   13,   14,   15,  163,
  109,  110,  111,  112,  166,   62,   64,  171,   64,  177,
  178,  132,   22,  172,  173,   64,   91,  180,    4,    5,
   92,  174,  181,   62,  183,   62,   62,   24,    7,   22,
   48,   64,  109,  110,  111,  112,   30,   22,   53,   62,
  170,  102,  103,  104,  105,  106,  107,   28,   64,  149,
  148,   37,  147,   64,   37,   37,   37,   65,  121,  165,
    0,   37,   37,   37,    0,   37,   37,   37,   64,   37,
    0,   37,   37,   37,    0,   37,   37,    0,   37,   37,
   78,   78,   78,   78,   78,   78,   64,    0,   64,   64,
    0,    0,   37,    0,    0,   37,   37,   37,   37,    0,
   37,    0,   64,   37,    0,   37,   37,   37,    0,   37,
   37,   37,    0,   37,    0,   37,   37,   37,    0,   37,
   37,    0,   37,   37,   37,   37,   37,   37,    0,   37,
   37,   37,   37,   37,   37,   37,    0,   37,    0,    0,
   37,   37,   70,    0,    0,   70,    0,   70,    0,    0,
   70,    0,   70,   70,   70,    0,   70,   70,   70,    0,
   70,    0,   70,   70,   70,    0,   70,    0,    0,    0,
    0,   70,   70,   70,   70,    0,   70,   70,   70,   70,
   70,   70,   70,    0,   70,    0,   53,   70,   70,   53,
    0,   53,    0,    0,   53,    0,   53,   53,   53,    0,
   53,   53,   53,    0,   53,    0,   53,   53,   53,    0,
   53,    0,    0,    0,    0,   53,   53,   53,   53,    0,
   53,   53,   53,   53,   53,   53,   53,    0,   53,    0,
   55,   53,   53,   55,    0,   55,    0,    0,   55,    0,
   55,   55,   55,    0,   55,   55,   55,    0,   55,    0,
   55,   55,   55,    0,   55,    0,    0,    0,    0,   55,
   55,   55,   55,    0,   55,   55,   55,   55,   55,   55,
   55,    0,   55,    0,   63,   55,   55,   63,    0,   63,
    0,    0,   63,    0,   63,   63,   63,    0,   63,   63,
   63,    0,   63,    0,   63,   63,   63,    0,   63,   77,
   77,   77,   77,   77,   77,    0,    0,    0,   63,   63,
   63,   63,   63,   63,   63,    0,   63,    0,   60,   63,
   63,   60,    0,   60,    0,    0,   60,    0,   60,   60,
   60,    0,   60,   60,   60,    0,   60,    0,   60,   60,
   60,    0,   60,   76,   76,   76,   76,   76,   76,    0,
    0,    0,   60,   60,   60,   60,   60,   60,   60,    0,
   60,    0,   61,   60,   60,   61,    0,   61,    0,    0,
   61,    0,   61,   61,   61,    0,   61,   61,   61,    0,
   61,    0,   61,   61,   61,    0,   61,   75,   75,   75,
   75,   75,   75,    0,    0,    0,   61,   61,   61,   61,
   61,   61,   61,    0,   61,    0,   75,   61,   61,   75,
    0,   75,    0,    0,    0,    0,   75,   75,   75,    0,
   75,   75,   75,    0,   75,    0,   75,   75,   75,    0,
   75,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   75,   75,   75,   75,   75,   75,   75,    0,   75,    0,
   57,   75,   75,   57,    0,   57,    0,    0,    0,    0,
   57,   57,   57,    0,   57,   57,   57,    0,   57,    0,
   57,   57,   57,    0,   57,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   68,    0,
   57,   68,   57,   68,    0,   57,   57,    0,   68,   68,
   68,    0,   68,   68,   68,    0,   68,    0,   68,   68,
   68,    0,   68,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   65,    0,   68,   65,
   68,   65,    0,   68,   68,    0,   65,   65,   65,    0,
   65,   65,   65,    0,   65,    0,   65,   65,   65,    0,
   65,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   66,    0,   65,   66,   65,   66,
    0,   65,   65,    0,   66,   66,   66,    0,   66,   66,
   66,    0,   66,    0,   66,   66,   66,    0,   66,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   25,    0,   66,   25,   66,    0,    0,   66,
   66,    0,   25,   25,   25,    0,   25,   25,   25,   22,
   25,  175,   25,   25,   25,    0,   25,    0,    0,   13,
   14,   15,    0,   23,   24,   25,    0,   26,    0,   32,
   27,   28,   32,   29,    0,    0,    0,   25,   25,   32,
   32,   32,    0,   32,   32,   32,   22,   32,    0,   32,
   32,   32,    0,   32,   30,   31,   13,   14,   15,    0,
   23,   24,   25,    0,   26,    0,   24,   27,   28,   24,
   29,    0,    0,    0,   32,   32,   24,   24,   24,    0,
   24,   24,   24,    0,   24,    0,   24,   24,   24,    0,
   24,   30,   31,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   24,   24,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         17,
    3,   23,  258,   52,   52,   23,   30,   31,  259,   33,
  257,   35,  261,   23,   38,   39,   40,   41,   42,   43,
   49,   49,  257,   52,   52,  257,  260,  284,  285,  261,
   48,   49,  261,   57,   52,  264,  283,  261,   48,   49,
  272,   44,   52,  277,   66,   69,   68,  261,   66,   67,
   68,  283,  180,   75,  301,  183,   66,   75,   68,  108,
  108,   45,   91,   91,  261,   75,    4,    5,  300,  301,
    8,    9,  261,   91,  286,  287,  288,  289,  257,  259,
   23,   91,  261,  262,  113,  113,  282,  257,  284,  285,
  108,  261,  261,  272,  260,  113,  264,  283,  108,  283,
  149,  149,  272,  113,  283,   48,   49,  262,  262,   52,
  128,  257,  297,  283,  265,  261,  260,  262,  128,  148,
  148,  300,  301,   66,  146,   68,  272,  262,  146,  262,
  148,  149,   75,  267,  268,  269,  146,  283,  148,  149,
  162,  262,  160,   23,  162,  163,  262,  171,   91,   87,
  262,  175,  162,  163,  300,  301,  262,  262,  142,  263,
  144,  299,  266,  259,  274,  108,  270,  280,   48,   49,
  113,  262,   52,  276,  261,  159,  267,  268,  269,  299,
  286,  287,  288,  289,  260,  128,   66,  298,   68,  173,
  174,  265,  257,  262,  262,   75,  261,  277,  302,  303,
  265,  262,  277,  146,  260,  148,  149,  272,    0,  260,
  262,   91,  286,  287,  288,  289,  260,  277,  283,  162,
  163,  291,  292,  293,  294,  295,  296,  260,  108,  136,
  135,  257,  128,  113,  260,  261,  262,   24,   70,  160,
   -1,  267,  268,  269,   -1,  271,  272,  273,  128,  275,
   -1,  277,  278,  279,   -1,  281,  282,   -1,  284,  285,
  291,  292,  293,  294,  295,  296,  146,   -1,  148,  149,
   -1,   -1,  298,   -1,   -1,  257,  302,  303,  260,   -1,
  262,   -1,  162,  265,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,
  282,   -1,  284,  285,  286,  287,  288,  289,   -1,  291,
  292,  293,  294,  295,  296,  297,   -1,  299,   -1,   -1,
  302,  303,  257,   -1,   -1,  260,   -1,  262,   -1,   -1,
  265,   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,
  275,   -1,  277,  278,  279,   -1,  281,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,   -1,  291,  292,  293,  294,
  295,  296,  297,   -1,  299,   -1,  257,  302,  303,  260,
   -1,  262,   -1,   -1,  265,   -1,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,
  281,   -1,   -1,   -1,   -1,  286,  287,  288,  289,   -1,
  291,  292,  293,  294,  295,  296,  297,   -1,  299,   -1,
  257,  302,  303,  260,   -1,  262,   -1,   -1,  265,   -1,
  267,  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,
  277,  278,  279,   -1,  281,   -1,   -1,   -1,   -1,  286,
  287,  288,  289,   -1,  291,  292,  293,  294,  295,  296,
  297,   -1,  299,   -1,  257,  302,  303,  260,   -1,  262,
   -1,   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,
  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,  291,
  292,  293,  294,  295,  296,   -1,   -1,   -1,  291,  292,
  293,  294,  295,  296,  297,   -1,  299,   -1,  257,  302,
  303,  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,   -1,  277,  278,
  279,   -1,  281,  291,  292,  293,  294,  295,  296,   -1,
   -1,   -1,  291,  292,  293,  294,  295,  296,  297,   -1,
  299,   -1,  257,  302,  303,  260,   -1,  262,   -1,   -1,
  265,   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,
  275,   -1,  277,  278,  279,   -1,  281,  291,  292,  293,
  294,  295,  296,   -1,   -1,   -1,  291,  292,  293,  294,
  295,  296,  297,   -1,  299,   -1,  257,  302,  303,  260,
   -1,  262,   -1,   -1,   -1,   -1,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,
  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  291,  292,  293,  294,  295,  296,  297,   -1,  299,   -1,
  257,  302,  303,  260,   -1,  262,   -1,   -1,   -1,   -1,
  267,  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,
  277,  278,  279,   -1,  281,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,
  297,  260,  299,  262,   -1,  302,  303,   -1,  267,  268,
  269,   -1,  271,  272,  273,   -1,  275,   -1,  277,  278,
  279,   -1,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,  297,  260,
  299,  262,   -1,  302,  303,   -1,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,
  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,   -1,  297,  260,  299,  262,
   -1,  302,  303,   -1,  267,  268,  269,   -1,  271,  272,
  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,   -1,  297,  260,  299,   -1,   -1,  302,
  303,   -1,  267,  268,  269,   -1,  271,  272,  273,  257,
  275,  259,  277,  278,  279,   -1,  281,   -1,   -1,  267,
  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,  257,
  278,  279,  260,  281,   -1,   -1,   -1,  302,  303,  267,
  268,  269,   -1,  271,  272,  273,  257,  275,   -1,  277,
  278,  279,   -1,  281,  302,  303,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  257,  278,  279,  260,
  281,   -1,   -1,   -1,  302,  303,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,
  281,  302,  303,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  302,  303,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=303;
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
"PARA","OP_ATRIBUICAO","NUMERICO","OP_INCREMENTO","OP_DECREMENTO","OP_SOMA",
"OP_SUB","OP_DIV","OP_MULT","OP_MOD","OP_IGUALDADE","OP_DIFERENTE","OP_MAIOR",
"OP_MENOR","OP_MAIOR_IGUAL","OP_MENOR_IGUAL","VIRGULA","DOIS_PONTOS",
"PONTO_VIRGULA","STRING","CHAR","COMENTARIO_LINHA","COMENTARIO_BLOCO",
};
final static String yyrule[] = {
"$accept : inicio",
"inicio : programa",
"programa : inclusao programa",
"programa : funcao_principal programa",
"programa : FUNCAO declaracao atributos bloco programa",
"programa : COMENTARIO_LINHA programa",
"programa : COMENTARIO_BLOCO programa",
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
"comandos : cmd_for comandos",
"comandos : COMENTARIO_LINHA comandos",
"comandos : COMENTARIO_BLOCO comandos",
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
"cmd_for : PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA incremento FECHA_PARENTESES bloco",
"cmd_for : PARA ABRE_PARENTESES atribuicao PONTO_VIRGULA expressoes PONTO_VIRGULA decremento FECHA_PARENTESES bloco",
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

//#line 213 "inicioCT.y"

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
//#line 592 "Parser.java"
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
//#line 86 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 88 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 89 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 90 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 91 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 7:
//#line 93 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 8:
//#line 95 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval + "\n"; }
break;
case 9:
//#line 97 "inicioCT.y"
{ yyval.sval = "{\n " + val_peek(1).sval + "}"; }
break;
case 10:
//#line 99 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 11:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 12:
//#line 102 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 13:
//#line 103 "inicioCT.y"
{ yyval.sval = "return " + val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 14:
//#line 104 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + ";\n " + val_peek(0).sval; }
break;
case 15:
//#line 105 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 16:
//#line 106 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 17:
//#line 107 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 18:
//#line 108 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 19:
//#line 109 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 20:
//#line 110 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 21:
//#line 111 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 22:
//#line 112 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 23:
//#line 114 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n "; }
break;
case 24:
//#line 115 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 25:
//#line 116 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n  "; }
break;
case 26:
//#line 118 "inicioCT.y"
{ yyval.sval = "switch(" + val_peek(4).sval + "){\n  " + val_peek(1).sval + "\n }\n "; }
break;
case 27:
//#line 120 "inicioCT.y"
{ yyval.sval = "case " + val_peek(6).sval + ":{\n   " + val_peek(3).sval + "  break;\n}" + val_peek(0).sval; }
break;
case 28:
//#line 121 "inicioCT.y"
{ yyval.sval = "case " + val_peek(5).sval + ":{\n   " + val_peek(2).sval + "  break;\n}"; }
break;
case 29:
//#line 122 "inicioCT.y"
{ yyval.sval = "case " + val_peek(4).sval + ":\n   " + val_peek(2).sval + "  break;\n  " + val_peek(0).sval; }
break;
case 30:
//#line 123 "inicioCT.y"
{ yyval.sval = "case " + val_peek(3).sval + ":\n   " + val_peek(1).sval + "  break;"; }
break;
case 31:
//#line 125 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 32:
//#line 126 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n  "; }
break;
case 33:
//#line 128 "inicioCT.y"
{ yyval.sval = "do{\n  " + val_peek(5).sval + "}while(" + val_peek(1).sval + ");\n "; }
break;
case 34:
//#line 130 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 35:
//#line 131 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n "; }
break;
case 36:
//#line 133 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 37:
//#line 135 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 38:
//#line 136 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 39:
//#line 138 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 40:
//#line 139 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 41:
//#line 140 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 42:
//#line 142 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 43:
//#line 143 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 44:
//#line 145 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 45:
//#line 146 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 46:
//#line 148 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 47:
//#line 149 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 48:
//#line 151 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 152 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 50:
//#line 154 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 51:
//#line 155 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 52:
//#line 156 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 53:
//#line 158 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 54:
//#line 159 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 55:
//#line 161 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 56:
//#line 162 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 57:
//#line 164 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 165 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 59:
//#line 167 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 60:
//#line 168 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 61:
//#line 169 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 62:
//#line 170 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 63:
//#line 171 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 64:
//#line 173 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 65:
//#line 174 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 66:
//#line 175 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 67:
//#line 176 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 68:
//#line 177 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 69:
//#line 179 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 70:
//#line 180 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 71:
//#line 181 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 72:
//#line 182 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 73:
//#line 183 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 74:
//#line 184 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 75:
//#line 186 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 76:
//#line 187 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 188 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 78:
//#line 189 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 79:
//#line 190 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 80:
//#line 191 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 81:
//#line 193 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 82:
//#line 194 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 83:
//#line 195 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 84:
//#line 197 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 85:
//#line 198 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 86:
//#line 199 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 87:
//#line 200 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 88:
//#line 202 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 89:
//#line 203 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 90:
//#line 204 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 91:
//#line 205 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 92:
//#line 206 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 93:
//#line 207 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 94:
//#line 209 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 95:
//#line 210 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 96:
//#line 211 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 1125 "Parser.java"
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
