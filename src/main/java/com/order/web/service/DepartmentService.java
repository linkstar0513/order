package com.order.web.service;

import com.order.web.pojo.Department;

import java.util.List;

public interface DepartmentService {

    Department selectByPrimaryKey(String id);

    //查询uuid对应的所有部门
    public List<Department> getDepartmentByUuid(String uuid);

    //增加部门
    public void addDepartment(Department department);
}
