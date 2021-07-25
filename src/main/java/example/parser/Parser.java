package example.parser;

import example.tokenizer.*;

public class Parser {
    private final Token[] tokens;

    public Parser(final Token[] tokens) {
        this.tokens = tokens;
    }

    public static ParseResult parseExpression(final int startPos) throws ParseException {
        return parseAdditive(startPos);
    } // parseExpression

        public Op parseAdditiveOp(final int atPos) throws ParseException {
            final Token tokenHere = tokens[atPos];
            if (tokenHere instanceof PlusToken) {
                return new PlusOp();
            } else if (tokenHere instanceof MinusOp) {
                return new MinusOp();
            } else {
                throw new ParseException("Expected additive operator, gotL " + tokenHere.toString());
            }
        }

    // ADDITIVE PARSE
    // a ::= m (('+' | '-') m)*
    public static ParseResult parseAdditive(final int startPos) throws ParseException {
        ParseResult result = parseMultiplicative(startPos);
        try{
            while (result.nextPosition < tokens.length()) {
                final Op op = parseAdditive(result.nextPosition);
                final ParseResult innerMultiplicative = parseMultiplicative(result.nextPosition + 1);
                result = new ParseResult(new OperatorExpression( result.exp, innerMultiplicative.exp, op), innerMultiplicative.nextPosition);
            }
        } catch (final ParseException e) {}

        return result;
    } // parseAdditive

    public void ensureTokenIs(final int atPos, final Token expectedToken) throws ParseException {
        if (!tokens[atPos].equals(expectedToken)) {
            throw new ParseException("Missing" + expectedToken.toString());
        }
    } // ensureTokenIs

    // MULTIPLICATIVE PARSE
    // m ::= p ('*' p)*
    public static ParseResult parseMultiplicative(final int startPos) throws ParseException {
        ParseResult result = parsePrimary(startPos);
        try {
            while (result.nextPosition < tokens.length()) {
                ensureTokenIs(result.nextPosition, new MultiplicationToken());
                ParseResult nextPrimary = parsePrimary(result.nextPosition + 1);
                result = new ParseResult(new OperatorExpression(result.exp, nextPrimary.exp, new MultiplyOp()), nextPrimary.nextPosition);
            }
        } catch (final ParseException e) {}

        return result;
    } // parseMultiplicative

    public static ParseResult parsePrimary(final int startPos) throws ParseException {
        if (tokens[startPos] instanceof IntegerToken) {
            
            final IntegerToken asInt = (IntegerToken)tokens[startPos];
            return new ParseResult(new IntegerExpression(asInt.value), startPos + 1);

        } else if (tokens[startPos] instanceof LeftParenToken) {
            
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
