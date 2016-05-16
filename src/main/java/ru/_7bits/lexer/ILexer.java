package ru._7bits.lexer;


import ru._7bits.reader.IReader;

/**
 * Interface of lexeme parser.
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface ILexer {
    /**
     * Setter for input stream reader.
     * @param reader - the reader to set
     */
    void setReader(final IReader reader);

    /**
     * Reads a token from the input stream.
     * @return the token currently obtained
     * @throws LexerException
     */
    Token getToken() throws LexerException;
}

