package example.tokenizer;

import java.util.List;
import java.util.ArrayList;


public class Tokenizer {
    // ---START INSTANCE VARIABLES---
    public final String input;
    private int position;
    // ---END INSTANCE VARIABLES---

    //CONSTRUCTOR
    public Tokenizer(final String input) {
        this.input = input;
        this.position = 0;
    }

    private void skipWhitespace() {
        while (position < input.length() &&
               Character.isWhitespace(input.charAt(position))) {
            position++;
        }
    } // skipWhitespace

    private IntegerToken tokenizeInteger() {
        assert(position < input.length() && Character.isDigit(input.charAt(position)));
        // assumes position starts on a digit and position hasn't past input length    
        
        final StringBuffer digits = new StringBuffer();
        // use a string buffer so java doesn't internally make any substrings
        digits.append(input.charAt(position));
        position++;
        while (position < input.length() && 
                Character.isDigit(input.charAt(position))) {
            digits.append(input.charAt(position));
            position++;
        }

        final String digitAsString = digits.toString();
        final int asInteger = Integer.parseInt(digitAsString);

        return new IntegerToken(asInteger);
    } //tokenizeInteger

    public Token[] tokenize() throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        position = 0;

        skipWhitespace();

        while (position < input.length()) {
            final char currentChar = input.charAt(position);
            if (input.charAt(position) == '+') {
                tokens.add(new PlusToken());
                position++;
            } else if (input.charAt(position) == '-') {
                tokens.add(new MinusToken());
                position++;
            } else if (input.charAt(position) == '*') {
                tokens.add(new MultiplicationToken());
                position++;
            } else if (input.charAt(position) == '(') {
                tokens.add(new LeftParenToken());
                position++;
            } else if (input.charAt(position) == ')') {
                tokens.add(new RightParenToken());
                position++;
            } else if (Character.isDigit(currentChar)) {
                tokens.add(tokenizeInteger());
            } else {
                throw new TokenizerException("Invalid character: " + currentChar);
            }
        }

        return tokens.toArray(new Token[tokens.size()]); //have to give size of token to make array
    } //tokenize

    public static void main(final String[] args) throws TokenizerException {
        if (args.length != 1) {
            System.out.println("Needs a string to tokenize.");
        } else {
            final Tokenizer tokenizer = new Tokenizer(args[0]);
            final Token[] tokens = tokenizer.tokenize();
            for (final Token token : tokens) {
                System.out.println(token.toString());
            }
        }
    }
}
