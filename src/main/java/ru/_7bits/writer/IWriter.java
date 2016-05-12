package ru._7bits.writer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public interface IWriter {
    public void write(char c) throws WriterException;
    public void write(String s) throws WriterException;
    public void close() throws WriterException;
}
