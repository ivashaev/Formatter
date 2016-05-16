package ru._7bits.writer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class WriterException extends Exception {
    private Exception e;
    public WriterException(final Exception theE) {
        e = theE;
    }

    public Exception getE() {
        return e;
    }
}
