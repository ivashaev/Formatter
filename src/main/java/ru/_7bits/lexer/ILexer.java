package ru._7bits.lexer;


import ru._7bits.reader.IReader;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface ILexer {
    public void setReader(IReader reader);
    public Token getToken() throws LexerException;
}

