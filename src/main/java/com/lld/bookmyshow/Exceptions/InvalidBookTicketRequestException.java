package com.lld.bookmyshow.Exceptions;

public class InvalidBookTicketRequestException extends Exception{
    public InvalidBookTicketRequestException(String msg){
        super(msg);
    }
}
