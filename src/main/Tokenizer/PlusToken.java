package src.main.Tokenizer;

public class PlusToken implements Token{
    //public PlusToken() {}
    
    @Override
    public String toString() {
        return "+";
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof PlusToken;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
