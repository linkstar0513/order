package com.order.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.web.annotation.AuthToken;
import com.order.web.bean.ApiResult;
import com.order.web.pojo.Department;
import com.order.web.service.DepartmentService;
import com.order.web.util.ApiTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/department/get",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @AuthToken
    public ApiResult getAllDepartmentByUuid(@RequestHeader String access_token, @RequestAttribute String uuid){
        logger.error("order_token"+ access_token+"uuid"+uuid);
        //Department allDep = departmentService.selectByPrimaryKey(uuid);
        List<Department> allDep = departmentService.getDepartmentByUuid(uuid);
        return ApiTools.result(0000,"",allDep);
    }

    @RequestMapping(value = "/department/add",method = {RequestMethod.POST})
    public ApiResult addDepartment(@RequestBody Department department){

        try {
            logger.debug(objectMapper.writeValueAsString(department));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return ApiTools.result(0000,"",null);
    }
}
