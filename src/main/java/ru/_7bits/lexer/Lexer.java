package ru._7bits.lexer;

import ru._7bits.reader.IReader;
import ru._7bits.reader.ReaderException;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class Lexer implements ILexer{
    private IReader reader;

    static private boolean isSpace(char c){
        return (c == ' ' || c == '\t' || c == '\n' || c=='\r');
    }

    private Token getDefualtToken(char c) throws ReaderException{
        String firstCharOfToken = " \t\n\r;{}/\'\\\"";

        StringBuilder sb = new StringBuilder();
        sb.append(c);
        while (reader.hasNext()) {
            c = reader.seeChar();
            if (firstCharOfToken.indexOf(c) == -1){
                sb.append(reader.getChar());
            } else
                break;
        }
        return new Token(TokenType.DEFAULT, sb.toString());
    }

    private Token getOneLineCommentToken(char c) throws ReaderException, LexerException{
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        if (!reader.hasNext()) throw new LexerException();
        c = reader.getChar();
        sb.append(c);

        while (reader.hasNext()) {
            c = reader.getChar();
            if (c == '\n'){
                break;
            }
            sb.append(c);
        }
        return new Token(TokenType.ONE_LINE_COMMENT, sb.toString());
    }

    private Token getMultilineCommentToken(char c) throws ReaderException, LexerException{
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        if (!reader.hasNext()) throw new LexerException();
        c = reader.getChar();
        sb.append(c);

        boolean starFound = false;
        while (reader.hasNext()) {
            c = reader.getChar();
            sb.append(c);
            if (c == '/' && starFound){
                return new Token(TokenType.MULTILINE_COMMENT, sb.toString());
            }
            starFound = (c == '*');
        }
        throw new LexerException(); // the comment is not closed
    }

    private Token getCharLiteral(char c) throws ReaderException, LexerException {
        boolean ok = false;

        StringBuilder sb = new StringBuilder();
        sb.append(c);

        while (reader.hasNext()) {
            c = reader.getChar();
            sb.append(c);
            if (c == '\''){
                ok = true;
                break;
            }
            else if (c == '\\'){
                if (reader.hasNext() && reader.seeChar() == '\''){
                    c = reader.getChar();
                    sb.append(c);
                }
            }
        }
        if (ok) {
            return new Token(TokenType.CHAR_LITERAL, sb.toString());
        }
        throw new LexerException();
    }

    private Token getStringLiteral(char c) throws ReaderException, LexerException {
        boolean ok = false;

        StringBuilder sb = new StringBuilder();
        sb.append(c);

        while (reader.hasNext()) {
            c = reader.getChar();
            sb.append(c);
            if (c == '\"'){
                ok = true;
                break;
            }
            else if (c == '\\'){
                if (reader.hasNext() && reader.seeChar() == '\"'){
                    c = reader.getChar();
                    sb.append(c);
                }
            }
        }
        if (ok) {
            return new Token(TokenType.STRING_LITERAL, sb.toString());
        }
        throw new LexerException();
    }

    public Lexer(IReader theReader){
        reader = theReader;
    }

    public void setReader(IReader reader){
        this.reader = reader;
    }

    public Token getToken() throws LexerException{
         try {
             char c = ' ';

             while (reader.hasNext() && isSpace(c = reader.getChar())) {
                 // skip spaces
             }

             if (c == ' ') {
                 return new Token(TokenType.EOF, "");
             }

             if (c == ';') {
                 return new Token(TokenType.SEMICOLON, ";");
             }
             if (c == '{') {
                 return new Token(TokenType.LEFT_BRACE, "{");
             }
             if (c == '}') {
                 return new Token(TokenType.RIGHT_BRACE, "}");
             }
             if (c == '/' && reader.hasNext()){
                 if (reader.seeChar() == '/') {
                     return getOneLineCommentToken(c);
                 } else if (reader.seeChar() == '*') {
                     return getMultilineCommentToken(c);
                 } else {
                     // not a comment, process as default later
                 }
             }
             if (c == '\''){
                 return getCharLiteral(c);
             }
             if (c == '\"'){
                 return getStringLiteral(c);
             }

             return getDefualtToken(c);
         }
         catch (Exception e){
             throw new LexerException(e);
         }

    }



}


