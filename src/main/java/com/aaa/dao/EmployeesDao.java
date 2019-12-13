package com.aaa.dao;

import com.aaa.entity.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

// dao接口上添加注解
@Mapper
public interface EmployeesDao extends tk.mybatis.mapper.common.Mapper<Employees> {

    @Select("select * from employees")
    List<Map<String,Object>>  query();

    List<Map<String,Object>>  queryAll();
}
