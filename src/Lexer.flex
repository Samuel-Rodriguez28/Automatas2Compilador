import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
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
Numero = 0 | [1-9][0-9]*
Numero_Real = 0 | [1-9][0-9]* "." [1-9][0-9]* | "-" [1-9][0-9]* "." [1-9][0-9]*

/* String */
String = \"([^\"]*)\"

/* Booleano */
Boleano = true | false

%%

/* Tipos de dato */
{String} { return token(yytext(), "TEXTO", yyline, yycolumn); }
{Numero} { return token(yytext(), "ENTERO", yyline, yycolumn); }
{Numero_Real} { return token(yytext(), "REAL", yyline, yycolumn); } 
{Boleano} { return token(yytext(), "BOLEANO", yyline, yycolumn); }
{Letra} { return token(yytext(), "CHARACTER", yyline, yycolumn); }

/* Colores */
#[{Letra}|{Digito}]{6} { return token(yytext(), "COLOR", yyline, yycolumn); }

/*Operadores de agrupación*/
"(" { return token(yytext(), "PARENTESIS_A", yyline, yycolumn); }
")" { return token(yytext(), "PARENTESIS_C", yyline, yycolumn); }
"{" { return token(yytext(), "LLAVE_A", yyline, yycolumn); }
"}" { return token(yytext(), "LLAVE_C", yyline, yycolumn); }
"[" { return token(yytext(), "CORCHETE_A", yyline, yycolumn); }
"]" { return token(yytext(), "CORCHETE_B", yyline, yycolumn); }

/*Operadores Aritméticos*/
"+" { return token(yytext(), "SUMA", yyline, yycolumn); }
"-" { return token(yytext(), "RESTA", yyline, yycolumn); }
"/" { return token(yytext(), "DIVISION", yyline, yycolumn); }
"%" { return token(yytext(), "MODULO", yyline, yycolumn); }
"*" { return token(yytext(), "MULTIPLICACION", yyline, yycolumn); }

/*Signos de puntuación*/
"," { return token(yytext(), "COMA", yyline, yycolumn); }
"." { return token(yytext(), "PUNTO", yyline, yycolumn); }
";" { return token(yytext(), "PUNTO_COMA", yyline, yycolumn); }

/* Operador de asignación*/
"=" { return token(yytext(), "IGUAL", yyline, yycolumn); }

/*Operadores relacionales*/
"==" { return token(yytext(), "IGUAL_IGUAL", yyline, yycolumn); }
">=" { return token(yytext(), "MAYOR_IGUAL", yyline, yycolumn); }
"<=" { return token(yytext(), "MENOR_IGUAL", yyline, yycolumn); }
">" { return token(yytext(), "MAYOR", yyline, yycolumn); }
"<" { return token(yytext(), "MENOR", yyline, yycolumn); }
"!=" { return token(yytext(), "DIFERENTE", yyline, yycolumn); }

/*Palabras reservadas*/
if { return token(yytext(), "IF", yyline, yycolumn); }
else { return token(yytext(), "ELSE", yyline, yycolumn); }
while { return token(yytext(), "WHILE", yyline, yycolumn); }
do { return token(yytext(), "DO", yyline, yycolumn); }
int { return token(yytext(), "INT", yyline, yycolumn); }
String { return token(yytext(), "STRING", yyline, yycolumn); }
float { return token(yytext(), "FLOAT", yyline, yycolumn); }
char { return token(yytext(), "CHAR", yyline, yycolumn); }
boolean { return token(yytext(), "BOOLEAN", yyline, yycolumn); }
print { return token(yytext(), "PRINT", yyline, yycolumn); }
readIn { return token(yytext(), "READIN", yyline, yycolumn); }
begin { return token(yytext(), "BEGIN", yyline, yycolumn); }
end { return token(yytext(), "END", yyline, yycolumn); }
public { return token(yytext(), "PUBLIC", yyline, yycolumn); }
private { return token(yytext(), "PRIVATE", yyline, yycolumn); }
static { return token(yytext(), "STATIC", yyline, yycolumn); }
void { return token(yytext(), "VOID", yyline, yycolumn); }
class { return token(yytext(), "CLASS", yyline, yycolumn); }
return { return token(yytext(), "RETURN", yyline, yycolumn); }
try { return token(yytext(), "TRY", yyline, yycolumn); }
catch { return token(yytext(), "CATCH", yyline, yycolumn); }

/* Operadores logicos */
"&&" { return token(yytext(), "AND", yyline, yycolumn); }
"||" { return token(yytext(), "OR", yyline, yycolumn); }
"!" { return token(yytext(), "NOT", yyline, yycolumn); }


/* Identificador */
{Identificador} { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/*Número Erroneo*/
0{Numero} { return token(yytext(), "ERROR_1", yyline, yycolumn); }

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

. { return token(yytext(), "ERROR", yyline, yycolumn); }