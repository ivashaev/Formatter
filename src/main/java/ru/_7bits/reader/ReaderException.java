package ru._7bits.reader;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class ReaderException extends Exception {
    private Exception e;
    public ReaderException(Exception theE){
        e = theE;
    }
}
