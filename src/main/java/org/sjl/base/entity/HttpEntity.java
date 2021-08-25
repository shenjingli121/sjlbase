package org.sjl.base.entity;

import org.sjl.base.utils.HttpEntityUtil;

public class HttpEntity<T> {

    private Integer code;
    private String message;
    private T data;

    public HttpEntity() {
        this.code = HttpEntityUtil.SUCCESS_CODE;
    }

    public HttpEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

