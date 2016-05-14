package ru._7bits.formatter;

import ru._7bits.lexer.ILexer;
import ru._7bits.reader.IReader;
import ru._7bits.writer.IWriter;

/**
 * Created by User on 14.05.2016.
 */
public interface IFormatter {
    public void setLexer(ILexer lexer);
    public void format(IReader reader, IWriter writer) throws FormatterException;
}
