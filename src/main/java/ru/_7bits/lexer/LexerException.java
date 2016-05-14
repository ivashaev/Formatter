package ru._7bits.lexer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class LexerException extends Exception {
    private Exception e;

    public LexerException(Exception theE){
        e = theE;
    }
    public LexerException(){
    }

}
