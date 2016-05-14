package ru._7bits.lexer;

import junit.framework.TestCase;
import ru._7bits.reader.StringReader;

/**
 * Created by User on 14.05.2016.
 */
public class LexerTest extends TestCase {
    public void testGetTokenOne() throws Exception {
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

    public void testGetTokenTwo() throws Exception {
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

    public void testGetTokenThree() throws Exception {
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

    public void testGetTokenFour() throws Exception {
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

    public void testGetTokenFive() throws Exception {
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

    public void testGetTokenSix() throws Exception {
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

    public void testGetTokenSeven() throws Exception {
        String input = "';'";
        String strTokenFirst = "';'";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();
        assertTrue(theType == TokenType.CHAR_LITERAL && strActualToken.equals(strTokenFirst));
    }

    public void testGetTokenEight() throws Exception {
        String input = "'\\''";
        String etalon = "'\\''";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();

        assertTrue(theType == TokenType.CHAR_LITERAL && strActualToken.equals(etalon));

    }

    public void testGetTokenNine() throws Exception {
        String input = "\"qqq\n www\"";
        String etalon = "\"qqq\n www\"";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        TokenType theType = token.getTokenType();
        String strActualToken = token.getStrToken();

        assertTrue(theType == TokenType.STRING_LITERAL && strActualToken.equals(etalon));

    }

    public void testGetTokenTen() throws Exception {
        String input = "\"qqq\n\\\"\' ';www\"";
        String etalon = "\"qqq\n\\\"\' ';www\"";

        StringReader reader = new StringReader(input);
        Lexer lexer = new Lexer(reader);
        Token token = lexer.getToken();
        assertTrue(token.getTokenType() == TokenType.STRING_LITERAL && token.getStrToken().equals(etalon));
    }
}

