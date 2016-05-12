package ru._7bits.lexer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class Token {
    private TokenType tokenType = TokenType.DEFAULT;
    private String strToken;

    public Token(TokenType theType, String theStrToken){
        tokenType = theType;
        strToken = theStrToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getStrToken() {
        return strToken;
    }

    @Override
    public String toString() {
        return strToken;
    }
}
