package ru._7bits.lexer;

import ru._7bits.reader.IReader;
import ru._7bits.reader.ReaderException;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class Lexer implements ILexer{
    private IReader reader;
    private String stopSymbols = " \t\n\r;{}/\'\\\"()";

    static private boolean isSpace(char c){
        return (c == ' ' || c == '\t' || c == '\n' || c=='\r');
    }

    private Token getDefualtToken(char c) throws ReaderException{
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        while (reader.hasNext()) {
            c = reader.seeChar();
            if (stopSymbols.indexOf(c) == -1){
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
        Token[] tokenToRecognizeLen1 = {
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.COMMA, ","),
                new Token(TokenType.COLON, ":"),
                new Token(TokenType.DOT, "."),
                new Token(TokenType.QUESTION_SIGN, "?"),
                new Token(TokenType.LEFT_BRACE, "{"),
                new Token(TokenType.RIGHT_BRACE, "}"),
                new Token(TokenType.LEFT_PARENTHESIS, "("),
                new Token(TokenType.RIGHT_PARENTHESIS, ")"),
                new Token(TokenType.BIN_OPERATOR, "+"),
                new Token(TokenType.BIN_OPERATOR, "-"),
                new Token(TokenType.BIN_OPERATOR, "*"),
                new Token(TokenType.BIN_OPERATOR, "/"),
                new Token(TokenType.BIN_OPERATOR, "%"),
                new Token(TokenType.BIN_OPERATOR, "="),
                new Token(TokenType.BIN_OPERATOR, "<"),
                new Token(TokenType.BIN_OPERATOR, ">"),
                new Token(TokenType.BIN_OPERATOR, "&"),
                new Token(TokenType.BIN_OPERATOR, "|"),
                new Token(TokenType.BIN_OPERATOR, "^"),
                new Token(TokenType.UNARY_OPERATOR, "!")
        };

        Token[] tokenToRecognizeLen2 = {
                new Token(TokenType.BIN_OPERATOR, "+="),
                new Token(TokenType.UNARY_OPERATOR, "++"),
                new Token(TokenType.UNARY_OPERATOR, "+-"),
                new Token(TokenType.BIN_OPERATOR, "-="),
                new Token(TokenType.BIN_OPERATOR, "*="),
                new Token(TokenType.BIN_OPERATOR, "/="),
                new Token(TokenType.BIN_OPERATOR, "%="),
                new Token(TokenType.BIN_OPERATOR, "=="),
                new Token(TokenType.BIN_OPERATOR, "<="),
                new Token(TokenType.BIN_OPERATOR, ">="),
                new Token(TokenType.BIN_OPERATOR, "&="),
                new Token(TokenType.BIN_OPERATOR, "|="),
                new Token(TokenType.BIN_OPERATOR, "^="),
                new Token(TokenType.BIN_OPERATOR, "<<"),
                new Token(TokenType.BIN_OPERATOR, ">>"),
                new Token(TokenType.BIN_OPERATOR, "&&"),
                new Token(TokenType.BIN_OPERATOR, "||")
        };

        Token[] tokenToRecognizeLen3 = {
                new Token(TokenType.BIN_OPERATOR, "<<<"),
                new Token(TokenType.BIN_OPERATOR, ">>>"),
                new Token(TokenType.BIN_OPERATOR, ">>="),
                new Token(TokenType.BIN_OPERATOR, "<<=")
        };
        // tokens longer than 3 chars must be recognized by hand :)

        StringBuilder sb = new StringBuilder(stopSymbols);
        for (Token t: tokenToRecognizeLen1){
            char first = t.getStrToken().charAt(0);
            if (sb.toString().indexOf(first) == -1){
                sb.append(first);
            }
        }
        for (Token t: tokenToRecognizeLen2){
            char first = t.getStrToken().charAt(0);
            if (sb.toString().indexOf(first) == -1){
                sb.append(first);
            }
        }
        for (Token t: tokenToRecognizeLen3){
            char first = t.getStrToken().charAt(0);
            if (sb.toString().indexOf(first) == -1){
                sb.append(first);
            }
        }
        stopSymbols = sb.toString();

        try {
            char c = ' ', cNext = ' ', cNextNext = ' ';
            int countRead = 0;

            while (reader.hasNext() && isSpace(c = reader.getChar())) {
                // skip spaces
            }

            if (c == ' ') {
                return new Token(TokenType.EOF, "");
            }

            countRead = 1;
            if (reader.hasNext()) {
                cNext = reader.getChar();
                countRead++;
                if (reader.hasNext()) {
                    cNextNext = reader.getChar();
                    countRead++;
                }
            }

            Token tokenResult = null;
            if (countRead == 3) {
                for (Token t : tokenToRecognizeLen3) {
                    if (cNextNext == t.getStrToken().charAt(2) && cNext == t.getStrToken().charAt(1) && c == t.getStrToken().charAt(0)) {
                        tokenResult = t;
                    }
                }
                if (tokenResult == null) {
                    reader.unreadChar(cNextNext);
                    countRead--;
                }
            }
            if (tokenResult == null && countRead == 2) {
                for (Token t : tokenToRecognizeLen2) {
                    if (cNext == t.getStrToken().charAt(1) && c == t.getStrToken().charAt(0)) {
                        tokenResult = t;
                    }
                }
                if (tokenResult == null) {
                    reader.unreadChar(cNext);
                    countRead--;
                }
            }
            if (tokenResult == null) {
                for (Token t : tokenToRecognizeLen1) {
                    if (c == t.getStrToken().charAt(0)) {
                        tokenResult = t;
                    }
                }
                if (tokenResult == null) {
                    countRead--;
                    // the first char doesn't unread, explore it further
                }
            }

            if (tokenResult != null) {
                // distinguish // and /* from operators / and /=
                if (c == '/' && reader.hasNext()) {
                    if (reader.seeChar() == '/') {
                        return getOneLineCommentToken(c);
                    } else if (reader.seeChar() == '*') {
                        return getMultilineCommentToken(c);
                    }
                }
                //distinguishing <<<, >>> from <<<=. >>>=
                if (tokenResult.getStrToken().equals("<<<") && reader.hasNext() && reader.seeChar() == '='){
                    tokenResult = new Token(TokenType.BIN_OPERATOR, "<<<=");
                }
                else if (tokenResult.getStrToken().equals(">>>") && reader.hasNext() && reader.seeChar() == '='){
                    tokenResult = new Token(TokenType.BIN_OPERATOR, ">>>=");
                }
                return tokenResult;
            }

            if (c == '\'') {
                return getCharLiteral(c);
            }
            if (c == '\"') {
                return getStringLiteral(c);
            }

            return getDefualtToken(c);

        }
        catch (Exception e){
            throw new LexerException(e);
        }


//            if (c == ';') {
//             return new Token(TokenType.SEMICOLON, ";");
//            }
//            if (c == '{') {
//             return new Token(TokenType.LEFT_BRACE, "{");
//            }
//            if (c == '}') {
//             return new Token(TokenType.RIGHT_BRACE, "}");
//            }
//            if (c == '(') {
//             return new Token(TokenType.LEFT_PARENTHESIS, "(");
//            }
//            if (c == ')') {
//             return new Token(TokenType.RIGHT_PARENTHESIS, ")");
//            }
//            if (c == '/' && reader.hasNext()){
//             if (reader.seeChar() == '/') {
//                 return getOneLineCommentToken(c);
//             } else if (reader.seeChar() == '*') {
//                 return getMultilineCommentToken(c);
//             } else {
//                 // not a comment, process as default later
//             }
//            }
//            if (c == '\''){
//             return getCharLiteral(c);
//            }
//            if (c == '\"'){
//             return getStringLiteral(c);
//            }
//
//            return getDefualtToken(c);
//        }
//        catch (Exception e){
//            throw new LexerException(e);
//        }

    }



}


