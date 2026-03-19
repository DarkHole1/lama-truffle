# TODO List for Lama Truffle Implementation

This document lists all incomplete realizations and TODO items found in the codebase.

## Core Language Features

### Pattern Matching (Case Expressions)
- **File**: `src/main/java/com/lama/truffle/nodes/CaseNode.java`
- **Issue**: Pattern matching is not implemented - just executes first branch
- **TODO**: Implement proper pattern matching with scrutinee evaluation and pattern comparison

## Parser Implementation

### If Expression
- **File**: `src/main/java/com/lama/truffle/parser/LamaVisitorImpl.java`
- **Issue**: `visitIfExpression()` has TODO comment
- **TODO**: Complete if-elif-else expression implementation

### Function Calls
- **File**: `src/main/java/com/lama/truffle/parser/LamaVisitorImpl.java`
- **Issue**: Function call implementation is incomplete
- **TODO**: Implement proper function lookup and closure creation

### Anonymous Functions
- **File**: `src/main/java/com/lama/truffle/parser/LamaVisitorImpl.java`
- **Issue**: Anonymous function implementation is incomplete
- **TODO**: Complete closure creation with proper frame management

## Runtime System

### Variable Environment
- **File**: `src/main/java/com/lama/truffle/types/Closure.java`
- **Issue**: `set()` method returns `null` instead of setting variable
- **TODO**: Implement proper variable setting in closure environment

## Configuration

### Application Properties
- **File**: `src/main/resources/application.properties`
- **Issue**: Contains incomplete configuration lines
- **TODO**: Complete application configuration