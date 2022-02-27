package com.xjc.payment.result;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Result<T> result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = result.getData();
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return getCode() == 200;
    }

    public Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }


    public Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(500);
        result.setMsg("失败");
        return result;
    }

    public Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg("失败");
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
