package src.main.Tokenizer;

import java.util.List;
import java.util.ArrayList;


public class Tokenizer {
    public static Token[] tokenize(String input) throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        int position = 0;

        while (position < input.length()) {
            final char currentChar = input.charAt(position);
            if (input.charAt(position) == '+') {
                position++;
            } else if (Character.isDigit(currentChar)) {
                final StringBuffer digits = new StringBuffer();
                //Want to use a buffer so java doesn't internally make any substrings
                digits.append(currentChar);
                position++;
                while (position < input.length() && 
                        Character.isDigit(input.charAt(position))) {
                    digits.append(input.charAt(position));
                    position++;
                }

                final String digitAsString = digits.toString();
                final int asInteger = Integer.parseInt(digitAsString);

                tokens.add(new IntegerToken(asInteger));
            } else {
                throw new TokenizerException("Invalid character: " + currentChar);
            }
        }

        return tokens.toArray(new Token[tokens.size()]); //have to give size of token to make array
    } //tokenize
}
