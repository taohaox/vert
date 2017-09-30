package com.gonyb.http;

import java.util.Date;

/**
 * 统一返回的格式
 * Created by Gonyb on 2017/5/4.
 */
public class DoResult<T> {
    private static final int SUCCESS = 0;
    private static final int FAILED = 1;
    public static final int UNAUTHORIZED = 9;
    
    
    private int code;
    private String msg;
    private T data;
    private Date time = new Date();

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public DoResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DoResult() {
    }

    public static <T>DoResult success(T t){
        return new DoResult<>(0,"成功",t);
    }
    public static <T>DoResult success(T t,String msg){
        return new DoResult<>(0,msg,t);
    }
    public static DoResult success(){
        return new DoResult<>(0,"成功","");
    }
    public static DoResult failed(int code, String msg){
        return new DoResult<>(code,msg,"");
    }
    public static DoResult failed(String msg){
        return new DoResult<>(FAILED,msg,"");
    }
    public static DoResult failed(){
        return new DoResult<>(FAILED,"失败","");
    }
    public boolean hsaSuccess(){
        return getCode()==SUCCESS;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
