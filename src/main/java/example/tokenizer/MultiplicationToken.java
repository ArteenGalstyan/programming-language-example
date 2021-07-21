package example.tokenizer;

public class MultiplicationToken implements Token{
    //public MultiplicationToken() {}

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof MultiplicationToken;
    }

    @Override
    public int hashCode() {
        return 2;
    }
    
}
