package ru._7bits.lexer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public enum TokenType {
    LEFT_BRACE(true, false),
    RIGHT_BRACE(true, false),
    LEFT_PARENTHESIS(false, false),
    RIGHT_PARENTHESIS(false, true),
    UNARY_OPERATOR(false, false),
    BIN_OPERATOR(false, true),
    SEMICOLON(true, false),
    COLON(false, true),
    COMMA(false, true),
    DOT(false, false),
    QUESTION_SIGN(false, true),
    CHAR_LITERAL(false, true),
    STRING_LITERAL(false, true),
    ONE_LINE_COMMENT(true, false),
    MULTILINE_COMMENT(false, true),
    DEFAULT(false, true),
    EOF(false, false);

    private boolean needNewline;
    private boolean needChar;

    TokenType(boolean needNewline, boolean needChar){
        this.needNewline = needNewline;
        this.needChar = needChar;
    }

    public boolean isNeedNewLine(){
        return needNewline;
    }

    public boolean isNeedChar() {
        return needChar;
    }
}
