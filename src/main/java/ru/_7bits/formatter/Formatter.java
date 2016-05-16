package ru._7bits.formatter;

import ru._7bits.lexer.*;
import ru._7bits.reader.IReader;
import ru._7bits.reader.ReaderException;
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
     * Inserts linebreak or space char after prevToken according to rules for prevToken by definition (from its TokenType)
     * @param writer - output stream
     * @param level - level of a block enclosure for tab size
     * @param prevToken - the last written token
     * @param inForHeader - true if prevToken starts for-cycle header (writes the space char instead of linebreak)
     * @throws WriterException
     */
    private void makeStandardTabOrSpace(IWriter writer, int level, Token prevToken, boolean inForHeader) throws WriterException{
        if (prevToken.getTokenType().isNeedNewLine()){
            if (!inForHeader){
                writer.write(newLine);
                writer.write(makeTab(level));
            }
            else{
                writer.write(space);
            }
        }
        else if (prevToken.getTokenType().isNeedChar()){
            writer.write(space);
        }
    }

    public Formatter(ILexer lexer){
        this.lexer = lexer;
    }

    public void setLexer(ILexer lexer){
        this.lexer = lexer;
    }

    /**
     * Format an input stream.
     * @param reader - input stream of chars
     * @param writer - output stream
     * @throws FormatterException
     */
    public void format(IReader reader, IWriter writer) throws FormatterException {
        int level = 0;
        boolean inForHeader = false;
        int countParenthesis = 0;

        try {
            lexer.setReader(reader);

            Token prevToken = new Token(TokenType.EOF, "");
            Token token = lexer.getToken();

            while (token.getTokenType() != TokenType.EOF) {
                TokenType tokenType = token.getTokenType();
                TokenType prevTokenType = prevToken.getTokenType();

                if (tokenType == TokenType.SEMICOLON || tokenType == TokenType.COMMA || tokenType == TokenType.DOT) {
                    if (prevTokenType.isNeedNewLine() && !inForHeader) {
                        writer.write(newLine);
                        writer.write(makeTab(level));
                        }
                    writer.write(token.getStrToken());
                }
                else if (tokenType == TokenType.LEFT_BRACE) {
                    makeStandardTabOrSpace(writer, level, prevToken, inForHeader);
                    writer.write(token.getStrToken());
                    ++level;
                }
                else if (tokenType == TokenType.RIGHT_BRACE) {
                    if (level == 0) {
                        throw new FormatterException("Unbalanced brace }");
                    }
                    else {
                        level--;
                    }
                    if (!inForHeader) {
                        writer.write(newLine);
                        writer.write(makeTab(level));
                    }
                    writer.write(token.getStrToken());
                }
                else if (tokenType == TokenType.LEFT_PARENTHESIS) {
                    makeStandardTabOrSpace(writer, level, prevToken, inForHeader);
                    if (inForHeader){
                        countParenthesis++;
                    }
                    writer.write(token.getStrToken());
                }
                else if (tokenType == TokenType.RIGHT_PARENTHESIS) {
                    if (prevTokenType.isNeedNewLine()  && !inForHeader){
                        writer.write(newLine);
                        writer.write(makeTab(level));
                    }
                    if (inForHeader){
                        countParenthesis--;
                        if (countParenthesis == 0){
                            inForHeader = false;
                        }

                    }
                    writer.write(token.getStrToken());
                }
                else if (tokenType == TokenType.ONE_LINE_COMMENT) {
//                    if (needTab && prevTokenType == TokenType.ONE_LINE_COMMENT){
                    makeStandardTabOrSpace(writer, level, prevToken, inForHeader);
                    writer.write(token.getStrToken());
                }
                else if (tokenType == TokenType.MULTILINE_COMMENT) {
                    String strToken = token.getStrToken();

                    makeStandardTabOrSpace(writer, level, prevToken, inForHeader);
                    writer.write(token.getStrToken());

//                    for (int i = 0; i < strToken.length(); i++){
//                        char c = strToken.charAt(i);
//                        if (c == '\n' && prevTokenType.isNeedNewLine()){
//                            writer.write(newLine);
//                            writer.write(makeTab(level));
//                        }
//                        else if (c == '\r') {
//                            // skip it
//                        }
//                        else{
//                            writer.write(c);
//                        }
//                    }
                }
                else {
                    makeStandardTabOrSpace(writer, level, prevToken, inForHeader);
                    writer.write(token.getStrToken());
                    if (token.getStrToken().equals("for")){
                        inForHeader = true;
                        countParenthesis = 0;
                    }
                }

                prevToken = token;
                token = lexer.getToken();

            } //while

            reader.close();
            writer.close();
        }
        catch (LexerException e){
            throw new FormatterException(e, "Input error");
        }
        catch (WriterException e){
            throw new FormatterException(e, "Write error");
        }
        catch (ReaderException e){
            throw new FormatterException(e, "Read error");
        }

        if (level > 0){
            throw new FormatterException("Unbalanced brace {");
        }
    }

}
