package ru._7bits.writer;

import java.io.FileWriter;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FWriter implements IWriter {
    private FileWriter stream;

    /**
     * Creates new file stream writer
     * @param fileName a name of the output file
     * @throws WriterException
     */
    public FWriter(final String fileName) throws WriterException {
        try {
            stream = new FileWriter(fileName);
        } catch(Exception e) {
            throw new WriterException(e);
        }
    }

    /**
     * Writes a string to the output stream.
     * @param c the string to write
     * @throws WriterException
     */
    public void write(final char c) throws WriterException {
        try {
            stream.write(c);
        } catch(Exception e) {
            throw new WriterException(e);
        }
    }

    /** Writes a single character to the output stream.
            * @param s the character to write
    * @throws WriterException
    */
    public void write(final String s) throws WriterException {
        try {
            stream.write(s);
        } catch(Exception e) {
            throw new WriterException(e);
        }
    }

    /**
     * Closes the stream
     * @throws WriterException
     */
    public void close() throws WriterException {
        try {
            stream.close();
        } catch(Exception e) {
            throw new WriterException(e);
        }
    }
}
