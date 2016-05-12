package ru._7bits.lexer;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface ILexer {
    public Token getToken() throws LexerException;
}

