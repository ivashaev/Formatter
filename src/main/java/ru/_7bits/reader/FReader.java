package ru._7bits.reader;


import java.io.FileReader;
import java.io.PushbackReader;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FReader implements IReader {
    private PushbackReader stream;

    public FReader(String fileName) throws ReaderException {
        try {
            stream = new PushbackReader(new FileReader(fileName));
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }

    public char getChar() throws ReaderException {
        try {
            return (char)stream.read();
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }

    public char seeChar() throws ReaderException {
        try {
            int symbol = stream.read();
            stream.unread(symbol);
            return (char)symbol;
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }

    public boolean hasNext() throws ReaderException {
        try {
            return stream.ready();
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }

    public void close() throws ReaderException {
        try {
            stream.close();
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }
}
