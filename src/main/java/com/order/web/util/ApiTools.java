package com.order.web.util;

import com.order.web.bean.ApiResult;

import java.util.Map;

public class ApiTools {
    public static ApiResult result(int code, String message, Object data){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }
}
