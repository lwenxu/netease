package com.lwen.netease.handle;


import com.lwen.netease.Enmu.ResultEnmu;
import com.lwen.netease.Expection.NetException;
import com.lwen.netease.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof NetException) {
            NetException netException = (NetException) e;
            return new Result(netException.getCode(), netException.getMessage(), netException.getData());
        }else {
            return new Result(ResultEnmu.UNKNOWN_ERROR.getCode(), ResultEnmu.UNKNOWN_ERROR.getMsg(), null);
        }
    }
}
