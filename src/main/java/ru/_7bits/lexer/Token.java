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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        return strToken != null ? strToken.equals(token.strToken) : token.strToken == null;

    }

    @Override
    public int hashCode() {
        int result = tokenType != null ? tokenType.hashCode() : 0;
        result = 31 * result + (strToken != null ? strToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return strToken;
    }
}
