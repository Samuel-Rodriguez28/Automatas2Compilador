import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]* | 0 | "-" [1-9][0-9]*
Numero_Real = 0 | [1-9][0-9]* "." [1-9][0-9]* | "-" [1-9][0-9]* "." [1-9][0-9]*

/* String */
String = \"([^\"]*)\"

/* Booleano */
Boleano = true | false

%%


/* Identificador */
\${Identificador} { /*Ignore*/ }

/* Tipos de dato */
{String} { return textColor(yychar, yylength(), Color.magenta); }
{Numero} { return textColor(yychar, yylength(), Color.magenta); }
{Numero_Real} { return textColor(yychar, yylength(), Color.magenta); }
{Boleano} { return textColor(yychar, yylength(), Color.magenta); }
{Letra} { return textColor(yychar, yylength(), Color.magenta); }

/* Colores */
#[{Letra}|{Digito}]{6} { /*Ignore*/ }

/*Operadores de agrupación*/
"(" { return textColor(yychar, yylength(), Color.orange); }
")" { return textColor(yychar, yylength(), Color.orange); }
"{" { return textColor(yychar, yylength(), Color.orange); }
"}" { return textColor(yychar, yylength(), Color.orange); }
"[" { return textColor(yychar, yylength(), Color.orange); }
"]" { return textColor(yychar, yylength(), Color.orange); }

/*Signos de puntuación*/
"," { /*Ignore*/ }
"." { /*Ignore*/ }
";" { /*Ignore*/ }

/* Operador de asignación*/
"=" { /*Ignore*/ }

/*Operadores relacionales*/
"==" { return textColor(yychar, yylength(), Color.green); }
">=" { return textColor(yychar, yylength(), Color.green); }
"<=" { return textColor(yychar, yylength(), Color.green); }
">" { return textColor(yychar, yylength(), Color.green); }
"<" { return textColor(yychar, yylength(), Color.green); }
"!=" { return textColor(yychar, yylength(), Color.green); }

/*Palabras reservadas*/
if { return textColor(yychar, yylength(), Color.blue); }
else { return textColor(yychar, yylength(), Color.blue); }
while { return textColor(yychar, yylength(), Color.blue); }
do { return textColor(yychar, yylength(), Color.blue); }
int { return textColor(yychar, yylength(), Color.blue); }
String { return textColor(yychar, yylength(), Color.blue); }
float { return textColor(yychar, yylength(), Color.blue); }
char { return textColor(yychar, yylength(), Color.blue); }
print { return textColor(yychar, yylength(), Color.blue); }

/* Operadores logicos */
"&&" { /*Ignore*/ }
"||" { /*Ignore*/ }
"!" { /*Ignore*/ }


/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

. { /* Ignorar */ }