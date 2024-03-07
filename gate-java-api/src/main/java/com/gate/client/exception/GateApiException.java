package com.gate.client.exception;


public class GateApiException extends RuntimeException{

    private  GateApiError gateApiError;

    public  GateApiException(GateApiError gateApiError)
    {
         this.gateApiError=gateApiError;
    }

    public  GateApiException()
    {
        super();
    }

    public  GateApiException(String message)
    {
        super(message);
    }

    public String getMessage() {
        if (gateApiError != null) {
            return gateApiError.getMessage();
        }
        return super.getMessage();
    }

    public String getErrorCode() {
        if (gateApiError != null) {
            return gateApiError.getErrorCode();
        }
        return "";
    }
}
