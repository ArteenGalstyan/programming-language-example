package src.main.Tokenizer;

public class IntegerToken implements Token {
    public final int value;
    
    public IntegerToken(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof IntegerToken && value == ((IntegerToken)obj).value);
    }

    @Override
    public int hashCode() {
        return value;
    }
}
