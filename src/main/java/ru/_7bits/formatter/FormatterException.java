package ru._7bits.formatter;


/**
 * Created by I.V. Ashaev on 12.05.2016.
 */
public class FormatterException extends Exception {
    private String message;
    private Exception e;

    public FormatterException(String theMessage){
        message = theMessage;
    }

    public FormatterException(Exception theE, String theMessage){
        message = theMessage;
    }

    public String getMessage(){
        return message;
    }
}
