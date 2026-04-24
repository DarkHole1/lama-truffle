// Generated from /home/darkhole/Projects/itmo-devtools/lama-truffle/src/main/java/com/lama/truffle/parser/Lama.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LamaParser}.
 */
public interface LamaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LamaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(LamaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(LamaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(LamaParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(LamaParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#scopeExpression}.
	 * @param ctx the parse tree
	 */
	void enterScopeExpression(LamaParser.ScopeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#scopeExpression}.
	 * @param ctx the parse tree
	 */
	void exitScopeExpression(LamaParser.ScopeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(LamaParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(LamaParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinition(LamaParser.VariableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinition(LamaParser.VariableDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#variableDefinitionSequence}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinitionSequence(LamaParser.VariableDefinitionSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#variableDefinitionSequence}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinitionSequence(LamaParser.VariableDefinitionSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#variableDefinitionItem}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinitionItem(LamaParser.VariableDefinitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#variableDefinitionItem}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinitionItem(LamaParser.VariableDefinitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(LamaParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(LamaParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArguments(LamaParser.FunctionArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#functionArguments}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArguments(LamaParser.FunctionArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(LamaParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(LamaParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LamaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LamaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterBasicExpression(LamaParser.BasicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitBasicExpression(LamaParser.BasicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#assignmentExpr}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpr(LamaParser.AssignmentExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#assignmentExpr}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpr(LamaParser.AssignmentExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#listConstructorExpr}.
	 * @param ctx the parse tree
	 */
	void enterListConstructorExpr(LamaParser.ListConstructorExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#listConstructorExpr}.
	 * @param ctx the parse tree
	 */
	void exitListConstructorExpr(LamaParser.ListConstructorExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#disjunctionExpr}.
	 * @param ctx the parse tree
	 */
	void enterDisjunctionExpr(LamaParser.DisjunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#disjunctionExpr}.
	 * @param ctx the parse tree
	 */
	void exitDisjunctionExpr(LamaParser.DisjunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#conjunctionExpr}.
	 * @param ctx the parse tree
	 */
	void enterConjunctionExpr(LamaParser.ConjunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#conjunctionExpr}.
	 * @param ctx the parse tree
	 */
	void exitConjunctionExpr(LamaParser.ConjunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpr(LamaParser.ComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#comparisonExpr}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpr(LamaParser.ComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(LamaParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(LamaParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(LamaParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(LamaParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(LamaParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(LamaParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(LamaParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(LamaParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(LamaParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(LamaParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(LamaParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(LamaParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void enterListExpression(LamaParser.ListExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#listExpression}.
	 * @param ctx the parse tree
	 */
	void exitListExpression(LamaParser.ListExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#sExpression}.
	 * @param ctx the parse tree
	 */
	void enterSExpression(LamaParser.SExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#sExpression}.
	 * @param ctx the parse tree
	 */
	void exitSExpression(LamaParser.SExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#letExpression}.
	 * @param ctx the parse tree
	 */
	void enterLetExpression(LamaParser.LetExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#letExpression}.
	 * @param ctx the parse tree
	 */
	void exitLetExpression(LamaParser.LetExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression(LamaParser.IfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression(LamaParser.IfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void enterElsePart(LamaParser.ElsePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void exitElsePart(LamaParser.ElsePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#whileDoExpression}.
	 * @param ctx the parse tree
	 */
	void enterWhileDoExpression(LamaParser.WhileDoExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#whileDoExpression}.
	 * @param ctx the parse tree
	 */
	void exitWhileDoExpression(LamaParser.WhileDoExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#doWhileExpression}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileExpression(LamaParser.DoWhileExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#doWhileExpression}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileExpression(LamaParser.DoWhileExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#forExpression}.
	 * @param ctx the parse tree
	 */
	void enterForExpression(LamaParser.ForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#forExpression}.
	 * @param ctx the parse tree
	 */
	void exitForExpression(LamaParser.ForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(LamaParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(LamaParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#consPattern}.
	 * @param ctx the parse tree
	 */
	void enterConsPattern(LamaParser.ConsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#consPattern}.
	 * @param ctx the parse tree
	 */
	void exitConsPattern(LamaParser.ConsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#simplePattern}.
	 * @param ctx the parse tree
	 */
	void enterSimplePattern(LamaParser.SimplePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#simplePattern}.
	 * @param ctx the parse tree
	 */
	void exitSimplePattern(LamaParser.SimplePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#wildcardPattern}.
	 * @param ctx the parse tree
	 */
	void enterWildcardPattern(LamaParser.WildcardPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#wildcardPattern}.
	 * @param ctx the parse tree
	 */
	void exitWildcardPattern(LamaParser.WildcardPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#sExprPattern}.
	 * @param ctx the parse tree
	 */
	void enterSExprPattern(LamaParser.SExprPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#sExprPattern}.
	 * @param ctx the parse tree
	 */
	void exitSExprPattern(LamaParser.SExprPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#arrayPattern}.
	 * @param ctx the parse tree
	 */
	void enterArrayPattern(LamaParser.ArrayPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#arrayPattern}.
	 * @param ctx the parse tree
	 */
	void exitArrayPattern(LamaParser.ArrayPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#listPattern}.
	 * @param ctx the parse tree
	 */
	void enterListPattern(LamaParser.ListPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#listPattern}.
	 * @param ctx the parse tree
	 */
	void exitListPattern(LamaParser.ListPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(LamaParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(LamaParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#caseBranches}.
	 * @param ctx the parse tree
	 */
	void enterCaseBranches(LamaParser.CaseBranchesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#caseBranches}.
	 * @param ctx the parse tree
	 */
	void exitCaseBranches(LamaParser.CaseBranchesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#caseBranch}.
	 * @param ctx the parse tree
	 */
	void enterCaseBranch(LamaParser.CaseBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#caseBranch}.
	 * @param ctx the parse tree
	 */
	void exitCaseBranch(LamaParser.CaseBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link LamaParser#infixDefinition}.
	 * @param ctx the parse tree
	 */
	void enterInfixDefinition(LamaParser.InfixDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LamaParser#infixDefinition}.
	 * @param ctx the parse tree
	 */
	void exitInfixDefinition(LamaParser.InfixDefinitionContext ctx);
}