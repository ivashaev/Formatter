package ru._7bits.reader;


import java.io.FileReader;
import java.io.PushbackReader;

/**
 * File stream reader.
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FReader implements IReader {
    private static int unreadBufferSize = 4;
    private PushbackReader stream;

    /**
     * Creates a file stream
     * @param fileName - name of a file to read
     * @throws ReaderException
     */
    public FReader(final String fileName) throws ReaderException {
        try {
            stream = new PushbackReader(new FileReader(fileName), unreadBufferSize);
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Gets a current char
     * @return a char which have read
     * @throws ReaderException
     */
    public char getChar() throws ReaderException {
        try {
            return (char) stream.read();
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Sees a current char without removing it from the stream
     * @return the current char in the stream
     * @throws ReaderException
     */
    public char seeChar() throws ReaderException {
        try {
            int symbol = stream.read();
            stream.unread(symbol);
            return (char) symbol;
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Returns a single char back to the input stream.
     * @param c a char to return
     * @throws ReaderException
     */
    public void unreadChar(final char c) throws ReaderException {
        try {
            stream.unread(c);
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Checks for a new element in the stream.
     * @return true if the input stream contains a char
     * @throws ReaderException
     */
    public boolean hasNext() throws ReaderException {
        try {
            return stream.ready();
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /** Closes the stream */
    public void close() throws ReaderException {
        try {
            stream.close();
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }
}
