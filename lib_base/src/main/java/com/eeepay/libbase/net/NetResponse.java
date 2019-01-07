package com.eeepay.libbase.net;

import java.io.Serializable;
/**
 * Created by ixzus on 2017/12/27.
 * Email: iandroid@foxmail.com
 * Desc:
 */

public class NetResponse<T> implements Serializable {
    private int code;
    private String error;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "httpResponse{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", result=" + result +
                '}';
    }
}