package example.tokenizer;

public class MinusToken implements Token {
    //public MinusToken() {}

    @Override
    public String toString() {
        return "-";
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof MinusToken;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
