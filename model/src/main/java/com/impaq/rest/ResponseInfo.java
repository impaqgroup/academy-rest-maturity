package com.impaq.rest;

public class ResponseInfo {
    private boolean error;
    private String errorInfo;
    
    public ResponseInfo(boolean error, String errorInfo) {
        this.error = error;
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
