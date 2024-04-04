package com.lld.bookmyshow.dtos;

public class Response {
    private String error;
    private ResponseStatus responseStatus;

    public Response(String error, ResponseStatus responseStatus) {
        this.error = error;
        this.responseStatus = responseStatus;
    }

    public static Response getSuccessResponse(){
        return new Response(null, ResponseStatus.SUCCESS);
    }
    public static Response getFailureResponse(String msg){
        return new Response(msg, ResponseStatus.FAILURE);
    }
}
