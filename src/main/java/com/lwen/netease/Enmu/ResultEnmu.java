package com.lwen.netease.Enmu;


public enum ResultEnmu {
    SUCCESS(200, "成功! :) "),
    // ERROR(201, "失败！:( "),
    MUSIC_CACHE_INDEX_ERROR(201, "歌曲索引缓存失败"),
    UNKNOWN_ERROR(-1, "未知错误"),
    SEARCH_ERROR(202, "非法的检索类型");

    private int code;
    private String msg;

    ResultEnmu(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
