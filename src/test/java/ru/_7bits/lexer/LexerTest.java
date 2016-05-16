package ru._7bits.lexer;

import junit.framework.TestCase;
import ru._7bits.reader.StringReader;

/**
 * Created by User on 14.05.2016.
 */
public class LexerTest extends TestCase {
    public void testGetToken1() throws Exception {
        String input = "aaa // comment";
        String strTokenFirst = "aaa";
        String strTokenSecond = "// comment";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.ONE_LINE_COMMENT && token.getStrToken().equals(strTokenSecond));
    }

    public void testGetToken2() throws Exception {
        String input = "aaa//comment\n";
        String strTokenFirst = "aaa";
        String strTokenSecond = "//comment";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.ONE_LINE_COMMENT && token.getStrToken().equals(strTokenSecond));
    }

    public void testGetToken3() throws Exception {
        String input = "\n// aaa\n\n//bbb";
        String strTokenFirst = "// aaa";
        String strTokenSecond = "//bbb";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.ONE_LINE_COMMENT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.ONE_LINE_COMMENT && token.getStrToken().equals(strTokenSecond));
    }

    public void testGetToken4() throws Exception {
        String input = "aaa/* bbb*/ ccc";
        String strTokenFirst = "aaa";
        String strTokenSecond = "/* bbb*/";
        String strTokenThird = "ccc";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.MULTILINE_COMMENT && token.getStrToken().equals(strTokenSecond));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenThird));
    }

    public void testGetToken5() throws Exception {
        String input = "aaa\n/* bbb*/\n ccc";
        String strTokenFirst = "aaa";
        String strTokenSecond = "/* bbb*/";
        String strTokenThird = "ccc";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.MULTILINE_COMMENT && token.getStrToken().equals(strTokenSecond));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenThird));
    }

    public void testGetToken6() throws Exception {
        String input = "aaa\n/* bbb \n bbb*/\n ccc";
        String strTokenFirst = "aaa";
        String strTokenSecond = "/* bbb \n bbb*/";
        String strTokenThird = "ccc";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenFirst));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.MULTILINE_COMMENT && token.getStrToken().equals(strTokenSecond));
        token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.DEFAULT && token.getStrToken().equals(strTokenThird));
    }

    public void testGetToken7() throws Exception {
        String input = "';'";
        String strTokenFirst = "';'";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();
        assertTrue(theType == TokenType.CHAR_LITERAL && strActualToken.equals(strTokenFirst));
    }

    public void testGetToken8() throws Exception {
        String input = "'\\''";
        String etalon = "'\\''";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();

        assertTrue(theType == TokenType.CHAR_LITERAL && strActualToken.equals(etalon));

    }

    public void testGetToken9() throws Exception {
        String input = "\"qqq\n www\"";
        String etalon = "\"qqq\n www\"";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();

        assertTrue(theType == TokenType.STRING_LITERAL && strActualToken.equals(etalon));

    }

    public void testGetToken10() throws Exception {
        String input = "\"qqq=\n\\\"\' ';www\"";
        String etalon = "\"qqq=\n\\\"\' ';www\"";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.STRING_LITERAL && token.getStrToken().equals(etalon));
    }

    public void testGetToken11() throws Exception {
        String input = "(a+b)*c++";
        Token tokenEtalon = new Token(TokenType.LEFT_PARENTHESIS, "(");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken12() throws Exception {
        String input = "+b)*c++";
        Token tokenEtalon = new Token(TokenType.BIN_OPERATOR, "+");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken13() throws Exception {
        String input = "*=c++";
        Token tokenEtalon = new Token(TokenType.BIN_OPERATOR, "*=");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken14() throws Exception {
        String input = "=++c";
        Token tokenEtalon = new Token(TokenType.BIN_OPERATOR, "=");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken15() throws Exception {
        String input = "+++c";
        Token tokenEtalonFirst = new Token(TokenType.UNARY_OPERATOR, "++");
        Token tokenEtalonSecond = new Token(TokenType.BIN_OPERATOR, "+");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonFirst));
        token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonSecond));
    }

    public void testGetToken16() throws Exception {
        String input = "///comment/";
        Token tokenEtalonFirst = new Token(TokenType.ONE_LINE_COMMENT, "///comment/");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonFirst));
    }

    public void testGetToken17() throws Exception {
        String input = "==b";
        Token tokenEtalonFirst = new Token(TokenType.BIN_OPERATOR, "==");
        Token tokenEtalonSecond = new Token(TokenType.DEFAULT, "b");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonFirst));
        token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonSecond));
    }

    public void testGetToken18() throws Exception {
        String input = "<<= <<";
        Token tokenEtalonFirst = new Token(TokenType.BIN_OPERATOR, "<<=");
        Token tokenEtalonSecond = new Token(TokenType.BIN_OPERATOR, "<<");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonFirst));
        token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonSecond));
    }

    public void testGetToken19() throws Exception {
        String input = "=";
        Token tokenEtalonFirst = new Token(TokenType.BIN_OPERATOR, "=");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.equals(tokenEtalonFirst));
    }

    public void testGetToken20() throws Exception {
        String input = "aaa";
        Token tokenEtalon = new Token(TokenType.EOF, "");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        token = lexer.getToken();

        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken21() throws Exception {
        String input = "";
        Token tokenEtalon = new Token(TokenType.EOF, "");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();

        assertTrue(token.equals(tokenEtalon));
    }

    public void testGetToken22() throws Exception {
        String input = "< <= << <<< <<<=";
        Token tokenEtalonFirst = new Token(TokenType.BIN_OPERATOR, "<");
        Token tokenEtalonSecond = new Token(TokenType.BIN_OPERATOR, "<=");
        Token tokenEtalonThird = new Token(TokenType.BIN_OPERATOR, "<<");
        Token tokenEtalonFourth = new Token(TokenType.BIN_OPERATOR, "<<<");
        Token tokenEtalonFith = new Token(TokenType.BIN_OPERATOR, "<<<=");

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);

        Token tokenFirst = lexer.getToken();
        Token tokenSecond = lexer.getToken();
        Token tokenThird = lexer.getToken();
        Token tokenFourth = lexer.getToken();
        Token tokenFith = lexer.getToken();

        assertTrue(tokenFirst.equals(tokenEtalonFirst));
        assertTrue(tokenSecond.equals(tokenEtalonSecond));
        assertTrue(tokenThird.equals(tokenEtalonThird));
        assertTrue(tokenFourth.equals(tokenEtalonFourth));
        assertTrue(tokenFith.equals(tokenEtalonFith));
    }

}

