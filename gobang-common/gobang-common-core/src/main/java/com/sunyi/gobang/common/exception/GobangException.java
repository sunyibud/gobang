package com.sunyi.gobang.common.exception;

import com.sunyi.gobang.common.response.ResponseEnum;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
public class GobangException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private Object object;

    private ResponseEnum responseEnum;

    public GobangException(String msg) {
        super(msg);
    }

    public GobangException(String msg, Object object) {
        super(msg);
        this.object = object;
    }

    public GobangException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public GobangException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public GobangException(ResponseEnum responseEnum, Object object) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
        this.object = object;
    }


    public Object getObject() {
        return object;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

}
