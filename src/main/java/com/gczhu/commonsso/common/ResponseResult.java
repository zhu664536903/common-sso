package com.gczhu.commonsso.common;

public class ResponseResult<T> {
    private boolean status;
    private String msg;
    private T data;

    public ResponseResult(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(boolean status, String msg){
        this(status,msg,null);
    }

    public static <T> ResponseResult<T> ok(String message,T data){

        return new ResponseResult<T>(true,message,data);
    }
    public static <T> ResponseResult<T> ok(String message){

        return new ResponseResult<T>(true,message,null);
    }

    public static <T> ResponseResult<T> error(String message,T data){

        return new ResponseResult<T>(false,message,data);
    }
    public static <T> ResponseResult<T> error(String message){
        return new ResponseResult<T>(false,message,null);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
