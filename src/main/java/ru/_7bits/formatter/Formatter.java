package ru._7bits.formatter;

import ru._7bits.lexer.*;
import ru._7bits.reader.IReader;
import ru._7bits.writer.IWriter;
import ru._7bits.writer.WriterException;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class Formatter {
    static protected String tab = "    ";
    static protected String newLine = "\n";
    static protected String space = " ";

    ILexer lexer;

    /**
     *Create a string of tabs from the beginning of a line to a current token.
     * @param level - level of enclosure of a current token,
     * @return a String with level tabs
     */
    private String makeTab(int level){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level; i++) {
            result.append(tab);
        }
        return result.toString();
    }

    /**
     * Determine whenever a current token needs a linebreak after it.
     * @param tokenType - type of a current token
     * @return true if linebreak is necessary.
     */
    private boolean needNewLine(TokenType tokenType){
        return (tokenType == TokenType.SEMICOLON || tokenType == TokenType.LEFT_BRACE || tokenType == TokenType.RIGHT_BRACE
        || tokenType == TokenType.ONE_LINE_COMMENT);
    }

    public Formatter(ILexer lexer){
        this.lexer = lexer;
    }

    public void setLexer(ILexer lexer){
        this.lexer = lexer;
    }

    /**
     * Format an input stream according the following rules.
     * Any semicolon is followed by new line. Braces { and } is used in K&R-style.
     * Instructions are placed one by line and aligned according level of enclosure.
     * @param reader - input stream of chars
     * @param writer - output stream
     * @throws FormatterException
     */
    public void format(IReader reader, IWriter writer) throws FormatterException {
        int level = 0;

        try {
            lexer.setReader(reader);

            TokenType prevTokenType = TokenType.SEMICOLON;
            Token token = lexer.getToken();

            while (token.getTokenType() != TokenType.EOF) {
                if (token.getTokenType() == TokenType.SEMICOLON) {
                    writer.write(token.getStrToken());
                    writer.write(newLine);
                }
                else if (token.getTokenType() == TokenType.LEFT_BRACE) {
                    if (needNewLine(prevTokenType)){
                        writer.write(makeTab(level));
                    }
                    writer.write(token.getStrToken());
                    writer.write(newLine);
                    ++level;
                }
                else if (token.getTokenType() == TokenType.RIGHT_BRACE) {
                    if (level == 0) {
                        throw new FormatterException("Unbalanced brace }");
                    }
                    else {
                        level--;
                    }
                    writer.write(makeTab(level));
                    writer.write(token.getStrToken());
                    writer.write(newLine);
                }
                else if (token.getTokenType() == TokenType.ONE_LINE_COMMENT) {
                    if (needNewLine(prevTokenType)) {
                        writer.write(makeTab(level));
                    }
                    else {
                        writer.write(space);
                    }
                    writer.write(token.getStrToken());
                    writer.write(newLine);
                }
                else if (token.getTokenType() == TokenType.MULTILINE_COMMENT) {
                    boolean needNL = needNewLine(prevTokenType);
                    String strToken = token.getStrToken();

                    if (needNewLine(prevTokenType)) {
                        writer.write(makeTab(level));
                    }
                    else {
                        writer.write(space);
                    }
                    for (int i = 0; i < strToken.length(); i++){
                        char c = strToken.charAt(i);
                        if (c == '\n' && needNL){
                            writer.write(c);
                            writer.write(makeTab(level));
                        }
                        else if (c == '\r') {
                            // skip
                        }
                        else{
                            writer.write(c);
                        }
                    }
                }
                else if (token.getTokenType() == TokenType.CHAR_LITERAL || token.getTokenType() == TokenType.STRING_LITERAL) {
                    writer.write(token.getStrToken());
                }

                else if (token.getTokenType() == TokenType.DEFAULT) {
                    if (needNewLine(prevTokenType)) {
                        writer.write(makeTab(level));
                    }
                    else {
                        writer.write(space);
                    }
                    writer.write(token.getStrToken());
                }
                else {
                    throw new FormatterException("Unrecognized token");
                }

                prevTokenType = token.getTokenType();
                token = lexer.getToken();
            } //while

            writer.close();
        }
        catch (LexerException e){
            throw new FormatterException(e, "Input error");
        }
        catch (WriterException e){
            throw new FormatterException(e, "Write error");
        }

        if (level > 0){
            throw new FormatterException("Unbalanced brace {");
        }
    }

}
