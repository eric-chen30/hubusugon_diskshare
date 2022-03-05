package com.hubusugon.sugonnetdisk.part3.config;

import lombok.Data;

import java.io.Serializable;

/**
 * 仅用于Controller层包装返回的数据
 *
 * @author alexyan
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int SUCCESS = 0;

    public static final int UNKNOWN_EXCEPTION = -99;
    public static final int NOT_LOGIN = -1;
    public static final int NO_PERMISSION = -2;

    /**
     * 状态码  =0 成功
     * >0 服务器内部异常
     * <0 外部异常，由调用方处理
     */
    private int code;

    /**
     * 返回API调用情况
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public Result(Object data) {
        this(SUCCESS, "success", data);
    }
}