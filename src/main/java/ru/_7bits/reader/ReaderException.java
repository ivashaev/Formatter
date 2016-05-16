package ru._7bits.reader;

/**
 * Exception for the input stream reader.
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class ReaderException extends Exception {
    private Exception e;

    public ReaderException(final Exception theE){
        e = theE;
    }
    public ReaderException() {}

    public Exception getE() {
        return e;
    }
}
