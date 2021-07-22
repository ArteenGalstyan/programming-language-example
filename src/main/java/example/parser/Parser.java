package example.parser;

import example.tokenizer.*;

public class Parser {
    private final Token[] tokens;

    public Parser(final Token[] tokens) {
        this.tokens = tokens;
    }

    public static Expression parseExpression(final int startPos) throws ParseException {

    }

    public static Expression parsePrimary(final int startPos) throws ParseException {
        if(tokens[startPos] instanceof IntegerToken) {
            final IntegerToken asInt = (IntegerToken)tokens[startPos];
            return new ParseResult(new IntegerExpression(asInt.value), startPos + 1);
        } else if(tokens[startPos] instanceof LeftParenToken) {
            final ParseResult inner = parseExpression(startPos + 1);
            
            if (tokens[inner.nextPosition] instanceof RightParenToken) {
                return new ParseResult(inner.exp, inner.nextPosition + 1);
            } else {
                throw new ParseException("Missing Right Paranthesis");
            }
        } else {
            throw new ParseException("Not a primary expression");
        }
    } // parsePrimary
    
}
