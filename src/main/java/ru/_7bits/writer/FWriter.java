package ru._7bits.writer;

import java.io.FileWriter;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FWriter implements IWriter{
    FileWriter stream;

    public FWriter(String fileName) throws WriterException {
        try {
            stream = new FileWriter(fileName);
        }
        catch(Exception e){
            throw new WriterException(e);
        }
    }

    public void write(char c) throws WriterException {
        try{
            stream.write(c);
        }
        catch(Exception e){
            throw new WriterException(e);
        }
    }

    public void write(String s) throws WriterException {
        try{
            stream.write(s);
        }
        catch(Exception e){
            throw new WriterException(e);
        }
    }

    public void close() throws WriterException {
        try{
            stream.close();
        }
        catch(Exception e){
            throw new WriterException(e);
        }
    }
}
