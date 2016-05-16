package ru._7bits.formatter;


/**
 * Exception class for a code formatter
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FormatterException extends Exception {
    private String message;
    private Exception e;

    public FormatterException(final String theMessage){
        message = theMessage;
    }

    public FormatterException(final Exception theE, final String theMessage) {
        e = theE;
        message = theMessage;
    }

    public String getMessage(){
        return message;
    }

    public Exception getE() {
        return e;
    }
}
