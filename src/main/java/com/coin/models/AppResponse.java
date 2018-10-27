package com.coin.models;


public class AppResponse<T> {


    //    private String status;
    private int statusCode;
    private String statusMsg;

    private T result;

    public AppResponse(T t) {
        setResult(t);
    }

    public AppResponse() {
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;

    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
        this.statusCode = 200;
        this.statusMsg = "Success";
    }
}

