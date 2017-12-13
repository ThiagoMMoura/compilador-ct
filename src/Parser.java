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
    0,    3,    3,    3,    3,    3,    3,    4,   12,   31,
   32,    5,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    6,    6,   24,   24,   24,   25,   26,   26,
   26,   26,   33,   34,   27,   27,   28,   29,   29,    7,
   13,   13,   14,   14,   14,    9,    9,   23,   23,   10,
   10,   11,   11,   19,   19,   19,   20,   20,   21,   21,
   15,   15,   16,   16,   16,   16,   16,   17,   17,   17,
   17,   17,   22,   22,   22,   22,   22,   22,   18,   18,
   18,   18,   18,   18,   30,   30,   30,    2,    2,    2,
    2,    2,    1,    1,    1,    1,    1,    1,    8,    8,
    8,
};
final static short yylen[] = {                            2,
    1,    2,    2,    5,    2,    2,    0,    2,    3,    1,
    1,    2,    2,    2,    3,    2,    2,    2,    2,    2,
    2,    2,    2,    0,    7,    5,    4,    7,    8,    7,
    6,    5,    1,    1,    5,    4,    8,    9,    9,    2,
    1,    2,    2,    3,    3,    2,    3,    2,    2,    2,
    3,    1,    3,    3,    1,    1,    2,    3,    2,    3,
    1,    1,    3,    3,    5,    5,    3,    3,    3,    5,
    5,    3,    1,    1,    1,    1,    1,    3,    1,    1,
    1,    1,    3,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    1,    0,    0,   10,
    8,    0,   12,   99,  100,  101,    0,    0,    5,    6,
    3,    2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   55,   56,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   40,    0,    0,
   48,   42,    0,   73,   82,   81,    0,    0,   80,   62,
    0,   75,   76,    0,   77,   49,    0,    0,    0,    0,
    0,   22,   23,   11,    9,   13,    0,    0,    0,   14,
   16,   17,   18,   19,   20,   21,   46,    0,    0,   50,
    0,    0,    0,   43,    0,    0,    0,    0,    0,    0,
    0,    0,   15,   93,   94,   95,   96,   97,   98,    0,
   88,   89,   90,   91,   92,    0,    0,   86,   85,   87,
    0,    0,    0,    0,   54,   58,   60,   47,    4,   51,
    0,    0,    0,   45,   44,   84,   83,    0,    0,   78,
   68,    0,   63,    0,    0,    0,    0,    0,    0,   53,
    0,    0,    0,    0,   35,    0,    0,   66,    0,   71,
    0,    0,    0,    0,    0,    0,   25,    0,   28,    0,
    0,    0,    0,    0,    0,   37,    0,    0,    0,    0,
   38,   39,    0,   34,    0,    0,   31,    0,   29,
};
final static short yydgoto[] = {                          6,
  110,  116,    7,    8,    9,   33,   34,   18,   46,   51,
   91,   11,   35,   52,   92,   59,   60,   61,   36,   37,
   38,   64,   39,   40,   41,  164,   42,   43,   44,  121,
   12,   75,  175,  185,
};
final static short yysindex[] = {                       -63,
 -250, -225, -211,  -63,  -63,    0,    0,  -63,  -63,    0,
    0,  569,    0,    0,    0,    0, -216, -209,    0,    0,
    0,    0, -236,  -86, -214, -208, -192, -187, -250, -179,
  569,  569, -172,  569, -112,  569,    0,    0,  569,  569,
  569,  569,  569,  569, -158, -250, -175,    0, -106, -171,
    0,    0,  301,    0,    0,    0, -217,  569,    0,    0,
  178,    0,    0,  -52,    0,    0,  -86, -202,  -86,  569,
 -209,    0,    0,    0,    0,    0,  -86, -181, -180,    0,
    0,    0,    0,    0,    0,    0,    0, -157,  -63,    0,
 -142, -170, -166,    0, -143,  -39, -137, -132, -130, -124,
  178, -210,    0,    0,    0,    0,    0,    0,    0,  -86,
    0,    0,    0,    0,    0, -166, -123,    0,    0,    0,
 -121, -109, -172, -145,    0,    0,    0,    0,    0,    0,
  -86, -130, -210,    0,    0,    0,    0,  -52,  178,    0,
    0,  178,    0,  -52, -250, -250, -250, -122,  -86,    0,
 -166,  -86, -115, -107,    0,  -97, -125,    0,  -52,    0,
  178, -250, -202, -172,  -86, -209,    0, -118,    0,  -80,
 -217,  -79,  -78, -250,  569,    0, -250, -250,  569,  -92,
    0,    0,  -90,    0, -107, -172,    0, -107,    0,
};
final static short yyrindex[] = {                       176,
    0,    0,    0,  176,  176,    0,    0,  176,  176,    0,
    0,  -72,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   11,    0,    0,    0,    0,    0,    0,    0,
 -237, -237,    0, -237,    0, -237,    0,    0, -237, -237,
 -237, -237, -237, -237,    0,    0,  363,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   55, -237,    0,    0,
  400,    0,    0,  319,    0,    0,    0,    0,    0,  -72,
    0,    0,    0,    0,    0,    0,    0,   99,  143,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  176,    0,
    0,  -71,    0,    0,    0,    0,  222,  312,  358,    0,
    0,  391,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  187,  438,    0,
    0,  476,    0,  231,  552,    0,  589,    0,    0,    0,
    0,    0,  626,    0,    0,    0,    0,    0,  275,    0,
  514,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -48,  -87,    0,    0,    0,  -87,    0,
    0,    0,    0,    0,  -68,    0,    0,  -67,    0,
};
final static short yygindex[] = {                         0,
   62,   64,   34,    0,    0,  -29,   21,    0,    0,  179,
   75,  -10,  -18,    0,   -5,  -33,  -45,  -37,  137,  -23,
   47,  -32,  112,    0,    0, -151,    0,    0,    0,   48,
  -25, -101,    0,    0,
};
final static int YYTABLESIZE=929;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         48,
   62,   72,   73,   70,   76,   57,   80,  100,   10,   81,
   82,   83,   84,   85,   86,  101,   95,   96,   58,   99,
  102,  148,   24,   17,   49,   62,   62,   50,  103,   62,
   57,   57,   13,  187,   57,   89,  189,   19,   20,   24,
  123,   21,   22,   62,   45,   62,   49,   47,   57,  120,
   57,  140,   67,   62,   47,   14,   15,   16,   57,  132,
  133,  117,  169,  122,  141,   88,   78,   79,   68,   62,
   63,  125,  142,   69,   57,  111,  112,  113,  114,  115,
  118,   71,  143,  144,  188,   23,   62,   74,   50,   93,
   23,   57,   62,   94,   93,   63,   63,   57,  119,   63,
   25,  126,  127,   87,  128,   25,  160,   62,   14,   15,
   16,   54,   57,   63,  161,   63,   54,  158,  159,  130,
  154,  134,  129,   63,  136,   62,  131,   62,   62,  137,
   57,  138,   57,   57,  153,   65,  155,  139,  145,   63,
  146,   62,  172,  157,  120,  180,   57,  171,  179,  183,
   23,  167,  147,  149,   53,   90,   63,  156,  162,  170,
   65,   65,   63,  165,   65,   25,  181,  182,  163,   77,
   23,   78,   79,  166,   53,    7,   54,   63,   65,  174,
   65,  176,  177,  178,  184,   25,  186,   24,   65,   24,
   52,   32,   30,   55,   56,   63,   54,   63,   63,    1,
  152,  151,    2,   66,   65,  150,    3,  124,   33,    0,
  168,   63,  173,   55,   56,    0,    0,    0,   33,   33,
   33,   65,   33,   33,   33,  135,   33,   65,   33,   33,
   33,    0,   33,  111,  112,  113,  114,  115,    4,    5,
    0,    0,   65,    0,    0,    0,  111,  112,  113,  114,
  115,    0,    0,   33,   33,    0,    0,    0,    0,    0,
   65,    0,   65,   65,    0,    0,    0,   41,    0,    0,
   41,    0,   41,    0,    0,   41,   65,   41,   41,   41,
    0,   41,   41,   41,    0,   41,    0,   41,   41,   41,
    0,   41,   41,    0,   41,   41,   41,   41,   41,   41,
   41,   41,   41,   41,   41,   41,   41,   41,    0,   41,
    0,   74,   41,   41,   74,    0,   74,    0,    0,   74,
    0,   74,   74,   74,    0,   74,   74,   74,    0,   74,
    0,   74,   74,   74,    0,   74,    0,    0,    0,    0,
   74,   74,   74,   74,   74,   74,   74,   74,   74,   74,
   74,   74,    0,   74,    0,   57,   74,   74,   57,    0,
   57,    0,    0,   57,    0,   57,   57,   57,    0,   57,
   57,   57,    0,   57,    0,   57,   57,   57,    0,   57,
    0,    0,    0,    0,   57,   57,   57,   57,   57,   57,
   57,   57,   57,   57,   57,   57,    0,   57,    0,   59,
   57,   57,   59,    0,   59,    0,    0,   59,    0,   59,
   59,   59,    0,   59,   59,   59,    0,   59,    0,   59,
   59,   59,    0,   59,    0,    0,    0,    0,   59,   59,
   59,   59,   59,   59,   59,   59,   59,   59,   59,   59,
    0,   59,    0,   67,   59,   59,   67,    0,   67,    0,
    0,   67,    0,   67,   67,   67,    0,   67,   67,   67,
    0,   67,    0,   67,   67,   67,    0,   67,  104,  105,
  106,  107,  108,  109,    0,    0,    0,   67,   67,   67,
   67,   67,   67,   67,    0,   67,    0,   64,   67,   67,
   64,    0,   64,    0,    0,   64,    0,   64,   64,   64,
    0,   64,   64,   64,    0,   64,    0,   64,   64,   64,
    0,   64,   82,   82,   82,   82,   82,   82,    0,    0,
    0,   64,   64,   64,   64,   64,   64,   64,    0,   64,
    0,   65,   64,   64,   65,    0,   65,    0,    0,   65,
    0,   65,   65,   65,    0,   65,   65,   65,    0,   65,
    0,   65,   65,   65,    0,   65,    0,   23,    0,    0,
    0,   53,    0,    0,    0,   65,   65,   65,   65,   65,
   65,   65,   25,   65,    0,   79,   65,   65,   79,    0,
   79,    0,    0,   54,    0,   79,   79,   79,    0,   79,
   79,   79,    0,   79,    0,   79,   79,   79,    0,   79,
   97,   98,   81,   81,   81,   81,   81,   81,    0,   79,
   79,   79,   79,   79,   79,   79,    0,   79,    0,   41,
   79,   79,   41,   41,   41,    0,    0,    0,    0,   41,
   41,   41,    0,   41,   41,   41,    0,   41,    0,   41,
   41,   41,    0,   41,   41,    0,   41,   41,   80,   80,
   80,   80,   80,   80,    0,    0,   61,    0,    0,   61,
   41,   61,    0,    0,   41,   41,   61,   61,   61,    0,
   61,   61,   61,    0,   61,    0,   61,   61,   61,    0,
   61,   79,   79,   79,   79,   79,   79,    0,    0,    0,
    0,    0,    0,    0,   72,    0,   61,   72,   61,   72,
    0,   61,   61,    0,   72,   72,   72,    0,   72,   72,
   72,    0,   72,    0,   72,   72,   72,    0,   72,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   69,    0,   72,   69,   72,   69,    0,   72,
   72,    0,   69,   69,   69,    0,   69,   69,   69,    0,
   69,    0,   69,   69,   69,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   70,    0,   69,   70,   69,   70,    0,   69,   69,    0,
   70,   70,   70,    0,   70,   70,   70,    0,   70,    0,
   70,   70,   70,    0,   70,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,    0,
   70,   27,   70,    0,    0,   70,   70,    0,   27,   27,
   27,    0,   27,   27,   27,   23,   27,    0,   27,   27,
   27,    0,   27,    0,    0,   14,   15,   16,    0,   24,
   25,   26,    0,   27,    0,   36,   28,   29,   36,   30,
    0,    0,    0,   27,   27,   36,   36,   36,    0,   36,
   36,   36,    0,   36,    0,   36,   36,   36,    0,   36,
   31,   32,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   26,    0,    0,   26,    0,    0,    0,    0,
   36,   36,   26,   26,   26,    0,   26,   26,   26,    0,
   26,    0,   26,   26,   26,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   26,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         18,
   24,   31,   32,   29,   34,   24,   36,   53,  259,   39,
   40,   41,   42,   43,   44,   53,   50,   50,   24,   53,
   53,  123,  260,    3,  261,   49,   50,  264,   58,   53,
   49,   50,  258,  185,   53,   46,  188,    4,    5,  277,
   70,    8,    9,   67,  261,   69,  261,  257,   67,   68,
   69,  262,  261,   77,  257,  267,  268,  269,   77,   93,
   93,   67,  164,   69,  110,   45,  284,  285,  261,   93,
   24,   77,  110,  261,   93,  286,  287,  288,  289,  290,
  283,  261,  116,  116,  186,  257,  110,  260,  264,  261,
  257,  110,  116,  265,  261,   49,   50,  116,  301,   53,
  272,  283,  283,  262,  262,  272,  152,  131,  267,  268,
  269,  283,  131,   67,  152,   69,  283,  151,  151,  262,
  146,  265,   89,   77,  262,  149,  297,  151,  152,  262,
  149,  262,  151,  152,  145,   24,  147,  262,  262,   93,
  262,  165,  166,  149,  163,  175,  165,  166,  174,  179,
  257,  162,  262,  299,  261,  262,  110,  280,  274,  165,
   49,   50,  116,  261,   53,  272,  177,  178,  276,  282,
  257,  284,  285,  299,  261,    0,  283,  131,   67,  298,
   69,  262,  262,  262,  277,  272,  277,  260,   77,  277,
  262,  260,  260,  300,  301,  149,  283,  151,  152,  263,
  139,  138,  266,   25,   93,  131,  270,   71,  257,   -1,
  163,  165,  166,  300,  301,   -1,   -1,   -1,  267,  268,
  269,  110,  271,  272,  273,  265,  275,  116,  277,  278,
  279,   -1,  281,  286,  287,  288,  289,  290,  302,  303,
   -1,   -1,  131,   -1,   -1,   -1,  286,  287,  288,  289,
  290,   -1,   -1,  302,  303,   -1,   -1,   -1,   -1,   -1,
  149,   -1,  151,  152,   -1,   -1,   -1,  257,   -1,   -1,
  260,   -1,  262,   -1,   -1,  265,  165,  267,  268,  269,
   -1,  271,  272,  273,   -1,  275,   -1,  277,  278,  279,
   -1,  281,  282,   -1,  284,  285,  286,  287,  288,  289,
  290,  291,  292,  293,  294,  295,  296,  297,   -1,  299,
   -1,  257,  302,  303,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,  275,
   -1,  277,  278,  279,   -1,  281,   -1,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,  292,  293,  294,  295,
  296,  297,   -1,  299,   -1,  257,  302,  303,  260,   -1,
  262,   -1,   -1,  265,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,
   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,
  292,  293,  294,  295,  296,  297,   -1,  299,   -1,  257,
  302,  303,  260,   -1,  262,   -1,   -1,  265,   -1,  267,
  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,  277,
  278,  279,   -1,  281,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,  292,  293,  294,  295,  296,  297,
   -1,  299,   -1,  257,  302,  303,  260,   -1,  262,   -1,
   -1,  265,   -1,  267,  268,  269,   -1,  271,  272,  273,
   -1,  275,   -1,  277,  278,  279,   -1,  281,  291,  292,
  293,  294,  295,  296,   -1,   -1,   -1,  291,  292,  293,
  294,  295,  296,  297,   -1,  299,   -1,  257,  302,  303,
  260,   -1,  262,   -1,   -1,  265,   -1,  267,  268,  269,
   -1,  271,  272,  273,   -1,  275,   -1,  277,  278,  279,
   -1,  281,  291,  292,  293,  294,  295,  296,   -1,   -1,
   -1,  291,  292,  293,  294,  295,  296,  297,   -1,  299,
   -1,  257,  302,  303,  260,   -1,  262,   -1,   -1,  265,
   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,  275,
   -1,  277,  278,  279,   -1,  281,   -1,  257,   -1,   -1,
   -1,  261,   -1,   -1,   -1,  291,  292,  293,  294,  295,
  296,  297,  272,  299,   -1,  257,  302,  303,  260,   -1,
  262,   -1,   -1,  283,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,
  300,  301,  291,  292,  293,  294,  295,  296,   -1,  291,
  292,  293,  294,  295,  296,  297,   -1,  299,   -1,  257,
  302,  303,  260,  261,  262,   -1,   -1,   -1,   -1,  267,
  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,  277,
  278,  279,   -1,  281,  282,   -1,  284,  285,  291,  292,
  293,  294,  295,  296,   -1,   -1,  257,   -1,   -1,  260,
  298,  262,   -1,   -1,  302,  303,  267,  268,  269,   -1,
  271,  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,
  281,  291,  292,  293,  294,  295,  296,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,   -1,  297,  260,  299,  262,
   -1,  302,  303,   -1,  267,  268,  269,   -1,  271,  272,
  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,   -1,  297,  260,  299,  262,   -1,  302,
  303,   -1,  267,  268,  269,   -1,  271,  272,  273,   -1,
  275,   -1,  277,  278,  279,   -1,  281,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,   -1,  297,  260,  299,  262,   -1,  302,  303,   -1,
  267,  268,  269,   -1,  271,  272,  273,   -1,  275,   -1,
  277,  278,  279,   -1,  281,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,
  297,  260,  299,   -1,   -1,  302,  303,   -1,  267,  268,
  269,   -1,  271,  272,  273,  257,  275,   -1,  277,  278,
  279,   -1,  281,   -1,   -1,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  257,  278,  279,  260,  281,
   -1,   -1,   -1,  302,  303,  267,  268,  269,   -1,  271,
  272,  273,   -1,  275,   -1,  277,  278,  279,   -1,  281,
  302,  303,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,   -1,   -1,  260,   -1,   -1,   -1,   -1,
  302,  303,  267,  268,  269,   -1,  271,  272,  273,   -1,
  275,   -1,  277,  278,  279,   -1,  281,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  302,  303,
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
"operador_aritmetico : OP_MOD",
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

//#line 226 "inicioCT.y"

	// Referencia ao JFlex
	private Yylex lexer;
        public int dist = 0;

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

        public String indenta(int qtd){
            String str = "";
            for(int i=0;i<qtd;i++){
                str += "  ";
            }
            return str;
        }
//#line 614 "Parser.java"
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
//#line 90 "inicioCT.y"
{ System.out.println(val_peek(0).sval); }
break;
case 2:
//#line 92 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 3:
//#line 93 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 4:
//#line 94 "inicioCT.y"
{ yyval.sval = val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 5:
//#line 95 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 6:
//#line 96 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 7:
//#line 97 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 8:
//#line 99 "inicioCT.y"
{ yyval.sval = "int main() " + val_peek(0).sval + "\n"; }
break;
case 9:
//#line 101 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval;}
break;
case 10:
//#line 103 "inicioCT.y"
{ dist++; yyval.sval = "{\n"; }
break;
case 11:
//#line 105 "inicioCT.y"
{ dist--; yyval.sval = indenta(dist) + "}"; }
break;
case 12:
//#line 107 "inicioCT.y"
{ yyval.sval = "#include " + val_peek(0).sval; }
break;
case 13:
//#line 109 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 14:
//#line 110 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 15:
//#line 111 "inicioCT.y"
{ yyval.sval = indenta(dist) + "return " + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 16:
//#line 112 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + ";\n" + val_peek(0).sval; }
break;
case 17:
//#line 113 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 18:
//#line 114 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 19:
//#line 115 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 20:
//#line 116 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval ; }
break;
case 21:
//#line 117 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + val_peek(0).sval; }
break;
case 22:
//#line 118 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + "\n" + val_peek(0).sval ; }
break;
case 23:
//#line 119 "inicioCT.y"
{ yyval.sval = indenta(dist) + val_peek(1).sval + "\n" + val_peek(0).sval ; }
break;
case 24:
//#line 120 "inicioCT.y"
{ yyval.sval = ""; }
break;
case 25:
//#line 122 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "else" + val_peek(0).sval + "\n"; }
break;
case 26:
//#line 123 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 27:
//#line 124 "inicioCT.y"
{ yyval.sval = "if(" + val_peek(1).sval + ")\n" + indenta(1); }
break;
case 28:
//#line 126 "inicioCT.y"
{ yyval.sval = "switch(" + val_peek(4).sval + ")" + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval + "\n"; }
break;
case 29:
//#line 128 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(6).sval + ":" + val_peek(4).sval + val_peek(3).sval + indenta(dist) + "break;\n" + val_peek(1).sval + val_peek(0).sval; }
break;
case 30:
//#line 129 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(5).sval + ":" + val_peek(3).sval + val_peek(2).sval + indenta(dist) + "break;\n" + val_peek(0).sval; }
break;
case 31:
//#line 130 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(4).sval + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + "\n" + val_peek(0).sval; }
break;
case 32:
//#line 131 "inicioCT.y"
{ yyval.sval = indenta(dist) + "case " + val_peek(3).sval + val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 33:
//#line 133 "inicioCT.y"
{ dist++; yyval.sval = ":\n"; }
break;
case 34:
//#line 135 "inicioCT.y"
{ yyval.sval = indenta(dist) + "break;"; dist--; }
break;
case 35:
//#line 137 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 36:
//#line 138 "inicioCT.y"
{ yyval.sval = "while(" + val_peek(1).sval + ")\n"; }
break;
case 37:
//#line 140 "inicioCT.y"
{ yyval.sval = "do" + val_peek(6).sval + val_peek(5).sval + val_peek(4).sval + "while(" + val_peek(1).sval + ");\n"; }
break;
case 38:
//#line 142 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 39:
//#line 143 "inicioCT.y"
{ yyval.sval = "for(" + val_peek(6).sval + ";" + val_peek(4).sval + ";" + val_peek(2).sval + ")" + val_peek(0).sval + "\n"; }
break;
case 40:
//#line 145 "inicioCT.y"
{  yyval.sval = val_peek(1).sval + val_peek(0).sval;  }
break;
case 41:
//#line 147 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 42:
//#line 148 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 43:
//#line 150 "inicioCT.y"
{ yyval.sval = "[]"; }
break;
case 44:
//#line 151 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 45:
//#line 152 "inicioCT.y"
{ yyval.sval = "[" + val_peek(1).sval + "]"; }
break;
case 46:
//#line 154 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 47:
//#line 155 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 48:
//#line 157 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 49:
//#line 158 "inicioCT.y"
{ yyval.sval = "printf" + val_peek(0).sval; }
break;
case 50:
//#line 160 "inicioCT.y"
{ yyval.sval = "()"; }
break;
case 51:
//#line 161 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 52:
//#line 163 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 53:
//#line 164 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "," + val_peek(0).sval; }
break;
case 54:
//#line 166 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 55:
//#line 167 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 56:
//#line 168 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 57:
//#line 170 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "++"; }
break;
case 58:
//#line 171 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 59:
//#line 173 "inicioCT.y"
{ yyval.sval = val_peek(1).sval + "--"; }
break;
case 60:
//#line 174 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + "++" + val_peek(0).sval; }
break;
case 61:
//#line 176 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 177 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 179 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 64:
//#line 180 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 65:
//#line 181 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 66:
//#line 182 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 67:
//#line 183 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 68:
//#line 185 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 69:
//#line 186 "inicioCT.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 70:
//#line 187 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 71:
//#line 188 "inicioCT.y"
{ yyval.sval = "(" + val_peek(3).sval + ")" + val_peek(1).sval + val_peek(0).sval; }
break;
case 72:
//#line 189 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 73:
//#line 191 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 74:
//#line 192 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 193 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 76:
//#line 194 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 195 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 78:
//#line 196 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 79:
//#line 198 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 80:
//#line 199 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 81:
//#line 200 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 82:
//#line 201 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 83:
//#line 202 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 84:
//#line 203 "inicioCT.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 85:
//#line 205 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 86:
//#line 206 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 87:
//#line 207 "inicioCT.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 88:
//#line 209 "inicioCT.y"
{ yyval.sval = " + "; }
break;
case 89:
//#line 210 "inicioCT.y"
{ yyval.sval = " - "; }
break;
case 90:
//#line 211 "inicioCT.y"
{ yyval.sval = " / "; }
break;
case 91:
//#line 212 "inicioCT.y"
{ yyval.sval = " * "; }
break;
case 92:
//#line 213 "inicioCT.y"
{ yyval.sval = " % "; }
break;
case 93:
//#line 215 "inicioCT.y"
{ yyval.sval = " == "; }
break;
case 94:
//#line 216 "inicioCT.y"
{ yyval.sval = " != "; }
break;
case 95:
//#line 217 "inicioCT.y"
{ yyval.sval = " > "; }
break;
case 96:
//#line 218 "inicioCT.y"
{ yyval.sval = " < "; }
break;
case 97:
//#line 219 "inicioCT.y"
{ yyval.sval = " >= "; }
break;
case 98:
//#line 220 "inicioCT.y"
{ yyval.sval = " <= "; }
break;
case 99:
//#line 222 "inicioCT.y"
{ yyval.sval = "int "; }
break;
case 100:
//#line 223 "inicioCT.y"
{ yyval.sval = "float "; }
break;
case 101:
//#line 224 "inicioCT.y"
{ yyval.sval = "char "; }
break;
//#line 1167 "Parser.java"
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
