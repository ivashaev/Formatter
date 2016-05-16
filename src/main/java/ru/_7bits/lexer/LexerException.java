package ru._7bits.lexer;

/**
 * Exception class for a lexeme parser
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class LexerException extends Exception {
    private Exception e;

    public LexerException(final Exception theE){
        e = theE;
    }
    public LexerException(){   }

    public Exception getE() {
        return e;
    }
}
