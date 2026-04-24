// Generated from /home/darkhole/Projects/itmo-devtools/lama-truffle/src/main/java/com/lama/truffle/parser/Lama.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LamaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, IMPORT=31, 
		VAR=32, PUBLIC=33, FUN=34, TRUE=35, FALSE=36, SKIP_KW=37, IF=38, THEN=39, 
		ELSE=40, ELIF=41, FI=42, WHILE=43, DO=44, OD=45, FOR=46, CASE=47, OF=48, 
		ESAC=49, LET=50, IN=51, INFIX_KW=52, BOX=53, VAL=54, STR=55, ARRAY_KW=56, 
		SEXP=57, UIDENT=58, LIDENT=59, DECIMAL=60, STRING=61, CHAR=62, INFIX=63, 
		WS=64, LINE_COMMENT=65;
	public static final int
		RULE_compilationUnit = 0, RULE_importDecl = 1, RULE_scopeExpression = 2, 
		RULE_definition = 3, RULE_variableDefinition = 4, RULE_variableDefinitionSequence = 5, 
		RULE_variableDefinitionItem = 6, RULE_functionDefinition = 7, RULE_functionArguments = 8, 
		RULE_functionBody = 9, RULE_expression = 10, RULE_basicExpression = 11, 
		RULE_assignmentExpr = 12, RULE_listConstructorExpr = 13, RULE_disjunctionExpr = 14, 
		RULE_conjunctionExpr = 15, RULE_comparisonExpr = 16, RULE_additiveExpr = 17, 
		RULE_multiplicativeExpr = 18, RULE_unaryExpr = 19, RULE_postfixExpression = 20, 
		RULE_primary = 21, RULE_arrayExpression = 22, RULE_listExpression = 23, 
		RULE_sExpression = 24, RULE_letExpression = 25, RULE_ifExpression = 26, 
		RULE_elsePart = 27, RULE_whileDoExpression = 28, RULE_doWhileExpression = 29, 
		RULE_forExpression = 30, RULE_pattern = 31, RULE_consPattern = 32, RULE_simplePattern = 33, 
		RULE_wildcardPattern = 34, RULE_sExprPattern = 35, RULE_arrayPattern = 36, 
		RULE_listPattern = 37, RULE_caseExpression = 38, RULE_caseBranches = 39, 
		RULE_caseBranch = 40, RULE_infixDefinition = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "importDecl", "scopeExpression", "definition", "variableDefinition", 
			"variableDefinitionSequence", "variableDefinitionItem", "functionDefinition", 
			"functionArguments", "functionBody", "expression", "basicExpression", 
			"assignmentExpr", "listConstructorExpr", "disjunctionExpr", "conjunctionExpr", 
			"comparisonExpr", "additiveExpr", "multiplicativeExpr", "unaryExpr", 
			"postfixExpression", "primary", "arrayExpression", "listExpression", 
			"sExpression", "letExpression", "ifExpression", "elsePart", "whileDoExpression", 
			"doWhileExpression", "forExpression", "pattern", "consPattern", "simplePattern", 
			"wildcardPattern", "sExprPattern", "arrayPattern", "listPattern", "caseExpression", 
			"caseBranches", "caseBranch", "infixDefinition"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "','", "'='", "'('", "')'", "'{'", "'}'", "':='", "':'", 
			"'!!'", "'&&'", "'=='", "'!='", "'<='", "'<'", "'>='", "'>'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'['", "']'", "'.'", "'@'", "'#'", "'_'", 
			"'|'", "'->'", "'import'", "'var'", "'public'", "'fun'", "'true'", "'false'", 
			"'skip'", "'if'", "'then'", "'else'", "'elif'", "'fi'", "'while'", "'do'", 
			"'od'", "'for'", "'case'", "'of'", "'esac'", "'let'", "'in'", "'infix'", 
			"'box'", "'val'", "'str'", "'array'", "'sexp'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "IMPORT", "VAR", "PUBLIC", 
			"FUN", "TRUE", "FALSE", "SKIP_KW", "IF", "THEN", "ELSE", "ELIF", "FI", 
			"WHILE", "DO", "OD", "FOR", "CASE", "OF", "ESAC", "LET", "IN", "INFIX_KW", 
			"BOX", "VAL", "STR", "ARRAY_KW", "SEXP", "UIDENT", "LIDENT", "DECIMAL", 
			"STRING", "CHAR", "INFIX", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lama.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LamaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompilationUnitContext extends ParserRuleContext {
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(84);
				importDecl();
				}
				}
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(90);
			scopeExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDeclContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(LamaParser.IMPORT, 0); }
		public TerminalNode UIDENT() { return getToken(LamaParser.UIDENT, 0); }
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(IMPORT);
			setState(93);
			match(UIDENT);
			setState(94);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeExpressionContext extends ParserRuleContext {
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ScopeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scopeExpression; }
	}

	public final ScopeExpressionContext scopeExpression() throws RecognitionException {
		ScopeExpressionContext _localctx = new ScopeExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_scopeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(96);
					definition();
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(103);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(102);
				expression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinitionContext extends ParserRuleContext {
		public VariableDefinitionContext variableDefinition() {
			return getRuleContext(VariableDefinitionContext.class,0);
		}
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public InfixDefinitionContext infixDefinition() {
			return getRuleContext(InfixDefinitionContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_definition);
		try {
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				variableDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				functionDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				infixDefinition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDefinitionContext extends ParserRuleContext {
		public VariableDefinitionSequenceContext variableDefinitionSequence() {
			return getRuleContext(VariableDefinitionSequenceContext.class,0);
		}
		public TerminalNode VAR() { return getToken(LamaParser.VAR, 0); }
		public TerminalNode PUBLIC() { return getToken(LamaParser.PUBLIC, 0); }
		public VariableDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinition; }
	}

	public final VariableDefinitionContext variableDefinition() throws RecognitionException {
		VariableDefinitionContext _localctx = new VariableDefinitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			_la = _input.LA(1);
			if ( !(_la==VAR || _la==PUBLIC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(111);
			variableDefinitionSequence();
			setState(112);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDefinitionSequenceContext extends ParserRuleContext {
		public List<VariableDefinitionItemContext> variableDefinitionItem() {
			return getRuleContexts(VariableDefinitionItemContext.class);
		}
		public VariableDefinitionItemContext variableDefinitionItem(int i) {
			return getRuleContext(VariableDefinitionItemContext.class,i);
		}
		public VariableDefinitionSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinitionSequence; }
	}

	public final VariableDefinitionSequenceContext variableDefinitionSequence() throws RecognitionException {
		VariableDefinitionSequenceContext _localctx = new VariableDefinitionSequenceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableDefinitionSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			variableDefinitionItem();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(115);
				match(T__1);
				setState(116);
				variableDefinitionItem();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDefinitionItemContext extends ParserRuleContext {
		public TerminalNode LIDENT() { return getToken(LamaParser.LIDENT, 0); }
		public BasicExpressionContext basicExpression() {
			return getRuleContext(BasicExpressionContext.class,0);
		}
		public VariableDefinitionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDefinitionItem; }
	}

	public final VariableDefinitionItemContext variableDefinitionItem() throws RecognitionException {
		VariableDefinitionItemContext _localctx = new VariableDefinitionItemContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableDefinitionItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(LIDENT);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(123);
				match(T__2);
				setState(124);
				basicExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TerminalNode FUN() { return getToken(LamaParser.FUN, 0); }
		public TerminalNode LIDENT() { return getToken(LamaParser.LIDENT, 0); }
		public FunctionArgumentsContext functionArguments() {
			return getRuleContext(FunctionArgumentsContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(LamaParser.PUBLIC, 0); }
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUBLIC) {
				{
				setState(127);
				match(PUBLIC);
				}
			}

			setState(130);
			match(FUN);
			setState(131);
			match(LIDENT);
			setState(132);
			match(T__3);
			setState(133);
			functionArguments();
			setState(134);
			match(T__4);
			setState(135);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionArgumentsContext extends ParserRuleContext {
		public List<TerminalNode> LIDENT() { return getTokens(LamaParser.LIDENT); }
		public TerminalNode LIDENT(int i) {
			return getToken(LamaParser.LIDENT, i);
		}
		public FunctionArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArguments; }
	}

	public final FunctionArgumentsContext functionArguments() throws RecognitionException {
		FunctionArgumentsContext _localctx = new FunctionArgumentsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIDENT) {
				{
				setState(137);
				match(LIDENT);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(138);
					match(T__1);
					setState(139);
					match(LIDENT);
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionBodyContext extends ParserRuleContext {
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(T__5);
			setState(148);
			scopeExpression();
			setState(149);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public BasicExpressionContext basicExpression() {
			return getRuleContext(BasicExpressionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			basicExpression();
			setState(156);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(152);
					match(T__0);
					setState(153);
					expression();
					}
					} 
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicExpressionContext extends ParserRuleContext {
		public AssignmentExprContext assignmentExpr() {
			return getRuleContext(AssignmentExprContext.class,0);
		}
		public BasicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicExpression; }
	}

	public final BasicExpressionContext basicExpression() throws RecognitionException {
		BasicExpressionContext _localctx = new BasicExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_basicExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			assignmentExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExprContext extends ParserRuleContext {
		public ListConstructorExprContext listConstructorExpr() {
			return getRuleContext(ListConstructorExprContext.class,0);
		}
		public AssignmentExprContext assignmentExpr() {
			return getRuleContext(AssignmentExprContext.class,0);
		}
		public AssignmentExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpr; }
	}

	public final AssignmentExprContext assignmentExpr() throws RecognitionException {
		AssignmentExprContext _localctx = new AssignmentExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignmentExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			listConstructorExpr();
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(162);
				match(T__7);
				setState(163);
				assignmentExpr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListConstructorExprContext extends ParserRuleContext {
		public DisjunctionExprContext disjunctionExpr() {
			return getRuleContext(DisjunctionExprContext.class,0);
		}
		public ListConstructorExprContext listConstructorExpr() {
			return getRuleContext(ListConstructorExprContext.class,0);
		}
		public ListConstructorExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listConstructorExpr; }
	}

	public final ListConstructorExprContext listConstructorExpr() throws RecognitionException {
		ListConstructorExprContext _localctx = new ListConstructorExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listConstructorExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			disjunctionExpr();
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(167);
				match(T__8);
				setState(168);
				listConstructorExpr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DisjunctionExprContext extends ParserRuleContext {
		public List<ConjunctionExprContext> conjunctionExpr() {
			return getRuleContexts(ConjunctionExprContext.class);
		}
		public ConjunctionExprContext conjunctionExpr(int i) {
			return getRuleContext(ConjunctionExprContext.class,i);
		}
		public DisjunctionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunctionExpr; }
	}

	public final DisjunctionExprContext disjunctionExpr() throws RecognitionException {
		DisjunctionExprContext _localctx = new DisjunctionExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_disjunctionExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			conjunctionExpr();
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(172);
					match(T__9);
					setState(173);
					conjunctionExpr();
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConjunctionExprContext extends ParserRuleContext {
		public List<ComparisonExprContext> comparisonExpr() {
			return getRuleContexts(ComparisonExprContext.class);
		}
		public ComparisonExprContext comparisonExpr(int i) {
			return getRuleContext(ComparisonExprContext.class,i);
		}
		public ConjunctionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunctionExpr; }
	}

	public final ConjunctionExprContext conjunctionExpr() throws RecognitionException {
		ConjunctionExprContext _localctx = new ConjunctionExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conjunctionExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			comparisonExpr();
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(180);
					match(T__10);
					setState(181);
					comparisonExpr();
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public ComparisonExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpr; }
	}

	public final ComparisonExprContext comparisonExpr() throws RecognitionException {
		ComparisonExprContext _localctx = new ComparisonExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comparisonExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			additiveExpr();
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(188);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 258048L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(189);
				additiveExpr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_additiveExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			multiplicativeExpr();
			setState(197);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(193);
					_la = _input.LA(1);
					if ( !(_la==T__17 || _la==T__18) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(194);
					multiplicativeExpr();
					}
					} 
				}
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExprContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_multiplicativeExpr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			unaryExpr();
			setState(205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(201);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7340032L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(202);
					unaryExpr();
					}
					} 
				}
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ParserRuleContext {
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unaryExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(208);
				match(T__18);
				}
			}

			setState(211);
			postfixExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixExpressionContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LIDENT() { return getToken(LamaParser.LIDENT, 0); }
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		return postfixExpression(0);
	}

	private PostfixExpressionContext postfixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, _parentState);
		PostfixExpressionContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_postfixExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(214);
			primary();
			}
			_ctx.stop = _input.LT(-1);
			setState(253);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(251);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(216);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(217);
						match(T__3);
						setState(226);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8941009187333734480L) != 0)) {
							{
							setState(218);
							expression();
							setState(223);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__1) {
								{
								{
								setState(219);
								match(T__1);
								setState(220);
								expression();
								}
								}
								setState(225);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(228);
						match(T__4);
						}
						break;
					case 2:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(229);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(230);
						match(T__22);
						setState(231);
						expression();
						setState(232);
						match(T__23);
						}
						break;
					case 3:
						{
						_localctx = new PostfixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
						setState(234);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(235);
						match(T__24);
						setState(236);
						match(LIDENT);
						setState(249);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
						case 1:
							{
							setState(237);
							match(T__3);
							setState(246);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8941009187333734480L) != 0)) {
								{
								setState(238);
								expression();
								setState(243);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==T__1) {
									{
									{
									setState(239);
									match(T__1);
									setState(240);
									expression();
									}
									}
									setState(245);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
							}

							setState(248);
							match(T__4);
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(LamaParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(LamaParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(LamaParser.CHAR, 0); }
		public TerminalNode LIDENT() { return getToken(LamaParser.LIDENT, 0); }
		public TerminalNode TRUE() { return getToken(LamaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LamaParser.FALSE, 0); }
		public TerminalNode INFIX_KW() { return getToken(LamaParser.INFIX_KW, 0); }
		public TerminalNode INFIX() { return getToken(LamaParser.INFIX, 0); }
		public TerminalNode FUN() { return getToken(LamaParser.FUN, 0); }
		public FunctionArgumentsContext functionArguments() {
			return getRuleContext(FunctionArgumentsContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode SKIP_KW() { return getToken(LamaParser.SKIP_KW, 0); }
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public ListExpressionContext listExpression() {
			return getRuleContext(ListExpressionContext.class,0);
		}
		public ArrayExpressionContext arrayExpression() {
			return getRuleContext(ArrayExpressionContext.class,0);
		}
		public SExpressionContext sExpression() {
			return getRuleContext(SExpressionContext.class,0);
		}
		public IfExpressionContext ifExpression() {
			return getRuleContext(IfExpressionContext.class,0);
		}
		public WhileDoExpressionContext whileDoExpression() {
			return getRuleContext(WhileDoExpressionContext.class,0);
		}
		public DoWhileExpressionContext doWhileExpression() {
			return getRuleContext(DoWhileExpressionContext.class,0);
		}
		public ForExpressionContext forExpression() {
			return getRuleContext(ForExpressionContext.class,0);
		}
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public LetExpressionContext letExpression() {
			return getRuleContext(LetExpressionContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_primary);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				match(DECIMAL);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(STRING);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(258);
				match(CHAR);
				}
				break;
			case LIDENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(259);
				match(LIDENT);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(260);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(261);
				match(FALSE);
				}
				break;
			case INFIX_KW:
				enterOuterAlt(_localctx, 7);
				{
				setState(262);
				match(INFIX_KW);
				setState(263);
				match(INFIX);
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 8);
				{
				setState(264);
				match(FUN);
				setState(265);
				match(T__3);
				setState(266);
				functionArguments();
				setState(267);
				match(T__4);
				setState(268);
				functionBody();
				}
				break;
			case SKIP_KW:
				enterOuterAlt(_localctx, 9);
				{
				setState(270);
				match(SKIP_KW);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 10);
				{
				setState(271);
				match(T__3);
				setState(272);
				scopeExpression();
				setState(273);
				match(T__4);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 11);
				{
				setState(275);
				listExpression();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 12);
				{
				setState(276);
				arrayExpression();
				}
				break;
			case UIDENT:
				enterOuterAlt(_localctx, 13);
				{
				setState(277);
				sExpression();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 14);
				{
				setState(278);
				ifExpression();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 15);
				{
				setState(279);
				whileDoExpression();
				}
				break;
			case DO:
				enterOuterAlt(_localctx, 16);
				{
				setState(280);
				doWhileExpression();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 17);
				{
				setState(281);
				forExpression();
				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 18);
				{
				setState(282);
				caseExpression();
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 19);
				{
				setState(283);
				letExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayExpression; }
	}

	public final ArrayExpressionContext arrayExpression() throws RecognitionException {
		ArrayExpressionContext _localctx = new ArrayExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arrayExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__22);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8941009187333734480L) != 0)) {
				{
				setState(287);
				expression();
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(288);
					match(T__1);
					setState(289);
					expression();
					}
					}
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(297);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ListExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listExpression; }
	}

	public final ListExpressionContext listExpression() throws RecognitionException {
		ListExpressionContext _localctx = new ListExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_listExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(T__5);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8941009187333734480L) != 0)) {
				{
				setState(300);
				expression();
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(301);
					match(T__1);
					setState(302);
					expression();
					}
					}
					setState(307);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(310);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SExpressionContext extends ParserRuleContext {
		public TerminalNode UIDENT() { return getToken(LamaParser.UIDENT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sExpression; }
	}

	public final SExpressionContext sExpression() throws RecognitionException {
		SExpressionContext _localctx = new SExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_sExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(UIDENT);
			setState(325);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(313);
				match(T__3);
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8941009187333734480L) != 0)) {
					{
					setState(314);
					expression();
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(315);
						match(T__1);
						setState(316);
						expression();
						}
						}
						setState(321);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(324);
				match(T__4);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LetExpressionContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(LamaParser.LET, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode IN() { return getToken(LamaParser.IN, 0); }
		public LetExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letExpression; }
	}

	public final LetExpressionContext letExpression() throws RecognitionException {
		LetExpressionContext _localctx = new LetExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_letExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(LET);
			setState(328);
			pattern();
			setState(329);
			match(T__2);
			setState(330);
			expression();
			setState(331);
			match(IN);
			setState(332);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfExpressionContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(LamaParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(LamaParser.THEN, 0); }
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public TerminalNode FI() { return getToken(LamaParser.FI, 0); }
		public ElsePartContext elsePart() {
			return getRuleContext(ElsePartContext.class,0);
		}
		public IfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpression; }
	}

	public final IfExpressionContext ifExpression() throws RecognitionException {
		IfExpressionContext _localctx = new IfExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ifExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(IF);
			setState(335);
			expression();
			setState(336);
			match(THEN);
			setState(337);
			scopeExpression();
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE || _la==ELIF) {
				{
				setState(338);
				elsePart();
				}
			}

			setState(341);
			match(FI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElsePartContext extends ParserRuleContext {
		public TerminalNode ELIF() { return getToken(LamaParser.ELIF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(LamaParser.THEN, 0); }
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public ElsePartContext elsePart() {
			return getRuleContext(ElsePartContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(LamaParser.ELSE, 0); }
		public ElsePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elsePart; }
	}

	public final ElsePartContext elsePart() throws RecognitionException {
		ElsePartContext _localctx = new ElsePartContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_elsePart);
		int _la;
		try {
			setState(352);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELIF:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				match(ELIF);
				setState(344);
				expression();
				setState(345);
				match(THEN);
				setState(346);
				scopeExpression();
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE || _la==ELIF) {
					{
					setState(347);
					elsePart();
					}
				}

				}
				break;
			case ELSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				match(ELSE);
				setState(351);
				scopeExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileDoExpressionContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(LamaParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DO() { return getToken(LamaParser.DO, 0); }
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public TerminalNode OD() { return getToken(LamaParser.OD, 0); }
		public WhileDoExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileDoExpression; }
	}

	public final WhileDoExpressionContext whileDoExpression() throws RecognitionException {
		WhileDoExpressionContext _localctx = new WhileDoExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_whileDoExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(WHILE);
			setState(355);
			expression();
			setState(356);
			match(DO);
			setState(357);
			scopeExpression();
			setState(358);
			match(OD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileExpressionContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(LamaParser.DO, 0); }
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(LamaParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OD() { return getToken(LamaParser.OD, 0); }
		public DoWhileExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileExpression; }
	}

	public final DoWhileExpressionContext doWhileExpression() throws RecognitionException {
		DoWhileExpressionContext _localctx = new DoWhileExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_doWhileExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(DO);
			setState(361);
			scopeExpression();
			setState(362);
			match(WHILE);
			setState(363);
			expression();
			setState(364);
			match(OD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForExpressionContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(LamaParser.FOR, 0); }
		public List<ScopeExpressionContext> scopeExpression() {
			return getRuleContexts(ScopeExpressionContext.class);
		}
		public ScopeExpressionContext scopeExpression(int i) {
			return getRuleContext(ScopeExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DO() { return getToken(LamaParser.DO, 0); }
		public TerminalNode OD() { return getToken(LamaParser.OD, 0); }
		public ForExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forExpression; }
	}

	public final ForExpressionContext forExpression() throws RecognitionException {
		ForExpressionContext _localctx = new ForExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_forExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(FOR);
			setState(367);
			scopeExpression();
			setState(368);
			match(T__1);
			setState(369);
			expression();
			setState(370);
			match(T__1);
			setState(371);
			expression();
			setState(372);
			match(DO);
			setState(373);
			scopeExpression();
			setState(374);
			match(OD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public ConsPatternContext consPattern() {
			return getRuleContext(ConsPatternContext.class,0);
		}
		public SimplePatternContext simplePattern() {
			return getRuleContext(SimplePatternContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_pattern);
		try {
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				consPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				simplePattern();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConsPatternContext extends ParserRuleContext {
		public SimplePatternContext simplePattern() {
			return getRuleContext(SimplePatternContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ConsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_consPattern; }
	}

	public final ConsPatternContext consPattern() throws RecognitionException {
		ConsPatternContext _localctx = new ConsPatternContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_consPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			simplePattern();
			setState(381);
			match(T__8);
			setState(382);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimplePatternContext extends ParserRuleContext {
		public WildcardPatternContext wildcardPattern() {
			return getRuleContext(WildcardPatternContext.class,0);
		}
		public SExprPatternContext sExprPattern() {
			return getRuleContext(SExprPatternContext.class,0);
		}
		public ArrayPatternContext arrayPattern() {
			return getRuleContext(ArrayPatternContext.class,0);
		}
		public ListPatternContext listPattern() {
			return getRuleContext(ListPatternContext.class,0);
		}
		public TerminalNode LIDENT() { return getToken(LamaParser.LIDENT, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode DECIMAL() { return getToken(LamaParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(LamaParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(LamaParser.CHAR, 0); }
		public TerminalNode TRUE() { return getToken(LamaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(LamaParser.FALSE, 0); }
		public TerminalNode BOX() { return getToken(LamaParser.BOX, 0); }
		public TerminalNode VAL() { return getToken(LamaParser.VAL, 0); }
		public TerminalNode STR() { return getToken(LamaParser.STR, 0); }
		public TerminalNode ARRAY_KW() { return getToken(LamaParser.ARRAY_KW, 0); }
		public TerminalNode SEXP() { return getToken(LamaParser.SEXP, 0); }
		public TerminalNode FUN() { return getToken(LamaParser.FUN, 0); }
		public SimplePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simplePattern; }
	}

	public final SimplePatternContext simplePattern() throws RecognitionException {
		SimplePatternContext _localctx = new SimplePatternContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_simplePattern);
		int _la;
		try {
			setState(417);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				wildcardPattern();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
				sExprPattern();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(386);
				arrayPattern();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(387);
				listPattern();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(388);
				match(LIDENT);
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(389);
					match(T__25);
					setState(390);
					pattern();
					}
				}

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(393);
					match(T__18);
					}
				}

				setState(396);
				match(DECIMAL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(397);
				match(STRING);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(398);
				match(CHAR);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(399);
				match(TRUE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(400);
				match(FALSE);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(401);
				match(T__26);
				setState(402);
				match(BOX);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(403);
				match(T__26);
				setState(404);
				match(VAL);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(405);
				match(T__26);
				setState(406);
				match(STR);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(407);
				match(T__26);
				setState(408);
				match(ARRAY_KW);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(409);
				match(T__26);
				setState(410);
				match(SEXP);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(411);
				match(T__26);
				setState(412);
				match(FUN);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(413);
				match(T__3);
				setState(414);
				pattern();
				setState(415);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WildcardPatternContext extends ParserRuleContext {
		public WildcardPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcardPattern; }
	}

	public final WildcardPatternContext wildcardPattern() throws RecognitionException {
		WildcardPatternContext _localctx = new WildcardPatternContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_wildcardPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SExprPatternContext extends ParserRuleContext {
		public TerminalNode UIDENT() { return getToken(LamaParser.UIDENT, 0); }
		public List<PatternContext> pattern() {
			return getRuleContexts(PatternContext.class);
		}
		public PatternContext pattern(int i) {
			return getRuleContext(PatternContext.class,i);
		}
		public SExprPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sExprPattern; }
	}

	public final SExprPatternContext sExprPattern() throws RecognitionException {
		SExprPatternContext _localctx = new SExprPatternContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_sExprPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(UIDENT);
			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(422);
				match(T__3);
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935141764193845328L) != 0)) {
					{
					setState(423);
					pattern();
					setState(428);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(424);
						match(T__1);
						setState(425);
						pattern();
						}
						}
						setState(430);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(433);
				match(T__4);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayPatternContext extends ParserRuleContext {
		public List<PatternContext> pattern() {
			return getRuleContexts(PatternContext.class);
		}
		public PatternContext pattern(int i) {
			return getRuleContext(PatternContext.class,i);
		}
		public ArrayPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayPattern; }
	}

	public final ArrayPatternContext arrayPattern() throws RecognitionException {
		ArrayPatternContext _localctx = new ArrayPatternContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_arrayPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			match(T__22);
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935141764193845328L) != 0)) {
				{
				setState(437);
				pattern();
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(438);
					match(T__1);
					setState(439);
					pattern();
					}
					}
					setState(444);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(447);
			match(T__23);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListPatternContext extends ParserRuleContext {
		public List<PatternContext> pattern() {
			return getRuleContexts(PatternContext.class);
		}
		public PatternContext pattern(int i) {
			return getRuleContext(PatternContext.class,i);
		}
		public ListPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listPattern; }
	}

	public final ListPatternContext listPattern() throws RecognitionException {
		ListPatternContext _localctx = new ListPatternContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_listPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			match(T__5);
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8935141764193845328L) != 0)) {
				{
				setState(450);
				pattern();
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(451);
					match(T__1);
					setState(452);
					pattern();
					}
					}
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(460);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseExpressionContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(LamaParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OF() { return getToken(LamaParser.OF, 0); }
		public CaseBranchesContext caseBranches() {
			return getRuleContext(CaseBranchesContext.class,0);
		}
		public TerminalNode ESAC() { return getToken(LamaParser.ESAC, 0); }
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_caseExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			match(CASE);
			setState(463);
			expression();
			setState(464);
			match(OF);
			setState(465);
			caseBranches();
			setState(466);
			match(ESAC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseBranchesContext extends ParserRuleContext {
		public List<CaseBranchContext> caseBranch() {
			return getRuleContexts(CaseBranchContext.class);
		}
		public CaseBranchContext caseBranch(int i) {
			return getRuleContext(CaseBranchContext.class,i);
		}
		public CaseBranchesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBranches; }
	}

	public final CaseBranchesContext caseBranches() throws RecognitionException {
		CaseBranchesContext _localctx = new CaseBranchesContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_caseBranches);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			caseBranch();
			setState(473);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(469);
				match(T__28);
				setState(470);
				caseBranch();
				}
				}
				setState(475);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseBranchContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ScopeExpressionContext scopeExpression() {
			return getRuleContext(ScopeExpressionContext.class,0);
		}
		public CaseBranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBranch; }
	}

	public final CaseBranchContext caseBranch() throws RecognitionException {
		CaseBranchContext _localctx = new CaseBranchContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_caseBranch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
			pattern();
			setState(477);
			match(T__29);
			setState(478);
			scopeExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InfixDefinitionContext extends ParserRuleContext {
		public TerminalNode INFIX_KW() { return getToken(LamaParser.INFIX_KW, 0); }
		public TerminalNode INFIX() { return getToken(LamaParser.INFIX, 0); }
		public BasicExpressionContext basicExpression() {
			return getRuleContext(BasicExpressionContext.class,0);
		}
		public InfixDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infixDefinition; }
	}

	public final InfixDefinitionContext infixDefinition() throws RecognitionException {
		InfixDefinitionContext _localctx = new InfixDefinitionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_infixDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			match(INFIX_KW);
			setState(481);
			match(INFIX);
			setState(482);
			match(T__2);
			setState(483);
			basicExpression();
			setState(484);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return postfixExpression_sempred((PostfixExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean postfixExpression_sempred(PostfixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001A\u01e7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0005\u0000V\b\u0000\n\u0000\f\u0000"+
		"Y\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0005\u0002b\b\u0002\n\u0002\f\u0002e\t\u0002"+
		"\u0001\u0002\u0003\u0002h\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003m\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005v\b\u0005\n\u0005\f\u0005"+
		"y\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006~\b\u0006\u0001"+
		"\u0007\u0003\u0007\u0081\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u008d\b\b\n\b\f\b\u0090\t\b\u0003\b\u0092\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0005\n\u009b\b\n\n\n\f\n\u009e\t\n"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00a5\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u00aa\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u00af\b\u000e\n\u000e\f\u000e\u00b2\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u00b7\b\u000f\n\u000f\f\u000f\u00ba\t\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00bf\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u00c4\b\u0011\n\u0011\f\u0011\u00c7"+
		"\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00cc\b\u0012"+
		"\n\u0012\f\u0012\u00cf\t\u0012\u0001\u0013\u0003\u0013\u00d2\b\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00de\b\u0014\n"+
		"\u0014\f\u0014\u00e1\t\u0014\u0003\u0014\u00e3\b\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u00f2\b\u0014\n\u0014\f\u0014\u00f5\t\u0014\u0003\u0014\u00f7\b"+
		"\u0014\u0001\u0014\u0003\u0014\u00fa\b\u0014\u0005\u0014\u00fc\b\u0014"+
		"\n\u0014\f\u0014\u00ff\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u011d\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0123\b\u0016\n\u0016\f\u0016\u0126\t\u0016\u0003\u0016"+
		"\u0128\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0005\u0017\u0130\b\u0017\n\u0017\f\u0017\u0133\t\u0017\u0003"+
		"\u0017\u0135\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u013e\b\u0018\n\u0018\f\u0018"+
		"\u0141\t\u0018\u0003\u0018\u0143\b\u0018\u0001\u0018\u0003\u0018\u0146"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0003\u001a\u0154\b\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u015d\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u0161\b\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u017b\b\u001f\u0001"+
		" \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0003!\u0188\b!\u0001!\u0003!\u018b\b!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u01a2\b!\u0001\"\u0001"+
		"\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0005#\u01ab\b#\n#\f#\u01ae\t#\u0003"+
		"#\u01b0\b#\u0001#\u0003#\u01b3\b#\u0001$\u0001$\u0001$\u0001$\u0005$\u01b9"+
		"\b$\n$\f$\u01bc\t$\u0003$\u01be\b$\u0001$\u0001$\u0001%\u0001%\u0001%"+
		"\u0001%\u0005%\u01c6\b%\n%\f%\u01c9\t%\u0003%\u01cb\b%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0005\'\u01d8"+
		"\b\'\n\'\f\'\u01db\t\'\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0000\u0001(*\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>@BDFHJLNPR\u0000\u0004\u0001\u0000 !\u0001\u0000\f\u0011\u0001\u0000"+
		"\u0012\u0013\u0001\u0000\u0014\u0016\u020e\u0000W\u0001\u0000\u0000\u0000"+
		"\u0002\\\u0001\u0000\u0000\u0000\u0004c\u0001\u0000\u0000\u0000\u0006"+
		"l\u0001\u0000\u0000\u0000\bn\u0001\u0000\u0000\u0000\nr\u0001\u0000\u0000"+
		"\u0000\fz\u0001\u0000\u0000\u0000\u000e\u0080\u0001\u0000\u0000\u0000"+
		"\u0010\u0091\u0001\u0000\u0000\u0000\u0012\u0093\u0001\u0000\u0000\u0000"+
		"\u0014\u0097\u0001\u0000\u0000\u0000\u0016\u009f\u0001\u0000\u0000\u0000"+
		"\u0018\u00a1\u0001\u0000\u0000\u0000\u001a\u00a6\u0001\u0000\u0000\u0000"+
		"\u001c\u00ab\u0001\u0000\u0000\u0000\u001e\u00b3\u0001\u0000\u0000\u0000"+
		" \u00bb\u0001\u0000\u0000\u0000\"\u00c0\u0001\u0000\u0000\u0000$\u00c8"+
		"\u0001\u0000\u0000\u0000&\u00d1\u0001\u0000\u0000\u0000(\u00d5\u0001\u0000"+
		"\u0000\u0000*\u011c\u0001\u0000\u0000\u0000,\u011e\u0001\u0000\u0000\u0000"+
		".\u012b\u0001\u0000\u0000\u00000\u0138\u0001\u0000\u0000\u00002\u0147"+
		"\u0001\u0000\u0000\u00004\u014e\u0001\u0000\u0000\u00006\u0160\u0001\u0000"+
		"\u0000\u00008\u0162\u0001\u0000\u0000\u0000:\u0168\u0001\u0000\u0000\u0000"+
		"<\u016e\u0001\u0000\u0000\u0000>\u017a\u0001\u0000\u0000\u0000@\u017c"+
		"\u0001\u0000\u0000\u0000B\u01a1\u0001\u0000\u0000\u0000D\u01a3\u0001\u0000"+
		"\u0000\u0000F\u01a5\u0001\u0000\u0000\u0000H\u01b4\u0001\u0000\u0000\u0000"+
		"J\u01c1\u0001\u0000\u0000\u0000L\u01ce\u0001\u0000\u0000\u0000N\u01d4"+
		"\u0001\u0000\u0000\u0000P\u01dc\u0001\u0000\u0000\u0000R\u01e0\u0001\u0000"+
		"\u0000\u0000TV\u0003\u0002\u0001\u0000UT\u0001\u0000\u0000\u0000VY\u0001"+
		"\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XZ\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000Z[\u0003\u0004\u0002"+
		"\u0000[\u0001\u0001\u0000\u0000\u0000\\]\u0005\u001f\u0000\u0000]^\u0005"+
		":\u0000\u0000^_\u0005\u0001\u0000\u0000_\u0003\u0001\u0000\u0000\u0000"+
		"`b\u0003\u0006\u0003\u0000a`\u0001\u0000\u0000\u0000be\u0001\u0000\u0000"+
		"\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000dg\u0001\u0000"+
		"\u0000\u0000ec\u0001\u0000\u0000\u0000fh\u0003\u0014\n\u0000gf\u0001\u0000"+
		"\u0000\u0000gh\u0001\u0000\u0000\u0000h\u0005\u0001\u0000\u0000\u0000"+
		"im\u0003\b\u0004\u0000jm\u0003\u000e\u0007\u0000km\u0003R)\u0000li\u0001"+
		"\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000"+
		"m\u0007\u0001\u0000\u0000\u0000no\u0007\u0000\u0000\u0000op\u0003\n\u0005"+
		"\u0000pq\u0005\u0001\u0000\u0000q\t\u0001\u0000\u0000\u0000rw\u0003\f"+
		"\u0006\u0000st\u0005\u0002\u0000\u0000tv\u0003\f\u0006\u0000us\u0001\u0000"+
		"\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000x\u000b\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000"+
		"\u0000z}\u0005;\u0000\u0000{|\u0005\u0003\u0000\u0000|~\u0003\u0016\u000b"+
		"\u0000}{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\r\u0001\u0000"+
		"\u0000\u0000\u007f\u0081\u0005!\u0000\u0000\u0080\u007f\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0005\"\u0000\u0000\u0083\u0084\u0005;\u0000\u0000"+
		"\u0084\u0085\u0005\u0004\u0000\u0000\u0085\u0086\u0003\u0010\b\u0000\u0086"+
		"\u0087\u0005\u0005\u0000\u0000\u0087\u0088\u0003\u0012\t\u0000\u0088\u000f"+
		"\u0001\u0000\u0000\u0000\u0089\u008e\u0005;\u0000\u0000\u008a\u008b\u0005"+
		"\u0002\u0000\u0000\u008b\u008d\u0005;\u0000\u0000\u008c\u008a\u0001\u0000"+
		"\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000"+
		"\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000"+
		"\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0089\u0001\u0000"+
		"\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0011\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0005\u0006\u0000\u0000\u0094\u0095\u0003\u0004"+
		"\u0002\u0000\u0095\u0096\u0005\u0007\u0000\u0000\u0096\u0013\u0001\u0000"+
		"\u0000\u0000\u0097\u009c\u0003\u0016\u000b\u0000\u0098\u0099\u0005\u0001"+
		"\u0000\u0000\u0099\u009b\u0003\u0014\n\u0000\u009a\u0098\u0001\u0000\u0000"+
		"\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u0015\u0001\u0000\u0000"+
		"\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0003\u0018\f\u0000"+
		"\u00a0\u0017\u0001\u0000\u0000\u0000\u00a1\u00a4\u0003\u001a\r\u0000\u00a2"+
		"\u00a3\u0005\b\u0000\u0000\u00a3\u00a5\u0003\u0018\f\u0000\u00a4\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u0019"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a9\u0003\u001c\u000e\u0000\u00a7\u00a8"+
		"\u0005\t\u0000\u0000\u00a8\u00aa\u0003\u001a\r\u0000\u00a9\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u001b\u0001"+
		"\u0000\u0000\u0000\u00ab\u00b0\u0003\u001e\u000f\u0000\u00ac\u00ad\u0005"+
		"\n\u0000\u0000\u00ad\u00af\u0003\u001e\u000f\u0000\u00ae\u00ac\u0001\u0000"+
		"\u0000\u0000\u00af\u00b2\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u001d\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b8\u0003 \u0010"+
		"\u0000\u00b4\u00b5\u0005\u000b\u0000\u0000\u00b5\u00b7\u0003 \u0010\u0000"+
		"\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000"+
		"\u00b9\u001f\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000"+
		"\u00bb\u00be\u0003\"\u0011\u0000\u00bc\u00bd\u0007\u0001\u0000\u0000\u00bd"+
		"\u00bf\u0003\"\u0011\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf!\u0001\u0000\u0000\u0000\u00c0\u00c5\u0003"+
		"$\u0012\u0000\u00c1\u00c2\u0007\u0002\u0000\u0000\u00c2\u00c4\u0003$\u0012"+
		"\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c6#\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c8\u00cd\u0003&\u0013\u0000\u00c9\u00ca\u0007\u0003\u0000\u0000\u00ca"+
		"\u00cc\u0003&\u0013\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cc\u00cf"+
		"\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00cd\u00ce"+
		"\u0001\u0000\u0000\u0000\u00ce%\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d2\u0005\u0013\u0000\u0000\u00d1\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0003(\u0014\u0000\u00d4\'\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d6\u0006\u0014\uffff\uffff\u0000\u00d6\u00d7\u0003"+
		"*\u0015\u0000\u00d7\u00fd\u0001\u0000\u0000\u0000\u00d8\u00d9\n\u0003"+
		"\u0000\u0000\u00d9\u00e2\u0005\u0004\u0000\u0000\u00da\u00df\u0003\u0014"+
		"\n\u0000\u00db\u00dc\u0005\u0002\u0000\u0000\u00dc\u00de\u0003\u0014\n"+
		"\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000"+
		"\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e3\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000"+
		"\u0000\u00e2\u00da\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00fc\u0005\u0005\u0000"+
		"\u0000\u00e5\u00e6\n\u0002\u0000\u0000\u00e6\u00e7\u0005\u0017\u0000\u0000"+
		"\u00e7\u00e8\u0003\u0014\n\u0000\u00e8\u00e9\u0005\u0018\u0000\u0000\u00e9"+
		"\u00fc\u0001\u0000\u0000\u0000\u00ea\u00eb\n\u0001\u0000\u0000\u00eb\u00ec"+
		"\u0005\u0019\u0000\u0000\u00ec\u00f9\u0005;\u0000\u0000\u00ed\u00f6\u0005"+
		"\u0004\u0000\u0000\u00ee\u00f3\u0003\u0014\n\u0000\u00ef\u00f0\u0005\u0002"+
		"\u0000\u0000\u00f0\u00f2\u0003\u0014\n\u0000\u00f1\u00ef\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f6\u00ee\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f8\u00fa\u0005\u0005\u0000\u0000\u00f9\u00ed\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000"+
		"\u0000\u00fb\u00d8\u0001\u0000\u0000\u0000\u00fb\u00e5\u0001\u0000\u0000"+
		"\u0000\u00fb\u00ea\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000"+
		"\u0000\u00fe)\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000"+
		"\u0100\u011d\u0005<\u0000\u0000\u0101\u011d\u0005=\u0000\u0000\u0102\u011d"+
		"\u0005>\u0000\u0000\u0103\u011d\u0005;\u0000\u0000\u0104\u011d\u0005#"+
		"\u0000\u0000\u0105\u011d\u0005$\u0000\u0000\u0106\u0107\u00054\u0000\u0000"+
		"\u0107\u011d\u0005?\u0000\u0000\u0108\u0109\u0005\"\u0000\u0000\u0109"+
		"\u010a\u0005\u0004\u0000\u0000\u010a\u010b\u0003\u0010\b\u0000\u010b\u010c"+
		"\u0005\u0005\u0000\u0000\u010c\u010d\u0003\u0012\t\u0000\u010d\u011d\u0001"+
		"\u0000\u0000\u0000\u010e\u011d\u0005%\u0000\u0000\u010f\u0110\u0005\u0004"+
		"\u0000\u0000\u0110\u0111\u0003\u0004\u0002\u0000\u0111\u0112\u0005\u0005"+
		"\u0000\u0000\u0112\u011d\u0001\u0000\u0000\u0000\u0113\u011d\u0003.\u0017"+
		"\u0000\u0114\u011d\u0003,\u0016\u0000\u0115\u011d\u00030\u0018\u0000\u0116"+
		"\u011d\u00034\u001a\u0000\u0117\u011d\u00038\u001c\u0000\u0118\u011d\u0003"+
		":\u001d\u0000\u0119\u011d\u0003<\u001e\u0000\u011a\u011d\u0003L&\u0000"+
		"\u011b\u011d\u00032\u0019\u0000\u011c\u0100\u0001\u0000\u0000\u0000\u011c"+
		"\u0101\u0001\u0000\u0000\u0000\u011c\u0102\u0001\u0000\u0000\u0000\u011c"+
		"\u0103\u0001\u0000\u0000\u0000\u011c\u0104\u0001\u0000\u0000\u0000\u011c"+
		"\u0105\u0001\u0000\u0000\u0000\u011c\u0106\u0001\u0000\u0000\u0000\u011c"+
		"\u0108\u0001\u0000\u0000\u0000\u011c\u010e\u0001\u0000\u0000\u0000\u011c"+
		"\u010f\u0001\u0000\u0000\u0000\u011c\u0113\u0001\u0000\u0000\u0000\u011c"+
		"\u0114\u0001\u0000\u0000\u0000\u011c\u0115\u0001\u0000\u0000\u0000\u011c"+
		"\u0116\u0001\u0000\u0000\u0000\u011c\u0117\u0001\u0000\u0000\u0000\u011c"+
		"\u0118\u0001\u0000\u0000\u0000\u011c\u0119\u0001\u0000\u0000\u0000\u011c"+
		"\u011a\u0001\u0000\u0000\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d"+
		"+\u0001\u0000\u0000\u0000\u011e\u0127\u0005\u0017\u0000\u0000\u011f\u0124"+
		"\u0003\u0014\n\u0000\u0120\u0121\u0005\u0002\u0000\u0000\u0121\u0123\u0003"+
		"\u0014\n\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0123\u0126\u0001\u0000"+
		"\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000"+
		"\u0000\u0000\u0125\u0128\u0001\u0000\u0000\u0000\u0126\u0124\u0001\u0000"+
		"\u0000\u0000\u0127\u011f\u0001\u0000\u0000\u0000\u0127\u0128\u0001\u0000"+
		"\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0005\u0018"+
		"\u0000\u0000\u012a-\u0001\u0000\u0000\u0000\u012b\u0134\u0005\u0006\u0000"+
		"\u0000\u012c\u0131\u0003\u0014\n\u0000\u012d\u012e\u0005\u0002\u0000\u0000"+
		"\u012e\u0130\u0003\u0014\n\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130"+
		"\u0133\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131"+
		"\u0132\u0001\u0000\u0000\u0000\u0132\u0135\u0001\u0000\u0000\u0000\u0133"+
		"\u0131\u0001\u0000\u0000\u0000\u0134\u012c\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136"+
		"\u0137\u0005\u0007\u0000\u0000\u0137/\u0001\u0000\u0000\u0000\u0138\u0145"+
		"\u0005:\u0000\u0000\u0139\u0142\u0005\u0004\u0000\u0000\u013a\u013f\u0003"+
		"\u0014\n\u0000\u013b\u013c\u0005\u0002\u0000\u0000\u013c\u013e\u0003\u0014"+
		"\n\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013e\u0141\u0001\u0000\u0000"+
		"\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000"+
		"\u0000\u0140\u0143\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000"+
		"\u0000\u0142\u013a\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0146\u0005\u0005\u0000"+
		"\u0000\u0145\u0139\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000"+
		"\u0000\u01461\u0001\u0000\u0000\u0000\u0147\u0148\u00052\u0000\u0000\u0148"+
		"\u0149\u0003>\u001f\u0000\u0149\u014a\u0005\u0003\u0000\u0000\u014a\u014b"+
		"\u0003\u0014\n\u0000\u014b\u014c\u00053\u0000\u0000\u014c\u014d\u0003"+
		"\u0014\n\u0000\u014d3\u0001\u0000\u0000\u0000\u014e\u014f\u0005&\u0000"+
		"\u0000\u014f\u0150\u0003\u0014\n\u0000\u0150\u0151\u0005\'\u0000\u0000"+
		"\u0151\u0153\u0003\u0004\u0002\u0000\u0152\u0154\u00036\u001b\u0000\u0153"+
		"\u0152\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154"+
		"\u0155\u0001\u0000\u0000\u0000\u0155\u0156\u0005*\u0000\u0000\u01565\u0001"+
		"\u0000\u0000\u0000\u0157\u0158\u0005)\u0000\u0000\u0158\u0159\u0003\u0014"+
		"\n\u0000\u0159\u015a\u0005\'\u0000\u0000\u015a\u015c\u0003\u0004\u0002"+
		"\u0000\u015b\u015d\u00036\u001b\u0000\u015c\u015b\u0001\u0000\u0000\u0000"+
		"\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u0161\u0001\u0000\u0000\u0000"+
		"\u015e\u015f\u0005(\u0000\u0000\u015f\u0161\u0003\u0004\u0002\u0000\u0160"+
		"\u0157\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0161"+
		"7\u0001\u0000\u0000\u0000\u0162\u0163\u0005+\u0000\u0000\u0163\u0164\u0003"+
		"\u0014\n\u0000\u0164\u0165\u0005,\u0000\u0000\u0165\u0166\u0003\u0004"+
		"\u0002\u0000\u0166\u0167\u0005-\u0000\u0000\u01679\u0001\u0000\u0000\u0000"+
		"\u0168\u0169\u0005,\u0000\u0000\u0169\u016a\u0003\u0004\u0002\u0000\u016a"+
		"\u016b\u0005+\u0000\u0000\u016b\u016c\u0003\u0014\n\u0000\u016c\u016d"+
		"\u0005-\u0000\u0000\u016d;\u0001\u0000\u0000\u0000\u016e\u016f\u0005."+
		"\u0000\u0000\u016f\u0170\u0003\u0004\u0002\u0000\u0170\u0171\u0005\u0002"+
		"\u0000\u0000\u0171\u0172\u0003\u0014\n\u0000\u0172\u0173\u0005\u0002\u0000"+
		"\u0000\u0173\u0174\u0003\u0014\n\u0000\u0174\u0175\u0005,\u0000\u0000"+
		"\u0175\u0176\u0003\u0004\u0002\u0000\u0176\u0177\u0005-\u0000\u0000\u0177"+
		"=\u0001\u0000\u0000\u0000\u0178\u017b\u0003@ \u0000\u0179\u017b\u0003"+
		"B!\u0000\u017a\u0178\u0001\u0000\u0000\u0000\u017a\u0179\u0001\u0000\u0000"+
		"\u0000\u017b?\u0001\u0000\u0000\u0000\u017c\u017d\u0003B!\u0000\u017d"+
		"\u017e\u0005\t\u0000\u0000\u017e\u017f\u0003>\u001f\u0000\u017fA\u0001"+
		"\u0000\u0000\u0000\u0180\u01a2\u0003D\"\u0000\u0181\u01a2\u0003F#\u0000"+
		"\u0182\u01a2\u0003H$\u0000\u0183\u01a2\u0003J%\u0000\u0184\u0187\u0005"+
		";\u0000\u0000\u0185\u0186\u0005\u001a\u0000\u0000\u0186\u0188\u0003>\u001f"+
		"\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000"+
		"\u0000\u0188\u01a2\u0001\u0000\u0000\u0000\u0189\u018b\u0005\u0013\u0000"+
		"\u0000\u018a\u0189\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000"+
		"\u0000\u018b\u018c\u0001\u0000\u0000\u0000\u018c\u01a2\u0005<\u0000\u0000"+
		"\u018d\u01a2\u0005=\u0000\u0000\u018e\u01a2\u0005>\u0000\u0000\u018f\u01a2"+
		"\u0005#\u0000\u0000\u0190\u01a2\u0005$\u0000\u0000\u0191\u0192\u0005\u001b"+
		"\u0000\u0000\u0192\u01a2\u00055\u0000\u0000\u0193\u0194\u0005\u001b\u0000"+
		"\u0000\u0194\u01a2\u00056\u0000\u0000\u0195\u0196\u0005\u001b\u0000\u0000"+
		"\u0196\u01a2\u00057\u0000\u0000\u0197\u0198\u0005\u001b\u0000\u0000\u0198"+
		"\u01a2\u00058\u0000\u0000\u0199\u019a\u0005\u001b\u0000\u0000\u019a\u01a2"+
		"\u00059\u0000\u0000\u019b\u019c\u0005\u001b\u0000\u0000\u019c\u01a2\u0005"+
		"\"\u0000\u0000\u019d\u019e\u0005\u0004\u0000\u0000\u019e\u019f\u0003>"+
		"\u001f\u0000\u019f\u01a0\u0005\u0005\u0000\u0000\u01a0\u01a2\u0001\u0000"+
		"\u0000\u0000\u01a1\u0180\u0001\u0000\u0000\u0000\u01a1\u0181\u0001\u0000"+
		"\u0000\u0000\u01a1\u0182\u0001\u0000\u0000\u0000\u01a1\u0183\u0001\u0000"+
		"\u0000\u0000\u01a1\u0184\u0001\u0000\u0000\u0000\u01a1\u018a\u0001\u0000"+
		"\u0000\u0000\u01a1\u018d\u0001\u0000\u0000\u0000\u01a1\u018e\u0001\u0000"+
		"\u0000\u0000\u01a1\u018f\u0001\u0000\u0000\u0000\u01a1\u0190\u0001\u0000"+
		"\u0000\u0000\u01a1\u0191\u0001\u0000\u0000\u0000\u01a1\u0193\u0001\u0000"+
		"\u0000\u0000\u01a1\u0195\u0001\u0000\u0000\u0000\u01a1\u0197\u0001\u0000"+
		"\u0000\u0000\u01a1\u0199\u0001\u0000\u0000\u0000\u01a1\u019b\u0001\u0000"+
		"\u0000\u0000\u01a1\u019d\u0001\u0000\u0000\u0000\u01a2C\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a4\u0005\u001c\u0000\u0000\u01a4E\u0001\u0000\u0000\u0000"+
		"\u01a5\u01b2\u0005:\u0000\u0000\u01a6\u01af\u0005\u0004\u0000\u0000\u01a7"+
		"\u01ac\u0003>\u001f\u0000\u01a8\u01a9\u0005\u0002\u0000\u0000\u01a9\u01ab"+
		"\u0003>\u001f\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000\u01ab\u01ae\u0001"+
		"\u0000\u0000\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001"+
		"\u0000\u0000\u0000\u01ad\u01b0\u0001\u0000\u0000\u0000\u01ae\u01ac\u0001"+
		"\u0000\u0000\u0000\u01af\u01a7\u0001\u0000\u0000\u0000\u01af\u01b0\u0001"+
		"\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b3\u0005"+
		"\u0005\u0000\u0000\u01b2\u01a6\u0001\u0000\u0000\u0000\u01b2\u01b3\u0001"+
		"\u0000\u0000\u0000\u01b3G\u0001\u0000\u0000\u0000\u01b4\u01bd\u0005\u0017"+
		"\u0000\u0000\u01b5\u01ba\u0003>\u001f\u0000\u01b6\u01b7\u0005\u0002\u0000"+
		"\u0000\u01b7\u01b9\u0003>\u001f\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000"+
		"\u01b9\u01bc\u0001\u0000\u0000\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000"+
		"\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bb\u01be\u0001\u0000\u0000\u0000"+
		"\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bd\u01b5\u0001\u0000\u0000\u0000"+
		"\u01bd\u01be\u0001\u0000\u0000\u0000\u01be\u01bf\u0001\u0000\u0000\u0000"+
		"\u01bf\u01c0\u0005\u0018\u0000\u0000\u01c0I\u0001\u0000\u0000\u0000\u01c1"+
		"\u01ca\u0005\u0006\u0000\u0000\u01c2\u01c7\u0003>\u001f\u0000\u01c3\u01c4"+
		"\u0005\u0002\u0000\u0000\u01c4\u01c6\u0003>\u001f\u0000\u01c5\u01c3\u0001"+
		"\u0000\u0000\u0000\u01c6\u01c9\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001"+
		"\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01cb\u0001"+
		"\u0000\u0000\u0000\u01c9\u01c7\u0001\u0000\u0000\u0000\u01ca\u01c2\u0001"+
		"\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001"+
		"\u0000\u0000\u0000\u01cc\u01cd\u0005\u0007\u0000\u0000\u01cdK\u0001\u0000"+
		"\u0000\u0000\u01ce\u01cf\u0005/\u0000\u0000\u01cf\u01d0\u0003\u0014\n"+
		"\u0000\u01d0\u01d1\u00050\u0000\u0000\u01d1\u01d2\u0003N\'\u0000\u01d2"+
		"\u01d3\u00051\u0000\u0000\u01d3M\u0001\u0000\u0000\u0000\u01d4\u01d9\u0003"+
		"P(\u0000\u01d5\u01d6\u0005\u001d\u0000\u0000\u01d6\u01d8\u0003P(\u0000"+
		"\u01d7\u01d5\u0001\u0000\u0000\u0000\u01d8\u01db\u0001\u0000\u0000\u0000"+
		"\u01d9\u01d7\u0001\u0000\u0000\u0000\u01d9\u01da\u0001\u0000\u0000\u0000"+
		"\u01daO\u0001\u0000\u0000\u0000\u01db\u01d9\u0001\u0000\u0000\u0000\u01dc"+
		"\u01dd\u0003>\u001f\u0000\u01dd\u01de\u0005\u001e\u0000\u0000\u01de\u01df"+
		"\u0003\u0004\u0002\u0000\u01dfQ\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005"+
		"4\u0000\u0000\u01e1\u01e2\u0005?\u0000\u0000\u01e2\u01e3\u0005\u0003\u0000"+
		"\u0000\u01e3\u01e4\u0003\u0016\u000b\u0000\u01e4\u01e5\u0005\u0001\u0000"+
		"\u0000\u01e5S\u0001\u0000\u0000\u00000Wcglw}\u0080\u008e\u0091\u009c\u00a4"+
		"\u00a9\u00b0\u00b8\u00be\u00c5\u00cd\u00d1\u00df\u00e2\u00f3\u00f6\u00f9"+
		"\u00fb\u00fd\u011c\u0124\u0127\u0131\u0134\u013f\u0142\u0145\u0153\u015c"+
		"\u0160\u017a\u0187\u018a\u01a1\u01ac\u01af\u01b2\u01ba\u01bd\u01c7\u01ca"+
		"\u01d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}