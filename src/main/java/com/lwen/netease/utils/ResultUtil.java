package com.lwen.netease.utils;

import com.lwen.netease.entity.Result;

public class ResultUtil {

    public static Result success(int code,String msg,Object data){
        return new Result(code, msg, data);
    }
}
