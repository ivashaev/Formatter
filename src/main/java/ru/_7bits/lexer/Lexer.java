package ru._7bits.lexer;

import ru._7bits.reader.IReader;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class Lexer implements ILexer{
    private IReader reader;
    static private boolean isSpace(char c){
        return (c == ' ' || c == '\t' || c == '\n' || c=='\r');
    }

    public Lexer(IReader theReader){
        reader = theReader;
    }

     public Token getToken() throws LexerException{
        char c = ' ';

         try {
             while (reader.hasNext() && isSpace(c = reader.getChar())) {
                 // tab spaces
             }

             if (c == ' ') {
                 return new Token(TokenType.EOF, "");
             }

             if (c == ';') {
                 return new Token(TokenType.SEMICOLON, ";");
             } else if (c == '{') {
                 return new Token(TokenType.LEFT_BRACE, "{");
             } else if (c == '}') {
                 return new Token(TokenType.RIGHT_BRACE, "}");
             } else {
                 StringBuilder sb = new StringBuilder();
                 sb.append(c);
                 while (reader.hasNext()) {
                     c = reader.seeChar();
                     if (c != ';' && c != '{' && c != '}' && !isSpace(c)) {
                         sb.append(reader.getChar());
                     } else
                         break;
                 }
                 return new Token(TokenType.DEFAULT, sb.toString());
             }
         }
         catch (Exception e){
             throw new LexerException(e);
         }

    }

}
