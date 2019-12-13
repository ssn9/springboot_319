package com.aaa.controller;

import com.aaa.service.EmployeesService;
import com.github.pagehelper.PageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("demo01")
public class Demo01Controller {

    @Resource
    EmployeesService employeesService;

    @RequestMapping("m1")
    @ResponseBody
    public String m1(){
        return "ok";
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(){
        return "test2";
    }

    @RequestMapping("query")
    public String query(Model model){
        model.addAttribute("list",employeesService.query());
        return "success";
    }

    @RequestMapping("select")
    public String select(Model model){
        System.out.println("select");
        PageHelper.startPage(1,2);
        model.addAttribute("list",employeesService.select());
        return "success";
    }
}
