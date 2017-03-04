package me.letitfly.mat.api.model;

/**
 * Created by Trumeet on 2017/3/4.
 */

public class HttpResult<T> {
    private int Version;
    private String Charset;
    private String error = "";

    private T Variables;

    public boolean isSuccess () {
        return error == null || error.isEmpty();
    }

    public T getData () {
        return Variables;
    }

    public String getError () {
        return error;
    }
}