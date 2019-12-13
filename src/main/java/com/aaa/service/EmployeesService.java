package com.aaa.service;

import com.aaa.dao.EmployeesDao;
import com.aaa.entity.Employees;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeesService {

    @Resource
    EmployeesDao dao;

    public List<Map<String,Object>> query(){
        return dao.query();
    }

    public List<Map<String,Object>> select(){
        return dao.queryAll();
    }

    public List<Employees> selectAll(){
        PageHelper.startPage(1,3);
        return dao.selectAll();
    }
}
