package ru._7bits.writer;

/**
 * Writer of the output stream
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface IWriter {
    /**
     * Writes a single character to the output stream.
     * @param c the character to write
     * @throws WriterException
     */
    public void write(final char c) throws WriterException;

    /**
     * Writes a string to the output stream.
     * @param s the string to write
     * @throws WriterException
     */
    public void write(final String s) throws WriterException;

    /**
     * Closes the stream
     * @throws WriterException
     */
    public void close() throws WriterException;
}
