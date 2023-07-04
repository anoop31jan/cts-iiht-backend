package com.cts.iiht.taskservice.exception;


public class DataValidationException extends Exception {


    public DataValidationException(){
        super();
    }

    public DataValidationException(String message){
        super(message);
    }

    public DataValidationException(String message, Throwable cause){
        super(message, cause);
    }

    public DataValidationException(Throwable cause) {
        super(cause);
    }

}
