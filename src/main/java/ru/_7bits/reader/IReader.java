package ru._7bits.reader;

/**
 * Reader of the input stream interface.
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface IReader {
    /**
     * Gets a current char
     * @return a char which have read
     * @throws ReaderException
     */
     char getChar() throws ReaderException;

    /**
     * Returns a current char without removing it from the stream
     * @return the current char in the stream
     * @throws ReaderException
     */
    char seeChar() throws ReaderException;

    /**
     * Returns a single char back to the input stream.
     * @param c a char to return
     * @throws ReaderException
     */
    void unreadChar(char c) throws ReaderException;

    /**
     * Checks for a new element in the stream.
     * @return true if the input stream contains a char
     * @throws ReaderException
     */
    boolean hasNext() throws ReaderException;

    /** Closes the stream */
    void close() throws ReaderException;
}
