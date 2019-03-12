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
    public static ApiResult result(){
        ApiResult apiResult = new ApiResult();
        return apiResult;
    }
    public static ApiResult success(Object data){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(00000);
        apiResult.setMessage("success");
        apiResult.setData(data);
        return apiResult;
    }
}
