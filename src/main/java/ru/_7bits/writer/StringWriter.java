package ru._7bits.writer;

/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class StringWriter implements IWriter {
    StringBuilder stringBuilder;

    public StringWriter() {
        stringBuilder = new StringBuilder();
    }

    public StringWriter(StringBuilder sb){
        stringBuilder = sb;
    }

    public void write(char c) throws WriterException{
        stringBuilder.append(c);
    }

    public void write(String s) throws WriterException {
        for (int i = 0; i < s.length(); i++){
            stringBuilder.append(s.charAt(i));
        }
    }

    public void close() throws WriterException{
        //empty
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
