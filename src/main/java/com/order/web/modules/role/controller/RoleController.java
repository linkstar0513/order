package com.order.web.modules.role.controller;

import com.order.web.bean.ApiResult;
import com.order.web.util.ApiTools;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @RequestMapping("/all")
    public ApiResult getRole(){
        Object object = SecurityContextHolder.getContext().getAuthentication();
        return ApiTools.result(1000,"message",object);
    }
}
