package ru._7bits.formatter;

import ru._7bits.lexer.ILexer;
import ru._7bits.reader.IReader;
import ru._7bits.writer.IWriter;

/**
 * Interface of Formatter
 * Created by I.V. Ashaev on 14.05.2016.
 */
public interface IFormatter {
    /**
     * Setter for lexeme parser.
     * @param lexer - lexeme parser to use.
     */
     void setLexer(final ILexer lexer);

    /**
     * Format a Java code.
     * @param reader - input stream
     * @param writer - output stream
     * @throws FormatterException
     */
    void format(final IReader reader, final IWriter writer) throws FormatterException;
}
