package ru._7bits.lexer;

/**
 * Types of tokens.
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

    /**
     * Creates a new element.
     * @param needNewline the token needs linebreak after it,
     * @param needChar the token needs a single space char after it
     */
    TokenType(final boolean needNewline, final boolean needChar) {
        this.needNewline = needNewline;
        this.needChar = needChar;
    }

    public boolean isNeedNewLine() {
        return needNewline;
    }

    public boolean isNeedChar() {
        return needChar;
    }
}
