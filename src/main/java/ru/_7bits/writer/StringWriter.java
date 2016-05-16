package ru._7bits.writer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class StringWriter implements IWriter {
    StringBuilder stringBuilder;

    /**
     * Creates a writer with empty output string.
     */
    public StringWriter() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Creates writer with existing string builder.
     * @param sb
     */
    public StringWriter(final StringBuilder sb){
        stringBuilder = sb;
    }

    /**
     * Writes a string to the output stream.
     * @param c the string to write
     * @throws WriterException
     */
    public void write(final char c) throws WriterException {
        stringBuilder.append(c);
    }

    /**
     * Writes a string to the output stream.
     * @param s the string to write
     * @throws WriterException
     */
    public void write(final String s) throws WriterException {
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append(s.charAt(i));
        }
    }

    /**
     * Closes the stream
     * @throws WriterException
     */
    public void close() throws WriterException{
        //empty
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
