package ru._7bits.reader;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class StringReader implements IReader {
    private String string;
    private int currentIndex;

    /**
     * Creates a string-based input stream reader.
     * @param str symbols to read
     */
    public StringReader(final String str) {
        string = str;
        currentIndex = 0;
    }

    /**
     * Gets a current char
     * @return a char which have read
     * @throws ReaderException
     */
    public char getChar() throws ReaderException {
        try {
            return string.charAt(currentIndex++);
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Returns a current char without removing it from the stream
     * @return the current char in the stream
     * @throws ReaderException
     */

    public char seeChar()  throws ReaderException {
        try {
            return string.charAt(currentIndex);
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
        if (currentIndex <= 0) {
            throw new ReaderException();
        }
        try {
            currentIndex--;
            if (c != string.charAt(currentIndex)) {
                currentIndex++;
                throw new ReaderException();
            }
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
        return currentIndex < string.length();
    }

    /** Closes the stream */
    public void close() throws ReaderException {
        //empty
    }
}
