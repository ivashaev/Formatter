package ru._7bits.reader;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface IReader {
    public char getChar() throws ReaderException;
    public char seeChar() throws ReaderException;
    public boolean hasNext() throws ReaderException;
    public void close() throws ReaderException;
}
