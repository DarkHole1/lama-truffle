// Generated from src/main/java/com/lama/truffle/parser/Lama.g4 by ANTLR 4.12.0
package com.lama.truffle.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LamaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LamaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LamaParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(LamaParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(LamaParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#scopeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScopeExpression(LamaParser.ScopeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(LamaParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#variableDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinition(LamaParser.VariableDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#variableDefinitionSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinitionSequence(LamaParser.VariableDefinitionSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#variableDefinitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDefinitionItem(LamaParser.VariableDefinitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(LamaParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#functionArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArguments(LamaParser.FunctionArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(LamaParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LamaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#basicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicExpression(LamaParser.BasicExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#assignmentExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpr(LamaParser.AssignmentExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#listConstructorExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListConstructorExpr(LamaParser.ListConstructorExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#disjunctionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisjunctionExpr(LamaParser.DisjunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#conjunctionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConjunctionExpr(LamaParser.ConjunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#comparisonExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpr(LamaParser.ComparisonExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(LamaParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(LamaParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(LamaParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(LamaParser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(LamaParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#arrayExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(LamaParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#listExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExpression(LamaParser.ListExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#sExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSExpression(LamaParser.SExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#letExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpression(LamaParser.LetExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#ifExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpression(LamaParser.IfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#elsePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsePart(LamaParser.ElsePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#whileDoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileDoExpression(LamaParser.WhileDoExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#doWhileExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileExpression(LamaParser.DoWhileExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#forExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForExpression(LamaParser.ForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(LamaParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#consPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConsPattern(LamaParser.ConsPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#simplePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimplePattern(LamaParser.SimplePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#wildcardPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcardPattern(LamaParser.WildcardPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#sExprPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSExprPattern(LamaParser.SExprPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#arrayPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayPattern(LamaParser.ArrayPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#listPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListPattern(LamaParser.ListPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(LamaParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#caseBranches}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBranches(LamaParser.CaseBranchesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#caseBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBranch(LamaParser.CaseBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link LamaParser#infixDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixDefinition(LamaParser.InfixDefinitionContext ctx);
}