package com.order.web.service.impl;

import com.order.web.mapper.DepartmentMapper;
import com.order.web.pojo.Department;
import com.order.web.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public Department selectByPrimaryKey(String departmentid){
        return departmentMapper.selectByPrimaryKey(departmentid);
    };
    @Override
    public List<Department> getDepartmentByUuid(String uuid) {
        List<Department> list = new ArrayList<>();
        return list;
    }

    @Override
    public void addDepartment(Department department) {
        departmentMapper.insert(department);
    }
}
