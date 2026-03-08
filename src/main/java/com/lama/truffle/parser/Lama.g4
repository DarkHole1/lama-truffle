grammar Lama;

// ==========================================
// PARSER RULES
// ==========================================

compilationUnit
    : importDecl* scopeExpression
    ;

importDecl
    : 'import' UIDENT ';'
    ;

scopeExpression
    : definition* expression?
    ;

definition
    : variableDefinition
    | functionDefinition
    | infixDefinition
    ;

variableDefinition
    : ('var' | 'public') variableDefinitionSequence ';'
    ;

variableDefinitionSequence
    : variableDefinitionItem (',' variableDefinitionItem)*
    ;

variableDefinitionItem
    : LIDENT ('=' basicExpression)?
    ;

functionDefinition
    : 'public'? 'fun' LIDENT '(' functionArguments ')' functionBody
    ;

functionArguments
    : (LIDENT (',' LIDENT)*)?
    ;

functionBody
    : '{' scopeExpression '}'
    ;

expression
    : basicExpression (';' expression)*
    ;

basicExpression
    : assignmentExpr
    ;

assignmentExpr
    : listConstructorExpr (':=' assignmentExpr)?  // right-associative
    ;

listConstructorExpr
    : disjunctionExpr (':' listConstructorExpr)?  // right-associative
    ;

disjunctionExpr
    : conjunctionExpr ('!!' conjunctionExpr)*  // left-associative
    ;

conjunctionExpr
    : comparisonExpr ('&&' comparisonExpr)*  // left-associative
    ;

comparisonExpr
    : additiveExpr (('==' | '!=' | '<=' | '<' | '>=' | '>') additiveExpr)?  // non-associative: single comparison only
    ;

additiveExpr
    : multiplicativeExpr (('+' | '-') multiplicativeExpr)*  // left-associative
    ;

multiplicativeExpr
    : unaryExpr (('*' | '/' | '%') unaryExpr)*  // left-associative
    ;

unaryExpr
    : '-'? postfixExpression
    ;

postfixExpression
    : primary
    | postfixExpression '(' (expression (',' expression)*)? ')'
    | postfixExpression '[' expression ']'
    ;

primary
    : DECIMAL
    | STRING
    | CHAR
    | LIDENT
    | 'true'
    | 'false'
    | 'infix' INFIX
    | 'fun' '(' functionArguments ')' functionBody
    | 'skip'
    | '(' scopeExpression ')'
    | listExpression
    | arrayExpression
    | sExpression
    | ifExpression
    | whileDoExpression
    | doWhileExpression
    | forExpression
    | caseExpression
    | letExpression
    ;

arrayExpression
    : '[' '[' (expression (',' expression)*)? ']' ']'
    ;

listExpression
    : '{' '[' (expression (',' expression)*)? ']' '}'
    ;

sExpression
    : UIDENT ('(' (expression (',' expression)*)? ')')?
    ;

letExpression
    : 'let' pattern '=' expression 'in' expression
    ;

ifExpression
    : 'if' expression 'then' scopeExpression elsePart? 'fi'
    ;

elsePart
    : 'elif' expression 'then' scopeExpression elsePart?
    | 'else' scopeExpression
    ;

whileDoExpression
    : 'while' expression 'do' scopeExpression 'od'
    ;

doWhileExpression
    : 'do' scopeExpression 'while' expression 'od'
    ;

forExpression
    : 'for' scopeExpression ',' expression ',' expression 'do' scopeExpression 'od'
    ;

pattern
    : consPattern
    | simplePattern
    ;

consPattern
    : simplePattern ':' pattern
    ;

simplePattern
    : wildcardPattern
    | sExprPattern
    | arrayPattern
    | listPattern
    | LIDENT ('@' pattern)?
    | '-'? DECIMAL
    | STRING
    | CHAR
    | 'true'
    | 'false'
    | '#' 'box'
    | '#' 'val'
    | '#' 'str'
    | '#' 'array'
    | '#' 'sexp'
    | '#' 'fun'
    | '(' pattern ')'
    ;

wildcardPattern
    : '_' 
    ;

sExprPattern
    : UIDENT ('(' (pattern (',' pattern)*)? ')')?
    ;

arrayPattern
    : '[' '[' (pattern (',' pattern)*)? ']' ']'
    ;

listPattern
    : '{' '[' (pattern (',' pattern)*)? ']' '}'
    ;

caseExpression
    : 'case' expression 'of' caseBranches 'esac'
    ;

caseBranches
    : caseBranch ('|' caseBranch)*
    ;

caseBranch
    : pattern '->' scopeExpression
    ;

infixDefinition
    : 'infix' INFIX '=' basicExpression ';' 
    ;

// ==========================================
// LEXER RULES
// ==========================================

// Keywords
IMPORT     : 'import';
VAR        : 'var';
PUBLIC     : 'public';
FUN        : 'fun';
TRUE       : 'true';
FALSE      : 'false';
SKIP_KW    : 'skip';
IF         : 'if';
THEN       : 'then';
ELSE       : 'else';
ELIF       : 'elif';
FI         : 'fi';
WHILE      : 'while';
DO         : 'do';
OD         : 'od';
FOR        : 'for';
CASE       : 'case';
OF         : 'of';
ESAC       : 'esac';
LET        : 'let';
IN         : 'in';
INFIX_KW   : 'infix';
BOX        : 'box';
VAL        : 'val';
STR        : 'str';
ARRAY_KW   : 'array';
SEXP       : 'sexp';

// Identifiers
UIDENT     : [A-Z][a-zA-Z0-9]*;
LIDENT     : [a-z][a-zA-Z0-9]*;

// Literals
DECIMAL    : [0-9]+;

STRING     : '"' (~["\\] | '\\' .)* '"';
CHAR       : '\'' (~['\\] | '\\' .)* '\'';

// Operators
// INFIX token retained only for infixDefinition rule.
// String literals in parser rules (':=', ':', '!!', etc.) generate
// implicit lexer tokens with higher priority, ensuring correct tokenization.
INFIX      : [+*/%$#@!|&^?<>:=\-]+;

// Whitespace & Comments
WS         : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;
