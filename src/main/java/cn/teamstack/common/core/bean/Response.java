package cn.teamstack.common.core.bean;


import cn.teamstack.common.core.util.McodeHelper;

public class Response<T> {

    public boolean result;
    public String code;
    public String msg;
    public T data;

    public Response() {
    }

//    private Response(boolean result, String code) {
//        this.result = result;
//        this.code = code;
//    }

    private Response(boolean result, String code, String msg) {
        this.result = result;
        this.code = code;
        this.msg = msg;
    }

    private Response(boolean result, String code, T data) {
        this.result = result;
        this.code = code;
        this.data = data;
    }

    private Response(boolean result, String code, T data, String msg) {
        this.result = result;
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <R> Response<R> success(String mcode, R data) {
        return new Response(true, mcode, data);
    }

    public static <R> Response<R> success(Enum mEnum, R data) {
        return new Response(true, mEnum.name(), data);
    }

    public static <R> Response<R> success(R data) {
        return new Response(true, McodeHelper.mCode(0), data, "success");
    }

    public static Response<Void> success() {
        return new Response(true, McodeHelper.mCode(0), "success");
    }

    public static Response<Void> failed(String code) {
        return new Response(false, code, "failed");
    }

    public static Response<Void> failed(String code, String msg) {
        return new Response(false, code, msg);
    }

    public static Response<Void> failed(Enum mEnum) {
        return new Response(false, mEnum.name(), "failed");
    }

    public static <R> Response<R> failed(String code, R data) {
        return new Response(false, code, data);
    }

    public static Response<Void> error() {
        return new Response(false, "999999", "system error");
    }

}
