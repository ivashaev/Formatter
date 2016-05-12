package ru._7bits.reader;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class StringReader implements IReader {
    private String string;
    private int currentIndex;

    public StringReader(String str){
        string = str;
        currentIndex = 0;
    }

    public char getChar() throws ReaderException{
        try {
            return string.charAt(currentIndex++);
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }

    public char seeChar()  throws ReaderException{
        try {
            return string.charAt(currentIndex);
        }
        catch (Exception e){
            throw new ReaderException(e);
        }
    }
    public boolean hasNext() throws ReaderException{
        return currentIndex < string.length();
    }

    public void close() throws ReaderException {
        //empty
    }
}
