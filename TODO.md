# TODO List for Lama Truffle Implementation

This document lists all incomplete realizations and TODO items found in the codebase.

## Remaining TODOs

### 3. CONS Operator - Incomplete Implementation
- **File**: `src/main/java/com/lama/truffle/nodes/BinaryOperationNode.java` (line 89)
- **Issue**: CONS operator returns only `left` value as placeholder
- **TODO**: Implement proper list construction using `com.lama.truffle.types.List`
- **Priority**: High (needed for list pattern matching to work correctly)

### 4. Closure Execution - Not Implemented
- **File**: `src/main/java/com/lama/truffle/types/Closure.java` (lines 49-51)
- **Issue**: `Closure.execute()` returns `null` as placeholder
- **TODO**: Implement proper closure execution with:
  - Frame creation with captured variables
  - Parameter binding
  - Body execution
- **Priority**: High (needed for function calls)

### 5. Function Calls - Not Implemented
- **File**: `src/main/java/com/lama/truffle/parser/LamaVisitorImpl.java`
- **Issue**: `visitPostfixExpression()` creates `FunctionCallNode` but execution is not implemented
- **TODO**: Implement function lookup and closure invocation in `FunctionCallNode`
- **Priority**: High

### 6. Array Indexing - Not Implemented
- **File**: `src/main/java/com/lama/truffle/parser/LamaVisitorImpl.java` (line ~250)
- **Issue**: Array indexing `postfixExpression '[' expression ']'` returns array without indexing
- **TODO**: Implement array element access
- **Priority**: Medium

### 7. Variable Access - Uses VariableEnvironment
- **File**: `src/main/java/com/lama/truffle/nodes/VariableAccessNode.java`
- **Issue**: Should use `VariableEnvironment` for variable lookup
- **TODO**: Verify/update variable access to use `VariableEnvironment.get()`
- **Priority**: Medium

---

## Summary by Priority

| Priority | Count | Description |
|----------|-------|-------------|
| High     | 3     | CONS operator, Closure execution, Function calls |
| Medium   | 2     | Array indexing, Variable access |
